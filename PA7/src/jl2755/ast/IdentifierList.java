package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

public class IdentifierList {
	private Identifier id;
	private IdentifierList identifierList;
	
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
}
