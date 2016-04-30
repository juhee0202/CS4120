package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRPhiFunction;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;

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
	
	public void putArgBeforeThisNode(IRStmt argStmt) {
		IRCFGNode blankNode = new IRCFGNode(argStmt);
		blankNode.name = name;
		blankNode.ABIName = name;
		blankNode.use1 = use1;
		blankNode.use2 = use2;
		blankNode.def = def;
		blankNode.successor1 = this;
		blankNode.predecessors = predecessors;
		predecessors = new ArrayList<CFGNode>();
		predecessors.add(blankNode);
		blankNode.idom = idom;
		idom = blankNode;
		blankNode.children = new HashSet<CFGNode>();
		blankNode.children.add(this);
		
	}

	/**
	 * Replaces the usage of var to var_i
	 * @param node
	 * @param s
	 * @param i
	 */
	public void replaceUsage(String var, String newVar) {
		if (underlyingIRStmt instanceof IRCJump) {
			replaceUsage(((IRCJump)underlyingIRStmt).expr(), var, newVar);
		} else if (underlyingIRStmt instanceof IRExp) {
			replaceUsage(((IRExp)underlyingIRStmt).expr(), var, newVar);
		} else if (underlyingIRStmt instanceof IRJump) {
			replaceUsage(((IRJump)underlyingIRStmt).target(), var, newVar);
		} else if (underlyingIRStmt instanceof IRMove) {
			replaceUsage(((IRMove)underlyingIRStmt).expr(), var, newVar);
		}
	}

	/**
	 * Replaces var to var_i in expr
	 * @param expr
	 * @param var
	 * @param i
	 */
	public void replaceUsage(IRExpr expr, String var, String newVar) {
		if (expr instanceof IRBinOp) {
			replaceUsage(((IRBinOp)expr).left(), var, newVar);
			replaceUsage(((IRBinOp)expr).right(), var, newVar);
		} else if (expr instanceof IRCall) {
			replaceUsage(((IRCall)expr).target(), var, newVar);			
			List<IRExpr> args = ((IRCall)expr).args();
			for (IRExpr arg : args) {
				replaceUsage(arg, var, newVar);
			}
			((IRCall)expr).setArgs(args);
		} else if (expr instanceof IRMem) {
			replaceUsage(((IRMem)expr).expr(), var, newVar);
		} else if (expr instanceof IRTemp) {
			if (((IRTemp)expr).name().equals(var)) {
				((IRTemp)expr).setName(newVar);
			}
		}
	}

	/**
	 * Replaces the definition of var to var_i
	 * @param node
	 * @param var
	 * @param i
	 */
	public void replaceDefinition(String var, String newVar) {
		if (underlyingIRStmt instanceof IRMove) {
			IRMove move = (IRMove)underlyingIRStmt;
			IRTemp newTemp = new IRTemp(newVar);
			IRMove newMove = new IRMove(newTemp, move.expr());
			underlyingIRStmt = newMove;
		} else if (underlyingIRStmt instanceof IRPhiFunction) {
			((IRPhiFunction)underlyingIRStmt).setVar(newVar);
		} else { 
			assert(underlyingIRStmt instanceof IRMove);
			// shouldn't get here
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
