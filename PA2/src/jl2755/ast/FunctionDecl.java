package jl2755.ast;

public class FunctionDecl {
	private Identifier identifier;
	private FunctionParam functionParam;
	private ReturnType returnType;
	private BlockStmt blockStmt;
	
	public FunctionDecl(Identifier s, 
						FunctionParam fp,
						ReturnType rt,
						BlockStmt bs) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
		blockStmt = bs;
	}
}
