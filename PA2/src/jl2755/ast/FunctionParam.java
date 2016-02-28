package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class FunctionParam {
	private Identifier identifier;
	private Type type;
	private FunctionParam functionParam;
	private int index;
	
	public FunctionParam(){
		index = 2755;
	}
	
	public FunctionParam(Identifier id, Type t) {
		identifier = id;
		type = t;
		index = 0;
	}
	
	public FunctionParam(Identifier id, Type t, FunctionParam fp) {
		identifier = id;
		type = t;
		functionParam = fp;
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 2755){
			return;
		}
		tempPrinter.startList();
		identifier.prettyPrintNode();
		type.prettyPrintNode();
		tempPrinter.endList();
		if (index == 1) {
			functionParam.prettyPrintNode();
		}	
	}
}
