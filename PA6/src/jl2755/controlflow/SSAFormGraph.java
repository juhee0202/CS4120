package jl2755.controlflow;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class SSAFormGraph implements OptimizationGraph {
	/** Underlying ControlFlowGraph */
	private ControlFlowGraph cfg;
	
	/** Start node */
	private CFGNode head;
	
	/** Maps CFGNode to its set of used variable names */
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	private Map<CFGNode, String> node2def;
	
	/** Maps variable to its use sites */
	private Map<String, Set<CFGNode>> var2use;
	
	/** Maps variable to its def site */
	private Map<String, CFGNode> var2def;
	
	public SSAFormGraph(ControlFlowGraph cfg, 
			Map<CFGNode, Set<String>> node2use, Map<CFGNode, String> node2def,
			Map<String, Set<CFGNode>> var2use, Map<String, CFGNode> var2def) {
		this.cfg = cfg;
		this.head = cfg.getHead();
		this.node2use = node2use;
		this.node2def = node2def;
		this.var2use = var2use;
		this.var2def = var2def;
	}
	
	public ControlFlowGraph getCfg() {
		return cfg;
	}
	
	public CFGNode getHead() {
		return head;
	}

	public Set<String> getAllVariables() {
		Set<String >allVars = new HashSet<String>();
		allVars.addAll(var2use.keySet());
		allVars.addAll(var2def.keySet());
		allVars.remove(null);
		return allVars;
	}
	
	public Set<CFGNode> getAllNodes() {
		Set<CFGNode> allNodes = new HashSet<CFGNode>();
		allNodes.addAll(node2use.keySet());
		allNodes.addAll(node2def.keySet());
		return allNodes;
	}
	
	public Map<CFGNode, Set<String>> getNodeToUseMap() {
		return node2use;
	}
	
	public Map<CFGNode, String> getNodeToDefMap() {
		return node2def;
	}

	/** 
	 * @return
	 * given a variable name, this map returns
	 *  a list of CFG nodes (stmts) that use that variable 
	 */
	public Map<String, Set<CFGNode>> getVarToUseMap() {
		return var2use;
	}
	
	/**
	 * @return 
	 * Given a variable name, this map returns
	 * the CGNode (assignment stmt) that defines this variable
	 */
	public Map<String, CFGNode> getVarToDefMap() {
		return var2def;
	}

	public void setCfg(ControlFlowGraph cfg) {
		this.cfg = cfg;
	}

	public void setNode2use(Map<CFGNode, Set<String>> node2use) {
		this.node2use = node2use;
	}

	public void setNode2def(Map<CFGNode, String> node2def) {
		this.node2def = node2def;
	}

	public void setVar2use(Map<String, Set<CFGNode>> var2use) {
		this.var2use = var2use;
	}

	public void setVar2def(Map<String, CFGNode> var2def) {
		this.var2def = var2def;
	}
	
	/**
	 * Removes predecessor-less node from the graph while properly updating the maps
	 * @param node
	 */
	public void removeUnreachableNode(CFGNode node) {
		Set<CFGNode> successors = node.getSuccessors();
		for (CFGNode succ : successors) {
			succ.predecessors.remove(node);
		}
		
		Set<CFGNode> children = node.children;
		for (CFGNode child : children) {
			child.idom = null;
		}
		
		Set<String> use = node2use.get(node);
		String def = node2def.get(node);
		
		for (String var : use) {
			Set<CFGNode> usesites = var2use.get(var);
			usesites.remove(node);
			var2use.put(var, usesites);
		}
		if (def != null) {
			var2def.remove(def);
		}
		
		node2use.remove(node);
		node2def.remove(node);
	}
	
	/**
	 * Removes the given node from the graph.
	 * This function does not modify the maps. 
	 * Updating the maps is handled by the caller.
	 * @param node with 1> successor
	 */
	public void removeDefNode(CFGNode node) {
		List<CFGNode> predecessors = node.predecessors;
		CFGNode successor = node.successor1;
		
		if (node == head) {
			head = node.successor1;
		}
		
		// link predecessors to successor
		for (CFGNode pred : predecessors) {
			if (pred.successor1 == node) {
				pred.successor1 = successor;
			} else if (pred.successor2 == node) {
				pred.successor2 = successor;
			}
		}
		successor.predecessors = predecessors;
		
		Set<CFGNode> children = node.children;	// at most one child
		assert(children.size() <= 1);
		CFGNode idom = node.idom;
		
		// link idom to children
		if (idom != null) {
			idom.children = children;
			for (CFGNode child : children) {
				child.idom = idom;
			}	
		}
	}
	
	/**
	 * Very dumb print method
	 */
	@Override
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
		} else if (head == null) {
			System.out.println("Head is null");
		} else {
			// TODO: implement print for AACFGNode
		}
	}
}
