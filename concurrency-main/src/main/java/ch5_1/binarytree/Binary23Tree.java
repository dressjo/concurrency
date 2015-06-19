package ch5_1.binarytree;

import static java.lang.System.out;


public class Binary23Tree<V> {

	AbstractNode<V> root;

	public Binary23Tree(AbstractNode<V> root) {
       this.root = root; 
	}

	public void insert(V value) {
		traverseInsert(root, value);
	}

	private void traverseInsert(AbstractNode<V> node, V value) {
		if (node.isLeaf()) {
			if (!node.hasValue1() && !node.hasValue2()) {
				node.setValue1(value);
				return;
			} else if (node.hasValue1() && !node.hasValue2()) {
				if (node.compareTo(value,  node.getValue1()) > 0) {
					node.setValue2(value);
					return;
				} else if (node.compareTo(value, node.getValue1()) < 0) {
					node.setValue2(node.getValue1());
					node.setValue1(value);
					return;
				}
			} else {
				if (!node.hasParent()) {
					V[] sortedKeys = node.getSortedKeys(node.getValue1(),
							node.getValue2(), value);
					node.setChild1(node.createNode(node, sortedKeys[0]));
					node.setChild3(node.createNode(node, sortedKeys[2]));
					node.setValue1(sortedKeys[1]);
					node.setValue2(null);
				} else {
					final V[] sortedKeys = node.getSortedKeys(node.getValue1(), node.getValue2(), value);
					if (!node.getParent().hasValue2()) {
						node.getParent().nodeSetAndSort(sortedKeys[1]);
						if (node.compareTo(sortedKeys[2], node.getParent().getValue2()) > 0) {
							node.getParent().setChild3(node.createNode(node, sortedKeys[2]));
						} else if (node.compareTo(sortedKeys[2], node.getParent().getValue2()) < 0
							        && node.compareTo(sortedKeys[2], node.getParent().getValue1()) > 0) {
							node.getParent().setChild2(node.createNode(node, sortedKeys[2]));
						}
						if (node.compareTo(sortedKeys[0], node.getParent().getValue1()) > 0
							 && node.compareTo(sortedKeys[0], node.getParent().getValue2()) < 0) {
							node.getParent().setChild2(node.createNode(node, sortedKeys[0]));
						} else if (node.compareTo(sortedKeys[0], node.getParent().getValue1()) < 0) {
							node.getParent().setChild1(node.createNode(node, sortedKeys[0]));
						}
					} else {
						node.setChild1(node.createNode(node, sortedKeys[0]));
						node.setChild3(node.createNode(node, sortedKeys[2]));
						node.setValue1(sortedKeys[1]);
						node.setValue2(null);
						pushUp(node, sortedKeys[1]);
					}
				}
			}
		} else {
			if (node.compareTo(value, node.getValue1()) == 0		
					|| (node.hasValue2() && node.compareTo(value, node.getValue2()) == 0)) {
				return;
			} else if (node.hasChild1() && node.compareTo(value, node.getValue1()) < 0) {
				traverseInsert(node.getChild1(), value);
				return;
			} else {
				if (node.hasValue2()) {
					if (node.compareTo(value, node.getValue2()) <= 0) {
						if (!node.hasChild2()) {
							node.setChild2(node.createNode(node, value));
						} else {
							traverseInsert(node.getChild2(), value);
						}
					} else {
						if (!node.hasChild3()) {
							node.setChild3(node.createNode(node, value));
						} else {
							traverseInsert(node.getChild3(), value);
						}
					}
				} else {
					if (!node.hasChild3()) {
						node.setChild3(node.createNode(node, value));
					} else {
						traverseInsert(node.getChild3(), value);
					}
				}
			}

		}
	}
	
	private void pushUp(AbstractNode<V> node, V value) {
		
		V[] parentKeys = node.getSortedKeys(node.getParent().getValue1(), node.getParent().getValue2(), value);
		AbstractNode<V> newLeftNode = node.createNode(node.getParent(), parentKeys[0]);
		AbstractNode<V> newRightNode = node.createNode(node.getParent(), parentKeys[2]);
		
		if(node.compareTo(value, node.getParent().getValue1()) < 0) {
			node.getParent().setChild1(node);
		} else if(node.getParent().hasValue2() && node.compareTo(value, node.getParent().getValue2()) < 0) {
			node.getParent().setChild2(node);
		} else if(node.getParent().hasValue2()  && node.compareTo(value, node.getParent().getValue2()) > 0) {
			node.getParent().setChild3(node);
			newLeftNode.setChild1(node.getParent().getChild1());
			newLeftNode.setChild3(node.getParent().getChild2());
			newRightNode.setChild1(node.getChild1());
			newRightNode.setChild3(node.getChild3());
		}	
		
		node.getParent().setValue1(parentKeys[1]);
		node.getParent().setValue2(null);
	
		node.getParent().setChild1(newLeftNode);
		node.getParent().setChild2(null);
		node.getParent().setChild3(newRightNode);
		
	}
		
	public void print() {
		traversePrint(root, new MutableInteger(0));
	}

	private void traversePrint(AbstractNode<V> node, MutableInteger level) {
		out.print("Level: " + level.get());
		out.println(" value1: " + node.getValue1());
		out.print("Level: " + level.get());
		out.println(node.getValue2() != null ? " value2: " + node.getValue2()
				: " value2: " + "Only one key");
		if (node.getChild1() != null) {
			level.getAndIncrement();
			traversePrint(node.getChild1(), level);
		}
		if (node.getChild2() != null) {
			traversePrint(node.getChild2(), level);
		}
		if (node.getChild3() != null) {
			traversePrint(node.getChild3(), level);
		}
	}

	class MutableInteger {
		private int value;

		public MutableInteger(int value) {
			this.value = value;
		}

		public void set(int value) {
			this.value = value;
		}

		public int getAndIncrement() {
			return value++;

		}

		public int get() {
			return value;
		}
	}

	public static void main(String args[]) {	
		
		Binary23Tree<Integer> binary23Tree = new Binary23Tree<Integer>(new IntegerNode());
		binary23Tree.insert(5);
		binary23Tree.insert(10);
		binary23Tree.insert(20);
		binary23Tree.insert(8);
		binary23Tree.insert(7);
		binary23Tree.insert(15);
		binary23Tree.insert(30);
		binary23Tree.insert(11);
		
		binary23Tree.print();
			
		Binary23Tree<String> binaryString23Tree = new Binary23Tree<String>(new StringNode());
		binaryString23Tree.insert("dddd");
		binaryString23Tree.insert("cccc");
		binaryString23Tree.insert("aaaa");
		binaryString23Tree.insert("gggg");
		binaryString23Tree.insert("zzzz");
		binaryString23Tree.insert("ffff");
		binaryString23Tree.insert("vvvv");

		binaryString23Tree.print();
		
	}

}
