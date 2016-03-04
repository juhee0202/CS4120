package jl2755.ast;

/**
 * A function declaration only, no body.
 * 
 * i.e. identifier(functionParam)returnType
 */
public class InterfaceFunc {
	private Identifier identifier;
	private FunctionParam functionParam;
	private ReturnType returnType;
	
	public InterfaceFunc(Identifier s, FunctionParam fp, ReturnType rt) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
	}
}
