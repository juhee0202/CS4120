public class BinaryExpr extends Expr() {
	Expr expr1, expr2;
	BinaryOp op;

	public Expr() {
		BinaryExpr();
	}
	
	public BinaryExpr(BinaryOp o, Expr left, Expr right) {
		expr1 = left;
		expr2 = right;
		op = o;
	}

	public BinaryOp getBinaryOp() {
		return op;
	}

	public void setBinaryOp(BinaryOp o) {
		op = o;
	}

	public Expr getExpr1() {
		return expr1;
	}

	public Expr getExpr2() {
		return expr2;
	}

	public void setExpr1(Expr e) {
		expr1 = e;
	}
	public void setExpr2(Expr e) {
		expr2 = e;
	}

	public String toString() {
		//TODO
	}

}