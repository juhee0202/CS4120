package jl2755.ast;

public class VarDecl implements NakedStmt {
	private String identifier;
	private Type type;
	
	public VarDecl(String id, Type t) {
		identifier = id;
		type = t;
	}
}
