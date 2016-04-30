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
import jl2755.dataflow.InterferenceGraph;
import jl2755.dataflow.LiveVariableAnalyzer;

public class RegisterAllocator extends Optimization {
	
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
	
	private boolean Omc;
	
	public RegisterAllocator(boolean omc) {
		// Initialize globals
		funcToRegsUsed = new HashMap<String,Set<Register>>();
		regStack = new Stack<Register>();
		neighborStack = new Stack<Set<Register>>();
		Omc = omc;
	}
	
	@Override
	public boolean run(ControlFlowGraph cfg) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Algorithm for graph coloring with move coalescing.
	 * 
	 * @param 
	 */
	public List<Instruction> registerAllocation(List<Instruction> instructions,
			IRFuncDecl currentFunc) {
		// Clear all globals
		System.out.println("FUNC: "+currentFunc.getName());
		regStack.clear();
		neighborStack.clear();
		program = instructions;
		stackCounter = currentFunc.getNumSavedCalleeRegs();
		currentFunction = currentFunc.getABIName();
		int count = 0;
		boolean didCoalesce;
		boolean didFreeze;
		boolean didPotentiallySpill;
		boolean didActuallySpill = true;
		
//		// Construct CFG
//		cfg = constructCFG();
		
		while (didActuallySpill) {
			build();
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
		return program;
	}

	/**
	 * 	Build: Run LVA, create Interference Graph
	 */
	private void build() {
		// Construct CFG
		cfg = constructCFG();
		// Run live variable analysis
		LiveVariableAnalyzer lva = new LiveVariableAnalyzer(cfg);
		lva.analyze();
		nodeToLiveRegs = lva.getInMap();
		
		// Create interference graph
		graph = new InterferenceGraph(nodeToLiveRegs);
		for (Register r : graph.getNodes()) {
			if (regToMovInstructions.containsKey(r)) {
				r.setMoveRelated(true);
			}
		}
		Set<Register> removes = new HashSet<Register>();
		for (Register r : regToInstructions.keySet()) {
			if (!graph.getNodes().contains(r) && !r.isBuiltIn()) {
				// Flag to remove
				removes.add(r);
			}
		}
		for (Register r : removes) {
			List<Instruction> ri = new ArrayList<Instruction>();
			ri.addAll(regToInstructions.get(r));
			for (Instruction i : ri) {
				for (List<Instruction> is : regToInstructions.values()) {
					if (is.contains(i)) {
						is.remove(i);
					}
				}
				for (List<Instruction> mis : regToMovInstructions.values()) {
					if (mis.contains(i)) {
						mis.remove(i);
					}
				}
				program.remove(i);
			}
			regToInstructions.remove(r);
			regToMovInstructions.remove(r);
		}
//		System.out.println(graph.getEdges());
//		System.out.println(regToMovInstructions);
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
			program.remove(removeMove);
			// Remove move from replacing move list
			regToMovInstructions.get(replacingReg).remove(removeMove);
			// Remove move from replacingS instruction list
			regToInstructions.get(replacingReg).remove(removeMove);
			
			// Check for moves to itself and remove
			List<Instruction> removes = new ArrayList<Instruction>();
			for (Instruction move : regToMovInstructions.get(replacingReg)) {
				if (move.getDest().equals(move.getSrc())) {
					program.remove(move);
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
		Map<String, Integer> regToStack = new HashMap<String, Integer>();
		while (!regStack.empty()) {
			// Pop from stack
			Register reg = regStack.pop();
			Set<Register> neighbors = neighborStack.pop();
			
			// Add back to interference graph
			graph.add(reg, neighbors);
			System.out.println(reg);
			System.out.println(neighbors);
			// Attempt to color reg
			Register color = color(reg, edges.get(reg));
			if (color == null) {
				// Spill reg to stack
				actuallySpill(reg);
				System.out.println("HELLO");
				result = true;
			} else {
				// Set all related instruction to use color
				usedRegs.add(color);
				replace(reg, color, true);
			}
		}
		System.out.println("DONE");
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
		System.out.println(rand);
		assert(rand < NUM_COLORS);
		Register spillingReg = availableRegs[rand];
		
		Instruction saveToStack = new Instruction(Operation.PUSHQ,spillingReg);
		Instruction movFromStack = new Instruction(Operation.MOVQ,mem,spillingReg);
		Instruction movToStack = new Instruction(Operation.MOVQ,spillingReg,mem);
		Instruction restoreFromStack = new Instruction(Operation.POPQ,spillingReg);
//		regToStack.put(reg.getName(), add);
		for (Instruction inst : regToInstructions.get(reg)) {
			int index = program.indexOf(inst);
			program.add(index+1, restoreFromStack);
			program.add(index+1, movToStack);
			if (inst.getOp() != Operation.MOVQ) {
				program.add(index, movFromStack);
			};
			program.add(index, saveToStack);
			
			
			
//			List<Instruction> newInsts = 
//					addNecessaryInstruction(inst, regToStack, reg);
//			program.remove(index);
//			program.addAll(index, newInsts);
			
		}
		replace(reg, spillingReg, false);
		
		
		
		
//		Set<CFGNode> cfgNodes = new HashSet<CFGNode>();
//		cfgNodes.addAll(cfg.getAllNodes());
//		for (CFGNode n : cfgNodes) {
//			Instruction i = ((AACFGNode) n).getUnderlyingInstruction();
//			if (regToInstructions.get(reg).contains(i)) {
//				AACFGNode mFS = new AACFGNode(movFromStack);
//				AACFGNode mTS = new AACFGNode(movToStack);
//				AACFGNode newN = new AACFGNode(i);
//				
//				for (CFGNode pred : n.getPredecessors()) {
//					((AACFGNode) pred).replaceSuccessor((AACFGNode) n, mFS);;
//				}
//				mFS.addSuccessor(newN);
//				newN.addSuccessor(mTS);
//				cfg.remove(n);
//				if (n.getSuccessor1() != null) {
//					mTS.addSuccessor((AACFGNode) n.getSuccessor1());
//					if (n.getSuccessor2() != null) {
//						mTS.addSuccessor((AACFGNode) n.getSuccessor2());
//					}
//				}
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
	
	/**
	 * Inserts the necessary instructions to shuttle values to and from stack
	 * 
	 * @param instructions	the list of instructions for our program
	 * @param regToStack	the map of register names to the relative stack offset
	 * @return				list of instructions with correct insertions
	 */
	public List<Instruction> addNecessaryInstruction(
			Instruction currentInstruction, Map<String, Integer> regToStack,
			Register replaced, Register replacing) {
		Operand src = currentInstruction.getSrc();
		Operand dest = currentInstruction.getDest();
		
		List<Instruction> added = new ArrayList<Instruction>();
		
		Register rcx = new Register(RegisterName.RCX);
		Register rdx = new Register(RegisterName.RDX);
		Register r11 = new Register(RegisterName.R11);
		Register rbp = new Register(RegisterName.RBP);
		
		if (src == null || src instanceof Constant) {
			// push, pop, call, jumps, div?
			if (dest instanceof Register) {
				assert(((Register) dest).getType() == RegisterName.TEMP);
				
				String reg = ((Register) dest).getName();
				Memory mem;
				if (regToStack.containsKey(reg)) {
					int addr = regToStack.get(reg);
					mem = new Memory(new Constant(addr),rbp);
					if (currentInstruction.getOp() != Operation.MOVQ) {
						Instruction movToReg = new Instruction(Operation.MOVQ,mem,replacing);
						added.add(movToReg);
					}
				} else {
					int addr = -8*++stackCounter;
					mem = new Memory(new Constant(addr),rbp);
					regToStack.put(reg,addr);
				}
				added.add(currentInstruction);
				
				if (src != null) {
					Instruction movToMem = new Instruction(Operation.MOVQ,rcx,mem);
					added.add(movToMem);
				}
			} else if (dest instanceof Memory) {
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,rcx,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);
			} else {
				// dest is constant or label
				Operation op = currentInstruction.getOp();
				Operand label = currentInstruction.getDest();
				added.add(currentInstruction);
			}
		} else {
			// src is not null and is not constant
			if (dest instanceof Memory && src instanceof Register) {
				// src must be register that must be in stack
				if (((Register) src).getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(((Register) src).getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
					currentInstruction.setSrc(r11);
					added.add(movToReg1);
				}
				
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr2 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr2),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr3 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr3),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,rcx,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr3 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr3),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);			
			} else if (dest instanceof Register && src instanceof Register) {
				// dest and src are registers
				String regD = ((Register) dest).getName();
				String regS = ((Register) src).getName();
				Memory memD;
				Instruction movToMemD = null;
				if (((Register) dest).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(regD)) {
						int addrD = regToStack.get(regD);
						memD = new Memory(new Constant(addrD),rbp);
						if (currentInstruction.getOp() != Operation.MOVQ) {
							Instruction movToRegD = new Instruction(Operation.MOVQ,memD,rcx);
							added.add(movToRegD);
						}
					} else {
						// Need to create a new memory address
						int addrD = -8*++stackCounter;
						memD = new Memory(new Constant(addrD),rbp);
						regToStack.put(regD,addrD);
					}
					currentInstruction.setDest(rcx);
					movToMemD = new Instruction(Operation.MOVQ,rcx,memD);
				}
				if (((Register) src).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(regS)) {
						int addrS = regToStack.get(regS);
						Memory memS = new Memory(new Constant(addrS),rbp);
						Instruction movToRegS = new Instruction(Operation.MOVQ,memS,rdx);
						added.add(movToRegS);
					} else {
						System.out.println(currentInstruction);
						System.out.println(regS);
						System.out.println(regToStack);
						System.out.println("Access a register that hasn't been set!");
						assert(false);
					}
					currentInstruction.setSrc(rdx);
				}
				added.add(currentInstruction);
				if (movToMemD != null) {
					added.add(movToMemD);
				}
			} else if (dest instanceof Register && src instanceof Memory){	
				// dest is register, src is memory
				String reg = ((Register) dest).getName();
				Memory mem1;
				Instruction movToMem1 = null;
				if (((Register) dest).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(reg)) {
						int addr1 = regToStack.get(reg);
						mem1 = new Memory(new Constant(addr1),rbp);
						if (currentInstruction.getOp() != Operation.MOVQ) {
							Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
							added.add(movToReg1);
						}
					} else {
						int addr1 = -8*++stackCounter;
						mem1 = new Memory(new Constant(addr1),rbp);
						regToStack.put(reg,addr1);
					}
					currentInstruction.setDest(r11);
					movToMem1 = new Instruction(Operation.MOVQ,r11,mem1);
				}
				
