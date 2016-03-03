package jl2755.type;

import java.util.ArrayList;
import java.util.List;

import jl2755.ast.TupleInit;
import jl2755.ast.VarDecl;

/**
 * Represents a Tuple type
 */
public class TupleType implements VType {
	private List<VType> types;
	
	public TupleType() {
		types = new ArrayList<VType>();
	}
	
	/** 
	 * Creates a TupleType object from the left side of a TupleInit stmt.
	 * @param argTupleInit and makes a List of
	 * VarTypes for the elements in the Tuple
	 * 
	 * underscores are represented by UnitType
	 */
	public TupleType(TupleInit argTupleInit) {
		types = new ArrayList<VType>();
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
	
	public void addToTypes(VarType argType) {
		types.add(argType);
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
