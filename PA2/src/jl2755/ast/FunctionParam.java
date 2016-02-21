package jl2755.ast;

public class FunctionParam {
	private VarDecl varDecl;
	private FunctionParam functionParam;
	private int index;
	
	public FunctionParam(VarDecl vd) {
		varDecl = vd;
		index = 0;
	}
	
	public FunctionParam(VarDecl vd, FunctionParam fp) {
		varDecl = vd;
		functionParam = fp;
		index = 1;
	}
}
