package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class BlockStmt implements NakedStmt {
	private StmtList stmtList;
	private ReturnStmt returnStmt;
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
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index > 0) {
			stmtList.prettyPrintNode();
		}
		if (index == 2) {
			returnStmt.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
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
