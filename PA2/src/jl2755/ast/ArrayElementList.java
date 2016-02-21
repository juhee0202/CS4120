package jl2755.ast;

public class ArrayElementList {
	private Expr expr;
	private ArrayElementList arrElemList;
	
	public ArrayElementList(Expr e, 
							ArrayElementList l) {
		expr = e;
		arrElemList = l;
	}
}
