package jl2755.controlflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SSAFormConverter {
	/** A ControlFlowGraph from which we are converting */
	private ControlFlowGraph cfg;
	
	/** Dominator tree of cfg */
	private DominatorTree dominatorTree;
	
	/** Dominance Frontier map */
	private Map<CFGNode, Set<CFGNode>> dominanceFrontier;
	
	/** Dominance Relation map */
	private Map<CFGNode, Set<CFGNode>> dominanceMap;
	
	/** Resulting SSA Form graph */
	private SSAFormGraph ssaGraph;
	
	public SSAFormConverter(ControlFlowGraph argCfg) {
		cfg = argCfg;
		dominatorTree = new DominatorTree(cfg);
		dominanceFrontier = new HashMap<CFGNode, Set<CFGNode>>();
		dominanceMap = dominatorTree.dominanceMap;
		ssaGraph = convertToSSAForm();
	}

	private SSAFormGraph convertToSSAForm() {
		// 1) compute Dominance Frontier
		computeDominanceFrontier();
		// 2) add phi-functions for vars
		insertPhiFunctions();
		// 3) rename all defs&uses of vars using subscripts
		return null;
	}

	/**
	 * Inserts phi-function by following Dominance Frontier criterion
	 */
	private void insertPhiFunctions() {
		// TODO Auto-generated method stub
		
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
}
