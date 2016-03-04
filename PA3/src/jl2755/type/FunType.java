package jl2755.type;

import java.util.List;
import jl2755.ast.Type;
import jl2755.ast.FunctionCall;
import jl2755.ast.FunctionDecl;
import jl2755.ast.InterfaceFunc;

/**
 *	Represents the function type
 *	fn params -> returns
 *
 *	0 parameter: params is a unit
 *	1 parameter: params is a VarType
 *	1< parameters: params is a TupleType
 *
 *	0 return type: returns is a unit
 *	1 return type: returns is a VarType
 *	1< return types: returns is a TupleType 
 */
public class FunType implements VType {
	private VType paramTypes;
	private VType returnTypes;
	
	public FunType() {
		paramTypes = null;
		returnTypes = null;
	}
	
	/**
	 *	Creates a FunType instance from a function declaration
	 */
	public FunType(FunctionDecl fd) {
		/* Set Params */
		List<Type> l = fd.getParamTypes();
		if (l.size() == 0) {
			paramTypes = new UnitType();
		}
		else if (l.size() == 1) {
			paramTypes = new VarType(l.get(0));
		}
		else {
			paramTypes = new TupleType();
			for (int i = 0; i < l.size(); i++) {
				VarType vt = new VarType(l.get(i));
				((TupleType) paramTypes).addToTypes(vt);
			}
		}
		
		/* Set Returns */
		List<Type> ll = fd.getReturnTypes();
		if (ll.size() == 0) {
			returnTypes = new UnitType();
		}
		else if (ll.size() == 1) {
			returnTypes = new VarType(ll.get(0));
		}
		else {
			returnTypes = new TupleType();
			for (int i = 0; i < ll.size(); i++) {
				VarType vt = new VarType(ll.get(i));
				((TupleType) returnTypes).addToTypes(vt);
			}
		}
	}
	
	public FunType(InterfaceFunc argIntFunc) {
		/* Set Params */
		List<Type> l = argIntFunc.getFunctionParam().getParamTypes();
		if (l.size() == 0) {
			paramTypes = new UnitType();
		}
		else if (l.size() == 1) {
			paramTypes = new VarType(l.get(0));
		}
		else {
			paramTypes = new TupleType();
			for (int i = 0; i < l.size(); i++) {
				VarType vt = new VarType(l.get(i));
				((TupleType) paramTypes).addToTypes(vt);
			}
		}
		
		/* Set Returns */
		List<Type> ll = argIntFunc.getReturnType().getReturnTypes();
		if (ll.size() == 0) {
			returnTypes = new UnitType();
		}
		else if (ll.size() == 1) {
			returnTypes = new VarType(ll.get(0));
		}
		else {
			returnTypes = new TupleType();
			for (int i = 0; i < ll.size(); i++) {
				VarType vt = new VarType(ll.get(i));
				((TupleType) returnTypes).addToTypes(vt);
			}
		}
	}
	
	public VType getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(VType paramTypes) {
		this.paramTypes = paramTypes;
	}
	public VType getReturnTypes() {
		return returnTypes;
	}
	public void setReturnTypes(VType returnTypes) {
		this.returnTypes = returnTypes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FunType)) {
			return false;
		}
		FunType vt = (FunType)o;
		return (this.getParamTypes()).equals(vt.getParamTypes()) 
				&& (this.getReturnTypes()).equals(vt.getReturnTypes());
	}
	
	@Override
	public String toString() {
		String returnString = "(";
		returnString += paramTypes.toString();
		returnString += "):";
		returnString += returnTypes.toString();
		return returnString;
	}
}
