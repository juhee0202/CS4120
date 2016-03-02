package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class WhileStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt;
	
	public WhileStmt(Expr e, Stmt s) {
		expr = e;
		stmt = s;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("while");
		expr.prettyPrintNode();
		stmt.prettyPrintNode();
		tempPrinter.endList();
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public Stmt getStmt() {
		return stmt;
	}

	public void setStmt(Stmt stmt) {
		this.stmt = stmt;
	}
}
