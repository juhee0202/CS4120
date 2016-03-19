package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.type.VType;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates two expressions joined together
 * by a BinaryOp.
 */
public class BinaryExpr implements OpExpr {
	private Expr leftExpr;
	private int leftExpr_col;
	private int leftExpr_line;
	private Expr rightExpr;
	private BinaryOp op;
	private VType type;

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

	public BinaryOp getOp() {
		return op;
	}

	public void setOp(BinaryOp op) {
		this.op = op;
	}

	public VType getType() {
		return type;
	}

	public void setType(VType type) {
		this.type = type;
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

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(op.toString());
		leftExpr.prettyPrintNode();
		rightExpr.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(ASTVisitor v){
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