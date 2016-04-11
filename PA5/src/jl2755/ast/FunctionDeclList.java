package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Represents a list of function declarations in a recursive manner.
 * index 
 * 	- 0: no funtion declaration
 * 	- 1: 1 <= function declarations
 */
public class FunctionDeclList {
	private FunctionDecl functionDecl;
	private FunctionDeclList functionDeclList;
	private int index;
	
	// one functionDecl
	public FunctionDeclList(FunctionDecl fd) {
		index = 0;
		functionDecl = fd;
	}	
	
	// two or more functionDecls
	public FunctionDeclList(FunctionDecl fd, 
							FunctionDeclList fdl) {
		functionDecl = fd;
		functionDeclList = fdl;
		index = 1;
	}
	
	/**
	 * @return a List of FunctionDecls 
	 */
	public List<FunctionDecl> getFunctionDecls() {
		List<FunctionDecl> list = new ArrayList<FunctionDecl>();
		if (index == 0) {
			list.add(functionDecl);
		}
		if (index == 1) {
			list.add(functionDecl);
			list.addAll(functionDeclList.getFunctionDecls());
		}
		return list;
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
	
	public FunctionDecl getFunctionDecl() {
		return functionDecl;
	}

	public void setFunctionDecl(FunctionDecl functionDecl) {
		this.functionDecl = functionDecl;
	}

	public FunctionDeclList getFunctionDeclList() {
		return functionDeclList;
	}

	public void setFunctionDeclList(FunctionDeclList functionDeclList) {
		this.functionDeclList = functionDeclList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
