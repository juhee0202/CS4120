package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
}
