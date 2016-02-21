package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class FunctionDeclList {
	private FunctionDecl functionDecl;
	private FunctionDeclList functionDeclList;
	
	public FunctionDeclList(FunctionDecl fd, 
							FunctionDeclList fdl) {
		functionDecl = fd;
		functionDeclList = fdl;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		functionDecl.prettyPrintNode();
		functionDeclList.prettyPrintNode();
		tempPrinter.endList();
	}
}
