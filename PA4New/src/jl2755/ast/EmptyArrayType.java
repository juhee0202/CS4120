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
	private Brackets brackets;
	/**
	 * 0 if it's a primitive type followed by empty brackets: int[][].
	 */
	private int index;
	
	public EmptyArrayType(PrimitiveType pt, Brackets b){
		primitiveType = pt;
		brackets = b;
		index = 0;
	}

	@Override
	public void prettyPrintNode() {
		int numbBrackets = brackets.getNumBrackets();
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		for (int i = 0; i < numbBrackets; i++){
			tempPrinter.startList();
			tempPrinter.printAtom("[]");
		}
		primitiveType.prettyPrintNode();
		for (int i = 0; i < numbBrackets; i++){
			tempPrinter.endList();
		}
	}

	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	public void setPrimitiveType(PrimitiveType primitiveType) {
		this.primitiveType = primitiveType;
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

	@Override
	public void accept(ASTVisitor v) {
		return;
	}
}
