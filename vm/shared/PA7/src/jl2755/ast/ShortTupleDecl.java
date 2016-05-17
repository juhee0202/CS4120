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

	private Identifier id;
	private IdentifierList identifierList;
	private Type type;
	
	public ShortTupleDecl(Identifier id, IdentifierList idl, Type t) {
		this.id = id;
		identifierList = idl;
		identifierList.setType(t);
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
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		id.prettyPrintNode();
		type.prettyPrintNode();
		tempPrinter.endList();
		
		identifierList.prettyPrintNode();
	}
}
