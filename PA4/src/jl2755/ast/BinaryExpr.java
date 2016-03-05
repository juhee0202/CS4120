package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class BinaryExpr implements OpExpr {
	private Expr leftExpr;
	private int leftExpr_col;
	private int leftExpr_line;
	private Expr rightExpr;
	private BinaryOp op;

	public BinaryExpr(Expr leftE, Expr rightE, BinaryOp o) {
		leftExpr = leftE;
		rightExpr = rightE;
		leftExpr_col = leftE.getColumnNumber();
		leftExpr_line = leftE.getLineNumber();
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

	public int getLeftExpr_col() {
		return leftExpr_col;
	}

	public void setLeftExpr_col(int leftExpr_col) {
		this.leftExpr_col = leftExpr_col;
	}

	public int getLeftExpr_line() {
		return leftExpr_line;
	}

	public void setLeftExpr_line(int leftExpr_line) {
		this.leftExpr_line = leftExpr_line;
	}

	public BinaryOp getOp() {
		return op;
	}

	public void setOp(BinaryOp op) {
		this.op = op;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(op.toString());
		leftExpr.prettyPrintNode();
		rightExpr.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return leftExpr_col;
	}

	@Override
	public int getLineNumber() {
		return leftExpr_line;
	}
}