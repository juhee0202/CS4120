package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class IfStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt1;
	private Stmt stmt2;
	/**
	 * 0 if there's no else clause,
	 * 1 if there is an else clause.
	 */
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
		tempPrinter.startList();
		tempPrinter.printAtom("if");
		expr.prettyPrintNode();
		stmt1.prettyPrintNode();
		if (index == 1) {
			stmt2.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public Stmt getStmt1() {
		return stmt1;
	}

	public void setStmt1(Stmt stmt1) {
		this.stmt1 = stmt1;
	}

	public Stmt getStmt2() {
		return stmt2;
	}

	public void setStmt2(Stmt stmt2) {
		this.stmt2 = stmt2;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
