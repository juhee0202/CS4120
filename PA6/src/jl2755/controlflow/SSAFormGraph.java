package jl2755.controlflow;

import java.util.Map;
import java.util.Set;

public class SSAFormGraph {
	/** Underlying ControlFlowGraph */
	private ControlFlowGraph cfg;
	
	/** Maps CFGNode to its set of used variable names */
	// node of IRPhiFunction isn't included
	private Map<CFGNode, Set<String>> node2use;
	
	/** Maps CFGNode to the defined variable name */
	// node of IRPhiFunction isn't included
	private Map<CFGNode, String> node2def;
	
	public SSAFormGraph(ControlFlowGraph cfg, Map<CFGNode, Set<String>> node2use, Map<CFGNode, String> node2def) {
		this.cfg = cfg;
		this.node2use = node2use;
		this.node2def = node2def;
	}
}
