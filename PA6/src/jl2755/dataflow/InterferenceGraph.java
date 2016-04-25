package jl2755.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import jl2755.assembly.Instruction;
import jl2755.assembly.Instruction.Operation;
import jl2755.assembly.Register;
import jl2755.assembly.Register.RegisterName;
import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;

public class InterferenceGraph {
	
	/** Set of all nodes in the graph. */
	private Set<Register> nodes;
	
	/** Map of all nodes to its neighbors in the graph. */
	private Map<Register, Set<Register>> edges;
	

	/**
	 * Creates the Interference Graph from the map of CFGNodes to live registers
	 * and begins graph coloring.
	 * 
	 * @param map	the map of CFGNodes to registers live at that node
	 */
	public InterferenceGraph(Map<AACFGNode, Set<Register>> map) {
		// Initialize globals
		nodes = new HashSet<Register>();
		edges = new HashMap<Register,Set<Register>>();
		
		// For each node, add each register in the set to nodes
		for (CFGNode node : map.keySet()) {
			HashSet<Register> regSet = (HashSet<Register>) map.get(node);
			// For each register in the set, add all other registers to its neighbors
			for (Register currReg : regSet) {
				HashSet<Register> setWithoutCurrReg = (HashSet<Register>) regSet.clone();
				setWithoutCurrReg.remove(currReg);
				if (!nodes.contains(currReg)) {
					nodes.add(currReg);
					edges.put(currReg, setWithoutCurrReg);
				} else {
					Set<Register> newSet = edges.get(currReg);
					newSet.addAll(setWithoutCurrReg);
					edges.put(currReg, newSet);
				}
				
			}
		}
	}
	
	public void remove(Register reg) {
		nodes.remove(reg);
		for (Register neighbor : edges.get(reg)) {
			Set<Register> neighbors = edges.get(neighbor);
			edges.remove(reg);
			edges.put(neighbor,neighbors);
		}
	}
	
	public Set<Register> getNodes() {
		return nodes;
	}

	public Map<Register, Set<Register>> getEdges() {
		return edges;
	}

	@Override
	public String toString() {
		String s = "";
		for (Register reg : edges.keySet()) {
			s += reg.getName() + ":\t";
			for (Register neighbor : edges.get(reg)) {
				s += neighbor.getName() + ", ";
			}
			s += "\n";
		}
		return s;
	}
}
