package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Represents multiple assignment statement in a recursive manner.
 * (Note that left side of the statement has to be variable declarations)
 * 
 * index 
 * 	- 0: _ = f()
 *  - 1: _, tupleDeclList = f()
 *  - 2: id:type, tupleDeclList = f()
 */
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

	public TupleDeclList getTupleDeclList() {
		return tupleDeclList;
	}

	public void setTupleDeclList(TupleDeclList tupleDeclList) {
		this.tupleDeclList = tupleDeclList;
	}

	public FunctionCall getFunctionCall() {
		return functionCall;
	}

	public void setFunctionCall(FunctionCall functionCall) {
		this.functionCall = functionCall;
	}

	public VarDecl getVarDecl() {
		return varDecl;
	}

	public void setVarDecl(VarDecl varDecl) {
		this.varDecl = varDecl;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
