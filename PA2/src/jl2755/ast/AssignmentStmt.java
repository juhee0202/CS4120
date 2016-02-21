package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
}
