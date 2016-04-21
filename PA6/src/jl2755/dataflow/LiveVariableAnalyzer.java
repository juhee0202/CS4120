package jl2755.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;

/**
 * Class that perform the live variable analysis
 * on the control flow graph.
 *
 * @param <Operand>
 */
public class LiveVariableAnalyzer<Operand> extends Dataflow<Operand> {
	
	/**
	 * A mapping to keep track of the in set for
	 * each CFGNode.
	 */
	private HashMap<CFGNode, Set<Operand>> inMap;
	
	/**
	 * Assigns the cfg field, and hardcodes
	 * the direction of analysis to be backward. 
	 * 
	 * @param argCFG
	 */
	public LiveVariableAnalyzer(ControlFlowGraph argCFG) {
		cfg = argCFG;
		directionOfAnalysis = Direction.BACKWARD;
	}
	
	/**
	 * This is the meet operator for the live variable
	 * analysis dataflow. Should be the union of all
	 * the Operands.
	 */
	@Override
	public Set<Operand> meetOperator(Operand... args) {
		// TODO: Implement Operand .equals method.
		return null;
	}

	/**
	 * This is the transfer function for the live variable
	 * analysis dataflow. Should be 
	 * in(n) = use(n) U (U_n'>n in(n') - def(n))
	 * 
	 * @return false iff the in before and after the 
	 * transferFunction are the same.
	 */
	@Override
	public boolean transferFunction(CFGNode arg) {
		// TODO Complete
		return false;
	}

	/**
	 * Initializes worklist to contain all nodes.
	 * Initializes the in of all nodes to be the empty set.
	 * In a while loop, it polls the worklist for a node,
	 * applies the transfer function, and if something changed,
	 * adds all the predecessors to the worklist.
	 */
	@Override
	public void analyze() {
		Set<CFGNode> allNodes = cfg.getAllNodes();
		Iterator<CFGNode> iteratorVersion = allNodes.iterator();
		while (iteratorVersion.hasNext()) {
			CFGNode temp = iteratorVersion.next();
			inMap.put(temp, new HashSet<Operand>());
		}
		WorklistQueue<CFGNode> ourQueue = new WorklistQueue<CFGNode>();
		ourQueue.addAll(allNodes);
		while (!ourQueue.isEmpty()) {
			CFGNode currentNode = ourQueue.poll();
			if (transferFunction(currentNode)) {
				ourQueue.addAll(currentNode.getPredecessors());
			}
		}
	}
	
	public HashMap<CFGNode,Set<Operand>> getInMap() {
		return inMap;
	}
}
