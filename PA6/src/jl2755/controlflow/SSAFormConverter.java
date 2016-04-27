package jl2755.controlflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SSAFormConverter {
	/** A ControlFlowGraph from which we are converting */
	private ControlFlowGraph cfg;
	
	/** Dominator tree of cfg */
	private DominatorTree dominatorTree;
	
	/** Dominance Frontier map */
	private Map<CFGNode, Set<CFGNode>> dominanceFrontier;
	
	/** Resulting SSA Form graph */
	private SSAFormGraph ssaGraph;
	
	public SSAFormConverter(ControlFlowGraph argCfg) {
		cfg = argCfg;
		dominatorTree = new DominatorTree(cfg);
		dominanceFrontier = new HashMap<CFGNode, Set<CFGNode>>();
		ssaGraph = convertToSSAForm();
	}

	private SSAFormGraph convertToSSAForm() {
		// 1) compute Dominance Frontier
		computeDominanceFrontier();
		// 2) add phi-functions for vars
		// 3) rename all defs&uses of vars using subscripts
		return null;
	}

	/**
	 * Computes the dominance frontier (DF) map
	 * DF[v] = set of node w's such that
	 * 	1) v dominates some predecessor of w
	 * 	2) v does not strictly dominate w
	 * 		- v strictly dominates w if v dominates w and v != w
	 */
	private void computeDominanceFrontier() {
		// TODO Auto-generated method stub
		
	}
}
