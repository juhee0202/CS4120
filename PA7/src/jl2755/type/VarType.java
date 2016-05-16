package jl2755.type;

import java.util.ArrayList;
import java.util.List;

import jl2755.Main;
import jl2755.SemanticErrorObject;
import jl2755.ast.ArrayLiteral;
import jl2755.ast.EmptyArrayType;
import jl2755.ast.Expr;
import jl2755.ast.FunctionCall;
import jl2755.ast.Identifier;
import jl2755.ast.IndexedBrackets;
import jl2755.ast.MixedArrayType;
import jl2755.ast.PrimitiveType;
import jl2755.ast.ShortTupleDecl;
import jl2755.ast.Type;
import jl2755.ast.VarDecl;

/**
 *	Ordinary types t expressible in the language
 *	int, bool, or t[ ] where t can either be Primitive or Object
 */
public class VarType implements VType {

	private boolean isPrimitive;
	private boolean isArray;
	private boolean isObject;
	private String elementType;
	private int numBrackets;
	private List<Expr> exprs;
	
//	private boolean isBool;				// true if primitive boolean, else false
//	private boolean isPrimitiveArray;	// true if Primitive array type, else false
//	private Integer numBrackets;		// number of brackets used in declaration of this variable
//										// 0 if primitive type
//		
	public VarType(jl2755.ast.Type t) {
		// int or bool
		if (t instanceof PrimitiveType) {
			PrimitiveType pt = (PrimitiveType) t;
			isPrimitive = true;
			isArray = false;
			isObject = false;
			elementType = (pt.getIndex() == 0)? "int" : "bool";
			numBrackets = 0;
			
		// Point[][]
		} else if (t instanceof EmptyArrayType) {
			EmptyArrayType eat = (EmptyArrayType) t;
			isPrimitive = false;
			isArray = true;
			isObject = false;
			elementType = eat.getElementType();
			numBrackets = eat.getBrackets().getNumBrackets();
			exprs = new ArrayList<Expr>();
		// object type
		} else {
			Identifier id = (Identifier) t;
			isPrimitive = false;
			isArray = false;
			isObject = true;
			elementType = id.toString();
			numBrackets = 0;
		}
		
//		if (t instanceof PrimitiveType) {
//			PrimitiveType temp = (PrimitiveType)t;
//			setIsBool(temp.getIndex() == 1);
//			numBrackets = 0;
//		} else {
//			EmptyArrayType temp = (EmptyArrayType)t;
//			setIsBool(temp.getPrimitiveType().getIndex() == 1);
//			numBrackets = temp.getBrackets().getNumBrackets();
//		}
	}
	
	public VarType(MixedArrayType mat){
		isPrimitive = false;
		isArray = true;
		isObject = false;
		elementType = mat.getElementType();
		numBrackets = mat.getTotalNumBrackets();
		exprs = mat.getAllExprs();
//		isBool = mat.getP_type().getIndex() == 1;
//		numBrackets = mat.getTotalNumBrackets();
	}
	
