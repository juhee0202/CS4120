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
}
