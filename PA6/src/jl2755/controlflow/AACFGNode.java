package jl2755.controlflow;

import jl2755.assembly.Instruction;
import jl2755.assembly.Operand;

public class AACFGNode extends CFGNode {
	/**
	 * The Instruction that this CFGNode represents.
	 */
	private Instruction underlyingInstruction;
	/**
	 * The use (if applicable) of this AACFGNode.
	 */
	private Operand use1;
	/**
	 * The second (there can be at most 2) use of
	 * this AACFGNode.
	 */
	private Operand use2;
	/**
	 * The one and only (if applicable) def
	 * of this AACFGNode.
	 */
	private Operand def;
	
	public AACFGNode(Instruction argInstruction) {
		super();
		underlyingInstruction = argInstruction;
		
	}
	
	private void computeUses() {
		// TODO: Complete
	}
	
	private void computeDefs() {
		// TODO: Complete
	}
	
	public void addSuccessor(AACFGNode argNode) {
		proposeToSuccessor(argNode);
		if (successor1 == null) {
			successor1 = argNode;
		}
		else if (successor2 == null) {
			successor2 = argNode;
		}
		else {
			assert(false);
			System.out.println("Already added 2 successors");
		}
	}

	public Instruction getUnderlyingInstruction() {
		return underlyingInstruction;
	}

	public void setUnderlyingInstruction(Instruction underlyingInstruction) {
		this.underlyingInstruction = underlyingInstruction;
	}
}
