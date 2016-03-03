package jl2755.ast;

/**
 *	Represents function arguments in a recursive manner
 *	index
 *	- 0: 1 argument
 *	- 1: 1< arguments
 */
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

	public void prettyPrintNode() {
		expr.prettyPrintNode();
		if (index == 1) {
			functionArg.prettyPrintNode();
		}
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public FunctionArg getFunctionArg() {
		return functionArg;
	}

	public void setFunctionArg(FunctionArg functionArg) {
		this.functionArg = functionArg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
