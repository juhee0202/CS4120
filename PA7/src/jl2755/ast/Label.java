package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class Label {
	private String name;
	private WhileStmt whileStmt;
	private int line;
	private int col;
	
	public Label(String n, int l, int c) {
		name = n;
		line = l;
		col = c;
	}
	
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

	public WhileStmt getWhileStmt() {
		return whileStmt;
	}

	public int getLine() {
		return line;
	}

	public int getCol() {
		return col;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(name);
	}
}
