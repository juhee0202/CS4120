package jl2755.ast;

public class ReturnStmt {
	private ReturnList returnList;
	private int index;
	
	public ReturnStmt() {
		returnList = null;
		index = 0;
	}
	
	public ReturnStmt(ReturnList rl) {
		returnList = rl;
		index = 1;
	}
}
