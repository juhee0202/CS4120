package jl2755.ast;

import jl2755.visitor.Visitor;

public class ArrayElementList {
	private Expr expr;
	private ArrayElementList arrElemList;
	private int index;
	
    public ArrayElementList(Expr e) {
        expr = e;
        index = 0;
    }

	public ArrayElementList(Expr e, ArrayElementList l) {
		expr = e;
		arrElemList = l;
		index = 1;
	}
	
	public ArrayElementList(){
		index = 2;
	}
	
	public void prettyPrintNode(){
		if (index == 2){
			return;
		}
		expr.prettyPrintNode();
		if (index == 1) {
			arrElemList.prettyPrintNode();
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
