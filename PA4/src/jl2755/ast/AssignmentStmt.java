package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class AssignmentStmt implements NakedStmt {
	private Identifier identifier;
	private IndexedBrackets indexedBrackets;
	private FunctionCall functionCall;
	private Expr expr;
	private int index;
	
	public AssignmentStmt(Identifier id, Expr e) {
		identifier = id;
		expr = e;
		index = 0;
	}

	// not using ArrayElement class
	public AssignmentStmt(Identifier id, IndexedBrackets ib, Expr e) {
		identifier = id;
		indexedBrackets = ib;
		expr = e;
		index = 1;
	}
	
	public AssignmentStmt(FunctionCall fc, IndexedBrackets ib, Expr e) {
		functionCall = fc;
		indexedBrackets = ib;
		expr = e;
		index = 2;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		if (index == 0) {
			identifier.prettyPrintNode();
			expr.prettyPrintNode();
		} else if (index == 1){
			int numbBrackets = indexedBrackets.getNumBrackets();
			List<Expr> bracketContents = indexedBrackets.getContent();
			for (int i = 0; i < numbBrackets; i ++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			identifier.prettyPrintNode();
			for (int i = 0; i < numbBrackets; i++){
				bracketContents.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			expr.prettyPrintNode();
		} else if (index == 2){
			int numbBrackets = indexedBrackets.getNumBrackets();
			List<Expr> bracketContents = indexedBrackets.getContent();
			for (int i = 0; i < numbBrackets; i ++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			functionCall.prettyPrintNode();
			for (int i = 0; i < numbBrackets; i++){
				bracketContents.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			expr.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public IndexedBrackets getIndexedBrackets() {
		return indexedBrackets;
	}

	public void setIndexedBrackets(IndexedBrackets indexedBrackets) {
		this.indexedBrackets = indexedBrackets;
	}

	public FunctionCall getFunctionCall() {
		return functionCall;
	}

	public void setFunctionCall(FunctionCall functionCall) {
		this.functionCall = functionCall;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
