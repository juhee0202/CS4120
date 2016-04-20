package jl2755.controlflow;

import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;

public class IRCFGNode extends CFGNode {
	/**
	 * The Instruction that this CFGNode represents.
	 */
	private IRStmt underlyingIRStmt;
	/**
	 * The use (if applicable) of this AACFGNode.
	 */
	private IRExpr use1;
	/**
	 * The second (there can be at most 2) use of
	 * this AACFGNode.
	 */
	private IRExpr use2;
	/**
	 * The one and only (if applicable) def
	 * of this AACFGNode.
	 */
	private IRExpr def;
	
	public IRCFGNode(IRStmt argStmt) {
		super();
		underlyingIRStmt = argStmt;
		
	}
	
	private void computeUses() {
		// TODO: Complete
	}
	
	private void computeDefs() {
		// TODO: Complete
	}
	
	public void addSuccessor(IRCFGNode argNode) {
		proposeToSuccessor(argNode);
		if (successor1 == null) {
			successor1 = argNode;
		}
		else if (successor2 == null) {
			successor2 = argNode;
		}
		else {
			assert(false);
			System.out.println("Already added 2 successors");
		}
	}	
}
