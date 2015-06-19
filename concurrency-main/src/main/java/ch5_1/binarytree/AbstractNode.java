package ch5_1.binarytree;


public abstract class AbstractNode<V> {

	private AbstractNode<V> parent;

	private AbstractNode<V> child1;
	private AbstractNode<V> child2;
	private AbstractNode<V> child3;
	
	public AbstractNode() {
	}

	public AbstractNode(AbstractNode<V> parent) {
		this.parent = parent;
	}

	public boolean isLeaf() {
		return child1 == null && child2 == null && child3 == null;
	}
	
	public boolean hasParent() {
		return parent != null;
	}

	public boolean hasChild1() {
		return child1 != null;
	}

	public boolean hasChild2() {
		return child2 != null;
	}

	public boolean hasChild3() {
		return child3 != null;
	}

	public AbstractNode<V> getParent() {
		return parent;
	}

	public void setParent(AbstractNode<V> parent) {
		this.parent = parent;
	}

	public AbstractNode<V> getChild1() {
		return child1;
	}

	public void setChild1(AbstractNode<V> child1) {
		this.child1 = child1;
	}

	public AbstractNode<V> getChild2() {
		return child2;
	}

	public void setChild2(AbstractNode<V> child2) {
		this.child2 = child2;
	}

	public AbstractNode<V> getChild3() {
		return child3;
	}

	public void setChild3(AbstractNode<V> child3) {
		this.child3 = child3;
	}
	
	public abstract AbstractNode<V> createNode(AbstractNode<V> node, V value);
	
	public abstract V[] getSortedKeys(V value1, V value2, V value3); 
	
	public abstract int compareTo(V value1, V value2);
	
	public abstract void nodeSetAndSort(V value);
	
    public abstract boolean hasValue1(); 
	
	public abstract boolean hasValue2();
	
	public abstract V getValue1();

	public abstract void setValue1(V value1);
		
	public abstract V getValue2();

	public abstract void setValue2(V value2);

}
