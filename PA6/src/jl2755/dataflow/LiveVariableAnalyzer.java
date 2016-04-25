package jl2755.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;

/**
 * Class that perform the live variable analysis
 * on the control flow graph.
 *
 * @param <Register>
 */
public class LiveVariableAnalyzer<Register> extends Dataflow<Register> {
	
	/**
	 * A mapping to keep track of the in set for
	 * each AACFGNode.
	 */
	private HashMap<AACFGNode, Set<Register>> inMap;
	
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
	public Set<Register> meetOperator(Register... args) {
		Set<Register> results = new HashSet<Register>();
		for (int i = 0; i < args.length; i++) {
			results.add(args[i]);
		}
		return results;
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
		CFGNode firstSuccessor = arg.getSuccessor1();
		CFGNode secondSuccessor = arg.getSuccessor2();
		
		assert(firstSuccessor == null ||firstSuccessor instanceof AACFGNode);
		assert(secondSuccessor == null || secondSuccessor instanceof AACFGNode);
		
		assert(arg instanceof AACFGNode);
		
		AACFGNode AAView = (AACFGNode) arg;
		
		
		
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
			inMap.put((AACFGNode) temp, new HashSet<Register>());
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
	
	public HashMap<AACFGNode,Set<Register>> getInMap() {
		return inMap;
	}
}
