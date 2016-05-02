package jl2755.optimization;

import java.util.List;
import java.util.Set;

import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.OptimizationGraph;
import jl2755.dataflow.IRExprOverrider;
import jl2755.dataflow.SSALiveAnalyzer;

public class IRDeadCodeEliminator extends Optimization {

	public IRDeadCodeEliminator() {
	}
	
	@Override
	public boolean run(OptimizationGraph cfg) {
		
		ControlFlowGraph cfgView = (ControlFlowGraph) cfg;
		
		SSALiveAnalyzer analyzer = new SSALiveAnalyzer((ControlFlowGraph) cfg);
		
		analyzer.analyze();
		
		boolean hasChanged = false;
		
		// Delete all nodes that don't have
		// their defs in their outs
		outer:
		while (true) {
			inner:
			for (CFGNode node : cfgView.getAllNodes()) {
				Set<IRExprOverrider> outSet = analyzer.outMap.get(node);
				IRExprOverrider def = analyzer.defs.get(node);
				if (def == null) {
					continue inner;
				}
				if (!outSet.contains(def)) {
					// Delete this node
					List<CFGNode> parentNodes = node.getPredecessors();
					if (node.getSuccessor1() != null) {
						node.getPredecessors().remove(node);
						node.getPredecessors().addAll(parentNodes);
					}
					// Ignoring the case of second successor
					// because CJump's will never be removed
					// and they are the only ones who have
					// 2 successors
//					if (node.getSuccessor2() != null) {
//						node.getPredecessors().remove(node);
//						node.getPredecessors().addAll(parentNodes);
//					}
					
					for (int i = 0; i < parentNodes.size(); i ++) {
						CFGNode current = parentNodes.get(i);
						if (current.getSuccessor1() == node) {
							current.setSuccessor1(node.getSuccessor1());
						}
						else if (current.getSuccessor2() == node) {
							current.setSuccessor2(node.getSuccessor1());
						}
					}
					hasChanged = true;
					continue outer;
					
				}
			}
			break outer;
		}
		
		
		return hasChanged;
	}
	

}
