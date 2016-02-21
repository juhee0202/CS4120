package jl2755.ast;

public class Identifier implements Expr{
	private String theValue;
	
	public Identifier(String argValue){
		theValue = argValue;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(Identifier.toString(this));
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return theValue;
	}
}
