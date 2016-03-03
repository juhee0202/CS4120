package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private MixedArrayType mixedArrayType;
	private PrimitiveType primitiveType;
	/**
	 * 0 for mixedArrayType, and 1 for PrimitiveType
	 */
	private int index;
	
	public VarDecl(Identifier id, MixedArrayType mat) {
		identifier = id;
		mixedArrayType = mat;
		index = 0;
	}
	
	public VarDecl(Identifier id, PrimitiveType pt) {
		identifier = id;
		primitiveType = pt;
		index = 1;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		if (index == 0){
			mixedArrayType.prettyPrintNode();
		}
		else{
			primitiveType.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public MixedArrayType getMixedArrayType() {
		return mixedArrayType;
	}

	public void setMixedArrayType(MixedArrayType mixedArrayType) {
		this.mixedArrayType = mixedArrayType;
	}

	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	public void setPrimitiveType(PrimitiveType primitiveType) {
		this.primitiveType = primitiveType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public void accept(Visitor v){
		v.visit(this);
	}
}
