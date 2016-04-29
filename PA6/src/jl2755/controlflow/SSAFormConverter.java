package jl2755.controlflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.cornell.cs.cs4120.xic.ir.*;

public class SSAFormConverter {
	/** A ControlFlowGraph from which we are converting */
	private ControlFlowGraph cfg;
	
	/** Dominator tree of cfg */
	private DominatorTree dominatorTree;
	
	/** Dominance Frontier map */
	private Map<CFGNode, Set<CFGNode>> dominanceFrontier;
	
	/** Dominance Relation map */
	private Map<CFGNode, Set<CFGNode>> dominanceMap;
	
	/** Maps CFGNode to its set of used variable names */
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	private Map<CFGNode, String> node2def;
	
	/** Set of all variable names in cfg */
	private Set<String> allVars;
	
	public SSAFormConverter(ControlFlowGraph argCfg) {
		cfg = argCfg;
		dominatorTree = new DominatorTree(cfg);
		dominanceFrontier = new HashMap<CFGNode, Set<CFGNode>>();
		dominanceMap = dominatorTree.dominanceMap;
		allVars = new HashSet<String>();
		initializeUseAndDefMaps();
	}
	
	/**
	 * Initialize node2use & node2def maps and allVariables 
	 */
	private void initializeUseAndDefMaps() {
		node2use = new HashMap<CFGNode, Set<String>>();
		node2def = new HashMap<CFGNode, String>();
		
		Set<CFGNode> visitedNodes = new HashSet<CFGNode>();
		Stack<CFGNode> stack = new Stack<CFGNode>();
		stack.add(cfg.getHead());
		while (!stack.isEmpty()) {
			CFGNode node = stack.pop();
			if (!visitedNodes.contains(node)) {
				Set<String> use = computeUse(node);
				String def = computeDef(node);
				node2use.put(node, use);
				node2def.put(node, def);
				allVars.addAll(use);
				if (def != null) {
					allVars.add(def);
				}
				Set<CFGNode> successors = node.getSuccessors();
				stack.addAll(successors);
				visitedNodes.add(node);
			}
		}
	}

	public SSAFormGraph convertToSSAForm() {
		// 1) compute Dominance Frontier
		computeDominanceFrontier();
		// 2) add phi-functions for vars
		insertPhiFunctions();
		// 3) rename all defs&uses of vars using subscripts
		renameVariables();
		// 4) construct SSAFormGraph
		SSAFormGraph ssaGraph = new SSAFormGraph(cfg, node2use, node2def);
		
		return ssaGraph;
	}

	/**
	 * Renames variables by walking down the dominator tree
	 */
	private void renameVariables() {
		/* Initialize */
		Map<String, Integer> var2count = new HashMap<String, Integer>();
		Map<String, Stack<Integer>> var2stack = new HashMap<String, Stack<Integer>>();
		for (String var : allVars) {
			var2count.put(var, 0);
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(0);
			var2stack.put(var, stack);
		}
		
		/* Rename */
		rename(dominatorTree.getRoot(), var2count, var2stack);
	}

	/**
	 * Renames variables in node
	 * @param node
	 */
	private void rename(CFGNode node, Map<String, Integer> var2count, Map<String, Stack<Integer>> var2stack) {
		IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
		
		// rename each variable in use[node] 
		if (!(stmt instanceof IRPhiFunction)) {
			Set<String> uses = node2use.get(node);
			Set<String> newUses = new HashSet<String>();
			for (String s : uses) {
				Stack<Integer> stack = var2stack.get(s);
				int i = stack.peek();
				newUses.add(s + i);
			}
			node2use.put(node, newUses);
		}
		
		// update the count and stack of def[node]
		String def = node2def.get(node);
		if (def != null) {
			int count = var2count.get(def);
			var2count.put(def, count+1);
			Stack<Integer> stack = var2stack.get(def);
			stack.push(count+1);
			var2stack.put(def,stack);			
		}
		
		// update successor's phi-function if applicable
		for (CFGNode succ : node.getSuccessors()) {
			IRStmt succStmt = ((IRCFGNode)succ).underlyingIRStmt;
			if (succStmt instanceof IRPhiFunction) {
				int i = succ.predecessors.indexOf(node);
				String a = ((IRPhiFunction)succStmt).getVar();
				int a_count = var2stack.get(a).peek();
				((IRPhiFunction)succStmt).setOperand(i, a + a_count);
			}
		}
		
		// rename children
		for (CFGNode child : node.children) {
			rename(child, var2count, var2stack);
		}
		
		var2stack.remove(def);
	}

