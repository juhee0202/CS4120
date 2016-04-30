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
	
	/** SSA form of the given cfg */
	private SSAFormGraph ssaGraph;
	
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
		
		this.ssaGraph = ssaGraph;
		return ssaGraph;
	}
	
	/**
	 * Revert to ControlFlowGraph form
	 * For each phi-function node:
	 * 	for each operand:
	 * 		create an assignment stmt node 
	 * @return
	 */
	public ControlFlowGraph convertBack() {
		/* Find all IRPhiFunction nodes */
		Set<CFGNode> phiNodes = new HashSet<CFGNode>();
		Set<CFGNode> allNodes = ssaGraph.getAllNodes();
		for (CFGNode node : allNodes) {
			IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
			if (stmt instanceof IRPhiFunction) {
				phiNodes.add(node);
			}
		}
		
		/* Split each phi-function node */
		for (CFGNode phiNode : phiNodes) {
			List<CFGNode> predecessors = ((IRCFGNode)phiNode).predecessors;
			CFGNode successor = ((IRCFGNode)phiNode).successor1;
			
			IRPhiFunction phiStmt = (IRPhiFunction) ((IRCFGNode)phiNode).underlyingIRStmt;
			String[] operands = phiStmt.getOperands();
			IRTemp target = new IRTemp(phiStmt.getVar());
			List<CFGNode> newPredecessors = new ArrayList<CFGNode>();
			for (int i = 0; i < predecessors.size(); i++) {
				CFGNode originalPred = predecessors.get(i);
				IRTemp expr = new IRTemp(operands[i]);
				IRMove move = new IRMove(target, expr);
				CFGNode newPred = new IRCFGNode(move);
				
				// update links
				if (originalPred.successor1 == phiNode) {
					originalPred.successor1 = newPred;
				} else if (originalPred.successor2 == phiNode) {
					originalPred.successor2 = newPred;
				}
				newPred.predecessors.add(predecessors.get(i));
				((IRCFGNode)newPred).addSuccessor((IRCFGNode)successor);
				
				newPredecessors.add(newPred);
			}
		}
		
		return cfg;
	}

	private void computeVarMaps() {
		var2use = new HashMap<String, Set<CFGNode>>();
		var2def = new HashMap<String, CFGNode>();
		for (Entry<CFGNode, String> entry : node2def.entrySet()) {
			if (entry.getValue() != null) {
				var2def.put(entry.getValue(), entry.getKey());
			}	
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
			int x = 5;
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
				((IRCFGNode) node).replaceUsage(s, s+i);
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
			((IRCFGNode) node).replaceDefinition(def, def+count);
			node2def.put(node, def + count);
		}
		
		// update successor's phi-function if applicable
		// renamed is only for IRPhiFunction
		if (!((IRCFGNode)node).renamed) {
			for (CFGNode succ : node.getSuccessors()) {
				IRStmt succStmt = ((IRCFGNode)succ).underlyingIRStmt;
				if (succStmt instanceof IRLabel) {
					succ = succ.successor1;
					succStmt = ((IRCFGNode)succ).underlyingIRStmt;
				}
				if (succStmt instanceof IRPhiFunction) {
					((IRCFGNode)succ).renamed = true;
					int i = ((IRCFGNode)succ).realPredecessors.indexOf(node);
					String a = ((IRPhiFunction)succStmt).getOriginalVar();
					
					int a_count = var2stack.get(a).peek();
					((IRPhiFunction)succStmt).setOperand(i, a + a_count);
					Set<String> use = node2use.get(succ);
					use.remove(a);
					use.add(a + a_count);
					node2use.put(succ, use);
					
					// update successor's successor's phi-functions if applicable
					// this happens when more than one var requires a phi-function
					CFGNode succsucc = succ.successor1;
					IRStmt succsuccStmt = ((IRCFGNode)succsucc).underlyingIRStmt;
					while(succsuccStmt instanceof IRPhiFunction) {
						((IRCFGNode)succsucc).renamed = true;
						a = ((IRPhiFunction)succsuccStmt).getOriginalVar();
						a_count = var2stack.get(a).peek();
						((IRPhiFunction)succsuccStmt).setOperand(i, a + a_count);
						use = node2use.get(succsucc);
						use.remove(a);
						use.add(a + a_count);
						node2use.put(succsucc, use);	
						succsucc = succsucc.successor1;
						succsuccStmt = ((IRCFGNode)succsucc).underlyingIRStmt;
					}
				}
			}
		}

		
		// rename children
		for (CFGNode child : node.children) {
			IRStmt childStmt = ((IRCFGNode)child).underlyingIRStmt;
			if (childStmt instanceof IRLabel) {
				child = child.successor1;
				((IRCFGNode)child).realPredecessor = node;
			}
			rename(child);
		}
		
		if (def != null) {
			Stack<Integer> stack = var2stack.get(def);
			stack.pop();
			var2stack.put(def, stack);
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
						// insert it right after y where y is a label
						IRStmt yStmt = ((IRCFGNode)y).underlyingIRStmt;
						assert(yStmt instanceof IRLabel);
						List<CFGNode> predecessors = y.predecessors;
						int numPhiOperand = predecessors.size();
						IRPhiFunction phiFunction = new IRPhiFunction(var, numPhiOperand);
						IRCFGNode newNode = new IRCFGNode(phiFunction);
						newNode.realPredecessors = predecessors;
						
						// update successor's link
						CFGNode succ = y.successor1;
						y.successor1 = newNode;
						newNode.predecessors.add(y);
						newNode.successor1 = succ;
						succ.predecessors.remove(y);
						succ.predecessors.add(newNode);
						
						// update idom links
						Set<CFGNode> children = y.children;
						y.children = new HashSet<CFGNode>();
						y.children.add(newNode);
						newNode.children = children;
						newNode.idom = y;
						for (CFGNode child : children) {
							child.idom = newNode;
						}
						
						// update node2use & node2def maps
						Set<String> use = new HashSet<String>();
						use.add(var);
						node2use.put(newNode, use);
						node2def.put(newNode, var);
						
						// update phisites map
						sites.add(newNode);
						phisites.put(var, sites);
						
						if (node2def.get(y) != null && !node2def.get(y).equals(var)) {
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
