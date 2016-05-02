package jl2755.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.IRCFGNode;

public class SSALiveAnalyzer extends Dataflow<IRTemp>{

	/**
	 * A mapping to keep track of the in set for
	 * each IRCFGNode.
	 */
	public HashMap<IRCFGNode, Set<IRExprOverrider>> inMap;
	
	/**
	 * A mapping to keep track of the uses for each IRCFGNode.
	 */
	public HashMap<IRCFGNode, Set<IRExprOverrider>> uses;
	
	public HashMap<IRCFGNode, Set<IRExprOverrider>> outMap;
	
	/**
	 * A mapping to keep track of the def for each IRCFGNode.
	 */
	public HashMap<IRCFGNode, IRExprOverrider> defs;
	
	private ControlFlowGraph cfg;
	
	public SSALiveAnalyzer(ControlFlowGraph argCfg) {
		inMap = new HashMap<IRCFGNode,Set<IRExprOverrider>>();
		uses = new HashMap<IRCFGNode, Set<IRExprOverrider>>();
		defs = new HashMap<IRCFGNode, IRExprOverrider>();
		outMap = new HashMap<IRCFGNode, Set<IRExprOverrider>>();
		cfg = argCfg;
		for (CFGNode node : argCfg.getAllNodes()) {
			IRCFGNode irView = (IRCFGNode) node;
			computeUses(irView);
			computeDefs(irView);
		}
	}
	
	@Override
	public Set<IRTemp> meetOperator(IRTemp... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean transferFunction(CFGNode arg) {
		CFGNode firstSuccessor = arg.getSuccessor1();
		CFGNode secondSuccessor = arg.getSuccessor2();
		
		IRCFGNode irView = (IRCFGNode) arg;
		Set<IRExprOverrider> tempSet = new HashSet<IRExprOverrider>();
		
		if (firstSuccessor != null) {
			tempSet.addAll(inMap.get(firstSuccessor));
		}
		if (secondSuccessor != null) {
			tempSet.addAll(inMap.get(secondSuccessor));
		}
		// tempSet at this point should be the union
		// of the ins of the children.
		Set<IRExprOverrider> outSet = new HashSet<IRExprOverrider>(tempSet);
		outMap.put(irView, tempSet);
		
		if (defs.get(arg) != null) {
			tempSet.remove(defs.get(arg));
		}
		
		tempSet.addAll(uses.get(arg));
		
		Set<IRExprOverrider> originalInSet = inMap.get(arg);
		
		inMap.put(irView, tempSet);
		
		return !originalInSet.equals(tempSet);
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
			inMap.put((IRCFGNode) temp, new HashSet<IRExprOverrider>());
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
	
	private void computeUses(IRCFGNode argNode) {
		IRStmt underlyingStmt = argNode.underlyingIRStmt;
		if (underlyingStmt instanceof IRCJump) {
			IRCJump cjumpView = (IRCJump) underlyingStmt;
			uses.put(argNode, extractUses(cjumpView.expr()));
			return;
		}
		if (underlyingStmt instanceof IRExp) {
			IRExp expView = (IRExp) underlyingStmt;
			// Sanity check
			assert(expView.expr() instanceof IRCall);
			uses.put(argNode, extractUses(expView.expr()));
			return;
		}
		if (underlyingStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingStmt;
			if (!(moveView.target() instanceof IRTemp)) {
				uses.put(argNode, extractUses(moveView.target()));
			}
			uses.put(argNode, extractUses(moveView.expr()));
			return;
		}
		uses.put(argNode, new HashSet<IRExprOverrider>());
	}
	
	private Set<IRExprOverrider> extractUses(IRExpr argExpr) {
		if (argExpr instanceof IRBinOp) {
			IRBinOp binopView = (IRBinOp) argExpr;
			Set<IRExprOverrider> leftSide = extractUses(binopView.left());
			Set<IRExprOverrider> rightSide = extractUses(binopView.right());
			leftSide.addAll(rightSide);
			return leftSide;
		}
		else if (argExpr instanceof IRCall) {
			Set<IRExprOverrider> tempSet = new HashSet<IRExprOverrider>();
			IRCall callView = (IRCall) argExpr;
			List<IRExpr> args = callView.args();
			for (int i = 0; i < args.size(); i++) {
				tempSet.addAll(extractUses(args.get(i)));
			}
			return tempSet;
		}
		else if (argExpr instanceof IRMem) {
			IRMem memView = (IRMem) argExpr;
			return extractUses(memView.expr());
		}
		else if (argExpr instanceof IRTemp) {
			Set<IRExprOverrider> tempSet = new HashSet<IRExprOverrider>();
			tempSet.add(new IRExprOverrider(argExpr,null));
			return tempSet;
		}
		return new HashSet<IRExprOverrider>();
	}
	
	private void computeDefs(IRCFGNode argNode) {
		IRStmt underlyingStmt = argNode.underlyingIRStmt;
		if (underlyingStmt instanceof IRMove) {
			IRMove moveView = (IRMove) underlyingStmt;
			if (moveView.target() instanceof IRTemp) {
				IRExprOverrider overrider = new IRExprOverrider(moveView.target(),null);
				defs.put(argNode, overrider);
				return;
			}
		}
	}

	private void addUse(IRCFGNode argNode, Set<IRExprOverrider> argTemps) {
		Set<IRExprOverrider> currentUses = uses.get(argNode);
		currentUses.addAll(argTemps);
	}
	
	private void addDef(IRCFGNode argNode, IRExprOverrider argRegister) {
		defs.put(argNode, argRegister);
	}
}
