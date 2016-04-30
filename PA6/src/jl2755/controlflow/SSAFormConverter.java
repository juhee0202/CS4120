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
	
	/** Set of all original variable names in cfg */
	private Set<String> originalVars;
	
	/** Set of all new variable names in ssaGraph */
	private Set<String> newVars;
	
	/** Def sites of original variables */
	private Map<String, Set<CFGNode>> defsites;
	
	/** Maps used in rename function */
	private Map<String, Integer> var2count;
	private Map<String, Stack<Integer>> var2stack;
	
	public SSAFormConverter(ControlFlowGraph argCfg) {
		cfg = argCfg;
		dominatorTree = new DominatorTree(cfg);
		dominanceFrontier = new HashMap<CFGNode, Set<CFGNode>>();
		dominanceMap = dominatorTree.dominanceMap;
		originalVars = new HashSet<String>();
		newVars = new HashSet<String>();
		initializeUseAndDefMaps();
	}
	
	/**
	 * Initialize node2use & node2def maps and allVariables 
	 */
	private void initializeUseAndDefMaps() {
		node2use = new HashMap<CFGNode, Set<String>>();
		node2def = new HashMap<CFGNode, String>();
		
		Set<CFGNode> allNodes = cfg.getAllNodes();
		for (CFGNode node : allNodes) {
			Set<String> use = computeUse(node);
			String def = computeDef(node);
			node2use.put(node,  use);
			node2def.put(node, def);
			
			for (String s : use) {
				originalVars.add(s);
			}
			if (def != null) {
				originalVars.add(def);
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
		ssaGraph = new SSAFormGraph(cfg, node2use, node2def, var2use, var2def);
		
		return ssaGraph;
	}

	/**
	 * Revert to ControlFlowGraph form
	 * For each phi-function node:
	 * 	for each operand:
	 * 		create an assignment stmt node 
	 * 
	 * TODO: keep track of where to insert split phi-function node
	 * @return ControlFlowGraph
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
			CFGNode successor = ((IRCFGNode)phiNode).successor1;
			
			IRPhiFunction phiStmt = (IRPhiFunction) ((IRCFGNode)phiNode).underlyingIRStmt;
			String[] operands = phiStmt.getOperands();
			CFGNode[] insertBeforePoints = phiStmt.getInsertBeforePoints();
			IRTemp target = new IRTemp(phiStmt.getVar());
			
			for (int i = 0; i < operands.length; i++) {
				// insert before point of i-th operand's move node
				CFGNode insertBeforePoint = insertBeforePoints[i];
				
				// i-th operand's move node
				IRTemp expr = new IRTemp(operands[i]);
				IRMove move = new IRMove(target, expr);
				CFGNode operandMoveNode = new IRCFGNode(move,"");
				
				// insert the operand's move node
				cfg.insertBefore(insertBeforePoint, operandMoveNode);
			}
			
			// remove the phi function node
			cfg.remove(phiNode);
		}
		
		return cfg;
	}

	private void computeVarMaps() {
		var2use = new HashMap<String, Set<CFGNode>>();
		var2def = new HashMap<String, CFGNode>();
		
		for (String var : newVars) {
			var2use.put(var, new HashSet<CFGNode>());
			var2def.put(var, null);
		}
		
		for (Entry<CFGNode, String> entry : node2def.entrySet()) {
			if (entry.getValue() != null) {
				var2def.put(entry.getValue(), entry.getKey());
				var2use.put(entry.getValue(), new HashSet<CFGNode>());
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
		}
	}

	/**
	 * Renames variables by walking down the dominator tree
	 */
	private void renameVariables() {
		/* Initialize */
		var2count = new HashMap<String, Integer>();
		var2stack = new HashMap<String, Stack<Integer>>();
		for (String var : originalVars) {
			var2count.put(var, 0);
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(0);
			var2stack.put(var, stack);
		}
		
		/* Rename */
		rename(dominatorTree.getRoot());
		
		// may not need this
		if (newVars.size() == 0) {
			newVars = originalVars;
		}
	}

	/**
	 * Recursively renames every variables in the subtree rooted at node
	 * @param node (subtree rooted at node)
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
				String newVar = s + i;
				newVars.add(newVar);	// update newVars set
				newUses.add(newVar);
				((IRCFGNode) node).replaceUsage(s, newVar);
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
			String newVar = def + count;
			newVars.add(newVar);	// update newVars set
			((IRCFGNode) node).replaceDefinition(def, newVar);
			node2def.put(node, newVar);
		}
		
		// update successor's phi-function if applicable
		// renamed flag is used for IRPhiFunction only
		if (!((IRCFGNode)node).renamed) {
			IRStmt nodeStmt = ((IRCFGNode)node).underlyingIRStmt;
			for (CFGNode succ : node.getSuccessors()) {
				CFGNode insertBeforePoint = succ; // insert before the phi function node.
				IRStmt succStmt = ((IRCFGNode)succ).underlyingIRStmt;
				if (succStmt instanceof IRLabel) {
					if (nodeStmt instanceof IRJump || nodeStmt instanceof IRCJump) {
						// insert before the jump
						insertBeforePoint = node;
					}
					succ = succ.successor1;
					succStmt = ((IRCFGNode)succ).underlyingIRStmt;
				}
				if (succStmt instanceof IRPhiFunction) {
					((IRCFGNode)succ).renamed = true;
					int i = ((IRCFGNode)succ).realPredecessors.indexOf(node);
					String a = ((IRPhiFunction)succStmt).getOriginalVar();
					// TODO: comeback
					((IRPhiFunction)succStmt).setInsertBeforePoint(i, insertBeforePoint);
					
					int a_count = var2stack.get(a).peek();
					String newVar = a + a_count;
					newVars.add(newVar);	// update newVars set
					((IRPhiFunction)succStmt).setOperand(i, newVar);
					Set<String> use = node2use.get(succ);
					use.remove(a);
					use.add(newVar);
					node2use.put(succ, use);
					
					// update successor's successor's phi-functions if applicable
					// this happens when more than one var requires a phi-function
					CFGNode succsucc = succ.successor1;
					IRStmt succsuccStmt = ((IRCFGNode)succsucc).underlyingIRStmt;
					while(succsuccStmt instanceof IRPhiFunction) {
						((IRCFGNode)succsucc).renamed = true;
						((IRPhiFunction)succsuccStmt).setInsertBeforePoint(i, insertBeforePoint);
						a = ((IRPhiFunction)succsuccStmt).getOriginalVar();
						a_count = var2stack.get(a).peek();
						newVar = a + a_count;
						newVars.add(newVar);	// update newVars set
						((IRPhiFunction)succsuccStmt).setOperand(i, newVar);
						use = node2use.get(succsucc);
						use.remove(a);
						use.add(newVar);
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
		defsites = new HashMap<String, Set<CFGNode>>();
		
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
		for (String var : originalVars) {
			phisites.put(var, new HashSet<CFGNode>());
		}
		
		/* Insert */
		for (String var : originalVars) {
			Stack<CFGNode> W = new Stack<CFGNode>();
			Set<CFGNode> sites = defsites.get(var);
			if (sites != null) {
				W.addAll(sites);
			}
			
			while (!W.isEmpty()) {
				CFGNode node = W.pop();
				for (CFGNode y : dominanceFrontier.get(node)) {
					sites = phisites.get(var);
					if (!sites.contains(y)) {						
						// create a new IRPhiFunction stmt
						IRStmt yStmt = ((IRCFGNode)y).underlyingIRStmt;
						assert(yStmt instanceof IRLabel);
						List<CFGNode> predecessors = y.predecessors;
						int numPhiOperand = predecessors.size();
						IRPhiFunction phiFunction = new IRPhiFunction(var, numPhiOperand);
						IRCFGNode newNode = new IRCFGNode(phiFunction,"");
						newNode.realPredecessors = predecessors;
						// insert it right after y (IRLabel)
						cfg.insert(y, newNode);
						
						// update tree information
						Set<CFGNode> children = y.children;
						newNode.children = children;
						y.children.clear();
						y.children.add(newNode);
						
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
						
						if (!node2def.get(y).equals(var)) {
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
	
	public Set<CFGNode> getAllNodes() {
		return cfg.getAllNodes();
	}
}