package jl2755.ast;

public class FunctionArg {
	private Expr expr;
	private FunctionArg functionArg;
	
	public FunctionArg(Expr e, FunctionArg fArg) {
		expr = e;
		functionArg = fArg;
	}
}
