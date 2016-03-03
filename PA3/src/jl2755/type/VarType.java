package jl2755.type;

import jl2755.ast.EmptyArrayType;
import jl2755.ast.MixedArrayType;
import jl2755.ast.PrimitiveType;
import jl2755.ast.VarDecl;

/**
 *	Ordinary types t expressible in the language
 *	int, bool, or t[ ]
 */
public class VarType implements VType {
	/** true: boolean, false: int*/
	private boolean isBool;
	/** The number of brackets used in the declaration
	 * of this array, if it is one 
	 * 0 if it is a primitive type
	 * */
	private Integer numBrackets;
	
	public VarType(jl2755.ast.Type t) {
		if (t instanceof PrimitiveType) {
			PrimitiveType temp = (PrimitiveType)t;
			setBool(temp.getIndex() == 1);
			numBrackets = 0;
		} else {
			EmptyArrayType temp = (EmptyArrayType)t;
			setBool(temp.getPrimitiveType().getIndex() == 1);
			numBrackets = temp.getBrackets().getNumBrackets();
		}
	}
	
	public VarType(MixedArrayType mat){
		isBool = mat.getP_type().getIndex() == 1;
		numBrackets = mat.getTotalNumBrackets();
	}
	
	public VarType(VarDecl vd){
		if (vd.getIndex() == 0){
			MixedArrayType mat = vd.getMixedArrayType();
			isBool = mat.getP_type().getIndex() == 1;
			numBrackets = mat.getTotalNumBrackets();
		}
		if (vd.getIndex() == 1){
			PrimitiveType pt = vd.getPrimitiveType();
			setBool(pt.getIndex() == 1);
			numBrackets = 0;
		}
	}
	
	public boolean isPrimitive() {
		return numBrackets == 0;
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
