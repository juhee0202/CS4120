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
	protected List<CFGNode> predecessors;
	
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
		predecessors = new ArrayList<CFGNode>();
		children = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode) {
		successor1 = argNode;
		proposeToSuccessor(successor1);
		predecessors = new ArrayList<CFGNode>();
		children = new HashSet<CFGNode>();
	}
	
	public CFGNode(CFGNode argNode1, CFGNode argNode2) {
		successor1 = argNode1;
		successor2 = argNode2;
		proposeToSuccessor(successor1);
		proposeToSuccessor(successor2);
		predecessors = new ArrayList<CFGNode>();
		children = new HashSet<CFGNode>();
	}
	
	protected void proposeToSuccessor(CFGNode argNode) {
		if (argNode.predecessors != null) {
			argNode.predecessors.add(this);
		}
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

	public List<CFGNode> getPredecessors() {
		return predecessors;
	}
	
	public boolean hasPredecessor() {
		return predecessors.size() > 0;
	}
	
	public boolean hasSuccessor() {
		return successor1 != null || successor2 != null;
	}
	
	public void addChild(CFGNode node) {
		children.add(node);
		node.idom = this;
	}
	
	public CFGNode getIdom() {
		return idom;
	}

	public void setIdom(CFGNode idom) {
		this.idom = idom;
	}

	public Set<CFGNode> getChildren() {
		return children;
	}

	public void setChildren(Set<CFGNode> children) {
		this.children = children;
	}

	public void setSuccessor1(CFGNode successor1) {
		this.successor1 = successor1;
	}

	public void setSuccessor2(CFGNode successor2) {
		this.successor2 = successor2;
	}

	public void setPredecessors(List<CFGNode> predecessors) {
		this.predecessors = predecessors;
	}

	public abstract String dotOutput();
}
