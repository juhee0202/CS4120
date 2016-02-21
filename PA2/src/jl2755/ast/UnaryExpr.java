package jl2755.ast;

public class UnaryExpr implements Expr {
	private Expr expr;
	private UnaryOp op;

	public UnaryExpr(Expr e, UnaryOp o) {
		expr = e;
		op = o;
	}

	public UnaryOp getUnaryOp() {
		return op;
	}

	public void setUnaryOp(UnaryOp o) {
		op = o;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr e) {
		expr = e;
	}
}