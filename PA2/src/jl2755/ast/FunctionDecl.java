package jl2755.ast;

public class FunctionDecl {
	private String identifier;
	private FunctionParam functionParam;
	private ReturnType returnType;
	private BlockStmt blockStmt;
	
	public FunctionDecl(String s, 
						FunctionParam fp,
						ReturnType rt,
						BlockStmt bs) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
		blockStmt = bs;
	}
}
