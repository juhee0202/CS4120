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
	private Map<Register, Set<Register>> neighbors;
	

	/**
	 * Creates the Interference Graph from the map of CFGNodes to live registers
	 * and begins graph coloring.
	 * 
	 * @param map	the map of CFGNodes to registers live at that node
	 */
	public InterferenceGraph(Map<AACFGNode, Set<Register>> map) {
		// Initialize globals
		nodes = new HashSet<Register>();
		neighbors = new HashMap<Register,Set<Register>>();
		
		// For each node, add each register in the set to nodes
		for (CFGNode node : map.keySet()) {
			HashSet<Register> regSet = (HashSet<Register>) map.get(node);
			// For each register in the set, add all other registers to its neighbors
			for (Register currReg : regSet) {
				HashSet<Register> setWithoutCurrReg = (HashSet<Register>) regSet.clone();
				setWithoutCurrReg.remove(currReg);
				if (!nodes.contains(currReg)) {
					nodes.add(currReg);
					neighbors.put(currReg, setWithoutCurrReg);
				} else {
					Set<Register> newSet = neighbors.get(currReg);
					newSet.addAll(setWithoutCurrReg);
					neighbors.put(currReg, newSet);
				}
				
			}
		}
	}
	
	public void remove(Register reg) {
		nodes.remove(reg);
		for (Register neighbor : neighbors.get(reg)) {
			Set<Register> edges = neighbors.get(neighbor);
			edges.remove(reg);
			neighbors.put(neighbor,edges);
		}
	}
	
	public Set<Register> getNodes() {
		return nodes;
	}

	public Map<Register, Set<Register>> getNeighbors() {
		return neighbors;
	}

	@Override
	public String toString() {
		String s = "";
		for (Register reg : neighbors.keySet()) {
			s += reg.getName() + ":\t";
			for (Register neighbor : neighbors.get(reg)) {
				s += neighbor.getName() + ", ";
			}
			s += "\n";
		}
		return s;
	}
}
