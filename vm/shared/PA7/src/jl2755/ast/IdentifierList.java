package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class IdentifierList {
	private Identifier id;
	private IdentifierList identifierList;
	private Type type;
	
	public IdentifierList(Identifier id) {
		this.id = id;
	}
	
	public IdentifierList(Identifier id, IdentifierList idl) {
		this.id = id;
		identifierList = idl;
	}
	
	/**
	 * @return a list of all identifiers of this IdentifierList
	 */
	public List<Identifier> getAllIdentifiers() {
		List<Identifier> identifiers = new ArrayList<Identifier>();
		identifiers.add(id);
		if (identifierList != null) {
			identifiers.addAll(identifierList.getAllIdentifiers());
		}
		return identifiers;
	}
	
	/**
	 * @return true if id is the last id of the whole list
	 */
	public boolean isLastId() {
		return identifierList == null;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
		if (identifierList != null) {
			identifierList.setType(type);
		}
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		id.prettyPrintNode();
		type.prettyPrintNode();
		tempPrinter.endList();
		if (identifierList != null) {
			identifierList.prettyPrintNode();
		}
	}
}
