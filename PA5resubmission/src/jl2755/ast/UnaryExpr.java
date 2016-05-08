package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.type.VType;
import jl2755.visitor.ASTVisitor;

/**
 * Class that represents an expression preceded by a 
 * UnaryOp.
 */
public class UnaryExpr implements OpExpr {
	private Expr expr;
	private UnaryOp op;
	private int op_col;
	private int op_line;
	private VType type;
	private boolean isSurroundedByParentheses = false;

	public UnaryExpr(Expr e, UnaryOp o, int oleft, int oright) {
		expr = e;
		op = o;
		op_line = oleft;
		op_col = oright;
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
		expr.prettyPrintNode();
//		tempPrinter.printAtom(expr.toString());	TODO PA2 MISTAKE
		tempPrinter.endList();
	}
	
	public void accept(ASTVisitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return op_col;
	}

	@Override
	public int getLineNumber() {
		return op_line;
	}

	public VType getType() {
		return type;
	}

	public void setType(VType type) {
		this.type = type;
	}

	@Override
	public void setSurroundedParentheses() {
		isSurroundedByParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return isSurroundedByParentheses;
	}
}