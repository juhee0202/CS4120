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
	
	public InterfaceFunc(Identifier s, ReturnType rt) {
		identifier = s;
		functionParam = new FunctionParam();
		returnType = rt;
	}
	
	public InterfaceFunc(Identifier s, FunctionParam fp, ReturnType rt) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public FunctionParam getFunctionParam() {
		return functionParam;
	}

	public void setFunctionParam(FunctionParam functionParam) {
		this.functionParam = functionParam;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}
	
	@Override
	public String toString() {
		return identifier.toString();
	}
}
