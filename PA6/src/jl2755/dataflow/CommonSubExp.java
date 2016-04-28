package jl2755.dataflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import jl2755.assembly.Register;
import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.CSECFGNode;

public class CommonSubExp extends Dataflow<IRExpr> {

	@Override
	public Set<IRExpr> meetOperator(IRExpr... args) {
		// Not needed
		return null;
	}

	@Override
	public boolean transferFunction(CFGNode arg) {
		Iterator<CFGNode> iteratorOfPred = arg.getPredecessors().iterator();
		Set<IRExprOverrider> intersectionSet = null;
		while (iteratorOfPred.hasNext()) {
			CSECFGNode currentPred = (CSECFGNode) iteratorOfPred.next();
			if (intersectionSet == null) {
				intersectionSet = new HashSet<IRExprOverrider>(currentPred.getOut());
			}
			else {
				intersectionSet.retainAll(currentPred.getOut());
			}
		}
		if (intersectionSet == null) {
			intersectionSet = new HashSet<IRExprOverrider>();
		}
		CSECFGNode cseView = (CSECFGNode) arg;
		cseView.setIn(intersectionSet);
		
		
		Set<IRExprOverrider> currentInSet = new HashSet<IRExprOverrider>(cseView.getIn());
		currentInSet.addAll(cseView.getAllExpr());
		
		if (cseView.getAllKill()) {
			currentInSet.clear();
		}
		else {
			Set<IRExprOverrider> removeSet = new HashSet<IRExprOverrider>();
			for (IRExprOverrider ireo : currentInSet) {
				List<IRExprOverrider> killList = cseView.getKills();
				for (int i = 0; i < killList.size(); i++) {
					if (ireo.doesContain(killList.get(i))) {
						removeSet.add(ireo);
					}
				}
			}
			currentInSet.removeAll(removeSet);
		}
		return cseView.getIn().equals(currentInSet);
	}

	@Override
	public void analyze() {
		Set<CFGNode> allNodes = cfg.getAllNodes();
		Iterator<CFGNode> iteratorVersion = allNodes.iterator();
		while (iteratorVersion.hasNext()) {
			CSECFGNode temp = (CSECFGNode) iteratorVersion.next();
			temp.initializeOut();
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
}
