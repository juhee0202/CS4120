package jl2755.optimization;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRPhiFunction;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.OptimizationGraph;
import jl2755.controlflow.SSAFormGraph;
import jl2755.visitor.LIRVisitor;

public class DeadCodeEliminator extends Optimization{

	Queue<String> variables;
	Map<CFGNode, Set<String>> node2use;
	Map<CFGNode, String> node2def;
	Map<String, Set<CFGNode>> var2use;
	Map<String, CFGNode> var2def;
	LIRVisitor lirVisitor = new LIRVisitor();
	
	public DeadCodeEliminator() {
		variables = new PriorityQueue<String>();
	}
	
	@Override
	public boolean run(OptimizationGraph graph) {
		// TODO Auto-generated method stub
		boolean optimized = false;
		
		// initialization
		SSAFormGraph ssaGraph = (SSAFormGraph) graph;
		variables.addAll(ssaGraph.getAllVariables());
		node2use = ssaGraph.getNodeToUseMap();
		node2def = ssaGraph.getNodeToDefMap();
		var2use = ssaGraph.getVarToUseMap();
		var2def = ssaGraph.getVarToDefMap();
	
		// QUESTION: isn't this gonna be an infinite loop?
		while (!variables.isEmpty()) {
			String var = variables.poll();
			
			// if var is _RET01, then even if no one uses _RET01, we don't want to
			// eliminate the stmt because we must return the return value
			if (var.equals("_RET01")) {
				continue;
			}

			if ((var2use.get(var)).isEmpty()) {
				//stmt = v's statement of def
				IRCFGNode node = (IRCFGNode) var2def.get(var);
				
				// def[var] = null indicates that it must have been passed in
				// using special register, ex: __ARG00
				if (node == null) {
					var2def.remove(var);
					var2use.remove(var);
					continue;
				}
				IRStmt stmt = node.underlyingIRStmt;
				
				//if stmt has no side effects other than the
				//assignment to v. (precond: stmt must be IRMove because it's a def)
				if (!hasSideEffect(stmt)) {
					optimized = true;
					
					ssaGraph.removeDefNode(node);
					
					// for each var x_i used by node
					for (String v: node2use.get(node)) {
						//remove node from list of uses of each v
						Set<CFGNode> useSet = var2use.get(v);
						useSet.remove(node);
						var2use.put(v, useSet);
						variables.add(v);
					}
					node2use.remove(node);
					node2def.remove(node);
					var2def.remove(var);
					var2use.remove(var);
				}
			}
		}
			
			
		// update maps of ssaGraph
		ssaGraph.setNode2def(node2def);
		ssaGraph.setNode2use(node2use);
		ssaGraph.setVar2def(var2def);
		ssaGraph.setVar2use(var2use);
		return optimized;
	}
	
	/** pecondition: stmt is an IRMove*/
	public static boolean hasSideEffect(IRStmt stmt) {
		IRMove moveStmt = (IRMove) stmt;
		return (moveStmt.expr() instanceof IRCall) ||
			   (moveStmt.target() instanceof IRCall);
		// division by zero -> run time failure = side effect.
	}

}
