package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Class that represents an expression preceded by a 
 * UnaryOp.
 */
public class UnaryExpr implements OpExpr {
	private Expr expr;
	private UnaryOp op;
	private int op_col;
	private int op_line;

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
		tempPrinter.printAtom(expr.toString());
		tempPrinter.endList();
	}
	
	public void accept(Visitor v){
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
}