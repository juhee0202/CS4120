package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates a list of statements. Used
 * as a recursive box for statements and is used in
 * BlockStmt.
 */
public class StmtList {
	private Stmt stmt;
	private StmtList stmtList;
	/**
	 * 0 if base case: {s1},
	 * 1 if recursive case: {..., sn, ...}.
	 */
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

	public Stmt getStmt() {
		return stmt;
	}

	public void setStmt(Stmt stmt) {
		this.stmt = stmt;
	}

	public StmtList getStmtList() {
		return stmtList;
	}

	public void setStmtList(StmtList stmtList) {
		this.stmtList = stmtList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	public List<Stmt> getAllStmt() {
		List<Stmt> tempList = new ArrayList<Stmt>();
		tempList.add(stmt);
		if (index == 1) {
			tempList.addAll(stmtList.getAllStmt());
		}
		return tempList;
	}
}
