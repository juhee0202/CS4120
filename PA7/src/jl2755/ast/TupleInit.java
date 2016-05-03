package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Represents multiple assignment statement in a recursive manner.
 * (Note that left side of the statement has to be variable declarations)
 * 
 * index 
 * 	- 0: _ = f()
 *  - 1: _, tupleDeclList = f()
 *  - 2: vd, tupleDeclList = f()
 */
public class TupleInit implements NakedStmt {
	private TupleDeclList tupleDeclList;
	private FunctionCall functionCall;
	private int functionCall_col;
	private int functionCall_line;
	private VarDecl varDecl;
	private int index;
	
	public TupleInit(FunctionCall fc) {
		functionCall = fc;
		functionCall_col = fc.getColumnNumber();
		functionCall_line = fc.getLineNumber();
		index = 0;
	}
	
	public TupleInit(TupleDeclList tdl, FunctionCall fc) {
		tupleDeclList = tdl;
		functionCall = fc;
		functionCall_col = fc.getColumnNumber();
		functionCall_line = fc.getLineNumber();
		index = 1;
	}
	
	public TupleInit(VarDecl vd, TupleDeclList tdl, FunctionCall fc) {
		varDecl = vd;
		tupleDeclList = tdl;
		functionCall = fc;
		functionCall_col = fc.getColumnNumber();
		functionCall_line = fc.getLineNumber();
		index = 2;
	}
	
	/**
	 * @return a List of VarDecls in the tuple initialization statement
	 * UNDERSCOREs are represented by null
	 */
	public List<VarDecl> getVarDecls() {
		List<VarDecl> list = new ArrayList<VarDecl>();
		if (index == 0) {
			list.add(null);
		}
		else if (index == 1) {
			list.add(null);
			list.addAll(tupleDeclList.getVarDecls());
		}
		else {
			list.add(varDecl);
			list.addAll(tupleDeclList.getVarDecls());
		}
		return list;
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
	public void accept(ASTVisitor v) {
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

	public int getFunctionCall_col() {
		return functionCall_col;
	}

	public void setFunctionCall_col(int functionCall_col) {
		this.functionCall_col = functionCall_col;
	}

	public int getFunctionCall_line() {
		return functionCall_line;
	}

	public void setFunctionCall_line(int functionCall_line) {
		this.functionCall_line = functionCall_line;
	}
}
