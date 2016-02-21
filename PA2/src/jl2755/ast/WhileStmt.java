package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class WhileStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt;
	
	public WhileStmt(Expr e, Stmt s) {
		expr = e;
		stmt = s;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("while");
		expr.prettyPrintNode();
		stmt.prettyPrintNode();
	}
}
