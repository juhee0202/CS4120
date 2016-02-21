package jl2755.ast;

public class Stmt {
	private NakedStmt nakedStmt;
	private boolean hasSemicolon;
	
	public Stmt(NakedStmt ns, boolean b) {
		nakedStmt = ns;
		hasSemicolon = b;
	}
}
