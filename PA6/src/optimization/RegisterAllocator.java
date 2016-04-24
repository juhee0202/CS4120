package optimization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import jl2755.assembly.Instruction;
import jl2755.assembly.Register;
import jl2755.assembly.Register.RegisterName;
import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.dataflow.InterferenceGraph;
import jl2755.dataflow.LiveVariableAnalyzer;

public class RegisterAllocator {
	
	/** Current list of instructions. */
	private List<Instruction> program;
	
	/** Current CFG. */
	private ControlFlowGraph cfg;
	
	/** The current interference graph. */
	private InterferenceGraph graph;
	
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
	
	public RegisterAllocator(List<Instruction> instructions) {
		// Initialize globals
		registerUsage = new HashMap<String,Set<Register>>();
		regStack = new Stack<Register>();
		neighborStack = new Stack<Set<Register>>();
		
		program = instructions;
	}
	
	/**
	 * Algorithm for graph coloring with move coalescing.
	 * 
	 * @param 
	 */
	public boolean registerAllocation() {
		boolean didCoalesce;
		boolean didFreeze;
		boolean didPotentiallySpill;
		boolean didActuallySpill = true;
		while (didActuallySpill) {
			// Build: Construct CFG, run LVA, create Interference Graph
			build();
			
			didPotentiallySpill = true;
			while (didPotentiallySpill) {
				didFreeze = true;
				while (didFreeze) {
					didCoalesce = true;
					while (didCoalesce) {
						// Simplify: Push onto stack all low-degree non-move-related nodes
						simplify();
						
						// Coalesce: Conservatively coalesce move-related nodes
						// loop through CFGNodes and find mov with two regs
						// if possible, coalesce and repeat simplify and coalesce
						// else, move to freeze
						didCoalesce = coalesce();
					}
					// Freeze: abandon coalescing a move-related node
					// Choose a low-degree move-related node
					// Freeze the node and other related nodes
					// Repeat Simplify and Coalesce until nothing to freeze
					didFreeze = freeze();
				}
				// Spill: select a significant-degree node for spilling and push to stack
					// Repeat from Simplify until only pre-colored nodes remain
				didPotentiallySpill = spill();		
			}
			// Select: Pop from stack, assigning colors
				// If cannot assign, spill to stack, rewrite code, and repeat from Build
			didActuallySpill = select();
		}
		return false;
	}

	private void build() {
		// Construct CFG
		cfg = new ControlFlowGraph(program);
		
		// Run live variable analysis
		LiveVariableAnalyzer lva = new LiveVariableAnalyzer(cfg);
		liveVariables = lva.getInMap();
		
		// Create interference graph
		graph = new InterferenceGraph(liveVariables);
	}

	private void simplify() {
		Set<Register> nodes = graph.getNodes();
		for (Register reg : nodes) {
			Map<Register,Set<Register>> neighbors = graph.getNeighbors();
			if (!reg.isMoveRelated() && !reg.isBuiltIn()
					&& neighbors.get(reg).size() < NUM_COLORS) {
				// Remove reg from interference graph
				graph.remove(reg);
				
				// Push reg onto stack
				regStack.push(reg);
				neighborStack.push(neighbors.get(reg));
			}
		}
	}
	
	/**
	 * @return	true if a mov was coalesced, false otherwise
	 */
	private boolean coalesce() {
		boolean result = false;
		Map<Register,Set<Register>> neighbors = graph.getNeighbors();
		for (Register reg : map.keySet()) {
			Set<Instruction> moves = map.get(reg);
			for (Instruction move : moves) {
				Register otherReg = otherRegister(move,reg);
				if (neighbors.get(reg).size() + neighbors.get(otherReg).size() < NUM_COLORS
						&& !neighbors.get(reg).contains(otherReg)
						&& !(reg.isBuiltIn() && otherReg.isBuiltIn())) {
					// Remove move 
					moves.remove(move);
					map.put(reg,moves);
					
					Set<Instruction> allInsts = secondmap.get(reg);
					allInsts.remove(move);
					secondmap.put(reg,allInsts);
					
					program.remove(move);
					
					// Replace all instances of otherReg with reg unless otherReg is built-in
					Register replacedReg;
					Register replacingReg;
					Set<Instruction> replacedMoves;
					Set<Instruction> replacedAll;
					if (otherReg.isBuiltIn()) {
						replacedReg = reg;
						replacingReg = otherReg;
						replacedMoves = moves;
						replacedAll = 
					} else {
						replacedReg = otherReg;
						replacingReg = reg;
					}
					// Remove replacedReg from globals
					Set<Instruction> replacedMoves = map.get(replaced);
					Set<Instruction>
					map.remove(replacedReg);
					// Replace operand of all related instructions
					for (Instruction inst : secondmap.get(replacedReg)) {	// Should automatically replace operands in program
						if (inst.getDest().equals(replacedReg)) {
							inst.setDest(replacingReg);
						} else {
							inst.setSrc(replacingReg);
						}
					}
					
					// Check if replacingReg is still move-related
					
					
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * @param	move	the move instruction
	 * @param	reg		the register you already know
	 * @return	the other register in the move instruction
	 */
	private Register otherRegister(Instruction move, Register reg) {
		if (move.getDest() == reg) {
			return (Register) move.getSrc();
		} else {
			return (Register) move.getDest();
		}
	}
	
	/**
	 * @return	true if a node was frozen
	 */
	private boolean freeze() {
		boolean result = false;
		// Choose a high-degree node
		Set<Register> nodes = graph.getNodes();
		Map<Register,Set<Register>> neighbors = graph.getNeighbors();
		Register frozen = null;
		for (Register reg : nodes) {
			if (!reg.isBuiltIn() && reg.isMoveRelated() 
					&& neighbors.get(reg).size() < NUM_COLORS) {
				frozen = reg;
				
				// TODO: change this!!
				Map<Register,Set<Instruction>> map = null;
				// Freeze all moves related to frozen
				frozen.setMoveRelated(false);
				Set<Instruction> frozenMoves = map.get(frozen);
				map.remove(frozen);
				for (Instruction frozenMove : frozenMoves) {
					Register relatedReg = otherRegister(frozenMove,frozen);
					Set<Instruction> relatedMoves = map.get(relatedReg);
					relatedMoves.remove(frozenMove);
					if (relatedMoves.size() == 0) {
						// Mark relatedReg as non-move-related
						relatedReg.setMoveRelated(false);
						map.remove(relatedReg);
					} else {
						map.put(relatedReg,relatedMoves);
					}
				}
				result = true;
				break;
			}
		}
		if (!result) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return	true if a node was spilt
	 */
	private boolean spill() {
		boolean result = false;
		
		
		return result;
	}
	
	/**
	 * @return	true if a node was frozen
	 */
	private boolean select() {
		boolean result = false;
		
		
		return result;
	}
}
