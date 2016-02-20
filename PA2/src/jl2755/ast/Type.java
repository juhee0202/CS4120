package jl2755.ast;

public class Type {
	private PrimitiveType primitiveChild;
	private ArrayType arrayChild;
	private int whichOne;
	
	public Type(PrimitiveType argPrimitive){
		primitiveChild = argPrimitive;
		whichOne = 0;
	}
	
	public Type(ArrayType argArray){
		arrayChild = argArray;
		whichOne = 1;
	}
}
