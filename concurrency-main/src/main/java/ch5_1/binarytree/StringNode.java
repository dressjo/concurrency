package ch5_1.binarytree;

import static java.lang.System.out;

import java.util.Arrays;

public class StringNode extends AbstractNode<String> {

	private String value1;
	private String value2;
	
	public StringNode() {
		super();
	}

	public StringNode(AbstractNode<String> parent, String value) {
		super(parent);
		this.value1 = value;
	}
	
	@Override
	public AbstractNode<String> createNode(AbstractNode<String> node,
			String value) {		
		return new StringNode(node, value);
	}

	@Override
	public String[] getSortedKeys(String value1, String value2, String value3) {	
		    String[] values = new String[] {value1, value2, value3};
			Arrays.sort(values);
			return values;	
	}
	
	@Override
	public void nodeSetAndSort(String value) {
		if (value.compareTo(this.value1) < 0) {
			this.value2 = this.value1;
			this.value1 = value;
		} else {
			this.value2 = value;
		}
	}
	
	@Override
	public int compareTo(String value1, String value2) {
		return value1.compareTo(value2);
	}
	
	@Override
	public boolean hasValue1() { 
		return value1 != null;
	}
	
	@Override
	public boolean hasValue2() {
		return value2 != null;
	}
	
	@Override
	public String getValue1() {
		return value1;
	}

	@Override
	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Override
	public String getValue2() {
		return value2;
	}

	@Override
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	public static void main(String[] args) {
		StringNode myStringNode = new StringNode();
		myStringNode.setValue1("bbb");
		myStringNode.nodeSetAndSort("aaa");
		out.println(myStringNode.value1);
		out.println(myStringNode.value2);
		out.println(myStringNode.value1.compareTo(myStringNode.getValue2()));
	}

}