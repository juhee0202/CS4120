package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.type.VType;
import jl2755.visitor.ASTVisitor;

public class Identifier implements Expr{
	private String theValue;
	private int theValue_col;
	private int theValue_line;
	private VType type;
	
	public Identifier(String argValue, int idleft, int idright){
		theValue = argValue;
		theValue_line = idleft;
		theValue_col = idright;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(theValue);
	}
	
	@Override
	public String toString(){
		return theValue;
	}

	public String getTheValue() {
		return theValue;
	}

	public int getTheValue_col() {
		return theValue_col;
	}

	public void setTheValue_col(int theValue_col) {
		this.theValue_col = theValue_col;
	}

	public int getTheValue_line() {
		return theValue_line;
	}

	public void setTheValue_line(int theValue_line) {
		this.theValue_line = theValue_line;
	}

	public void setTheValue(String theValue) {
		this.theValue = theValue;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return theValue_col;
	}

	@Override
	public int getLineNumber() {
		return theValue_line;
	}

	public VType getType() {
		return type;
	}

	public void setType(VType type) {
		this.type = type;
	}
}
