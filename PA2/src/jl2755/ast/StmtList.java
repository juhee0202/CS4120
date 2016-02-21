package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

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
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		stmt.prettyPrintNode();
		if (index == 1) {
			stmtList.prettyPrintNode();
		}
	}
}
