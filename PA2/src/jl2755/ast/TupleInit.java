package jl2755.ast;

public class TupleInit implements NakedStmt {
	private TupleDeclList tupleDeclList;
	private FunctionCall functionCall;
	private VarDecl varDecl;
	private int index;
	
	public TupleInit(FunctionCall fc) {
		functionCall = fc;
		index = 0;
	}
	
	public TupleInit(TupleDeclList tdl, FunctionCall fc) {
		tupleDeclList = tdl;
		functionCall = fc;
		index = 1;
	}
	
	public TupleInit(VarDecl vd, 
					 TupleDeclList tdl, 
					 FunctionCall fc) {
		varDecl = vd;
		tupleDeclList = tdl;
		functionCall = fc;
		index = 2;
	}
}
