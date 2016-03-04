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
	private int tupleDeclList_col;
	private int tupleDeclList_line;
	private FunctionCall functionCall;
	private int functionCall_col;
	private int functionCall_line;
	private VarDecl varDecl;
	private int varDecl_col;
	private int varDecl_line;
	private int index;
	
	public TupleInit(FunctionCall fc, int fcleft, int fcright) {
		functionCall = fc;
		functionCall_col = fcleft;
		functionCall_line = fcright;
		index = 0;
	}
	
	public TupleInit(TupleDeclList tdl, FunctionCall fc, 
					 int tdlleft, int tdlright, int fcleft, int fcright) {
		tupleDeclList = tdl;
		tupleDeclList_col = tdlleft;
		tupleDeclList_line = tdlright;
		functionCall = fc;
		functionCall_col = fcleft;
		functionCall_line = fcright;
		index = 1;
	}
	
	public TupleInit(VarDecl vd, TupleDeclList tdl, FunctionCall fc,
					 int vdleft, int vdright, int tdlleft, int tdlright,
					 int fcleft, int fcright) {
		varDecl = vd;
		varDecl_col = vdleft;
		varDecl_line = vdright;
		tupleDeclList = tdl;
		tupleDeclList_col = tdlleft;
		tupleDeclList_line = tdlright;
		functionCall = fc;
		functionCall_col = fcleft;
		functionCall_line = fcright;
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

	public int getTupleDeclList_col() {
		return tupleDeclList_col;
	}

	public void setTupleDeclList_col(int tupleDeclList_col) {
		this.tupleDeclList_col = tupleDeclList_col;
	}

	public int getTupleDeclList_line() {
		return tupleDeclList_line;
	}

	public void setTupleDeclList_line(int tupleDeclList_line) {
		this.tupleDeclList_line = tupleDeclList_line;
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

	public int getVarDecl_col() {
		return varDecl_col;
	}

	public void setVarDecl_col(int varDecl_col) {
		this.varDecl_col = varDecl_col;
	}

	public int getVarDecl_line() {
		return varDecl_line;
	}

	public void setVarDecl_line(int varDecl_line) {
		this.varDecl_line = varDecl_line;
	}
}
