public class UnaryExpr extends Expr() {
	Expr expr;
	UnaryOp op;

	public UnaryExpr(Expr e, UnaryOp o) {
		expr = e;
		op = o;
	}

	public BinaryOp getUnaryOp() {
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

	public String toString() {
		//TODO
	}
}