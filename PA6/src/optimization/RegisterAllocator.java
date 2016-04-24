package optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import jl2755.assembly.Constant;
import jl2755.assembly.Instruction;
import jl2755.assembly.Register;
import jl2755.assembly.Instruction.Operation;
import jl2755.assembly.Label;
import jl2755.assembly.Memory;
import jl2755.assembly.Operand;
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
	private Map<String, Set<Register>> funcToRegsUsed;
	
	/** Map of CFGNodes to live registers. */
	private Map<AACFGNode, Set<Register>> nodeToLiveRegs;

	/** Stack to use for register allocation. */
	private Stack<Register> regStack;
	
	/** Stack to keep track of neighbors. */
	private Stack<Set<Register>> neighborStack;
	
	/** Map of register to list of instructions containing the register */
	private Map<Register, List<Instruction>> regToInstructions;
	
	/** Map of register to its unfrozen mov instruction */
	private Map<Register, List<Instruction>> regToMovInstructions;
	
	/** Comparator for registers based on degree. */
	private final Comparator<Register> DEGREE_COMPARATOR = 
			new Comparator<Register>() {
		public int compare(Register r1, Register r2) {
			Map<Register, Set<Register>> edges = graph.getEdges();
			return edges.get(r1).size() - edges.get(r2).size();
		}
	};
	
	// Built-in registers to use for allocation
	private static final Register RAX = new Register(RegisterName.RAX);
	private static final Register RBX = new Register(RegisterName.RBX);
	private static final Register RCX = new Register(RegisterName.RCX);
	private static final Register RDX = new Register(RegisterName.RDX);
	private static final Register RSI = new Register(RegisterName.RSI);
	private static final Register RDI = new Register(RegisterName.RDI);
	private static final Register R8  = new Register(RegisterName.R8);
	private static final Register R9  = new Register(RegisterName.R9);
	private static final Register R10 = new Register(RegisterName.R10);
	private static final Register R11 = new Register(RegisterName.R11);
	private static final Register R12 = new Register(RegisterName.R12);
	private static final Register R13 = new Register(RegisterName.R13);
	private static final Register R14 = new Register(RegisterName.R14);
	private static final Register R15 = new Register(RegisterName.R15);
	
	private static final Register[] availableRegs = 
		{RAX,RBX,RCX,RDX,RSI,RDI,R8,R9,R10,R11,R12,R13,R14,R15};
	
	// Built-in registers off-limits for allocation
	private static final Register RSP = new Register(RegisterName.RSP);
	private static final Register RBP = new Register(RegisterName.RBP);
	
	private static final int NUM_COLORS = 14;
	
	private int stackCounter;
	
	public RegisterAllocator() {
		// Initialize globals
		funcToRegsUsed = new HashMap<String,Set<Register>>();
		regStack = new Stack<Register>();
		neighborStack = new Stack<Set<Register>>();
	}
	
	/**
	 * Algorithm for graph coloring with move coalescing.
	 * 
	 * @param 
	 */
	public List<Instruction> registerAllocation(List<Instruction> instructions) {
		// Clear all globals
		funcToRegsUsed.clear();
		regStack.clear();
		neighborStack.clear();
		program = instructions;
		stackCounter = 0;
		
		boolean didCoalesce;
		boolean didFreeze;
		boolean didPotentiallySpill;
		boolean didActuallySpill = true;
		while (didActuallySpill) {
			build();
			didPotentiallySpill = true;
			while (didPotentiallySpill) {
				didFreeze = true;
				while (didFreeze) {
					didCoalesce = true;
					while (didCoalesce) {
						simplify();
						didCoalesce = coalesce();
					}
					didFreeze = freeze();
				}
				didPotentiallySpill = spill();		
			}
			didActuallySpill = select();
		}
		return program;
	}

	/**
	 * 	Build: Construct CFG, run LVA, create Interference Graph
	 */
	private void build() {
		// Construct CFG
		cfg = constructCFG();
		
		// Run live variable analysis
		LiveVariableAnalyzer<Register> lva = new LiveVariableAnalyzer<Register>(cfg);
		nodeToLiveRegs = lva.getInMap();
		
		// Create interference graph
		graph = new InterferenceGraph(nodeToLiveRegs);
	}
	
	/**
	 * Construct a ControlFlowGraph using instructions
	 * Update reg2instructions and reg2regs maps
	 * @return a CFG of the list of instructions
	 */
	private ControlFlowGraph constructCFG() {
		/* Initialize the maps */
		regToInstructions = new HashMap<Register,List<Instruction>>();
		regToMovInstructions = new HashMap<Register,List<Instruction>>();
		
		/* Set of all CFG nodes */
		Set<CFGNode> nodeSet = new HashSet<CFGNode>();
		
		/* Maps a jump related node to the corresponding label */
		Map<AACFGNode, Label> node2label = new HashMap<AACFGNode,Label>();
		
		/* Maps a label to the node of instruction that immediately follows */
		Map<Label, AACFGNode> label2node = new HashMap<Label,AACFGNode>();
		
		/* Get the head node */
		int first_instr_index = 0;
		Instruction firstInstr = program.get(first_instr_index);
		while (firstInstr.getOp() == Operation.LABEL) {
			firstInstr = program.get(++first_instr_index);
		}
		updateRegisterMaps(firstInstr);
		AACFGNode head = new AACFGNode(firstInstr);
		
		nodeSet.add(head);
		
		/* Construct CFG */
		AACFGNode prev = head;
		for (int i = first_instr_index+1; i < program.size(); i++) {
			Instruction instr = program.get(i);
			Operation op = instr.getOp();
			AACFGNode curr = new AACFGNode(instr);
			
			// if it's a label instruction,
			// get the next label and put the pair in label2node map
			if (op == Operation.LABEL) {
				Label label = (Label) instr.getDest();
				instr = program.get(++i);
				curr = new AACFGNode(instr);
				label2node.put(label, curr);
			}
			
			// update register maps
			updateRegisterMaps(instr);
			// add node to nodeSet 
			nodeSet.add(curr);
			
			// if it's a jump instruction,
			// put the node, label pair in node2label map
			if (Operation.isJumpInstruction(op)) {
				Label label = (Label) instr.getDest();
				node2label.put(curr,label);
				
				if (op == Operation.JMP) {
					prev = null;
					continue;
				}
			}
			
			// link prev to curr
			if (prev != null) {
				prev.addSuccessor(curr);
			}
			prev = curr;
		}
		
		/* Link jumps */
		for (Entry<AACFGNode, Label> entry : node2label.entrySet()) {
			AACFGNode node1 = entry.getKey();
			Label label = entry.getValue();
			AACFGNode node2 = label2node.get(label);
			node1.addSuccessor(node2);
		}
		
		return new ControlFlowGraph(nodeSet, head);
	}
	
	/**
	 * Updates reg2instructions & reg2movInstructions maps
	 * @param Instruction instr
	 */
	private void updateRegisterMaps(Instruction instr) {
		Operand src = instr.getSrc();
		Operand dest = instr.getDest();
		
		if (src instanceof Register) {
			List<Instruction> list;
			if (regToInstructions.containsKey(src)) {
				list = regToInstructions.get(src);
			} else {
				list = new ArrayList<Instruction>();
			}
			list.add(instr);
			regToInstructions.put((Register) src, list);
		}
		
		if (dest instanceof Register) {
			List<Instruction> list;
			if (regToInstructions.containsKey(dest)) {
				list = regToInstructions.get(dest);
			} else {
				list = new ArrayList<Instruction>();
			}
			list.add(instr);
			regToInstructions.put((Register) dest,list);
		} 
		
		if (instr.isMoveWithTwoRegs()) {
			List<Instruction> srcList;
			if (regToMovInstructions.containsKey(src)) {
				srcList = regToMovInstructions.get(src);
			} else {
				srcList = new ArrayList<Instruction>();
			}
			srcList.add(instr);
			regToInstructions.put((Register) src, srcList);
			
			List<Instruction> destList;
			if (regToMovInstructions.containsKey(dest)) {
				destList = regToMovInstructions.get(dest);
			} else {
				destList = new ArrayList<Instruction>();
			}
			destList.add(instr);
			regToInstructions.put((Register) dest, destList);
		}
	}

	/**
	 * 	Simplify: Push onto stack all low-degree non-move-related nodes
	 */
	private void simplify() {
		Set<Register> nodes = graph.getNodes();
		for (Register reg : nodes) {
			Map<Register,Set<Register>> edges = graph.getEdges();
			if (!reg.isMoveRelated() && !reg.isBuiltIn()
					&& edges.get(reg).size() < NUM_COLORS) {
				// Push reg onto stack
				regStack.push(reg);
				neighborStack.push(edges.get(reg));
				
				// Remove reg from interference graph
				graph.remove(reg);
			}
		}
	}
	
	/**
	 *  Coalesce: Conservatively coalesce move-related nodes
	 *	 loop through CFGNodes and find mov with two regs
	 *	 if possible, coalesce and repeat simplify and coalesce
	 *	 else, move to freeze
	 *
	 * @return	true if a mov was coalesced, false otherwise
	 */
	private boolean coalesce() {
		boolean result = false;
		Map<Register,Set<Register>> edges = graph.getEdges();
		for (Register reg : regToMovInstructions.keySet()) {
			List<Instruction> moves = regToMovInstructions.get(reg);
			for (Instruction move : moves) {
				Register otherReg = otherRegister(move,reg);
				if (edges.get(reg).size() + edges.get(otherReg).size() < NUM_COLORS
						&& !edges.get(reg).contains(otherReg)
						&& !(reg.isBuiltIn() && otherReg.isBuiltIn())) {
					// Remove move from program
					program.remove(move);
					// Remove move from both move lists
					regToMovInstructions.get(reg).remove(move);
					regToMovInstructions.get(otherReg).remove(move);
					// Remove move from both instruction lists
					regToInstructions.get(reg).remove(move);
					regToInstructions.get(otherReg).remove(move);
					
					// Replace all instances of otherReg with reg unless otherReg is built-in
					Register replacedReg;
					Register replacingReg;
					if (otherReg.isBuiltIn()) {
						replacedReg = reg;
						replacingReg = otherReg;
					} else {
						replacedReg = otherReg;
						replacingReg = reg;
					}
					
					// Replace replacedReg of all related instructions with replacingReg
					List<Instruction> replacedInsts = regToInstructions.get(replacedReg);
					for (Instruction inst : replacedInsts) {	// Should automatically replace operands in program
						if (inst.getDest().equals(replacedReg)) {
							inst.setDest(replacingReg);
						} else {
							inst.setSrc(replacingReg);
						}
					}
					// Converge instruction lists
					regToInstructions.get(replacingReg).addAll(replacedInsts);
					regToInstructions.remove(replacedReg);
					// Converge move lists
					List<Instruction> replacedMovs = regToMovInstructions.get(replacedReg);
					regToMovInstructions.get(replacingReg).addAll(replacedMovs);
					regToMovInstructions.remove(replacedReg);
					
					// Check if replacingReg is still move-related
					if (regToMovInstructions.get(replacingReg).size() == 0) {
						replacingReg.setMoveRelated(false);
					}
					
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
	 *  Freeze: abandon coalescing a move-related node
	 *	 Choose a low-degree move-related node
	 *	 Freeze the node and other related nodes
	 *	 Repeat Simplify and Coalesce until nothing to freeze
	 * 
	 * @return	true if a node was frozen
	 */
	private boolean freeze() {
		boolean result = false;
		// Choose a high-degree node
		Set<Register> nodes = graph.getNodes();
		Map<Register,Set<Register>> edges = graph.getEdges();
		Register frozen = null;
		for (Register reg : nodes) {
			if (!reg.isBuiltIn() && reg.isMoveRelated() 
					&& edges.get(reg).size() < NUM_COLORS) {
				frozen = reg;
				
				// Freeze all moves related to frozen
				frozen.setMoveRelated(false);
				List<Instruction> frozenMoves = regToMovInstructions.get(frozen);
				regToMovInstructions.remove(frozen);
				for (Instruction frozenMove : frozenMoves) {
					Register relatedReg = otherRegister(frozenMove,frozen);
					List<Instruction> relatedMoves = regToMovInstructions.get(relatedReg);
					relatedMoves.remove(frozenMove);
					if (relatedMoves.size() == 0) {
						// Mark relatedReg as non-move-related
						relatedReg.setMoveRelated(false);
						regToMovInstructions.remove(relatedReg);
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
	 *  Spill: select a significant-degree node for spilling and push to stack
	 *	 Repeat from Simplify until only pre-colored nodes remain
	 * 
	 * @return	true if a node was spilled
	 */
	private boolean spill() {
		boolean result = false;
		Set<Register> nodes = graph.getNodes();
		Map<Register,Set<Register>> edges = graph.getEdges();
		
		// Sort the nodes by degree
		List<Register> sorted = new ArrayList<Register>(nodes);
		Collections.sort(sorted, DEGREE_COMPARATOR);

		// Choose highest-degree node
		int lastIndex = sorted.size() - 1;
		for (int i = lastIndex; i >= 0; i--) {
			Register highest = sorted.get(i);
			if (!highest.isBuiltIn() && edges.get(highest).size() >= NUM_COLORS) {
				// Push highest onto stack
				regStack.push(highest);
				neighborStack.push(edges.get(highest));
				
				// Remove highest from interference graph
				graph.remove(highest);
					
				result = true;
				break;
			}
		}
		return result;
	}
		
	/**
	 *  Select: Pop from stack, assigning colors
	 *	 If cannot assign, spill to stack, rewrite code, and repeat from Build
	 * 
	 * @return	true if a node was actually spilled
	 */
	private boolean select() {
		boolean result = false;
		Set<Register> nodes = graph.getNodes();
		Map<Register,Set<Register>> edges = graph.getEdges();
		for (Register reg : regStack) {
			// Attempt to color reg
			if (!color(reg,edges.get(reg))) {
				// Spill reg to stack
				Constant addr = new Constant(-8*++stackCounter);
				Memory mem = new Memory(addr,RBP);
				Instruction movFromStack = new Instruction(Operation.MOVQ,mem,reg);
				Instruction movToStack = new Instruction(Operation.MOVQ,reg,mem);
				for (Instruction inst : regToInstructions.get(reg)) {
					int index = program.indexOf(inst);
					program.add(index+1, movToStack);
					program.add(index, movFromStack);
				}
				result = true;
			}
		}
		return result;
	}

	/**
	 * Attempts to color the given register
	 * 
	 * @param reg	the given register to color
	 * @param neighbors	the set of neighbors of reg
	 * @return		true if successfully colored
	 * 				false otherwise
	 */
	private boolean color(Register reg, Set<Register> neighbors) {
		// Check colors of neighbors
		Set<Register> colors = new HashSet<Register>();
		colors.addAll(Arrays.asList(availableRegs));
		for (Register neighbor : neighbors) {
			colors.remove(neighbor);
		}
		if (colors.size() > 0) {
			
		}
		
		return false;
	}
}
