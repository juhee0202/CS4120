package jl2755.dataflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.SubTreeListMaker;

public class AvailableExpressionAnalysis extends Dataflow<IRExpr> {

	/**
	 * A map of IRCFGNode's to the IRExprOverriders that it kills.
	 */
	public HashMap<IRCFGNode,List<IRExprOverrider>> registerKillsMap;
	
	/**
	 * A map of IRCFGNode's to a boolean to see if this node kills
	 * everything or not.
	 */
	public HashMap<IRCFGNode,Boolean> allKillsMap;
	
	/**
	 * A map of IRCFGNode's to a SubTreeListMaker instance which
	 * calculates all of the sub expressions that are inside a given
	 * IRExpr.
	 */
	public HashMap<IRCFGNode,SubTreeListMaker> exprSubTreesMap;
	
	/**
	 * A map of IRCFGNode's to a SubTreeListMaker instance which
	 * calculates all of the sub expressions that are used inside a
	 * given IRExpr. Use it responsibly and only add uses.
	 */
	public HashMap<IRCFGNode,SubTreeListMaker> usesMap;
	
	/**
	 * A map from IRCFGNode's to their ins.
	 */
	public HashMap<IRCFGNode, Set<IRExprOverrider>> inMap;
	
	/**
	 * A map from IRCFGNode's to their outs.
	 */
	public HashMap<IRCFGNode, Set<IRExprOverrider>> outMap;
	
	public ControlFlowGraph cfg;
	
	public AvailableExpressionAnalysis(ControlFlowGraph argCfg) {
		cfg = argCfg;
		registerKillsMap = new HashMap<IRCFGNode, List<IRExprOverrider>>();
		allKillsMap = new HashMap<IRCFGNode,Boolean>();
		exprSubTreesMap = new HashMap<IRCFGNode, SubTreeListMaker>();
		usesMap = new HashMap<IRCFGNode, SubTreeListMaker>();
		inMap = new HashMap<IRCFGNode, Set<IRExprOverrider>>();
		outMap = new HashMap<IRCFGNode, Set<IRExprOverrider>>();
		
		for (CFGNode cfgNode: argCfg.getAllNodes()) {
			registerKillsMap.put((IRCFGNode) cfgNode, new ArrayList<IRExprOverrider>());
			allKillsMap.put((IRCFGNode) cfgNode, false);
			exprSubTreesMap.put((IRCFGNode) cfgNode, new SubTreeListMaker());
			inMap.put((IRCFGNode) cfgNode, new HashSet<IRExprOverrider>());
			outMap.put((IRCFGNode) cfgNode, new HashSet<IRExprOverrider>());
			computeKills((IRCFGNode) cfgNode);
			computeExprs((IRCFGNode) cfgNode);
			computeUses((IRCFGNode) cfgNode);
		}
	}
	
	@Override
	public Set<IRExpr> meetOperator(IRExpr... args) {
		// Not needed
		return null;
	}

	/**
	 * Returns true if and only if there was a change in the in or out.
	 */
	@Override
	public boolean transferFunction(CFGNode arg) {
		Set<IRExprOverrider> originalInSet = inMap.get(arg);
		Set<IRExprOverrider> originalOutSet = outMap.get(arg);
		Iterator<CFGNode> iteratorOfPred = arg.getPredecessors().iterator();
		Map<IRExprOverrider,IRExprOverrider> intersectionMap = null;
		while (iteratorOfPred.hasNext()) {
			IRCFGNode currentPred = (IRCFGNode) iteratorOfPred.next();
			if (intersectionMap == null) {
				intersectionMap = new HashMap<IRExprOverrider,IRExprOverrider>();
				Set<IRExprOverrider> tempSet = outMap.get(currentPred);
				for (IRExprOverrider ireo : tempSet) {
					intersectionMap.put(ireo, ireo);
				}
			}
			else {
				// Have to iterate through outMap.get(currentPred) so that we can, when
				// elements intersect, merge owners of the elements from both intersects.
				Set<IRExprOverrider> currentOutSet = outMap.get(currentPred);
				for (IRExprOverrider ireo : currentOutSet) {
					if (intersectionMap.containsKey(ireo)) {
						IRExprOverrider intersectedElement = intersectionMap.get(ireo);
						IRExprOverrider mergedElement = new IRExprOverrider(intersectedElement,ireo);
						intersectionMap.remove(intersectedElement);
						intersectionMap.put(mergedElement, mergedElement);
					}
				}
			}
		}
		if (intersectionMap == null) {
			intersectionMap = new HashMap<IRExprOverrider,IRExprOverrider>();
		}
		IRCFGNode cseView = (IRCFGNode) arg;
		inMap.put(cseView,intersectionMap.keySet());
		
		
		Set<IRExprOverrider> currentInSet = new HashSet<IRExprOverrider>(inMap.get(cseView));
		currentInSet.addAll(exprSubTreesMap.get(cseView).getList());
		
		if (allKillsMap.get(cseView)) {
			currentInSet.clear();
		}
		else {
			Set<IRExprOverrider> removeSet = new HashSet<IRExprOverrider>();
			for (IRExprOverrider ireo : currentInSet) {
				List<IRExprOverrider> killList = registerKillsMap.get(cseView);
				for (int i = 0; i < killList.size(); i++) {
					if (ireo.doesContain(killList.get(i))) {
						removeSet.add(ireo);
					}
				}
			}
			currentInSet.removeAll(removeSet);
		}
		outMap.put(cseView, currentInSet);
		return (!outMap.get(cseView).equals(originalOutSet)) || 
				(!inMap.get(cseView).equals(originalInSet));
	}

