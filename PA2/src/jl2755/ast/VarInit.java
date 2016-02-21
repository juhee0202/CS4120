package jl2755.ast;

public class VarInit implements NakedStmt {
	private VarDecl varDecl;
	private Expr expr;
	
	public VarInit(VarDecl vd, Expr e) {
		varDecl = vd;
		expr = e;
	}
}
