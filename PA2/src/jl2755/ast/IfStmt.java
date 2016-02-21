package jl2755.ast;

public class IfStmt implements NakedStmt {
	private Expr expr;
	private NakedStmt stmt1;
	private NakedStmt stmt2;
	private int index;

	public IfStmt(Expr e, NakedStmt s) {
		expr = e;
		stmt1 = s;
		stmt2 = null;
		index = 0;
	}
	
	public IfStmt(Expr e, NakedStmt s1, NakedStmt s2) {
		expr = e;
		stmt1 = s1;
		stmt2 = s2;
		index = 1;
	}
}
