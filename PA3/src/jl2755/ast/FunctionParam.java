package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 *	Represents the function parameters
 *	index
 *	- 0: one parameter 
 *	- 1: more than one parameter
 *	- 2755: no parameter
 */
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
	
	/**
	 * @return a List containing parameter types
	 */
	public List<Type> getParams() {
		List<Type> l = new ArrayList<Type>();
		if (index == 0){
			l.add(type);
		}
		if (index == 1){
			l.add(type);
			l.addAll(functionParam.getParams());
		}
		return l;
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
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
