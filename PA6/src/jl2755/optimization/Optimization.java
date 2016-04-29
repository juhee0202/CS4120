package jl2755.optimization;

import jl2755.controlflow.ControlFlowGraph;

public abstract class Optimization {
	public abstract boolean run(ControlFlowGraph cfg);
}
