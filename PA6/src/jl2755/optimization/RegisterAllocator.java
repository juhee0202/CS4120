package jl2755.optimization;

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

import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
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
import jl2755.controlflow.OptimizationGraph;
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
	
	/** Name of current function. */
	private String currentFunction;
	
	/** Map of CFGNodes to live registers. */
	private Map<AACFGNode, Set<Register>> nodeToLiveRegs;
	
	/** Map of CFGNodes to defs. */
	private Map<AACFGNode, Register> defs;

	/** Stack to use for register allocation. */
	private Stack<Register> regStack;
	
	/** Stack to keep track of neighbors. */
	private Stack<Set<Register>> neighborStack;
	
	/** Map of register to list of instructions containing the register */
	private Map<Register, List<Instruction>> regToInstructions;
	
	/** Map of register to its unfrozen mov instruction */
	private Map<Register, List<Instruction>> regToMovInstructions;
	
	/** Set of instructions to remove from program */
	private Set<Instruction> remove;
	
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
	
	private boolean Omc;
	
	public RegisterAllocator(boolean omc) {
		// Initialize globals
		funcToRegsUsed = new HashMap<String,Set<Register>>();
		regStack = new Stack<Register>();
		neighborStack = new Stack<Set<Register>>();
		Omc = omc;
		remove = new HashSet<Instruction>();
	}
	
	/**
	 * Algorithm for graph coloring with move coalescing.
	 * 
	 * @param 
	 */
	public List<Instruction> registerAllocation(List<Instruction> instructions,
			IRFuncDecl currentFunc) {
		// Clear all globals
		regStack.clear();
		neighborStack.clear();
		program = instructions;
		stackCounter = currentFunc.getNumSavedCalleeRegs();
		currentFunction = currentFunc.getABIName();

		boolean didCoalesce;
		boolean didFreeze;
		boolean didPotentiallySpill;
		boolean didActuallySpill = true;
		
		while (didActuallySpill) {
			while(build());
			didPotentiallySpill = true;
			while (didPotentiallySpill) {
				didFreeze = true;
				while (didFreeze) {
					didCoalesce = true;
					while (didCoalesce) {
						simplify();
						if (Omc) {
							didCoalesce = coalesce();
						} else {
							didCoalesce = false;
						}
					}
					didFreeze = freeze();
				}
				didPotentiallySpill = spill();	
			}
			didActuallySpill = select();
		}
		cleanUp();
		stackCounter++;
		for (Instruction i : remove) {
			program.remove(i);
		}
		return program;
	}

	/**
	 * 	Build: Run LVA, create Interference Graph
	 */
	private boolean build() {
		// Construct CFG
		cfg = constructCFG();
		remove.clear();
		
		// Run live variable analysis
		LiveVariableAnalyzer lva = new LiveVariableAnalyzer(cfg);
		lva.analyze();
		nodeToLiveRegs = lva.getInMap();
		defs = lva.getDefs();
		
		// Create interference graph
		graph = new InterferenceGraph(nodeToLiveRegs);
		
		// If there is a register in the def of a node that is not live coming out, remove it
		boolean result = false;
		Set<CFGNode> nodes = new HashSet<CFGNode>();
		nodes.addAll(cfg.getAllNodes());
		for (CFGNode n : nodes) {
			Set<Register> union = new HashSet<Register>();
			for (CFGNode succ : n.getSuccessors()) {
				union.addAll(nodeToLiveRegs.get(succ));
			}
			Instruction i = ((AACFGNode) n).getUnderlyingInstruction();
			Operation op = i.getOp();
			if (i.getDest() == null) {
				continue;
			}
			if (i.getDest() instanceof Memory) {
				continue;
			} else if (i.getDest() instanceof Register && ((Register) i.getDest()).isBuiltIn()) {
				continue;
			} else if (op != Operation.MOVQ) {
				continue;
			}
			if (defs.get(n) != null && !union.contains(defs.get(n))) {
				program.remove(i);
				System.out.println(i);
				result = true;
			}
		}
		return result;
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
				op = instr.getOp();
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
					if (prev != null) {
						prev.addSuccessor(curr);
						prev = null;
					}
					continue;
				}
			}
			
			// link prev to curr
			if (prev != null && prev.underlyingInstruction.getOp() != Operation.RET) {
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
		} else if (src instanceof Memory) {
			Register base = ((Memory) src).getRegisterBase();
			Register offset = ((Memory) src).getRegisterOffset();
			List<Instruction> list;
			if (regToInstructions.containsKey(base)) {
				list = regToInstructions.get(base);
			} else {
				list = new ArrayList<Instruction>();
			}
			list.add(instr);
			regToInstructions.put(base, list);
			if (offset != null) {
				if (regToInstructions.containsKey(offset)) {
					list = regToInstructions.get(offset);
				} else {
					list = new ArrayList<Instruction>();
				}
				list.add(instr);
				regToInstructions.put(offset, list);
			}
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
		} else if (dest instanceof Memory) {
			Register base = ((Memory) dest).getRegisterBase();
			Register offset = ((Memory) dest).getRegisterOffset();
			List<Instruction> list;
			if (regToInstructions.containsKey(base)) {
				list = regToInstructions.get(base);
			} else {
				list = new ArrayList<Instruction>();
			}
			list.add(instr);
			regToInstructions.put(base, list);
			if (offset != null) {
				if (regToInstructions.containsKey(offset)) {
					list = regToInstructions.get(offset);
				} else {
					list = new ArrayList<Instruction>();
				}
				list.add(instr);
				regToInstructions.put(offset, list);
			}
		}
		if (instr.isMoveWithTwoRegs()) {
			((Register) src).setMoveRelated(true);
			((Register) dest).setMoveRelated(true);
			List<Instruction> srcList;
			if (regToMovInstructions.containsKey(src)) {
				srcList = regToMovInstructions.get(src);
			} else {
				srcList = new ArrayList<Instruction>();
			}
			srcList.add(instr);
			regToMovInstructions.put((Register) src, srcList);
			
			List<Instruction> destList;
			if (regToMovInstructions.containsKey(dest)) {
				destList = regToMovInstructions.get(dest);
			} else {
				destList = new ArrayList<Instruction>();
			}
			destList.add(instr);
			regToMovInstructions.put((Register) dest, destList);
		}
	}
	
