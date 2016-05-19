package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that represents a while statement. There should be an
 * expression to check every iteration, and Stmt (which can
 * be a block statement) to run every iteration.
 */
public class WhileStmt implements NakedStmt {
	private Expr expr;
	private Stmt stmt;
	private Label label;
	private Label exitLabel;
	
	public WhileStmt(Expr e, Stmt s) {
		expr = e;
		stmt = s;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.startList();
		if (label != null) {
			label.prettyPrintNode();
		}
		tempPrinter.endList();
		tempPrinter.printAtom("while");
		expr.prettyPrintNode();
		stmt.prettyPrintNode();
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

	public Stmt getStmt() {
		return stmt;
	}

	public void setStmt(Stmt stmt) {
		this.stmt = stmt;
	}
	
	public void setLabel(Label l) {
		label = l;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public boolean hasLabel() {
		return label != null;
	}
	
	public void setExitLabel(Label exitLabel) {
		this.exitLabel = exitLabel;
	}
	
	public Label getExitLabel() {
		return exitLabel;
	}
}
