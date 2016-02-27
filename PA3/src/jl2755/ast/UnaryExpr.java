package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class UnaryExpr implements OpExpr {
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
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(op.toString());
		tempPrinter.printAtom(expr.toString());
		tempPrinter.endList();
	}
}