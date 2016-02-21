package jl2755.ast;

public class BlockStmt implements NakedStmt {
	private StmtList stmtList;
	private ReturnStmt returnStmt;
	private int index;
	
	public BlockStmt() {
		stmtList = null;
		returnStmt = null;
		index = 0;
	}
	
	public BlockStmt(StmtList sl) {
		stmtList = sl;
		returnStmt = null;
		index = 1;
	}
	
	public BlockStmt(StmtList sl, ReturnStmt rs) {
		stmtList = sl;
		returnStmt = rs;
		index = 2;
	}
}
