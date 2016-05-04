package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing an abbreviated multiple variable declarations
 * (there must be at least two variables being declared)
 * ex) x,y:int
 *
 */
public class ShortTupleDecl implements NakedStmt {

	private Identifier id;
	private IdentifierList identifierList;
	private Type type;
	
	public ShortTupleDecl(Identifier id, IdentifierList idl, Type t) {
		this.id = id;
		identifierList = idl;
		type = t;
	}
	
	/**
	 * @return a list of all identifiers
	 */
	public List<Identifier> getAllIdentifiers() {
		List<Identifier> identifiers = new ArrayList<Identifier>();
		identifiers.add(id);
		identifiers.addAll(identifierList.getAllIdentifiers());
		return identifiers;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public void prettyPrintNode() {
		// TODO Auto-generated method stub
	}
}
