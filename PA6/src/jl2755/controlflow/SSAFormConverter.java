package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	/** Maps a variable to its use site */
	private Map<String, Set<CFGNode>> var2use;
	
	/** Maps a variable to its def site */
	private Map<String, CFGNode> var2def;
	
	/** Set of all variable names in cfg */
	private Set<String> allVars;
	
	/** Maps used in rename function */
	private Map<String, Integer> var2count;
	private Map<String, Stack<Integer>> var2stack;
	
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
		// 3) rename all defs & uses of vars using subscripts
		renameVariables();
		// 4) compute var2use & var2def maps
		computeVarMaps();
		// 5) construct SSAFormGraph
		SSAFormGraph ssaGraph = new SSAFormGraph(cfg, node2use, node2def, var2use, var2def);
		
		return ssaGraph;
	}

	private void computeVarMaps() {
		var2use = new HashMap<String, Set<CFGNode>>();
		var2def = new HashMap<String, CFGNode>();
		for (Entry<CFGNode, String> entry : node2def.entrySet()) {
			var2def.put(entry.getValue(), entry.getKey());
		}
		
		for (Entry<CFGNode, Set<String>> entry : node2use.entrySet()) {
			CFGNode node = entry.getKey();
			Set<String> use = entry.getValue();
			for (String var : use) {
				Set<CFGNode> usesites;
				if (var2use.containsKey(var)) {
					usesites = var2use.get(var);
				} else {
					usesites = new HashSet<CFGNode>();
				}
				usesites.add(node);
				var2use.put(var, usesites);
			}
		}
	}

	/**
	 * Renames variables by walking down the dominator tree
	 */
	private void renameVariables() {
		/* Initialize */
		var2count = new HashMap<String, Integer>();
		var2stack = new HashMap<String, Stack<Integer>>();
		for (String var : allVars) {
			var2count.put(var, 0);
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(0);
			var2stack.put(var, stack);
		}
		
		/* Rename */
		rename(dominatorTree.getRoot());
	}

	/**
	 * Renames variables in node
	 * @param node
	 */
	private void rename(CFGNode node) {
		System.out.println(node);
		
		IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
		
		if (stmt instanceof IRReturn) {
			return;
		}
		
		// rename each variable in use[node] 
		if (!(stmt instanceof IRPhiFunction)) {
			Set<String> uses = node2use.get(node);
			Set<String> newUses = new HashSet<String>();
			for (String s : uses) {
				Stack<Integer> stack = var2stack.get(s);
				int i = stack.peek();
				newUses.add(s + i);
				replaceUsage(node, s, i);
			}
			node2use.put(node, newUses);
		}
		
		// rename def 
		// update the count and stack of def[node]
		String def = node2def.get(node);
		if (def != null) {
			int count = var2count.get(def) + 1;
			var2count.put(def, count);
			Stack<Integer> stack = var2stack.get(def);
			stack.push(count);
			var2stack.put(def,stack);
			replaceDefinition(node, def, count);
			node2def.put(node, def + count);
		}
		
		// update successor's phi-function if applicable
		for (CFGNode succ : node.getSuccessors()) {
			IRStmt succStmt = ((IRCFGNode)succ).underlyingIRStmt;
			if (succStmt instanceof IRPhiFunction) {
				int i = succ.predecessors.indexOf(node);
				String a = ((IRPhiFunction)succStmt).getOriginalVar();
				
				int a_count = var2stack.get(a).peek();
				((IRPhiFunction)succStmt).setOperand(i, a + a_count);
				Set<String> use = node2use.get(succ);
				use.remove(a);
				use.add(a + a_count);
				node2use.put(succ, use);
			}
		}
		
		// rename children
		for (CFGNode child : node.children) {
			rename(child);
		}
		
		if (def != null) {
			Stack<Integer> stack = var2stack.get(def);
			stack.pop();
			var2stack.put(def, stack);
		}
	}

	/**
	 * Replaces the usage of var to var_i
	 * @param node
	 * @param s
	 * @param i
	 */
	private void replaceUsage(CFGNode node, String var, int i) {
		IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
		if (stmt instanceof IRCJump) {
			replaceUsage(((IRCJump)stmt).expr(), var, i);
		} else if (stmt instanceof IRExp) {
			replaceUsage(((IRExp)stmt).expr(), var, i);
		} else if (stmt instanceof IRJump) {
			replaceUsage(((IRJump)stmt).target(), var, i);
		} else if (stmt instanceof IRMove) {
			replaceUsage(((IRMove)stmt).expr(), var, i);
		}
	}

	/**
	 * Replaces var to var_i in expr
	 * @param expr
	 * @param var
	 * @param i
	 */
	private void replaceUsage(IRExpr expr, String var, int i) {
		if (expr instanceof IRBinOp) {
			replaceUsage(((IRBinOp)expr).left(), var, i);
			replaceUsage(((IRBinOp)expr).right(), var, i);
		} else if (expr instanceof IRCall) {
			replaceUsage(((IRCall)expr).target(), var, i);			
			List<IRExpr> args = ((IRCall)expr).args();
			for (IRExpr arg : args) {
				replaceUsage(arg, var, i);
			}
			((IRCall)expr).setArgs(args);
		} else if (expr instanceof IRMem) {
			replaceUsage(((IRMem)expr).expr(), var, i);
		} else if (expr instanceof IRTemp) {
			if (((IRTemp)expr).name().equals(var)) {
				((IRTemp)expr).setName(var + i);
			}
		}
	}

	/**
	 * Replaces the definition of var to var_i
	 * @param node
	 * @param var
	 * @param i
	 */
	private void replaceDefinition(CFGNode node, String var, int i) {
		IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
		if (stmt instanceof IRMove) {
			IRMove move = (IRMove)stmt;
			IRTemp newTemp = new IRTemp(var + i);
			IRMove newMove = new IRMove(newTemp, move.expr());
			((IRCFGNode)node).underlyingIRStmt = newMove;
		} else if (stmt instanceof IRPhiFunction) {
			((IRPhiFunction)stmt).setVar(var + i);
			((IRCFGNode)node).underlyingIRStmt = stmt;
		} else { 
			assert(stmt instanceof IRMove);
			// shouldn't get here
		}
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
						y.predecessors = new ArrayList<CFGNode>();
						y.predecessors.add(newNode);
						
						// update idom's links
						CFGNode idom = y.idom;
						idom.children.remove(y);
						idom.children.add(newNode);
						newNode.idom = idom;
						newNode.children.add(y);
						y.idom = newNode;
						
						// update node2use & node2def maps
						Set<String> use = new HashSet<String>();
						use.add(var);
						node2use.put(newNode, use);
						node2def.put(newNode, var);
						
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
		 * Compute union of DF_up[c] for all child c
		 * DF_up[c] = Nodes in the dominance frontier of c that are not 
		 * 			  strictly dominated by c's immediate dominator.
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
