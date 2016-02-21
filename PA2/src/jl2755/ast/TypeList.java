package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		type.prettyPrintNode();
		if (index == 1) {
			typeList.prettyPrintNode();
		}
	}	
}
