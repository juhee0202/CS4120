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
public class TupleType implements VType {
	
	/**
	 * List of the VarTypes that represent
	 * the VarDecls on the left side.
	 * Null entries represent underscores.
	 */
	private List<VType> types = new ArrayList<VType>();
	
	/** 
	 * @param argTupleInit and makes a List of
	 * VarTypes for the elements in the Tuple
	 */
	public TupleType(TupleInit argTupleInit) {
		if (argTupleInit.getIndex() == 0){
			types.add(null);
		}
		if (argTupleInit.getIndex() == 1){
			types.add(null);
			List<VarDecl> tempDecls = argTupleInit.getTupleDeclList().getVarDecls();
			for (int i = 0; i < tempDecls.size(); i++){
				types.add(new VarType(tempDecls.get(i)));
			}
		}
		if (argTupleInit.getIndex() == 2){
			types.add(new VarType(argTupleInit.getVarDecl()));
			List<VarDecl> tempDecls = argTupleInit.getTupleDeclList().getVarDecls();
			for (int i = 0; i < tempDecls.size(); i++){
				types.add(new VarType(tempDecls.get(i)));
			}
		}
	}
	
	public List<VType> getTypes() {
		return types;
	}

	public void setTypes(List<VType> types) {
		this.types = types;
	}
	
	public void addToVarTypes(VarType argVarType) {
		types.add(argVarType);
	}

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
			if (!(types.get(i).equals(otherVarTypes.get(i)))){
				return false;
			}
		}
		return true;
	}
	
}
