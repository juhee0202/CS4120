package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates a list of statements preceded by
 * an open curly brace and proceeded by a closed curly brace.
 */
public class BlockStmt implements NakedStmt {
	private StmtList stmtList;
	private ReturnStmt returnStmt;
	/**
	 * 0 for no stmtList,
	 * 1 for only stmtList,
	 * 2 for both,
	 * 3 for only returnStmt.
	 */
	private int index;
	
	public BlockStmt() {
		stmtList = null;
		returnStmt = null;
		index = 0;
	}
	
	public BlockStmt(StmtList sl) {
		stmtList = sl;
		returnStmt = null;
		index = 1;
	}
	
	public BlockStmt(StmtList sl, ReturnStmt rs) {
		stmtList = sl;
		returnStmt = rs;
		index = 2;
	}
	
	public BlockStmt(ReturnStmt rs) {
		stmtList = null;
		returnStmt = rs;
		index = 3;
	}
	
	public void prettyPrintNode() {
		// TODO fix mistake
		/*
		 * 0 for no stmtList,
		 * 1 for only stmtList,
		 * 2 for both,
		 * 3 for only returnStmt.
		 */
		
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index ==1 || index == 2) {
			stmtList.prettyPrintNode();
		}
		if (index == 2 || index == 3) {
			returnStmt.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	public StmtList getStmtList() {
		return stmtList;
	}

	public void setStmtList(StmtList stmtList) {
		this.stmtList = stmtList;
	}

	public ReturnStmt getReturnStmt() {
		return returnStmt;
	}

	public void setReturnStmt(ReturnStmt returnStmt) {
		this.returnStmt = returnStmt;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
