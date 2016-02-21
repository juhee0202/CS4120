package jl2755.ast;

public class TypeList {
	private Type type;
	private TypeList typeList;
	private int index;
	
	public TypeList(Type t) {
		type = t;
		index = 0;
	}
	
	public TypeList(Type t, TypeList tl) {
		type = t;
		typeList = tl;
		index = 1;
	}
}
