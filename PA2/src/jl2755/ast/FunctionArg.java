package jl2755.ast;

public class FunctionArg {
	private Expr expr;
	private FunctionArg functionArg;
    private int index;

    public FunctionArg(Expr e) {
        expr = e;
        index = 0;
    }
	
	public FunctionArg(Expr e, FunctionArg fArg) {
		expr = e;
		functionArg = fArg;
        index = 1;
	}
}
