package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class IfStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt1;
	private Stmt stmt2;
	private int index;

	public IfStmt(Expr e, Stmt s) {
		expr = e;
		stmt1 = s;
		stmt2 = null;
		index = 0;
	}
	
	public IfStmt(Expr e, Stmt s1, Stmt s2) {
		expr = e;
		stmt1 = s1;
		stmt2 = s2;
		index = 1;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("if");
		expr.prettyPrintNode();
		stmt1.prettyPrintNode();
		if (index == 1) {
			stmt2.prettyPrintNode();
		}
	}
}
