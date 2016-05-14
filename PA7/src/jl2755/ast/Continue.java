package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class Continue implements NakedStmt {

	private int lineNumber;
	private int colNumber;
	
	public Continue(int cleft, int cright) {
		lineNumber = cleft;
		colNumber = cright;
	}
	
	
	public int getLineNumber() {
		return lineNumber;
	}


	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}


	public int getColumnNumber() {
		return colNumber;
	}


	public void setColumnNumber(int colNumber) {
		this.colNumber = colNumber;
	}


	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("break");
	}

	@Override
	public void accept(ASTVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
