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
	
	/* new features added to support for loop */
	private boolean isForLoop;
	private VarInit initVarInit;
	private AssignmentStmt initAssignment;
	private AssignmentStmt updateAssignment;
	private int index;
	private StmtList stmtList;
	
	public WhileStmt(Expr e, Stmt s) {
		expr = e;
		stmt = s;
		isForLoop = false;
		index = 0;
	}
	
	public WhileStmt(Expr e, StmtList sl) {
		expr = e;
		stmtList = sl;
		isForLoop = false;
		index = 4;
	}
	
	public WhileStmt(AssignmentStmt as1, Expr e, AssignmentStmt as2, Stmt s) {
		// TODO Auto-generated constructor stub
		initAssignment = as1;
		expr = e;
		updateAssignment = as2;
		stmt = s;
		isForLoop = true;
		index = 1;
	}

	public WhileStmt(VarInit vi, Expr e, AssignmentStmt as, Stmt s) {
		// TODO Auto-generated constructor stub
		initVarInit = vi;
		expr = e;
		updateAssignment = as;
		stmt = s;
		isForLoop = true;
		index = 2;
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

	/* new features added to support for loop */
	
	public boolean isForLoop() {
		return isForLoop;
	}

	public void setForLoop(boolean isForLoop) {
		this.isForLoop = isForLoop;
	}

	public VarInit getInitVarInit() {
		return initVarInit;
	}

	public void setInitVarInit(VarInit initVarInit) {
		this.initVarInit = initVarInit;
	}

	public AssignmentStmt getInitAssignment() {
		return initAssignment;
	}

	public void setInitAssignment(AssignmentStmt initAssignment) {
		this.initAssignment = initAssignment;
	}

	public AssignmentStmt getUpdateAssignment() {
		return updateAssignment;
	}

	public void setUpdateAssignment(AssignmentStmt updateAssignment) {
		this.updateAssignment = updateAssignment;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public StmtList getStmtList() {
		return stmtList;
	}

	public void setStmtList(StmtList stmtList) {
		this.stmtList = stmtList;
	}
}
