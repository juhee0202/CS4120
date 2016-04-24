package jl2755.controlflow;

import java.util.Set;

public class ControlFlowGraph {
	
	private Set<CFGNode> allNodes;
	private CFGNode head;
	
	public ControlFlowGraph(Set<CFGNode> allNodes, CFGNode head) {
		this.allNodes = allNodes;
		this.setHead(head);
	}
	
	public Set<CFGNode> getAllNodes() {
		return allNodes;
	}

	public CFGNode getHead() {
		return head;
	}

	public void setHead(CFGNode head) {
		this.head = head;
	}
}
