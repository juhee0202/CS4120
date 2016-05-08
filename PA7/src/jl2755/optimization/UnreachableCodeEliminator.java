package jl2755.optimization;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.OptimizationGraph;
import jl2755.controlflow.SSAFormGraph;

public class UnreachableCodeEliminator extends Optimization {

	@Override
	public boolean run(OptimizationGraph cfg) {
		boolean modified = false;
		
		if (cfg instanceof ControlFlowGraph) {
			// probably don't need this
		} else {
			SSAFormGraph ssaGraph = (SSAFormGraph)cfg;
			CFGNode head = ssaGraph.getHead();
			Set<CFGNode> allNodes = new HashSet<CFGNode>(); 
			allNodes.addAll(ssaGraph.getAllNodes());
			for (CFGNode node : allNodes) {
				if (!node.hasPredecessor() && node != head) {
					modified = true;
					ssaGraph.removeUnreachableNodes(node);
				}
			}
		}
		
		return modified;
	}

}