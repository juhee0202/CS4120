package jl2755.controlflow;

import edu.cornell.cs.cs4120.xic.ir.IRStmt;

// TODO: complete this class
public class IRCFGNode extends CFGNode {

	/**
	 * The Instruction that this CFGNode represents.
	 */
	protected IRStmt underlyingIRStmt;
	
	public IRCFGNode(IRStmt argStmt) {
		super();
		underlyingIRStmt = argStmt;
	}
	
	@Override
	public String dotOutput() {
		String s = "\t" + underlyingIRStmt;
		s += " -> {";
		if (successor1 != null) {
			s += ((IRCFGNode) successor1).underlyingIRStmt;
			if (successor2 != null) {
				s += " " + ((IRCFGNode) successor2).underlyingIRStmt;
			}
		}
		
		s += "}\n";
		s += successor1.dotOutput() + successor2.dotOutput();
		return s;
	}

}
