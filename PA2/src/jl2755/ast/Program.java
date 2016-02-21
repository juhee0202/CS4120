package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class Program{
	private UseId useId;
	private FunctionDeclList functionDeclList;
	private int index;
	
	public Program(FunctionDeclList fdl) {
		functionDeclList = fdl;
		index = 0;
	}
	
	public Program(UseId ui, FunctionDeclList fdl) {
		useId = ui;
		functionDeclList = fdl;
		index = 1;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("askdj");
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "";
	}
}
