package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class VarDecl implements NakedStmt {
	private Identifier identifier;
	private MixedArrayType mixedArrayType;
	private PrimitiveType primitiveType;
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

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		if (index == 0){
			mixedArrayType.prettyPrintNode();
		}
		else{
			primitiveType.prettyPrintNode();
		}
		tempPrinter.endList();
	}
}
