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
	
	private Set<String> allVars;
	
	/** Maps CFGNode to its set of used variable names */
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	private Map<CFGNode, String> node2def;
	
	/** Maps variable to its use sites */
	private Map<String, Set<CFGNode>> var2use;
	
	/** Maps variable to its def site */
	private Map<String, CFGNode> var2def;
	
	public SSAFormGraph(ControlFlowGraph cfg, Set<String> allVars,
			Map<CFGNode, Set<String>> node2use, Map<CFGNode, String> node2def,
			Map<String, Set<CFGNode>> var2use, Map<String, CFGNode> var2def) {
		this.cfg = cfg;
		this.head = cfg.getHead();
		this.allVars = allVars;
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
		return allVars;
	}
	
	public Set<CFGNode> getAllNodes() {
		return cfg.getAllNodes();
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
	 * Removes node from the graph
	 * @param node with less than or equal to one successor
	 */
	public void remove(CFGNode node) {
		if (node == head) {
			head = node.successor1;
		}
		cfg.remove(node);

		// update tree information
		Set<CFGNode> children = node.children;	// at most one child
		assert(children.size() <= 1);
		CFGNode idom = node.idom;
		
		if (idom != null) {
			idom.children = children;
		}
		for (CFGNode child : children) {
			child.idom = idom;
		}	
		
		// update maps
		Set<String> use = node2use.get(node);
		String def = node2def.get(node);
		node2use.remove(node);
		node2def.remove(def);
		
		for (String var : use) {
			Set<CFGNode> usesites = var2use.get(var);
			usesites.remove(node);
			allVars.remove(var);
			var2use.put(var, usesites);
		}
		if (def != null) {
			allVars.remove(def);
			var2def.put(def, null);
		}
	}
	
	/**
	 * Replace every usage of var with replaceVar
	 * @param var
	 * @param replaceVar
	 */
	public void substitute(String var, String replaceVar) {
		Set<CFGNode> usesites = var2use.get(var);
		Set<CFGNode> newUsesites = new HashSet<CFGNode>();
		for (CFGNode node : usesites) {
			// replace the usage in IRStmt
			((IRCFGNode)node).replaceUsage(var, replaceVar);
			newUsesites.add(node);
		}
		
		
		// update var2use map
		var2use.remove(var); 
		
		if (var2use.containsKey(replaceVar)) {
			newUsesites.addAll(var2use.get(replaceVar));
		}
		var2use.put(replaceVar, usesites);
		
		// update node2use map
		for (CFGNode node : newUsesites) {
			Set<String> use = node2use.get(node);
			use.remove(var);
			use.add(replaceVar);
		}
	}
	
	/**
	 * Removes predecessor-less node
	 * @param node
	 */
	public void removeUnreachableNodes(CFGNode node) {
		// break the link with its predecessors
		for (CFGNode succ : node.getSuccessors()) {
			succ.predecessors.remove(node);
		}
		node.successor1 = null;
		node.successor2 = null;
		// now we can delete this successor-less node
		remove(node);
	}
	
	/**
	 * Removes the given node from the graph.
	 * This function does not modify the maps. 
	 * Updating the maps is handled by the caller.
	 * @param node with less than or equal to one successor
	 */
	public void removeDefNode(CFGNode node) {
		if (node == head) {
			head = node.successor1;
		}
		cfg.remove(node);

		// update tree information
		Set<CFGNode> children = node.children;	// at most one child
		assert(children.size() <= 1);
		CFGNode idom = node.idom;
		
		if (idom != null) {
			idom.children = children;
		}
		for (CFGNode child : children) {
			child.idom = idom;
		}
		
		// update maps
	}
	
	/**
	 * Very dumb print method
	 */
	@Override
	public void print() {
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
					stack.push(succ);
//					if (!set.contains(succ)) {
//						stack.push(succ);
//						set.add(succ);
//					}
				}
			}
		} else if (head == null) {
			System.out.println("Head is null");
		} else {
			// TODO: implement print for AACFGNode
		}
	}
}
