package jl2755.ast;

import jl2755.visitor.Visitor;

public class StmtList {
	private Stmt stmt;
	private StmtList stmtList;
	private int index;
	
	public StmtList(Stmt s) {
		stmt = s;
		index = 0;
	}
	
	public StmtList(Stmt s, StmtList sl) {
		stmt = s;
		stmtList = sl;
		index = 1;
	}	
	
	public void prettyPrintNode() {
		stmt.prettyPrintNode();
		if (index == 1) {
			stmtList.prettyPrintNode();
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
