package jl2755.type;

import java.util.ArrayList;
import java.util.List;

import jl2755.ast.TupleInit;
import jl2755.ast.VarDecl;
import jl2755.visitor.Environment;

/**
 * Represents a Tuple type
 */
public class TupleType implements VType {
	private List<VType> types;
	
	public TupleType() {
		types = new ArrayList<VType>();
	}
	
	public TupleType(List<VType> argTypes) {
		types = argTypes;
	}
	
	/** 
	 * Creates a TupleType object from the left side of a TupleInit stmt.
	 * (Shouldn't use this to create a one element tuple object)
	 * 
	 * @param argTupleInit and makes a List of
	 * 	VTypes (VarType or ClassType) for the elements in the Tuple
	 * @param env
	 * 
	 * UNDERSCOREs are represented by UnitType
	 */
	public TupleType(TupleInit argTupleInit, Environment env) {
		types = new ArrayList<VType>();
		List<VarDecl> allDecls = argTupleInit.getVarDecls();
		for (int i = 0; i < allDecls.size(); i++){
			VarDecl varDecl = allDecls.get(i);
			if (varDecl == null) {
				types.add(new UnitType());
			}
			else {
				int index = varDecl.getIndex();
				if (index == 2) {
					VType vType = env.getClassType(varDecl.getClassType().getTheValue());
					types.add(vType);
				} else {
					types.add(new VarType(varDecl));
				}
			}
		}
	}
	
	public List<VType> getTypes() {
		return types;
	}

	public void setTypes(List<VType> types) {
		this.types = types;
	}
	
	/**
	 * appends argType to this TupleType object
	 * null argument is appended as a UnitType
	 * @param VType argType
	 */
	public void addToTypes(VType argType) {
		if (argType == null) {
			types.add(new UnitType());
		}
		types.add(argType);
	}
	
	/**
	 * Similar to addToTypes except it prepends argType
	 * @param VType argType
	 */
	public void prependToTypes(VType argType) {
		if (argType == null) {
			types.add(new UnitType());
		}
		types.add(0, argType);
	}

	/**
	 * Allows underscore to be matched with any VarType
	 */
	@Override
	public boolean equals(Object o){
		if (!(o instanceof TupleType)){
			return false;
		}
		TupleType tempTupleType = (TupleType) o;
		List<VType> otherVarTypes = tempTupleType.getTypes();
		if (types.size() != otherVarTypes.size()){
			return false;
		}
		for (int i = 0; i < types.size(); i++){
			if ( !(types.get(i).equals(otherVarTypes.get(i)) 
					|| types.get(i) instanceof UnitType
					|| otherVarTypes.get(i) instanceof UnitType) ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return number of types in the tuple
	 */
	public int numTypes() {
		return types.size();
	}
	
	@Override
	public String toString() {
		String returnString = types.get(0).toString();
		for (int i = 1; i < types.size(); i++) {
			returnString += ", " + types.get(i).toString();
		}
		return returnString;
	}

	@Override
	public boolean canDot() {
		return false;
	}
}
