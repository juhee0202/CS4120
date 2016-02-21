package jl2755.ast;

public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private Type type;
	
	public VarDecl(Identifier id, Type t) {
		identifier = id;
		type = t;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		identifier.prettyPrintNode();
		type.prettyPrintNode();
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "";
	}
}
