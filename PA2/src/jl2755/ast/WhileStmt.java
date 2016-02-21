package jl2755.ast;

public class WhileStmt implements NakedStmt {
	private Expr expr;
	private NakedStmt stmt;
	
	public WhileStmt(Expr e, NakedStmt s) {
		expr = e;
		stmt = s;
	}
}