	@Override
	public void analyze() {
		Set<CFGNode> allNodes = cfg.getAllNodes();
		WorklistQueue<CFGNode> ourQueue = new WorklistQueue<CFGNode>();
		ourQueue.addAll(allNodes);
		while (!ourQueue.isEmpty()) {
			CFGNode currentNode = ourQueue.poll();
			if (transferFunction(currentNode)) {
				ourQueue.addAll(currentNode.getPredecessors());
			}
		}
	}
	
	private void computeKills(IRCFGNode argNode) {
		IRStmt underlyingIRStmt = argNode.underlyingIRStmt;
		if (underlyingIRStmt instanceof IRCJump) {
			// No kills in an IRCJump since nothing is clobbered.
			return;
		}
		else if (underlyingIRStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingIRStmt;
			assert(expView.expr() instanceof IRCall);
			allKillsMap.put(argNode, true);
			return;
		}
		else if (underlyingIRStmt instanceof IRJump) {
			// No kills in an IRJump since nothing is clobbered.
			return;
		}
		else if (underlyingIRStmt instanceof IRLabel) {
			// Nothing should happen in labels
			return;
		}
		else if (underlyingIRStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingIRStmt;
			if (moveView.target() instanceof IRTemp) {
				registerKillsMap.get(argNode).add(new IRExprOverrider(moveView.target(),argNode));
			}
			else if (moveView.target() instanceof IRMem) {
				allKillsMap.put(argNode, true);
			}
			else {
				// Should never get here. Move should not move something into
				// anything other than a register or memory location.
				assert(false);
			}
			if (moveView.expr() instanceof IRCall) {
				allKillsMap.put(argNode, true);
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRReturn) {
			// No kills in IRReturn since nothing is clobbered.
			return;
		}
		// Should never get here since IRSeq should not be part of the CFG.
		assert(false);
	}
	
	private void computeExprs(IRCFGNode argNode) {
		IRStmt underlyingIRStmt = argNode.underlyingIRStmt;
		if (underlyingIRStmt instanceof IRCJump) {
			IRCJump cjumpView = (IRCJump) underlyingIRStmt;
			exprSubTreesMap.get(argNode).add(cjumpView.expr(),argNode);
			return;
		}
		else if (underlyingIRStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingIRStmt;
			assert(expView.expr() instanceof IRCall);
			IRCall callViewOfChild = (IRCall) expView.expr();
			for (int i = 0; i < callViewOfChild.args().size(); i++) {
				exprSubTreesMap.get(argNode).add(callViewOfChild.args().get(i),argNode);
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRJump) {
			// Do nothing since the child should be just a name.
			return;
		}
		else if (underlyingIRStmt instanceof IRLabel) {
			// Label doesn't compute any expressions.
			return;
		}
		else if (underlyingIRStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingIRStmt;
			if (moveView.expr() instanceof IRCall) {
				IRCall callViewOfChild = (IRCall) moveView.expr();
				for (int i = 0; i < callViewOfChild.args().size(); i++) {
					exprSubTreesMap.get(argNode).add(callViewOfChild.args().get(i),argNode);
				}
			}
			else {
				exprSubTreesMap.get(argNode).add(moveView.expr(),argNode);
			}
			if (moveView.target() instanceof IRMem) {
				exprSubTreesMap.get(argNode).add(moveView.target(),argNode);
			}
			return;
		}
		else if (underlyingIRStmt instanceof IRReturn) {
			// Should do nothing since something computed in return will never be used
			// anywhere else in the same scope
			return;
		}
		// Should never get here
		assert(false);
	}
	
	private void computeUses(IRCFGNode argNode) {
		IRStmt underlyingStmt = argNode.underlyingIRStmt;
		SubTreeListMaker tempMaker = new SubTreeListMaker();
		usesMap.put(argNode, tempMaker);
		if (underlyingStmt instanceof IRCJump) {
			IRCJump cjumpView = (IRCJump) underlyingStmt;
			tempMaker.add(cjumpView.expr(), argNode);
			usesMap.put(argNode, tempMaker);
			return;
		}
		else if (underlyingStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingStmt;
			assert(expView.expr() instanceof IRCall);
			IRCall callChild = (IRCall) expView.expr();
			for (int i = 0; i < callChild.args().size(); i++) {
				tempMaker.add(callChild.args().get(i),argNode);
			}
			usesMap.put(argNode, tempMaker);
			return;
		}
		else if (underlyingStmt instanceof IRJump) {
			// Nothing should be used here
			return;
		}
		else if (underlyingStmt instanceof IRLabel) {
			// Nothing should be used here
			return;
		}
		else if (underlyingStmt instanceof IRMove) {
			// In this case, technically both the source and target are expressions that are used
			IRMove moveView = (IRMove) underlyingStmt;
			tempMaker.add(moveView.expr(),argNode);
			tempMaker.add(moveView.target(),argNode);
			usesMap.put(argNode, tempMaker);
			return;
		}
		else if (underlyingStmt instanceof IRReturn) {
			// Nothing should be used here since IRReturn doesn't have any expressions inside of it
			return;
		}
		assert(false);
		// Should never fall through here.
	}
}
