package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates the array type of
 * a variable in its initialization.
 */
public class MixedArrayType implements Type {
	
	private PrimitiveType p_type;
	private Identifier o_type;
	private MixedBrackets mixedBrackets;
	private Brackets brackets;
	private MixedArrayType arrayType;
	/** 
	 * 0 is (int/bool)[], 
	 * 1 is (int/bool) with at least 1 brackets with numbers,
	 * 2 is object[],
	 * 3 is object with at least 1 brackets with numbers
	 **/
	private int index;
	
	public MixedArrayType(PrimitiveType p_type, Brackets brackets) {
		this.p_type = p_type;
		this.brackets = brackets;
		index = 0;
	}
	
	public MixedArrayType(PrimitiveType p_type, MixedBrackets mb) {
		this.p_type = p_type;
		this.mixedBrackets = mb;
		index = 1;
	}
	
	public MixedArrayType(Identifier id, Brackets b) {
		o_type = id;
		brackets = b;
		index = 2;
	}
	
	public MixedArrayType(Identifier id, MixedBrackets mb) {
		o_type = id;
		mixedBrackets = mb;
		index = 3;
	}
	
	public void prettyPrintNode() {
		int n = 0;
		List<Expr> list = new ArrayList<Expr>();
		
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0) {
			n = brackets.getNumBrackets();
			for (int i = 0; i < n; i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			p_type.prettyPrintNode();
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} else if (index == 1) {
			list = mixedBrackets.getContent();
			for (int i = 0; i < mixedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			p_type.prettyPrintNode();
			for (int i = 0; i < mixedBrackets.getNumBrackets() - list.size(); i++) {
				tempPrinter.endList();
			}
			for (int i = list.size()-1; i >= 0; i--){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		} else if (index == 2) {
			n = brackets.getNumBrackets();
			for (int i = 0; i < n; i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			o_type.prettyPrintNode();
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} else if (index == 3) {
			list = mixedBrackets.getContent();
			for (int i = 0; i < mixedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			o_type.prettyPrintNode();
			for (int i = 0; i < mixedBrackets.getNumBrackets() - list.size(); i++) {
				tempPrinter.endList();
			}
			for (int i = list.size()-1; i >= 0; i--){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
	}

	public PrimitiveType getP_type() {
		return p_type;
	}
	
	public Identifier getO_type() {
		return o_type;
	}

	public void setP_type(PrimitiveType p_type) {
		this.p_type = p_type;
	}

	public MixedBrackets getMixedBrackets() {
		return mixedBrackets;
	}

	public void setMixedBrackets(MixedBrackets mixedBrackets) {
		this.mixedBrackets = mixedBrackets;
	}

	public Brackets getBrackets() {
		return brackets;
	}

	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
	}

	public MixedArrayType getArrayType() {
		return arrayType;
	}

	public void setArrayType(MixedArrayType arrayType) {
		this.arrayType = arrayType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getTotalNumBrackets() {
		if (index == 0 || index == 2){
			return brackets.getNumBrackets();
		}
		else{
			return mixedBrackets.getNumBrackets();
		}
	}
	
	public String getElementType() {
		String elementType = "";
		if (index == 0 || index == 1) {
			elementType = (p_type.getIndex() == 0)? "int" : "bool";
		} else {
			elementType = o_type.getTheValue();
		}
		return elementType;
	}
	
	public void accept(ASTVisitor v){
		return;
	}

	@Override
	public int getColumnNumber() {
		if (index < 2) {
			return p_type.getColumnNumber();
		} else {
			return o_type.getColumnNumber();
		}
	}

	@Override
	public int getLineNumber() {
		if (index < 2) {
			return p_type.getLineNumber();
		} else {
			return o_type.getLineNumber();
		}
	}
}