//	private void pushToStack(Register reg) {
//		regStack.push(reg);
//		
//	}

	/**
	 * 	Simplify: Push onto stack all low-degree non-move-related nodes
	 */
	private void simplify() {
		Set<Register> nodes = graph.getNodes();
		Set<Register> remove = new HashSet<Register>();
		for (Register reg : nodes) {
			Map<Register,Set<Register>> edges = graph.getEdges();
			if (!regToMovInstructions.containsKey(reg) && !reg.isBuiltIn()
					&& edges.get(reg).size() < NUM_COLORS) {
				// Push reg onto stack
				regStack.push(reg);
				neighborStack.push(edges.get(reg));

				// Remove reg from interference graph
				remove.add(reg);
			}
		}
		for (Register r : remove) {
			graph.remove(r);
//			regToMovInstructions.remove(r);
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
		Set<Register> nodes = graph.getNodes();
		Map<Register,Set<Register>> edges = graph.getEdges();
		Instruction removeMove = null;
		Register removeReg1 = null;
		Register removeReg2 = null;
		Set<Register> totalEdges;
		outer:
		for (Register reg : regToMovInstructions.keySet()) {
			if (!nodes.contains(reg)) {
				continue;
			}
			List<Instruction> moves = regToMovInstructions.get(reg);
			for (Instruction move : moves) {
				Register otherReg = otherRegister(move,reg);
				if (!nodes.contains(otherReg) || edges.get(reg).contains(otherReg)) {
					continue;
				}
				totalEdges = new HashSet<Register>();
				totalEdges.addAll(edges.get(reg));
				totalEdges.addAll(edges.get(otherReg));
				if (totalEdges.size() < NUM_COLORS &&
						!(reg.isBuiltIn() && otherReg.isBuiltIn())) {
					removeMove = move;
					removeReg1 = reg;
					removeReg2 = otherReg;
					
					result = true;
					break outer;
				}
			}
		}
		if (result) {
			// Replace all instances of otherReg with reg unless otherReg is built-in
			Register replacedReg;
			Register replacingReg;
			if (removeReg2.isBuiltIn()) {
				assert(!removeReg1.isBuiltIn());
				replacedReg = removeReg1;
				replacingReg = removeReg2;
			} else {
				replacedReg = removeReg2;
				replacingReg = removeReg1;
			}			
			
			// Replace replacedReg of all related instructions with replacingReg
			replace(replacedReg, replacingReg, true);
			
			// Converge instruction lists
			regToInstructions.get(replacingReg).addAll(regToInstructions.get(replacedReg));
			regToInstructions.remove(replacedReg);
			// Converge move lists
			regToMovInstructions.get(replacingReg).addAll(regToMovInstructions.get(replacedReg));
			regToMovInstructions.remove(replacedReg);
			
			// Remove move from program
//			program.remove(removeMove);
			remove.add(removeMove);
			// Remove move from replacing move list
			regToMovInstructions.get(replacingReg).remove(removeMove);
			// Remove move from replacingS instruction list
			regToInstructions.get(replacingReg).remove(removeMove);
			
			// Check for moves to itself and remove
			List<Instruction> removes = new ArrayList<Instruction>();
			for (Instruction move : regToMovInstructions.get(replacingReg)) {
				if (move.getDest().equals(move.getSrc())) {
//					program.remove(move);
					remove.add(move);
					regToInstructions.get(replacingReg).remove(move);
					removes.add(move);
				}
			}
			for (Instruction m : removes) {
				regToMovInstructions.get(replacingReg).remove(m);
			}
			
			// Check if replacingReg is still move-related
			if (regToMovInstructions.get(replacingReg).size() == 0) {
//				replacingReg.setMoveRelated(false);
				regToMovInstructions.remove(replacingReg);
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
		if (move.getDest().equals(reg)) {
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
		for (Register reg : nodes) {
			if (!reg.isBuiltIn() && regToMovInstructions.containsKey(reg)
					&& edges.get(reg).size() < NUM_COLORS) {
				// Freeze all moves related to frozen reg
				List<Instruction> insts = new ArrayList<Instruction>();
				insts.addAll(regToMovInstructions.get(reg));
				for (Instruction frozenMove : insts) {
					Register relatedReg = otherRegister(frozenMove,reg);
					if (regToMovInstructions.containsKey(relatedReg)) {
						List<Instruction> relatedMoves = regToMovInstructions.get(relatedReg);
						relatedMoves.remove(frozenMove);
						if (relatedMoves.size() == 0) {
							// Mark relatedReg as non-move-related
							regToMovInstructions.remove(relatedReg);
						}
					}
				}
				
				regToMovInstructions.remove(reg);
				
				result = true;
				break;
			}
		}
		return result;
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
				regToMovInstructions.remove(highest);
//				highest.setMoveRelated(false);
				result = true;
				break;
			} else if (!highest.isBuiltIn()) {
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
		Set<Register> usedRegs = new HashSet<Register>();
		Set<Register> nodes = graph.getNodes();
		Map<Register, Set<Register>> edges = graph.getEdges();

		for (Register preColored : nodes) {
			assert(preColored.getType() != RegisterName.TEMP);
			usedRegs.add(preColored);
		}
		while (!regStack.empty()) {
			// Pop from stack
			Register reg = regStack.pop();
			Set<Register> neighbors = neighborStack.pop();
			
			// Add back to interference graph
			graph.add(reg, neighbors);
			
			// Attempt to color reg
			Register color = color(reg, edges.get(reg));
			if (color == null) {
				// Spill reg to stack
				actuallySpill(reg);
				result = true;
			} else {
				// Set all related instruction to use color
				usedRegs.add(color);
				replace(reg, color, true);
			}
		}
		funcToRegsUsed.put(currentFunction, usedRegs);
		return result;
	}

	private void actuallySpill(Register reg) {
		// Choose stack location
		int add = -8*++stackCounter;
		Constant addr = new Constant(add);
		Memory mem = new Memory(addr,RBP);
		
		// Choose a random register
		int rand = (int) Math.round((NUM_COLORS-1)*Math.random());
		assert(rand < NUM_COLORS);
		Register spillingReg = availableRegs[rand];
		
		// Set up shuttling instructions
		Instruction saveToStack = new Instruction(Operation.PUSHQ,spillingReg);
		Instruction movFromStack = new Instruction(Operation.MOVQ,mem,spillingReg);
		Instruction movToStack = new Instruction(Operation.MOVQ,spillingReg,mem);
		Instruction restoreFromStack = new Instruction(Operation.POPQ,spillingReg);
		List<Instruction> list = new ArrayList<Instruction>();
		
		for (Instruction inst : regToInstructions.get(reg)) {
			list.add(saveToStack);
			list.add(movToStack);
			list.add(restoreFromStack);
			Operand dest = inst.getDest();
			Operand src = inst.getSrc();
			boolean isDest = false;
			if (dest instanceof Register && dest.equals(reg)) {
				inst.setDest(spillingReg);
				isDest = true;
			} else if (src instanceof Register && src.equals(reg)) {
				inst.setSrc(spillingReg);
			} else {
				if (dest instanceof Memory) {
					Memory oldMem = (Memory) dest;
					Register base = oldMem.getRegisterBase();
					Register off = oldMem.getRegisterOffset();
					if (base.equals(reg)) {
						oldMem.setRegisterBase(spillingReg);
					}
					if (off != null && off.equals(reg)) {
						oldMem.setRegisterOffset(spillingReg);
					}
				} else {
					assert(src instanceof Memory);
					Memory oldMem = (Memory) src;
					Register base = oldMem.getRegisterBase();
					Register off = oldMem.getRegisterOffset();
					if (base.equals(reg)) {
						oldMem.setRegisterBase(spillingReg);
					}
					if (off != null && off.equals(reg)) {
						oldMem.setRegisterOffset(spillingReg);
					}
				}
			}
			int i = 1;
			// if instruction is not move where reg is dest, shuttle from stack
			if (! (inst.getOp() == Operation.MOVQ && isDest) ) {
				list.add(i++, movFromStack);
			}
			list.add(i, inst);
			int index = program.indexOf(inst);
			program.remove(inst);
			program.addAll(index, list);
			list.clear();
		}		
		graph.remove(reg);
//		int i = 0;
//		while (i < program.size() - 1) {
//			if (program.get(i).getOp() == Operation.POPQ &&
//					program.get(i+1).getOp() == Operation.PUSHQ &&
//					program.get(i).getDest() == program.get(i+1).getDest()) {
//				program.remove(i);
//				program.remove(i);
//			} else {
//				i++;
//			}
//		}
	}

	/**
	 * Attempts to color the given register
	 * 
	 * @param reg	the given register to color
	 * @param neighbors	the set of neighbors of reg
	 * @return		the new register to use if successfully colored
	 * 				null otherwise
	 */
	private Register color(Register reg, Set<Register> neighbors) {
		// Check colors of neighbors
		Set<Register> colors = new HashSet<Register>();
		colors.addAll(Arrays.asList(availableRegs));
		for (Register neighbor : neighbors) {
			colors.remove(neighbor);
		}
		for (CFGNode n : cfg.getAllNodes()) {
			if (nodeToLiveRegs.get(n).contains(reg)) {
				colors.remove(defs.get(n));
			}
		}
		if (colors.size() > 0) {
			for (Register color : colors) {
				return color;
			}
		}
		return null;
	}
	
	/**
	 * Replaces all instructions that use replaced with replacing.
	 * 
	 * @param replace	the register to replace
	 * @param replacing	the register that replaces
	 */
	private void replace(Register replaced, Register replacing, boolean color) {
		// Replace uses of replaced in instructions
		Register temp = (color) ? replaced : replacing;
		for (Instruction related : regToInstructions.get(temp)) {
			Operand src = related.getSrc();
			Operand dest = related.getDest();
			if (src instanceof Register) {
				if (replaced.equals(src)) {
					related.setSrc(replacing);
				}
			} else if (src instanceof Memory) {
				Memory mem = (Memory) src;
				Register base = mem.getRegisterBase();
				Register offset = mem.getRegisterOffset();
				if (replaced.equals(base)) {
					mem.setRegisterBase(replacing);
				} else if (offset != null && replaced.equals(offset)) {
					mem.setRegisterOffset(replacing);
				}
			}
			if (dest instanceof Register) {
				if (replaced.equals(dest)) {
					related.setDest(replacing);
				}
			} else if (dest instanceof Memory) {
				Memory mem = (Memory) dest;
				Register base = mem.getRegisterBase();
				Register offset = mem.getRegisterOffset();
				if (replaced.equals(base)) {
					mem.setRegisterBase(replacing);
				} else if (offset != null && replaced.equals(offset)) {
					mem.setRegisterOffset(replacing);
				}
			}
		}
		
		// Replace uses of replace in interference graph
		Set<Register> nodes = graph.getNodes();
		nodes.remove(replaced);
		nodes.add(replacing);
		Map<Register, Set<Register>> edges = graph.getEdges();
		if (edges.containsKey(replacing)) {
			edges.get(replacing).addAll(edges.get(replaced));
		} else {
			edges.put(replacing, edges.get(replaced));
		}
		edges.remove(replaced);
		for (Set<Register> neighbors : edges.values()) {
			if (neighbors.contains(replaced)) {
				neighbors.remove(replaced);
				neighbors.add(replacing);
			}
		}
		
		// Replace uses of replace in stacks
		if (regStack.contains(replaced)) {
			assert(false);
			int index = regStack.indexOf(replaced);
			regStack.add(index, replacing);
		}
		for (Object o : neighborStack.toArray()) {
			Set<Register> neighbors = (Set<Register>) o;
			if (neighbors.contains(replaced)) {
				neighbors.remove(replaced);
				neighbors.add(replacing);
			}
		}
	}

	public int getStackCounter() {
		return stackCounter;
	}
	
	/**
	 * Cleans up any unallocated temps (dead code)
	 */
	private void cleanUp() {
		List<Instruction> removes = new ArrayList<Instruction>();
		for (Instruction inst : program) {
			Operand source = inst.getSrc();
			Operand target = inst.getDest();
			Set<Register> sourceUsed = new HashSet<Register>();
			Set<Register> targetUsed = new HashSet<Register>();
			if (source != null) {
				sourceUsed.addAll(source.getRegistersUsed());
			}
			if (target != null) {
				targetUsed.addAll(target.getRegistersUsed());
			}
			boolean remove = false;
			for (Register rS : sourceUsed) {
				if (rS.getType() == RegisterName.TEMP) {
					remove = true;
					break;
				}
			}
			for (Register rT : targetUsed) {
				if (rT.getType() == RegisterName.TEMP) {
					remove = true;
					break;
				}
			}
			if (remove) {
				removes.add(inst);
			}
		}
		for (Instruction i : removes) {
			program.remove(i);
		}
	}
	
}
