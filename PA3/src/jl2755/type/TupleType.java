package jl2755.type;

import java.util.ArrayList;
import java.util.List;

import jl2755.ast.TupleInit;
import jl2755.ast.VarDecl;

/**
 * Represents the left side of a TupleInit
 * statement.
 *
 */
public class TupleType {
	
	/**
	 * List of the VarTypes that represent
	 * the VarDecls on the left side.
	 * Null entries represent underscores.
	 */
	private List<VarType> varTypes = new ArrayList<VarType>();
	
	/** 
	 * @param argTupleInit and makes a List of
	 * VarTypes for the elements in the Tuple
	 */
	public TupleType(TupleInit argTupleInit) {
		if (argTupleInit.getIndex() == 0){
			varTypes.add(null);
		}
		if (argTupleInit.getIndex() == 1){
			varTypes.add(null);
			List<VarDecl> tempDecls = argTupleInit.getTupleDeclList().getVarDecls();
			for (int i = 0; i < tempDecls.size(); i++){
				varTypes.add(new VarType(tempDecls.get(i)));
			}
		}
		if (argTupleInit.getIndex() == 2){
			varTypes.add(new VarType(argTupleInit.getVarDecl()));
			List<VarDecl> tempDecls = argTupleInit.getTupleDeclList().getVarDecls();
			for (int i = 0; i < tempDecls.size(); i++){
				varTypes.add(new VarType(tempDecls.get(i)));
			}
		}
	}
	
	public List<VarType> getVarTypes() {
		return varTypes;
	}

	public void setVarTypes(List<VarType> varTypes) {
		this.varTypes = varTypes;
	}
	
	public void addToVarTypes(VarType argVarType) {
		varTypes.add(argVarType);
	}

	@Override
	public boolean equals(Object o){
		if (!(o instanceof TupleType)){
			return false;
		}
		TupleType tempTupleType = (TupleType) o;
		List<VarType> otherVarTypes = tempTupleType.getVarTypes();
		if (varTypes.size() != otherVarTypes.size()){
			return false;
		}
		for (int i = 0; i < varTypes.size(); i++){
			if (!(varTypes.get(i).equals(otherVarTypes.get(i)))){
				return false;
			}
		}
		return true;
	}
	
}
