package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class Stmt {
	private NakedStmt nakedStmt;
	private boolean hasSemicolon;
	
	public Stmt(NakedStmt ns, boolean b) {
		nakedStmt = ns;
		hasSemicolon = b;
	}
	
	public void prettyPrintNode() {
		nakedStmt.prettyPrintNode();
	}
}