	/**
	 * Inserts phi-function by following the Dominance Frontier criterion
	 */
	private void insertPhiFunctions() {
		/* Initialize */	
		Map<String, Set<CFGNode>> defsites = new HashMap<String, Set<CFGNode>>();
		
		// initialize defsites map
		for (CFGNode node : cfg.getAllNodes()) {
			String def = node2def.get(node);
			if (def != null) {
				Set<CFGNode> sites;
				if (defsites.containsKey(def)) {
					sites = defsites.get(def);
				} else {
					sites = new HashSet<CFGNode>();
				}
				sites.add(node);
				defsites.put(def, sites);
			}
		}
		
		// phisites[var] = set of nodes that must have phi-functions for var 
		Map<String, Set<CFGNode>> phisites = new HashMap<String, Set<CFGNode>>();
		for (String var : allVars) {
			phisites.put(var, new HashSet<CFGNode>());
		}
		
		/* Insert */
		for (String var : allVars) {
			Stack<CFGNode> W = new Stack<CFGNode>();
			W.addAll(defsites.get(var));
			while (!W.isEmpty()) {
				CFGNode node = W.pop();
				for (CFGNode y : dominanceFrontier.get(node)) {
					Set<CFGNode> sites = phisites.get(var);
					if (!sites.contains(y)) {
						// create a new IRPhiFunction stmt
						// insert it right before y
						List<CFGNode> predecessors = y.predecessors;
						IRPhiFunction phiFunction = new IRPhiFunction(var, predecessors.size());
						CFGNode newNode = new IRCFGNode(phiFunction);
						
						// update predecessors' links
						for (CFGNode pred : predecessors) {
							if (pred.successor1 == y) {
								pred.successor1 = newNode;
							} else {
								pred.successor2 = newNode;
							}
						}
						newNode.predecessors = predecessors;
						newNode.successor1 = y;
						
						// update idom's links
						CFGNode idom = y.idom;
						idom.children.remove(y);
						idom.children.add(newNode);
						newNode.idom = idom;
						newNode.children.add(y);
						y.idom = newNode;
						
						// update phisites map
						sites.add(newNode);
						phisites.put(var, sites);
						
						if (!node2def.get(y).contains(var)) {
							W.push(y);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Computes the dominance frontier (DF) map
	 * DF[v] = set of node w's such that
	 * 	1) v dominates some predecessor of w
	 * 	2) v does not strictly dominate w
	 * 		- v strictly dominates w if v dominates w and v != w
	 */
	private void computeDominanceFrontier() {
		CFGNode root = cfg.getHead();
		computeDominanceFrontier(root);
	}

	private void computeDominanceFrontier(CFGNode node) {
		Set<CFGNode> set = new HashSet<CFGNode>();
		/* 
		 * Compute DF_local[n]
		 * DF_local[n] = The successors of n that are not strictly dominated by n 
		 */
		for (CFGNode succ : node.getSuccessors()) {
			if (succ.idom != node) {
				set.add(succ);
			}
		}
		
		/*
		 * Compute DF_up[n]
		 * DF_up[n] = Nodes in the dominance frontier of n that are not 
		 * 			  strictly dominated by n's immediate dominator.
		 */
		for (CFGNode child : node.children) {
			computeDominanceFrontier(child);
			for (CFGNode w : dominanceFrontier.get(child)) {
				if (!(dominanceMap.get(w).contains(node)) || node == w) {
					set.add(w);
				}
			}
		}
		
		dominanceFrontier.put(node, set);
	}
	
	private Set<String> computeUse(CFGNode node) {
		Set<String> uses = new HashSet<String>();
		
		// only interested in {IRCJump, IRExp, IRJump, IRMove}
		if (node instanceof IRCFGNode) {
			IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
			
			if (stmt instanceof IRCJump) {
				uses.addAll(computeUse(((IRCJump)stmt).expr()));
			} else if (stmt instanceof IRExp) {
				uses.addAll(computeUse(((IRExp)stmt).expr()));
			} else if (stmt instanceof IRJump) {
				uses.addAll(computeUse(((IRJump)stmt).target()));
			} else if (stmt instanceof IRMove) {
				uses.addAll(computeUse(((IRMove)stmt).expr()));
			}
			
		} else if (node instanceof AACFGNode) {
			// don't think we will ever convert AACFGNode graph to SSA form
		}
		
		return uses;
	}
	
	/**
	 * Compute use[expr]
	 * @param expr
	 * @return Set of variable(name) used for expr
	 */
	private Set<String> computeUse(IRExpr expr) {
		Set<String> uses = new HashSet<String>();
		
		// only interested in {IRBinOp, IRCall, IRMem, IRTemp}
		if (expr instanceof IRBinOp) {
			uses.addAll(computeUse(((IRBinOp)expr).left()));
			uses.addAll(computeUse(((IRBinOp)expr).right()));
		} else if (expr instanceof IRCall) {
			uses.addAll(computeUse(((IRCall)expr).target()));
			List<IRExpr> args = ((IRCall)expr).args();
			for (IRExpr arg : args) {
				uses.addAll(computeUse(arg));
			}
		} else if (expr instanceof IRMem) {
			uses.addAll(computeUse(((IRMem)expr).expr()));
		} else if (expr instanceof IRTemp) {
			uses.add(((IRTemp)expr).name());
		}
		
		return uses;
	}
	
	private String computeDef(CFGNode node) {
		String def = null;
		
		// only interested in {IRMove}
		if (node instanceof IRCFGNode) {
			IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
			if (stmt instanceof IRMove) {
				def = computeDef(((IRMove)stmt).target());
			}
		} else if (node instanceof AACFGNode) {
			// don't think we will ever convert AACFGNode graph to SSA form
		}
		
		return def;
	}

	private String computeDef(IRExpr expr) {
		String def = null;
		
		// only interested in {IRTemp}
		if (expr instanceof IRTemp) {
			def = ((IRTemp)expr).name();
		}
		
		return def;
	}
}
