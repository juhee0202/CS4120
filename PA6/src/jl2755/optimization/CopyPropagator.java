package jl2755.optimization;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRPhiFunction;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.OptimizationGraph;
import jl2755.controlflow.SSAFormGraph;

public class CopyPropagator extends Optimization {

	@Override
	public boolean run(OptimizationGraph cfg) {
		boolean modified = false;
		
		if (cfg instanceof ControlFlowGraph) {
			// probably don't need this
			// ControlFlowGraph ssaGraph = (ControlFlowGraph)cfg;
		} else {
			SSAFormGraph ssaGraph = (SSAFormGraph)cfg;
			Set<CFGNode> allNodes = ssaGraph.getAllNodes();
			for (CFGNode node : allNodes) {
				IRCFGNode irNode = (IRCFGNode)node;
				IRStmt stmt = irNode.underlyingIRStmt;
				String var = null;
				String replaceVar = null;
				if (stmt instanceof IRPhiFunction) {
					IRPhiFunction phiStmt = (IRPhiFunction)stmt;
					String[] operands = phiStmt.getOperands();
					if (operands.length == 1) {
						var = phiStmt.getVar();
						replaceVar = operands[0];
					}
				} else if (stmt instanceof IRMove) {
					IRMove moveStmt = (IRMove)stmt;
					IRExpr target = moveStmt.target();
					IRExpr expr = moveStmt.expr();
					if (target instanceof IRTemp && expr instanceof IRTemp) {
						var = ((IRTemp)target).name();
						replaceVar = ((IRTemp)expr).name();
					}
 				}
				
				// either 
				// 1) var = replaceVar
				// 2) var = phi(replaceVar)
				if (var != null && replaceVar != null) {
					// remove & substitute
					removeAndSubstitute(ssaGraph, node, var, replaceVar);
					modified = true;
				}
			}
		}
		
		return modified;
	}

	private void removeAndSubstitute(SSAFormGraph ssaGraph, CFGNode node, String var, String replaceVar) {
		/* Remove */
		List<CFGNode> predecessors = node.getPredecessors();
		CFGNode successor = node.getSuccessor1();
		
		// update predecessors' links 
		for (CFGNode pred : predecessors) {
			if (pred.getSuccessor1() == node) {
				pred.setSuccessor1(successor); 
			} else if (pred.getSuccessor2() == node) {
				pred.setSuccessor2(successor);
			}
		}
		// update successor's link
		successor.getPredecessors().remove(node);
		successor.getPredecessors().addAll(predecessors);
		
		Set<CFGNode> children = node.getChildren();
		assert(children.size() == 1);
		CFGNode idom = node.getIdom();
		
		// update idom's link
		idom.getChildren().remove(node);
		idom.getChildren().addAll(children);
		
		// update children's link
		for (CFGNode child : children) {
			child.setIdom(idom);
		}
		
		/* Update maps */
		Map<CFGNode, String> node2def = ssaGraph.getNodeToDefMap();
		node2def.remove(node);
		Map<CFGNode, Set<String>> node2use = ssaGraph.getNodeToUseMap();
		node2use.remove(node);
		Map<String, CFGNode> var2def = ssaGraph.getVarToDefMap();
		var2def.remove(var);
		
		/* Replace */
		Map<String, Set<CFGNode>> var2use = ssaGraph.getVarToUseMap();
		Set<CFGNode> usesites = var2use.get(var);
		for (CFGNode usesite : usesites) {
			((IRCFGNode)usesite).replaceUsage(var, replaceVar);
		}
		var2use.remove(var);
	}

}
