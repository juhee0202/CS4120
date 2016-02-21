package jl2755.ast;

public class ArrayLiteral implements Constant {
	private ArrayElementList arrElemList;
	
	public ArrayLiteral(ArrayElementList l) {
		arrElemList = l;
	}
}
