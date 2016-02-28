package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class Identifier implements Expr{
	private String theValue;
	
	public Identifier(String argValue){
		theValue = argValue;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(theValue);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString(){
		return theValue;
	}

	public String getTheValue() {
		return theValue;
	}

	public void setTheValue(String theValue) {
		this.theValue = theValue;
	}
}
