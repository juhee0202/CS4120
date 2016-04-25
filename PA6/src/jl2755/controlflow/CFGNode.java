package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CFGNode {
	/** 
	 * The first successor of this CFGNode,
	 * depending on the implementation.
	 */
	protected CFGNode successor1;
	/**
	 * The second successor (if applicable)
	 * of this CFGNode, depending on the implementation.
	 */
	protected CFGNode successor2;
	/**
	 * The list of predecessors of this CFGNode,
	 * depending on the implementation.
	 */
	protected Set<CFGNode> predecessors;

	public CFGNode() {
		predecessors = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode) {
		successor1 = argNode;
		proposeToSuccessor(successor1);
		predecessors = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode1, CFGNode argNode2) {
		successor1 = argNode1;
		successor2 = argNode2;
		proposeToSuccessor(successor1);
		proposeToSuccessor(successor2);
		predecessors = new HashSet<CFGNode>();
	}
	
	protected void proposeToSuccessor(CFGNode argNode) {
		argNode.predecessors.add(this);
	}

	public CFGNode getSuccessor1() {
		return successor1;
	}

	public CFGNode getSuccessor2() {
		return successor2;
	}

	public Set<CFGNode> getPredecessors() {
		return predecessors;
	}
	
	public abstract String dotOutput();
}
