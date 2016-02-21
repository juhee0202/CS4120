package jl2755.ast;

public class WhileStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt;
	
	public WhileStmt(Expr e, Stmt s) {
		expr = e;
		stmt = s;
	}
}
