package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Class that represents the assignment of pre-declared variables.
 */
public class AssignmentStmt implements NakedStmt {
	private Identifier identifier;
	private ArrayElement arrElem;
	private Expr expr;
	private int index;
	
	public AssignmentStmt(Identifier id, Expr e) {
		identifier = id;
		expr = e;
		index = 0;
	}

	public AssignmentStmt(ArrayElement ae, Expr e) {
		arrElem = ae;
		expr = e;
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		if (index == 0) {
			identifier.prettyPrintNode();
		} else {
			arrElem.prettyPrintNode();
		}
		expr.prettyPrintNode();
		tempPrinter.endList();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public ArrayElement getArrElem() {
		return arrElem;
	}

	public void setArrElem(ArrayElement arrElem) {
		this.arrElem = arrElem;
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
}
