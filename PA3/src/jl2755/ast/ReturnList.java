package jl2755.ast;

import jl2755.visitor.Visitor;

public class ReturnList {
	private Expr expr;
	private ReturnList returnList;
	private int index;
	
	public ReturnList(Expr e) {
		expr = e;
		returnList = null;
		index = 0;
	}
	
	public ReturnList(Expr e, ReturnList rl) {
		expr = e;
		returnList = rl;
		index = 1;
	}
	
	public void prettyPrintNode() {
		expr.prettyPrintNode();
		if (index == 1) {
			returnList.prettyPrintNode();
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
