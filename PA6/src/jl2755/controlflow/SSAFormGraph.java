package jl2755.controlflow;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SSAFormGraph {
	/** Underlying ControlFlowGraph */
	private ControlFlowGraph cfg;
	
	/** Maps CFGNode to its set of used variable names */
	// node of IRPhiFunction isn't included
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	// node of IRPhiFunction isn't included
	private Map<CFGNode, String> node2def;
	
	public SSAFormGraph(ControlFlowGraph cfg, Map<CFGNode, Set<String>> node2use, Map<CFGNode, String> node2def) {
		this.cfg = cfg;
		this.node2use = node2use;
		this.node2def = node2def;
	}
	
	public ControlFlowGraph getCfg() {
		return cfg;
	}

	/**
	 * Very dumb print method
	 */
	public void print() {
		CFGNode head = cfg.getHead();
		if (head instanceof IRCFGNode) {
			Set<CFGNode> set = new HashSet<CFGNode>();
			Stack<CFGNode> stack = new Stack<CFGNode>();
			stack.add(head);
			while(!stack.isEmpty()) {
				IRCFGNode node = (IRCFGNode) stack.pop();
				System.out.println(node.underlyingIRStmt);
				Set<String> use = node2use.get(node);
				String useString = "";
				for (String s : use) {
					useString += s + " ";
				}
				System.out.println("\t use: " + useString);
				System.out.println("\t def: " + node2def.get(node));
				for (CFGNode succ : node.getSuccessors()) {
					if (!set.contains(succ)) {
						stack.push(succ);
						set.add(succ);
					}
				}
			}
		} else {
			// TODO: implement print for AACFGNode
		}
	}
}
