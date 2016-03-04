package jl2755.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @return a Map containing each parameter's (id, type)
	 */
	public Map<String, Type> getParamsWithTypes() {
		Map<String, Type> map = new HashMap<String, Type>();
		if (index == 0) {
			map.put(identifier.toString(), type);
		}
		else if (index == 1) {
			map.put(identifier.toString(), type);
			map.putAll(functionParam.getParamsWithTypes());
		}
		return map;
	}	
	
	/**
	 * @return a List containing parameter types
	 */
	public List<Type> getParamTypes() {
		List<Type> l = new ArrayList<Type>();
		if (index == 0) {
			l.add(type);
		}
		else if (index == 1) {
			l.add(type);
			l.addAll(functionParam.getParamTypes());
		}
		return l;
	}
	
	/**
	 * @return a List containing parameter identifier's string values
	 */
	public List<String> getParams() {
		List<String> list = new ArrayList<String>();
		if (index == 0) {
			list.add(identifier.toString());
		}
		else if (index == 1) {
			list.add(identifier.toString());
			list.addAll(functionParam.getParams());
		}
		return list;
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
