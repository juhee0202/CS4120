package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