	public VarType(VarDecl vd){
		// MixedArrayType
		if (vd.getIndex() == 0) {
			isPrimitive = false;
			isArray = true;
			isObject = false;
			MixedArrayType mat = vd.getMixedArrayType();
			elementType = mat.getElementType();
			numBrackets = mat.getTotalNumBrackets();
			exprs = mat.getAllExprs();
		// PrimitiveType
		} else if (vd.getIndex() == 1) {
			isPrimitive = true;
			isArray = false;
			isObject = false;
			PrimitiveType pt = vd.getPrimitiveType();
			elementType = (pt.getIndex() == 0)? "int" : "bool";
			numBrackets = 0;
			
		// ClassType
		} else {
			isPrimitive = false;
			isArray = false;
			isObject = true;
			elementType = vd.getClassType().toString();
			numBrackets = 0;
			
		}
		
//		if (vd.getIndex() == 0){
//			MixedArrayType mat = vd.getMixedArrayType();
//			isBool = mat.getP_type().getIndex() == 1;
//			numBrackets = mat.getTotalNumBrackets();
//		} else if (vd.getIndex() == 1){
//			PrimitiveType pt = vd.getPrimitiveType();
//			setIsBool(pt.getIndex() == 1);
//			numBrackets = 0;
//		}
	}
	
	
//	/**
//	 * Precondition: creating either a PrimitiveType or ArrayType with only primitive elements
//	 * @param argIsBool
//	 * @param argNumBrackets
//	 */
//	public VarType(boolean argIsBool, int argNumBrackets){
//		numBrackets = argNumBrackets;
//		elementType = (argIsBool)? "bool" : "int";
//		if (argNumBrackets == 0) {
//			isPrimitive = true;
//			isArray = false;
//			isObject = false;
//		} else {
//			isPrimitive = false;
//			isArray = true;
//			isObject = false;
//		}
////		isBool = argIsBool;
////		numBrackets = argNumBrackets;
//	}
	
	public VarType(String elementType, int numBrackets) {
		this.elementType = elementType;
		this.numBrackets = numBrackets;
		
		if (elementType.toLowerCase().equals("int") || 
				elementType.toLowerCase().equals("bool")) {
			if (numBrackets == 0) {		// int
				isPrimitive = true;
				isArray = false;
				isObject = false;
			} else {					// int[][]
				isPrimitive = false;
				isArray = true;
				isObject = false;
				exprs = new ArrayList<Expr>();
			}
		} 
		// class type
		else {
			if (numBrackets == 0) {		// Point
				isPrimitive = false;
				isArray = false;
				isObject = true;
			} else {					// Point[][]
				isPrimitive = false;
				isArray = true;
				isObject = false;
				exprs = new ArrayList<Expr>();
			}
		}
	}
	
	/**
	 * @return true iff the variable is an int, bool, or an array
	 * of one of either.
	 */
	public boolean isPrimitiveBase() {
		if (isArray) {
			return elementType.equals("int") || elementType.equals("bool");
		}
		else {
			return isPrimitive;
		}
	}
	
	
	/**
	 * @return primitive type of this type
	 * ex) int[] -> int
	 * ex) bool[][] -> bool
	 * ex) int -> int
	 */
	public VarType getPrimitiveType() {
		if (elementType.equals("bool")) {
			return new VarType("bool", 0);
		} else if (elementType.equals("int")) {
			return new VarType("int", 0);
		} else {
			System.out.println("VarType.java: getPrimitiveType()");
			return null;
		}
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}
	
	public boolean isArray() {
		return isArray;
	}
	
	public boolean isObject() {
		return isObject;
	}
	
	/**
	 * 
	 * @return true if the VarType is an int with numBrackets == 0
	 */
	public boolean isInt() {
		return elementType.equals("int") && (numBrackets == 0);
	}
	
	public boolean getIsBool() {
		return elementType.equals("bool");
	}
	
	public boolean isBool() {
		return elementType.equals("bool") && (numBrackets == 0);
	}

	public int getNumBrackets() {
		return numBrackets;
	}
	
	public boolean canCast() {
		return numBrackets == 0;
	}
	
	public String getElementType() {
		return elementType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof NullType) {
			return !isPrimitive;
		}
		if (!(o instanceof VarType)) {
			return false;
		}
		VarType vt = (VarType) o;
		return elementType.equals(vt.getElementType()) 
				&& this.getNumBrackets() == vt.getNumBrackets(); 
	}
	
	@Override
	public String toString() {
		String returnString = elementType;

		for (int i = 0; i < numBrackets; i++) {
			returnString += "[]";
		}
		return returnString;
	}

	@Override
	public boolean canDot() {
		return false;
	}

	@Override
	public boolean singleReturn() {
		return true;
	}

	public List<Expr> getExprs() {
		return exprs;
	}

	public void setExprs(List<Expr> exprs) {
		this.exprs = exprs;
	}
}
