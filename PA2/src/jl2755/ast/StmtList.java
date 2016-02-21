package jl2755.ast;

public class StmtList {
	private NakedStmt stmt;
	private StmtList stmtList;
	private boolean hasSemicolon;
	private int index;
	
	public StmtList(NakedStmt s, boolean b) {
		stmt = s;
		hasSemicolon = b;
		index = 0;
	}
	
	public StmtList(NakedStmt s, StmtList sl, boolean b) {
		stmt = s;
		stmtList = sl;
		hasSemicolon = b;
		index = 1;
	}	
}
