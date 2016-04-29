package jl2755.controlflow;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SSAFormGraph implements OptimizationGraph {
	/** Underlying ControlFlowGraph */
	private ControlFlowGraph cfg;
	
	/** Maps CFGNode to its set of used variable names */
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	private Map<CFGNode, String> node2def;
	
	/** Maps variable to its use sites */
	private Map<String, Set<CFGNode>> var2use;
	
	/** Maps variable to its def site */
	private Map<String, CFGNode> var2def;
	
	/** Set of all variables */
	private Set<String> allVars;
	
	public SSAFormGraph(ControlFlowGraph cfg, 
			Map<CFGNode, Set<String>> node2use, Map<CFGNode, String> node2def,
			Map<String, Set<CFGNode>> var2use, Map<String, CFGNode> var2def) {
		this.cfg = cfg;
		this.node2use = node2use;
		this.node2def = node2def;
		this.var2use = var2use;
		this.var2def = var2def;
		
		// compute all variables
		allVars = new HashSet<String>();
		allVars.addAll(var2use.keySet());
		allVars.addAll(var2def.keySet());
	}
	
	public ControlFlowGraph getCfg() {
		return cfg;
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
		} else {
			// TODO: implement print for AACFGNode
		}
	}
}
