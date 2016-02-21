package jl2755.ast;

public class FunctionCall implements Expr,NakedStmt {
	private String identifier;
	private FunctionArg functionArg;
	
	public FunctionCall(String id, FunctionArg fArg) {
		identifier = id;
		functionArg = fArg; 
	}
}
