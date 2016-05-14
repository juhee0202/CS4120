package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates a raw array type. There can be
 * no numbers in the brackets. Examples are int[][], bool[].
 */
public class EmptyArrayType implements Type{
	
	private PrimitiveType primitiveType;
	private Identifier identifier;
	private Brackets brackets;
	/**
	 * 0 if it's a primitive type followed by empty brackets: int[][].
	 * 1 if it's an object type followed by empty brackets: Animal[][][].
	 */
	private int index;
	
	public EmptyArrayType(PrimitiveType pt, Brackets b){
		primitiveType = pt;
		brackets = b;
		index = 0;
	}
	
	public EmptyArrayType(Identifier id, Brackets b) {
		identifier = id;
		brackets = b;
		index = 1;
	}

	@Override
	public void prettyPrintNode() {
		int numbBrackets = brackets.getNumBrackets();
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		for (int i = 0; i < numbBrackets; i++){
			tempPrinter.startList();
			tempPrinter.printAtom("[]");
		}
		
		if (index == 0) {
			primitiveType.prettyPrintNode();
		} else {
			identifier.prettyPrintNode();
		}
		
		for (int i = 0; i < numbBrackets; i++){
			tempPrinter.endList();
		}
	}

	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	public Identifier getClassType() {
		return identifier;
	}

	public Brackets getBrackets() {
		return brackets;
	}

	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getElementType() {
		String elementType = "";
		if (index == 0) {
			elementType = (primitiveType.getIndex() == 0)? "int" : "bool";
		} else {
			elementType = identifier.getTheValue();
		}
		return elementType;
	}

	@Override
	public void accept(ASTVisitor v) {
		return;
	}
}
