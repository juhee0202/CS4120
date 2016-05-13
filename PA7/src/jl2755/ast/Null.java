package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class Null implements Expr {

	private int line;
	private int col;
	private boolean surroundedParentheses;
	
	public Null(int thisLeft, int thisRight) {
		line = thisLeft;
		col = thisRight;
		surroundedParentheses = false;
	}
	
	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("null");
		tempPrinter.endList();
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return col;
	}

	@Override
	public int getLineNumber() {
		return line;
	}

	@Override
	public void setSurroundedParentheses() {
		surroundedParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return surroundedParentheses;
	}
}
