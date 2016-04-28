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
	
	/**
	 * Immediate dominator of this CFGNode
	 * (aka immediate parent in Dominator Tree)
	 */
	protected CFGNode idom;
	
	/**
	 * Set of children nodes in Dominator Tree
	 */
	protected Set<CFGNode> children;

	public CFGNode() {
		predecessors = new HashSet<CFGNode>();
		children = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode) {
		successor1 = argNode;
		proposeToSuccessor(successor1);
		predecessors = new HashSet<CFGNode>();
		children = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode1, CFGNode argNode2) {
		successor1 = argNode1;
		successor2 = argNode2;
		proposeToSuccessor(successor1);
		proposeToSuccessor(successor2);
		predecessors = new HashSet<CFGNode>();
		children = new HashSet<CFGNode>();
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
	
	public Set<CFGNode> getSuccessors() {
		Set<CFGNode> successors = new HashSet<CFGNode>();
		if (successor1 != null) {
			successors.add(successor1);
		}
		if (successor2 != null) {
			successors.add(successor2);
		}
		return successors;
	}

	public Set<CFGNode> getPredecessors() {
		return predecessors;
	}
	
	public void addChild(CFGNode node) {
		children.add(node);
		node.idom = this;
	}
	
	public abstract String dotOutput();
}
