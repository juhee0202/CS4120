package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private Type type;
	
	public VarDecl(Identifier id, Type t) {
		identifier = id;
		type = t;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		type.prettyPrintNode();
		tempPrinter.endList();
	}
}
