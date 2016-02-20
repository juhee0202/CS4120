public class Plus extends Binary() {
	Expr left, right;
	Operator op = "+";

	public Plus(Expr l, Expr r, Operator o) {
		left = l;
		right = r;
		op = o;
	}

	public getOperator() {
		return op;
	}
}