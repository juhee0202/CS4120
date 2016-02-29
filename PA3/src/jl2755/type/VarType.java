package jl2755.type;

import jl2755.ast.EmptyArrayType;
import jl2755.ast.PrimitiveType;

public class VarType implements VType {
	private boolean isBool;		// true: boolean, false: int
	private int numBrackets;    // if an array type, numBrackets refer to the dimension
						        // if a primitive type, numBrackets = 0
	
	public VarType(jl2755.ast.Type t) {
		if (t instanceof PrimitiveType) {
			PrimitiveType temp = (PrimitiveType)t;
			setBool(temp.getIndex() == 1);
			setNumBrackets(0);
		} else {
			EmptyArrayType temp = (EmptyArrayType)t;
			setBool(temp.getPrimitiveType().getIndex() == 1);
			setNumBrackets(temp.getBrackets().getNumBrackets());
		}
	}

	public boolean isBool() {
		return isBool;
	}

	public void setBool(boolean isBool) {
		this.isBool = isBool;
	}

	public int getNumBrackets() {
		return numBrackets;
	}

	public void setNumBrackets(int numBrackets) {
		this.numBrackets = numBrackets;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VarType)) {
			return false;
		}
		VarType vt = (VarType)o;
		return this.isBool == vt.isBool 
				&& this.getNumBrackets() == vt.getNumBrackets(); 
	}
}
