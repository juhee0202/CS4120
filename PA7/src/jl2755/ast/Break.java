package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a break statement
 *
 */
public class Break implements NakedStmt {
	
	private int lineNumber;
	private int colNumber;
	private String labelName;
	
	public Break(int bleft, int bright) {
		lineNumber = bleft;
		colNumber = bright;
	}
	
	public Break(String label, int bleft, int bright) {
		labelName = label;
		lineNumber = bleft;
		colNumber = bright;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNum(int lineNum) {
		this.lineNumber = lineNum;
	}

	public int getColumnNumber() {
		return colNumber;
	}

	public void setColumnNumber(int colNumber) {
		this.colNumber = colNumber;
	}
	
	public String getLabel() {
		return labelName;
	}
	
	public boolean hasLabel() {
		return labelName != null;
	}

	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("break");
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}
