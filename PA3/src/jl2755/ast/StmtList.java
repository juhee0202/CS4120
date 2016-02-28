package jl2755.ast;


public class StmtList {
	private Stmt stmt;
	private StmtList stmtList;
	private int index;
	
	public StmtList(Stmt s) {
		stmt = s;
		index = 0;
	}
	
	public StmtList(Stmt s, StmtList sl) {
		stmt = s;
		stmtList = sl;
		index = 1;
	}	
	
	public void prettyPrintNode() {
		stmt.prettyPrintNode();
		if (index == 1) {
			stmtList.prettyPrintNode();
		}
	}
}
