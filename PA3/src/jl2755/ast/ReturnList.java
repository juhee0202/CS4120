package jl2755.ast;


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

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public ReturnList getReturnList() {
		return returnList;
	}

	public void setReturnList(ReturnList returnList) {
		this.returnList = returnList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
