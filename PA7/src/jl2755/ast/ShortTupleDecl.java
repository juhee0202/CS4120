package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing an abbreviated multiple variable declarations
 * (there must be at least two variables being declared)
 * ex) x,y:int
 *
 */
public class ShortTupleDecl implements NakedStmt {

	public enum Type {
		PRIMITIVE, OBJECT, MIXEDARRAY;
	}
	
	private Identifier identifier;
	private IdentifierList identifierList;
	private ShortTupleDecl.Type type;
	private PrimitiveType primitiveType;
	private Identifier objectId;
	private MixedArrayType mixedArrayType;
	
	public ShortTupleDecl(Identifier id, IdentifierList idl, PrimitiveType pt) {
		this.identifier = id;
		this.identifierList = idl;
		this.type = Type.PRIMITIVE;
		primitiveType = pt;
	}
	
	public ShortTupleDecl(Identifier id, IdentifierList idl, Identifier objectId) {
		this.identifier = id;
		this.identifierList = idl;
		this.type = Type.OBJECT;
		this.objectId = objectId;
	}
	
	public ShortTupleDecl(Identifier id, IdentifierList idl, MixedArrayType mat) {
		this.identifier = id;
		this.identifierList = idl;
		this.type = Type.MIXEDARRAY;
		mixedArrayType = mat;
	}
	
	/**
	 * @return a list of all identifiers
	 */
	public List<Identifier> getAllIdentifiers() {
		List<Identifier> identifiers = new ArrayList<Identifier>();
		identifiers.add(identifier);
		identifiers.addAll(identifierList.getAllIdentifiers());
		return identifiers;
	}
	
	

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier id) {
		this.identifier = id;
	}

	public IdentifierList getIdentifierList() {
		return identifierList;
	}

	public void setIdentifierList(IdentifierList identifierList) {
		this.identifierList = identifierList;
	}

	public ShortTupleDecl.Type getType() {
		return type;
	}

	public void setType(ShortTupleDecl.Type type) {
		this.type = type;
	}

	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	public void setPrimitiveType(PrimitiveType primitiveType) {
		this.primitiveType = primitiveType;
	}

	public Identifier getObjectId() {
		return objectId;
	}

	public void setObjectId(Identifier objectId) {
		this.objectId = objectId;
	}

	public MixedArrayType getMixedArrayType() {
		return mixedArrayType;
	}

	public void setMixedArrayType(MixedArrayType mixedArrayType) {
		this.mixedArrayType = mixedArrayType;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		identifier.prettyPrintNode();
		if (type == Type.PRIMITIVE) {
			primitiveType.prettyPrintNode();
		} else if (type == Type.MIXEDARRAY) {
			mixedArrayType.prettyPrintNode();
		} else {
			objectId.prettyPrintNode();
		}
		tempPrinter.endList();
		identifierList.prettyPrintNode();
	}
}
