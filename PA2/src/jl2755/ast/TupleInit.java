package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class TupleInit implements NakedStmt {
	private TupleDeclList tupleDeclList;
	private FunctionCall functionCall;
	private VarDecl varDecl;
	private int index;
	
	public TupleInit(FunctionCall fc) {
		functionCall = fc;
		index = 0;
	}
	
	public TupleInit(TupleDeclList tdl, FunctionCall fc) {
		tupleDeclList = tdl;
		functionCall = fc;
		index = 1;
	}
	
	public TupleInit(VarDecl vd, 
					 TupleDeclList tdl, 
					 FunctionCall fc) {
		varDecl = vd;
		tupleDeclList = tdl;
		functionCall = fc;
		index = 2;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("=");
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.printAtom("_");
			functionCall.prettyPrintNode();
		} else if (index == 1) {
			tempPrinter.printAtom("_");
			tupleDeclList.prettyPrintNode();
		} else {
			varDecl.prettyPrintNode();
			tupleDeclList.prettyPrintNode();
		}
		tempPrinter.endList();
	}	
}
