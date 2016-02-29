package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

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
	
	public void prettyPrintNode() {
		type.prettyPrintNode();
		if (index == 1) {
			typeList.prettyPrintNode();
		}
	}

	public List<Type> getReturnTypes() {
		List<Type> l = new ArrayList<Type>();
		l.add(type);
		if (index == 1) {
			l.addAll(typeList.getReturnTypes());
		}
		return l;
	}	
}
