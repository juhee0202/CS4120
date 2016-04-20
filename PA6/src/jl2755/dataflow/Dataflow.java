package jl2755.dataflow;

import jl2755.controlflow.ControlFlowGraph;

public abstract class Dataflow<T> {
	private ControlFlowGraph cfg;
	private Direction directionOfAnalysis;
	
	private enum Direction {
		BACKWARD, FORWARD;
	}
	
	public abstract T meetOperator(T... args);
	
	public abstract T transferFunction(T arg);
	
	public abstract ControlFlowGraph analyze();
}