package jl2755.ast;

public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private Type type;
	
	public VarDecl(Identifier id, Type t) {
		identifier = id;
		type = t;
	}
}
