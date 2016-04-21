package jl2755.dataflow;

import java.util.Map;
import java.util.Set;

import jl2755.assembly.Register;

public class InterferenceGraph {
	
	/** Set of all nodes in the graph. */
	private Set<Register> nodes;
	
	/** Map of all nodes to its neighbors in the graph. */
	private Map<Register,Set<Register>> neighbors;
	
	
}