				Memory memOp = (Memory) src;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(regBase.getName());
					Memory mem2 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem2,rcx);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem3 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem3,rdx);
						newMem = new Memory(cons,rcx,rdx,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,rcx,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
				}
				currentInstruction.setSrc(newMem);
				added.add(currentInstruction);
				if (movToMem1 != null) {
					added.add(movToMem1);
				}
			} else {
				// dest is constant, src is not
				Instruction movToReg1;
				if (src instanceof Memory) {
					Memory memOp = (Memory) src;
					Register regBase = memOp.getRegisterBase();
					Register regOff = memOp.getRegisterOffset();
					Constant cons = memOp.getConstantOffset();
					Memory newMem;
					
					int addr1 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
						added.add(movToReg2);
					} else if (regBase.getType() == RegisterName.TEMP) {
						if (regOff == null) {
							newMem = new Memory(cons,rcx);
						} else {
							newMem = new Memory(cons,rcx,regOff,memOp.getConstantFactor());
						}
						added.add(movToReg1);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
					currentInstruction.setSrc(newMem);
				} else {
					// src uses a built-in register
					if (((Register) src).getType() != RegisterName.TEMP) {
						added.add(currentInstruction);
						return added;
					}
					// src uses a temp register
					int addr1 = regToStack.get(((Register) src).getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
					currentInstruction.setSrc(r11);
					added.add(movToReg1);
				}
				added.add(currentInstruction);
			}
		}
		return added;
	}
	
}
