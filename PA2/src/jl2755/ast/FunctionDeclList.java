package jl2755.ast;

public class FunctionDeclList {
	private FunctionDecl functionDecl;
	private FunctionDeclList functionDeclList;
	
	public FunctionDeclList(FunctionDecl fd, 
							FunctionDeclList fdl) {
		functionDecl = fd;
		functionDeclList = fdl;
	}
}
