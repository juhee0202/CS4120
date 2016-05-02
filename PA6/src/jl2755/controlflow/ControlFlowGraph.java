package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import jl2755.assembly.Instruction;
import jl2755.assembly.Instruction.Operation;
import jl2755.assembly.Label;

public class ControlFlowGraph implements OptimizationGraph{
	
	private Set<CFGNode> allNodes; // be careful. this isn't updated properly.
	private CFGNode head;
	
	public ControlFlowGraph(Set<CFGNode> allNodes, CFGNode head) {
		this.allNodes = allNodes;
		this.setHead(head);
	}
	
	/**
	 * Creates a CFG from the given abstract assembly function.
	 * 
	 * @param program	the list of instructions that constitute a function
	 */
	public ControlFlowGraph(List<Instruction> program) {	
		// Initialize globals
		allNodes = new HashSet<CFGNode>();
		
		/* Maps a jump related node to the corresponding label */
		Map<AACFGNode, Label> node2label = new HashMap<AACFGNode,Label>();
		
		/* Maps a label to the node of instruction that immediately follows */
		Map<Label, AACFGNode> label2node = new HashMap<Label,AACFGNode>();
//		
////		/* Maps a function label to the node after a call instruction. */
////		Map<Label, AACFGNode> func2node = new HashMap<Label,AACFGNode>();
////		Label currFunc = null;
//		
		/* Get the head node */
		int first_instr_index = 0;
		
		Instruction firstInstr = program.get(0);
		
//		while (firstInstr.getOp() == Operation.LABEL) {
//			firstInstr = program.get(++first_instr_index);
//			Label label = (Label) firstInstr.getDest();
//			if (label.isFuncLabel()) {
//				currFunc = label;
//			}
//		}
		
		head = new AACFGNode(firstInstr);
		allNodes.add(head);
		
		/* Construct CFG */
		AACFGNode prev = (AACFGNode) head;
//		
////		boolean putInMap = false;
//		
		for (int i = first_instr_index+1; i < program.size(); i++) {
			Instruction instr = program.get(i);
			Operation op = instr.getOp();
			AACFGNode curr = new AACFGNode(instr);
//			
////			// if previous instruction was a call
////			if (putInMap) {
////				func2node.put(currFunc,curr);
////				putInMap = false;
////			}
//			
			// if it's a label instruction,
			// get the next label and put the pair in label2node map
			if (op == Operation.LABEL) {
				Label label = (Label) instr.getDest();
				instr = program.get(++i);
				curr = new AACFGNode(instr);
				label2node.put(label, curr);
//				
////				if (label.isFuncLabel()) {
////					currFunc = label;
////				}
//				
			}
			// add node to nodeSet 
			allNodes.add(curr);
			
			// if it's a jump instruction,
			// put the node, label pair in node2label map
			if (Operation.isJumpInstruction(op)) {
				Label label = (Label) instr.getDest();
				node2label.put(curr,label);
				
				if (op == Operation.JMP) {
					prev.addSuccessor(curr);
					prev = null;
					continue;
				}
			} 
//			
////			else if (op == Operation.CALLQ) {
////				putInMap = true;
////			} else if (op == Operation.RET) {
////				prev.addSuccessor(curr);
////				prev = null;
////				continue;
////			}
//			
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
	}
	
	/**
	 * Creates a CFG from the given IR function
	 * 
	 * @param function	the IRFuncDecl that defines a function
	 */
	public ControlFlowGraph(IRFuncDecl func) {	
		// Initialize globals
		allNodes = new HashSet<CFGNode>();
				
		/* Maps a jump related node to the corresponding label */
		Map<IRCFGNode, String> node2label = new HashMap<IRCFGNode, String>();
		
		/* Maps a label to the node of instruction that immediately follows */
		Map<String, IRCFGNode> label2node = new HashMap<String, IRCFGNode>();

		IRSeq body = (IRSeq) func.body();
		List<IRStmt> stmts = body.stmts();
		IRCFGNode head1 = new IRCFGNode(stmts.get(0));
		head1.setABIName(func.getABIName());
		head1.setName(func.getABIName());
		head = head1;
		allNodes.add(head);
		
		/* Construct CFG */
		IRCFGNode prev = head1;
		for (int i = 1; i < stmts.size(); i++) {
			IRStmt stmt = stmts.get(i);
			IRCFGNode curr = new IRCFGNode(stmt);
			curr.setABIName(func.getABIName());
			
			// if it's a label instruction,
			// store (label,node) in label2node map
			if (stmt instanceof IRLabel) {
				String label = ((IRLabel) stmt).name();
				curr.setABIName(func.getABIName());
				label2node.put(label, curr);
			}
			// add node to nodeSet 
			allNodes.add(curr);
			
			// if it's a jump instruction,
			// put the node, label pair in node2label map
			if (stmt instanceof IRJump) {
				IRJump jump = (IRJump) stmt;
				String label = ((IRName) jump.target()).name();
				node2label.put(curr,label);
				prev.addSuccessor(curr);
				prev = null;
				continue;
			} else if (stmt instanceof IRCJump) {
				IRCJump cjump = (IRCJump) stmt;
				String label = cjump.trueLabel();
				node2label.put(curr,label);
			} 
			
			// link prev to curr
			if (prev != null && !(prev.underlyingIRStmt instanceof IRReturn)) {
				prev.addSuccessor(curr);
			}
			prev = curr;
		}
		
		/* Link jumps */
		for (Entry<IRCFGNode, String> entry : node2label.entrySet()) {
			IRCFGNode node1 = entry.getKey();
			String label = entry.getValue();
			IRCFGNode node2 = label2node.get(label);
			node1.addSuccessor(node2);
		}
	}
	
	/**
	 * Inserts newNode in between node and node's successor. 
	 * @param node with one successor
	 * @param newNode
	 */
	public void insert(CFGNode node, CFGNode newNode) {
		CFGNode succ = node.successor1;

		// connect node and newNode
		node.successor1 = newNode;
		node.proposeToSuccessor(newNode);
		
		// connect newNode and succ
		succ.predecessors.remove(node);
		newNode.successor1 = succ;
		newNode.proposeToSuccessor(succ);
		
		allNodes.add(newNode);
	}
	
	/**
	 * Inserts newNode before node.
	 * newNode
	 * 	- successor1 = node
	 * 	- predecessors = node's predecessors
	 * @param node
	 * @param newNode
	 */
	public void insertBefore(CFGNode node, CFGNode newNode) {
		// link newNode with node's predecessors
		for (CFGNode pred : node.predecessors) {
			if (pred.successor1 == node) {
				pred.successor1 = newNode;
			} else {
				pred.successor2 = newNode;
			}
		}
		newNode.predecessors.addAll(node.predecessors);
		
		// link newNode with node
		node.predecessors.clear();
		newNode.successor1 = node;
		newNode.proposeToSuccessor(node);
		
		allNodes.add(newNode);
	}
	
	/**
	 * Removes the node from the graph.
	 * @param node with less than or equal to one successor
	 * @return true if successful, false otherwise
	 */
	public boolean remove(CFGNode node) {
		boolean result = true;
		List<CFGNode> preds = node.predecessors;
		if (preds != null && node.getSuccessor1() != null) {
			result &= node.getSuccessor1().predecessors.remove(node);
			result &= node.getSuccessor1().predecessors.addAll(preds);
			for (CFGNode pred : preds) {
				if (pred.successor1 == node) {
					pred.successor1 = node.getSuccessor1();
				} else {
					pred.successor1 = node.getSuccessor1();
				}	
			}
		}
		if (head == node) {
			head = node.getSuccessor1();
		}
		result &= allNodes.remove(node);
		return result;
	}
	
	public List<Instruction> flattenIntoAA() {
		List<Instruction> function = new ArrayList<Instruction>();
		AACFGNode next = (AACFGNode) head;
		while (next != null) {
			function.add(next.getUnderlyingInstruction());
			next = (AACFGNode) next.successor1;
		}
		return function;
	}
	
	public IRFuncDecl flattenIntoIR() {
		List<IRStmt> stmts = new ArrayList<IRStmt>();
		IRCFGNode next = (IRCFGNode) head;
		String name = next.getName();
		String ABIName = next.getABIName();
		Set<CFGNode> hasPrintedCFGNode = new HashSet<CFGNode>();
		Queue<CFGNode> trueLabelsToBeFlattened = new LinkedList<CFGNode>();
		while (next != null) {
			if (!hasPrintedCFGNode.contains(next)) {
				stmts.add(next.getUnderlyingIRStmt());
				hasPrintedCFGNode.add(next);
			}
			if (next.successor2 != null && !trueLabelsToBeFlattened.contains(next.successor2)) {
				if (!hasPrintedCFGNode.contains(next.successor2)) {
					trueLabelsToBeFlattened.add(next.successor2);
				}
			}
			next = (IRCFGNode) next.successor1;
			if (next == null || hasPrintedCFGNode.contains(next)) {
				next = (IRCFGNode) trueLabelsToBeFlattened.poll();
			}
		}
		IRSeq seq = new IRSeq(stmts);
		return new IRFuncDecl(name, ABIName, seq);
	}
	
	public Set<CFGNode> getAllNodes() {
		return allNodes;
	}
	
	public void addTheseNodes(Set<CFGNode> argNodes) {
		allNodes.addAll(argNodes);
	}

	public CFGNode getHead() {
		return head;
	}

	public void setHead(CFGNode head) {
		this.head = head;
	}
	
	public String dotOutput() {
		String s = "digraph {\n";
		s += "{\t" + "rankdir=LR\n\t" + "node[shape=box]\n" + "}";
		Set<CFGNode> visited = new HashSet<CFGNode>();
		s += head.dotOutput(visited);
		s += "}";
		return s;
	}
	
	/**
	 * Very dumb print method
	 */
	@Override
	public void print() {
		if (head instanceof IRCFGNode) {
			Set<CFGNode> set = new HashSet<CFGNode>();
			Stack<CFGNode> stack = new Stack<CFGNode>();
			stack.add(head);
			while(!stack.isEmpty()) {
				IRCFGNode node = (IRCFGNode) stack.pop();
				System.out.println(node.underlyingIRStmt);
//				System.out.println("\t" + node.);
				for (CFGNode succ : node.getSuccessors()) {
					stack.push(succ);
//					if (!set.contains(succ)) {
//						stack.push(succ);
//						set.add(succ);
//					}
				}
			}
		} else {
			// TODO: implement print for AACFGNode
		}
	}
}
