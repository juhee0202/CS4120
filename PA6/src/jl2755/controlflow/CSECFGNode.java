package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.dataflow.IRExprOverrider;

public class CSECFGNode extends CFGNode {
	/**
	 * The Instruction that this CFGNode represents.
	 */
	private IRStmt underlyingIRStmt;
	/**
	 * The list of IRExpr that underlyingIRStmt kills if
	 * they are used in any operation.
	 */
	private List<IRExprOverrider> registerKills;
	/**
	 * The list of IRExpr that underlyingIRStmt kills if
	 * they are used in a memory operation. Temps using
	 * this IRExpr should be fine.
	 */
	private List<IRExprOverrider> memoryKills;
	
	/**
	 * This boolean is for a very conservative approach to
	 * common subexpression elimination. If there's any assignment
	 * to a memory location, then we kill everything, since without
	 * pointer analysis we can never be sure that one memory location
	 * is an alias of the other.
	 */
	private boolean killEverything;
	/**
	 * The list of IRExpr that underlyingIRStmt evaluates.
	 */
	private SubTreeListMaker exprSubTrees;
	/**
	 * The in of this node.
	 */
	private Set<IRExprOverrider> in;
	/**
	 * The out of this node.
	 */
	private Set<IRExprOverrider> out;
	
	public CSECFGNode(IRStmt argStmt) {
		super();
		underlyingIRStmt = argStmt;
		registerKills = new ArrayList<IRExprOverrider>();
		memoryKills = new ArrayList<IRExprOverrider>();
		exprSubTrees = new SubTreeListMaker();
		in = new HashSet<IRExprOverrider>();
		out = new HashSet<IRExprOverrider>();
		killEverything = false;
		computeKills();
		computeExprs();
	}
	
	private void computeKills() {
		if (underlyingIRStmt instanceof IRCJump) {
			// No kills in an IRCJump since nothing is clobbered.
			return;
		}
		else if (underlyingIRStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingIRStmt;
			assert(expView.expr() instanceof IRCall);
			killEverything = true;
			return;
		}
		else if (underlyingIRStmt instanceof IRJump) {
			// No kills in an IRJump since nothing is clobbered.
			return;
		}
		else if (underlyingIRStmt instanceof IRLabel) {
			// Nothing should happen in labels
			return;
		}
		else if (underlyingIRStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingIRStmt;
			if (moveView.target() instanceof IRTemp) {
				registerKills.add(new IRExprOverrider(moveView.target()));
			}
			else if (moveView.target() instanceof IRMem) {
				killEverything = true;
			}
			else {
				// Should never get here. Move should not move something into
				// anything other than a register or memory location.
				assert(false);
			}
			if (moveView.expr() instanceof IRCall) {
				killEverything = true;
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRReturn) {
			// No kills in IRReturn since nothing is clobbered.
			return;
		}
		// Should never get here since IRSeq should not be part of the CFG.
		assert(false);
	}
	
	private void computeExprs() {
		if (underlyingIRStmt instanceof IRCJump) {
			IRCJump cjumpView = (IRCJump) underlyingIRStmt;
			exprSubTrees.add(cjumpView.expr());
			return;
		}
		else if (underlyingIRStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingIRStmt;
			assert(expView.expr() instanceof IRCall);
			IRCall callViewOfChild = (IRCall) expView.expr();
			for (int i = 0; i < callViewOfChild.args().size(); i++) {
				exprSubTrees.add(callViewOfChild.args().get(i));
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRJump) {
			// Do nothing since the child should be just a name.
			return;
		}
		else if (underlyingIRStmt instanceof IRLabel) {
			// Label doesn't compute any expressions.
			return;
		}
		else if (underlyingIRStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingIRStmt;
			if (moveView.expr() instanceof IRCall) {
				IRCall callViewOfChild = (IRCall) moveView.expr();
				for (int i = 0; i < callViewOfChild.args().size(); i++) {
					exprSubTrees.add(callViewOfChild.args().get(i));
				}
			}
			else {
				exprSubTrees.add(moveView.expr());
			}
			if (moveView.target() instanceof IRMem) {
				exprSubTrees.add(moveView.target());
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRReturn) {
			// Should do nothing since something computed in return will never be used
			// anywhere else in the same scope
			return;
		}
		// Should never get here
		assert(false);
		
	}
	
	public void initializeOut() {
		if (!killEverything) {
			in.removeAll(registerKills);
		}
	}
	
	public Set<IRExprOverrider> getIn() {
		return in;
	}
	
	public void setIn(Set<IRExprOverrider> argIn) {
		in = argIn;
	}
	
	public Set<IRExprOverrider> getOut() {
		return out;
	}
	
	public void setOut(Set<IRExprOverrider> argOut) {
		out = argOut;
	}
	
	public List<IRExprOverrider> getAllExpr() {
		return exprSubTrees.getList();
	}
	
	public boolean getAllKill() {
		return killEverything;
	}
	
	public List<IRExprOverrider> getKills() {
		return registerKills;
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
