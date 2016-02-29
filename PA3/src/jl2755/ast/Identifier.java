package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
	public String toString(){
		return theValue;
	}
}
