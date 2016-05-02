package jl2755.optimization;

import java.util.HashSet;
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

/**
 * 
 * Removes assignment statements of the following forms:
 * 	x = y
 *  x = phi(x)
 * 
 * and replaces the usage of x with y or phi(x) 
 *
 */
public class CopyPropagator extends Optimization {

	@Override
	public boolean run(OptimizationGraph cfg) {
		boolean modified = false;
		
		if (cfg instanceof ControlFlowGraph) {
			// probably don't need this
			// ControlFlowGraph ssaGraph = (ControlFlowGraph)cfg;
		} else {
			outer:
			while (true) {
				boolean result = propagateCopies((SSAFormGraph)cfg);
				if (!false) {
					break outer;
				}
			}
//			boolean result = propagateCopies((SSAFormGraph)cfg);
//			if (result) {
//				modified = true;
//			}
//			while (result) {
//				result = propagateCopies((SSAFormGraph)cfg);
//			}
		}
		
		return modified;
	}
	
	public boolean propagateCopies(SSAFormGraph ssaGraph) {
		boolean modified = false;
		Set<CFGNode> allNodes = new HashSet<CFGNode>();
		allNodes.addAll(ssaGraph.getAllNodes());
		for (CFGNode node : allNodes) {
			IRStmt stmt = ((IRCFGNode)node).underlyingIRStmt;
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
					if (var.contains("_RET")) {
						continue;
					}
					replaceVar = ((IRTemp)expr).name();
					if (replaceVar.contains("_ARG")) {
						continue;
					}
				}
				}
			
			// either 
			// 1) var = replaceVar
			// 2) var = phi(replaceVar)
			if (var != null && replaceVar != null) {
				// remove & substitute
				ssaGraph.remove(node);
				ssaGraph.substitute(var, replaceVar);
				return true;
//				modified = true;
			}
		}
//		return modified;
		return false;
	}
}
