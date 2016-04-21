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
	
	public abstract boolean transferFunction(CFGNode arg);
	
	public abstract void analyze();
}