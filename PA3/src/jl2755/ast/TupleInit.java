package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

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
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.printAtom("_");
			tempPrinter.endList();
			functionCall.prettyPrintNode();
		} else if (index == 1) {
			tempPrinter.printAtom("_");
			tupleDeclList.prettyPrintNode();
			tempPrinter.endList();
			functionCall.prettyPrintNode();
		} else {
			varDecl.prettyPrintNode();
			tupleDeclList.prettyPrintNode();
			tempPrinter.endList();
			functionCall.prettyPrintNode();
		}
		tempPrinter.endList();
	}	
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
