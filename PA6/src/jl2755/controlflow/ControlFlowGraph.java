package jl2755.controlflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.cornell.cs.cs4120.xic.ir.*;

import java.util.Map.Entry;

import jl2755.assembly.*;
import jl2755.assembly.Instruction.Operation;

public class ControlFlowGraph {
	
	private Set<CFGNode> allNodes;
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
		// TODO: finalize function or program
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
		
		AACFGNode head = new AACFGNode(firstInstr);
		
		allNodes.add(head);
		
		/* Construct CFG */
		AACFGNode prev = head;
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
	}
	
	/**
	 * Creates a CFG from the given IR function
	 * 
	 * @param function	the IRFuncDecl that defines a function
	 */
	public ControlFlowGraph(IRFuncDecl func) {	
		// TODO: finalize func or compunit
		/* Maps a jump related node to the corresponding label */
		Map<IRCFGNode, String> node2label = new HashMap<IRCFGNode, String>();
		
		/* Maps a label to the node of instruction that immediately follows */
		Map<String, IRCFGNode> label2node = new HashMap<String, IRCFGNode>();
//		
////		Map<String, IRFuncDecl> functions = program.functions();
//		
		/* Get the head node */
//		
////		IRFuncDecl func = functions.get("_Imain_paai");
//		

		IRSeq body = (IRSeq) func.body();
		List<IRStmt> stmts = body.stmts();
		IRCFGNode head = new IRCFGNode(stmts.get(0));
		head.setName(func.name());
		allNodes.add(head);
		
		/* Construct CFG */
		IRCFGNode prev = head;
		for (int i = 1; i < stmts.size(); i++) {
			IRStmt stmt = stmts.get(i);
			IRCFGNode curr = new IRCFGNode(stmt);
			
			// if it's a label instruction,
			// get the next label and put the pair in label2node map
			if (stmt instanceof IRLabel) {
				String label = ((IRLabel) stmt).name();
				stmt = stmts.get(++i);
				curr = new IRCFGNode(stmt);
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
			if (prev != null) {
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
		while (next != null) {
			stmts.add(next.getUnderlyingIRStmt());
			next = (IRCFGNode) next.successor1;
		}
		IRSeq seq = new IRSeq(stmts);
		return new IRFuncDecl(name, seq);
	}
	
	public Set<CFGNode> getAllNodes() {
		return allNodes;
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
		s += head.dotOutput();
		s += "}";
		return s;
	}
}
