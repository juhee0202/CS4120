package jl2755.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import jl2755.assembly.Register;
import jl2755.assembly.Register.RegisterName;
import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;

public class InterferenceGraph {
	
	/** Set of all nodes in the graph. */
	private Set<Register> nodes;
	
	/** Map of all nodes to its neighbors in the graph. */
	private Map<Register, Set<Register>> neighbors;
	
	/** Map of function names to the set of registers it uses. */
	private Map<String, Set<Register>> registerUsage;
	
	/** Map of CFGNodes to live registers. */
	private Map<AACFGNode, Set<Register>> liveVariables;

	/** Stack to use for register allocation. */
	private Stack<Register> regStack;
	/** Stack to keep track of neighbors. */
	private Stack<Set<Register>> neighborStack;
	
	// Built-in registers to use for allocation
	private static final Register RAX = new Register(RegisterName.RAX);
	private static final Register RBX = new Register(RegisterName.RBX);
	private static final Register RCX = new Register(RegisterName.RCX);
	private static final Register RDX = new Register(RegisterName.RDX);
	private static final Register RSI = new Register(RegisterName.RSI);
	private static final Register RDI = new Register(RegisterName.RDI);
	private static final Register RSP = new Register(RegisterName.RSP);
	private static final Register RBP = new Register(RegisterName.RBP);
	private static final Register R8  = new Register(RegisterName.R8);
	private static final Register R9  = new Register(RegisterName.R9);
	private static final Register R10 = new Register(RegisterName.R10);
	private static final Register R11 = new Register(RegisterName.R11);
	private static final Register R12 = new Register(RegisterName.R12);
	private static final Register R13 = new Register(RegisterName.R13);
	private static final Register R14 = new Register(RegisterName.R14);
	private static final Register R15 = new Register(RegisterName.R15);
	
	private static final int NUM_COLORS = 16;

	/**
	 * Creates the Interference Graph from the map of CFGNodes to live registers
	 * and begins graph coloring.
	 * 
	 * @param map	the map of CFGNodes to registers live at that node
	 */
	public InterferenceGraph(Map<AACFGNode, Set<Register>> map) {
		liveVariables = map;
		
		// Initialize globals
		nodes = new HashSet<Register>();
		neighbors = new HashMap<Register,Set<Register>>();
		regStack = new Stack<Register>();
		neighborStack = new Stack<Set<Register>>();
		
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
	
	/**
	 * Algorithm for graph coloring with move coalescing.
	 * 
	 * @param 
	 */
	public void registerAllocation() {
		// Push onto stack all low-degree non-move-related nodes
		for (Register reg : nodes) {
			if (!reg.isMoveRelated() && neighbors.get(reg).size() < NUM_COLORS) {
				// Remove reg from set and map
				nodes.remove(reg);
				Set<Register> edges = neighbors.remove(reg);
				for (Register neighbor : edges) {
					Set<Register> temp = neighbors.get(neighbor);
					temp.remove(reg);
					neighbors.put(neighbor, temp);
				}
				// Push reg onto stack
				regStack.push(reg);
				neighborStack.push(edges);
			}
		}
		
		// Conservatively coalesce move-related nodes
			// loop through CFGNodes and find mov with two regs
			// if possible, coalesce
		for (CFGNode node : liveVariables.keySet()) {
			AACFGNode inst = (AACFGNode) node;
			if (inst.)
		}
		
		// Freeze a move-related node (abandon coalescing on it)
		
		// 
	}

	private void optimisticGraphColoring() {
		// TODO Auto-generated method stub
		
	}
	
}
