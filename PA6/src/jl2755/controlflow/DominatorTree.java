package jl2755.controlflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class DominatorTree {
	
	/** A ControlFlowGraph from which we are building the Dominator Tree */
	private ControlFlowGraph cfg;
	
	/** Root of the Dominator Tree */
	private CFGNode root;
	
	/** Maps node n to the set of nodes that dominate n */
	Map<CFGNode, Set<CFGNode>> dominanceMap;
	
	public DominatorTree(ControlFlowGraph argCfg) {
		cfg = argCfg;
		constructDominatorTree();
	}

	private void constructDominatorTree() {
		/* Compute dominance relation */
		computeDominanceRelation();
		
		/* Construct dominator tree */
		root = cfg.getHead();
		constructDominatorTree(root, dominanceMap);
	}
	
	private void constructDominatorTree(CFGNode parent, Map<CFGNode, Set<CFGNode>> dominances) {
		/* Create a copy of provided dominance map */
		Map<CFGNode, Set<CFGNode>> dominances_copy = new HashMap<CFGNode, Set<CFGNode>>();
		dominances_copy.putAll(dominances);
		dominances_copy.remove(parent);
	
		/* Remove parent from each dominance set */
		for (Entry<CFGNode, Set<CFGNode>> entry : dominances.entrySet()) {
			CFGNode node = entry.getKey();
			Set<CFGNode> dominance = entry.getValue();
			Set<CFGNode> dominance_copy = new HashSet<CFGNode>();
			dominance_copy.addAll(dominance);
			if (dominance_copy.remove(parent)) {
				dominances_copy.put(node, dominance_copy);
			} else {
				// remove nodes that aren't part of this subtree
				dominances_copy.remove(node);
			}
		}
		
		/* Add nodes to the tree */
		for (Entry<CFGNode, Set<CFGNode>> entry : dominances_copy.entrySet()) {
			CFGNode node = entry.getKey();
			Set<CFGNode> dominance = entry.getValue();
			if (dominance.size() == 1) {
				assert(dominance.contains(node));  // should contain itself
				parent.addChild(node);
				constructDominatorTree(node, dominances_copy);
			}
		}
	}

	/**
	 * Computes dominance relation and update dominanceMap
	 * Currently, this is an inefficient iterative algorithm
	 * TODO: Replace with Lengauer and Tarjan's algorithm based on DFS
	 */
	private void computeDominanceRelation() {
		/* Initialize */
		dominanceMap = new HashMap<CFGNode, Set<CFGNode>>();
		Set<CFGNode> allNodes = cfg.getAllNodes();
		CFGNode head = cfg.getHead();
		for (CFGNode node : allNodes) {
			Set<CFGNode> dominatedNodes = new HashSet<CFGNode>();
			if (node == head) {
				dominatedNodes.add(node);
			} else {
				dominatedNodes.addAll(allNodes);
			}
			dominanceMap.put(node, dominatedNodes);
		}
		
		/* Compute */
		boolean converged = false;
		while (!converged) {
			converged = true;
			for (CFGNode node : allNodes) {
				// skip head
				if (node == head) {
					continue;
				}
				
				Set<CFGNode> oldDominance = dominanceMap.get(node);
				Set<CFGNode> newDominance = new HashSet<CFGNode>();
				newDominance.add(node);	// a node's dominance always contains itself.
				
				// compute the intersection of pred's dominance 
				Set<CFGNode> predDominance = null;
				for (CFGNode pred : node.predecessors) {
					Set<CFGNode> dominance = dominanceMap.get(pred);
					if (predDominance == null) {
						predDominance = new HashSet<CFGNode>(); 
						predDominance.addAll(dominance);
					} else {
						predDominance.retainAll(dominance);
					}
				}
				
				// compute the new dominance
				if (predDominance != null) {
					newDominance.addAll(predDominance);
				}			
				
				// update dominance relation
				if (oldDominance.size() > newDominance.size()) {					
					dominanceMap.put(node, newDominance);
					converged = false;
				}
			}
		}
	}

	public CFGNode getRoot() {
		return root;
	}
}
