package jl2755.controlflow;

import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;

public class IRCFGNode extends CFGNode {
	/** The IRStmt that this IRCFGNode represents. */
	public IRStmt underlyingIRStmt;
	/** The name of the function that this IRCFGNode is contained in. */
	private String name;
	/** The ABIName of the function that this IRCFGNode is contained in. */
	private String ABIName;
	
	/** The use (if applicable) of this AACFGNode. */
	private IRExpr use1;
	/** The second (there can be at most 2) use of this AACFGNode. */
	private IRExpr use2;
	/** The one and only (if applicable) def of this AACFGNode. */
	private IRExpr def;
	
	public IRCFGNode(IRStmt argStmt) {
		super();
		underlyingIRStmt = argStmt;
	}
	
	private void computeKills() {
		
	}
	
	private void computeExprs() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getABIName() {
		return ABIName;
	}

	public void setABIName(String aBIName) {
		ABIName = aBIName;
	}

	public IRStmt getUnderlyingIRStmt() {
		return underlyingIRStmt;
	}

	public void setUnderlyingIRStmt(IRStmt underlyingIRStmt) {
		this.underlyingIRStmt = underlyingIRStmt;
	}
	
	@Override
	public String toString() {
		return underlyingIRStmt.toString();
	}

	@Override
	public String dotOutput() {
		String s = "\t" + "\"" + underlyingIRStmt + "\"";
		s += " -> {";
		if (successor1 != null) {
			s += "\"" + ((IRCFGNode) successor1).underlyingIRStmt + "\"";
			if (successor2 != null) {
				s += ", \"" + ((IRCFGNode) successor2).underlyingIRStmt + "\"";
			}
		}
		s += "}\n";
		if (successor1 != null) {
			s += successor1.dotOutput();
			if (successor2 != null) {
				s += successor2.dotOutput();
			}
		}
		return s;
	}	

}
