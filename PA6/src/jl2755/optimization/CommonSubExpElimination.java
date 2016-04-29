package jl2755.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.OptimizationGraph;
import jl2755.controlflow.SubTreeListMaker;
import jl2755.dataflow.AvailableExpressionAnalysis;
import jl2755.dataflow.IRExprOverrider;

/**
 * Class that, after an available expressions analysis has been done,
 * uses common subexpressions by creating new IRMem into temps
 * and using that temp over, instead of recalculating expressions.
 */
public class CommonSubExpElimination extends Optimization{
	
	/**
	 * A mapping from an IRCFGNode to a boolean. This boolean will
	 * indicate whether this IRCFGNode has had an expression hoisted out of it.
	 * This is to ensure that some things don't happen more than once, in a very
	 * conservative way.
	 */
	private Map<IRCFGNode,IRExprOverrider> nodeHoistedMap;
	
	/**
	 * A HashMap to keep track of what we are calling the temp in which
	 * each available expression should be stored in.
	 */
	private Map<IRExprOverrider,Integer> tempToInt;
	
	/**
	 * The current int to assign to the next temp/IRExprOverrider if
	 * it doesn't have one in tempToInt.
	 */
	private int currentInt = 0;
	
	public CommonSubExpElimination() {
	}
	
	@Override
	public boolean run(OptimizationGraph argGraph) {
		ControlFlowGraph cfg = (ControlFlowGraph) argGraph;
		nodeHoistedMap = new HashMap<IRCFGNode, IRExprOverrider>();
		tempToInt = new HashMap<IRExprOverrider,Integer>();
		AvailableExpressionAnalysis analyzerObject = new AvailableExpressionAnalysis(cfg);
		analyzerObject.analyze();
		outer:
		for (CFGNode node: cfg.getAllNodes()) {
			Set<IRExprOverrider> setOfIn = analyzerObject.inMap.get(node);
			SubTreeListMaker setOfUse = analyzerObject.usesMap.get(node);
			List<IRExprOverrider> listOfUseExprs = setOfUse.getList();
			List<IRExprOverrider> listOfUseAndIn = new ArrayList<IRExprOverrider>();
			for (int i = 0; i < listOfUseExprs.size(); i++) {
				if (setOfIn.contains(listOfUseExprs.get(i))) {
					listOfUseAndIn.add(listOfUseExprs.get(i));
				}
			}
			
			if (listOfUseAndIn.size() == 0) {
				continue;
			}
			
			boolean allGood = true;
			IRExprOverrider theBest = null;
			while (!allGood) {
				allGood = true;
				if (listOfUseAndIn.size() == 0) {
					continue outer;
				}
				int indexOfCurrentHighest = 0;
				int valueOfHighest = SubTreeListMaker.getValue(listOfUseAndIn.get(0).encapsulatedIRExpr);
				// Pick the highest one in the use and in
				for (int i = 1; i < listOfUseAndIn.size(); i++) {
					int currentValue = SubTreeListMaker.getValue(listOfUseAndIn.get(i).encapsulatedIRExpr);
					if (currentValue > valueOfHighest) {
						indexOfCurrentHighest = i;
						valueOfHighest = currentValue;
					}
				}
				theBest = listOfUseAndIn.get(indexOfCurrentHighest);
				
				// Loop through all the owners of theBest expression. If any of them
				// are already claimed by some other expression, we can't hoist this expression.
				for (int i = 0; i < theBest.nodesOfOrigin.size(); i++) {
					IRExprOverrider currentOverrider = nodeHoistedMap.get(theBest.nodesOfOrigin.get(i));
					if (currentOverrider != null) {
						if (!currentOverrider.equals(theBest)) {
							allGood = false;
						}
					}
				}
				if (!allGood) {
					listOfUseAndIn.remove(indexOfCurrentHighest);
				}
			}
			
			if (theBest == null) {
				continue;
			}
			
			
			if (!tempToInt.containsKey(theBest)) {
				tempToInt.put(theBest, currentInt);
				currentInt++;
			}
			
			// Check that each parent has hoisted this particular IRExprOverrider or if it
			// hasn't hoisted anything at all. Loop until you find an expression that can be
			// hoisted from all its parents. If one is found, do work on hoisting it from parents
			// and using the temp variable in the current node too.
			for (int i = 0; i < theBest.nodesOfOrigin.size(); i++) {
				IRExprOverrider parentsHoisted = nodeHoistedMap.get(theBest.nodesOfOrigin.get(i));
				// If parentsHoisted is null, that means that no one else has ever hoisted
				// this particular parent before. It should be done here.
				if (parentsHoisted == null) {
					IRCFGNode parentNode = theBest.nodesOfOrigin.get(i);
					// Replace sub tree with move
					IRMove moveToBeInserted = new IRMove(new IRTemp("csetemp" + tempToInt.get(theBest)),
							theBest.encapsulatedIRExpr);
					parentNode.putArgBeforeThisNode(moveToBeInserted);
				}
			}
			
			// "Claim" the owners of theBest expression.
			for (int i = 0; i < theBest.nodesOfOrigin.size(); i++) {
				nodeHoistedMap.put(theBest.nodesOfOrigin.get(i), theBest);
			}
		}
		
		
		return false;
	}
	
}
