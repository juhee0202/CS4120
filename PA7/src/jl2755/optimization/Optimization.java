package jl2755.optimization;

import jl2755.controlflow.OptimizationGraph;

public abstract class Optimization {
	public abstract boolean run(OptimizationGraph cfg);
}
