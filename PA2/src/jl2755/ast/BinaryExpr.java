package jl2755.ast;

public class BinaryExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;
	private BinaryOp op;

	public BinaryExpr(Expr leftE, Expr rightE, BinaryOp o) {
		leftExpr = leftE;
		rightExpr = rightE;
		op = o;
	}

	public BinaryOp getBinaryOp() {
		return op;
	}

	public void setBinaryOp(BinaryOp o) {
		op = o;
	}

	public Expr getLeftExpr() {
		return leftExpr;
	}
	
	public Expr getRightExpr() {
		return rightExpr;
	}

	public void setLeftExpr(Expr e) {
		leftExpr = e;
	}
	
	public void setRightExpr(Expr e) {
		rightExpr = e;
	}
}