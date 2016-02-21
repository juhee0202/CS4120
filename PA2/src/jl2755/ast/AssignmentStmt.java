package jl2755.ast;

public class AssignmentStmt implements NakedStmt {
	private String identifier;
	private ArrayElement arrElem;
	private Expr expr;
	private int index;
	
	public AssignmentStmt(String id, Expr e) {
		identifier = id;
		expr = e;
		index = 0;
	}

	public AssignmentStmt(ArrayElement ae, Expr e) {
		arrElem = ae;
		expr = e;
		index = 1;
	}
}
