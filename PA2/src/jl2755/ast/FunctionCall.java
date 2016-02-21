package jl2755.ast;

public class FunctionCall implements Expr,NakedStmt {
	private String identifier;
	private FunctionArg functionArg;
    private int index;
	
	public FunctionCall(String id, FunctionArg fArg) {
		identifier = id;
		functionArg = fArg; 
        index = 1;
	}

    public FunctionCall(String id){
        identifier = id;
        index = 0;
    }
}
