package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Represents a variable declaration in which the type of the variable can be
 * int, bool, t[ ], or SomeClass
 * 
 * index 
 * 	- 0: MixedArrayType
 * 	- 1: PrimitiveType
 *  - 2: Identifier (ClassType)
 */
public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private MixedArrayType mixedArrayType;
	private PrimitiveType primitiveType;
	private Identifier classType;
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
	
	public VarDecl(Identifier id, Identifier classId) {
		identifier = id;
		classType = classId;
		classType.setIsClassName();
		index = 2;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		if (index == 0) {
			mixedArrayType.prettyPrintNode();
		}
		else if (index == 1) {
			primitiveType.prettyPrintNode();
		} else {
			classType.prettyPrintNode();
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
	
	public Identifier getClassType() {
		return classType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public void accept(ASTVisitor v){
		v.visit(this);
	}
}
