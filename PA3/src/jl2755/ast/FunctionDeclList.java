package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class FunctionDeclList {
	private FunctionDecl functionDecl;
	private FunctionDeclList functionDeclList;
	private int index;
	
	public FunctionDeclList() {
		index = 0;
	}	
	
	public FunctionDeclList(FunctionDecl fd, 
							FunctionDeclList fdl) {
		functionDecl = fd;
		functionDeclList = fdl;
		if (fdl == null) {
			index = 2;
		} else {
			index = 1;
		}
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		
		if (index == 0) {
			return;
		}
		
		tempPrinter.startList();
		functionDecl.prettyPrintNode();
		tempPrinter.endList();
		if (index == 1) {
			functionDeclList.prettyPrintNode();
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
