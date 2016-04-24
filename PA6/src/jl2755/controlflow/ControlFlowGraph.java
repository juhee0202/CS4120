package jl2755.controlflow;

import java.util.List;
import java.util.Set;

import jl2755.assembly.Instruction;
import jl2755.assembly.Instruction.Operation;

public class ControlFlowGraph {
	
	private Set<CFGNode> allNodes;
	
	public ControlFlowGraph(List<Instruction> instructions) {
		int first_instr_index = 0;
		Instruction firstInstr = instructions.get(first_instr_index);
		while (firstInstr.getOp() == Operation.LABEL) {
			firstInstr = instructions.get(++first_instr_index);
		}
		
		AACFGNode head = new AACFGNode(firstInstr);
		AACFGNode prev = head;
		for (int i = first_instr_index+1; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			AACFGNode curr = new AACFGNode(instr);
			
			if (isJumpInstruction(instr.getOp())) {
				
			}
			
			prev.addSuccessor(curr);
		}		
	}
	
	public Set<CFGNode> getAllNodes() {
		return allNodes;
	}
	
	private boolean isJumpInstruction(Operation op) {
		for (Operation operation : Operation.JUMP_OPS) {
			if (op == operation) {
				return true;
			}
		}
		return false;
	}
}
