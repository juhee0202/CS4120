package jl2755.type;

import java.util.ArrayList;
import java.util.List;

import jl2755.ast.EmptyArrayType;
import jl2755.ast.PrimitiveType;

public class VarType implements VType {
	private boolean isBool;		// true: boolean, false: int
	private List<Integer> dimensionSizes;
	
	public VarType(jl2755.ast.Type t) {
		if (t instanceof PrimitiveType) {
			PrimitiveType temp = (PrimitiveType)t;
			setBool(temp.getIndex() == 1);
			dimensionSizes = new ArrayList<Integer>();
		} else {
			EmptyArrayType temp = (EmptyArrayType)t;
			setBool(temp.getPrimitiveType().getIndex() == 1);
			setNumBrackets(temp.getBrackets().getNumBrackets());
		}
	}
	
	public VarType(MixedArrayType mat){
		
	}

	public boolean isBool() {
		return isBool;
	}

	public void setBool(boolean isBool) {
		this.isBool = isBool;
	}

	public int getDimensionSizes() {
		return dimensionSizes.size();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VarType)) {
			return false;
		}
		VarType vt = (VarType)o;
		return this.isBool == vt.isBool 
				&& this.getDimensionSizes() == vt.getDimensionSizes(); 
	}
}
