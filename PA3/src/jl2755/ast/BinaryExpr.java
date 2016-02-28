package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class BinaryExpr implements OpExpr {
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

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(op.toString());
		leftExpr.prettyPrintNode();
		rightExpr.prettyPrintNode();
		tempPrinter.endList();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}