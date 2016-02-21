package jl2755.ast;

public class FunctionCall implements Expr,NakedStmt {
	private Identifier identifier;
	private FunctionArg functionArg;
    private int index;
    private boolean isLength = false;
    private ArrayElement arrayElement;
	
	public FunctionCall(Identifier id, FunctionArg fArg) {
		identifier = id;
		functionArg = fArg; 
        index = 1;
	}

    public FunctionCall(Identifier id, boolean argLength){
        identifier = id;
        index = 0;
        if (argLength){
            index = 2;
            isLength = true;
        }
    }

    public FunctionCall(ArrayElement ae){
        arrayElement = ae;
        index = 3;
        isLength = true;
    }
}
