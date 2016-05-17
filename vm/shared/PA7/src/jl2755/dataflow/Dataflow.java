package jl2755.dataflow;

import java.util.Queue;
import java.util.Set;

import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;

public abstract class Dataflow<T> {
	protected ControlFlowGraph cfg;
	protected Direction directionOfAnalysis;
	protected Queue<CFGNode> worklist;
	
	protected enum Direction {
		BACKWARD, FORWARD;
	}
	
	public abstract Set<T> meetOperator(T... args);
	
	/**
	 * Should return true if something changed after applying the
	 * transfer function at this step.
	 * 
	 * @param arg the CFGNode to apply the transfer function to.
	 * @return true iff something has changed in this node.
	 */
	public abstract boolean transferFunction(CFGNode arg);
	
	public abstract void analyze();
}