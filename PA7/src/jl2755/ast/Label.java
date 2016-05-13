package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class Label {
	private String name;
	private WhileStmt whileStmt;
	
	public Label(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public WhileStmt getLoop() {
		return whileStmt;
	}
	
	public void setLoop(WhileStmt ws) {
		whileStmt = ws;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(name);
	}
}
