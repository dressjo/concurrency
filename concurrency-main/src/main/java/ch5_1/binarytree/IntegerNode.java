package ch5_1.binarytree;

import java.util.Arrays;

public class IntegerNode extends AbstractNode<Integer> {

	private Integer value1;
	private Integer value2;
	
	public IntegerNode() {
		super();
	}

	public IntegerNode(AbstractNode<Integer> parent, Integer value) {
		super(parent);
		this.value1 = value;
	}
	
	@Override
	public AbstractNode<Integer> createNode(AbstractNode<Integer> node,
			Integer value) {		
		return new IntegerNode(node, value);
	}

	@Override
	public Integer[] getSortedKeys(Integer value1, Integer value2, Integer value3) {	
		    Integer[] values = new Integer[] {value1, value2, value3};
			Arrays.sort(values);
			return values;	
	}
	
	@Override
	public void nodeSetAndSort(Integer value) {
		if (value < this.value1) {
			this.value2 = this.value1;
			this.value1 = value;
		} else {
			this.value2 = value;
		}
	}
	
	@Override
	public int compareTo(Integer value1, Integer value2) {
		return value1 - value2;
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
	public Integer getValue1() {
		return value1;
	}

	@Override
	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	@Override
	public Integer getValue2() {
		return value2;
	}

	@Override
	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

	

}