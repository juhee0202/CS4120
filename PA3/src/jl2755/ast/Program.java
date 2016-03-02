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
		if (index == 0) {
			tempPrinter.startList();
			tempPrinter.endList();
			tempPrinter.startList();
			functionDeclList.prettyPrintNode();
			tempPrinter.endList();
		} else {
			tempPrinter.startList();
			useId.prettyPrintNode();
			tempPrinter.endList();
			tempPrinter.startList();
			functionDeclList.prettyPrintNode();
			tempPrinter.endList();
		}
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "";
	}
}