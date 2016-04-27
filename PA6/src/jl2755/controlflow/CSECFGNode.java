package jl2755.controlflow;

import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;

public class CSECFGNode extends CFGNode {
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
	
	public CSECFGNode(IRStmt argStmt) {
		super();
		underlyingIRStmt = argStmt;
		
	}
	
	private void computeKills() {
		
	}
	
	private void computeExprs() {
		
	}
	
	public void addSuccessor(CSECFGNode argNode) {
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

	@Override
	public String dotOutput() {
		String s = "\t" + underlyingIRStmt;
		s += " -> {";
		if (successor1 != null) {
			s += ((CSECFGNode) successor1).underlyingIRStmt;
			if (successor2 != null) {
				s += " " + ((CSECFGNode) successor2).underlyingIRStmt;
			}
		}
		
		s += "}\n";
		s += successor1.dotOutput() + successor2.dotOutput();
		return s;
	}	
}
