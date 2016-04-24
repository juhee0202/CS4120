package jl2755.assembly;

import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class NormalInstruction extends Instruction {
	
	private Operation op;
	private Operand src;
	private Operand dest;
	
	/** Only used for copying */
	private NormalInstruction() {
	}
	
	public NormalInstruction(Operation operation) {
		op = operation;
	}
	
	public NormalInstruction(Operation operation, Operand destination) {
		op = operation;
		dest = destination;
		cost = 1;
	}
	
	public NormalInstruction(Operation operation, Operand source, Operand destination) {
		op = operation;
		src = source;
		dest = destination;
		cost = 1;
	}
	
	public NormalInstruction(Operation operation, Operand source, Operand destination,
						int cost) {
		op = operation;
		src = source;
		dest = destination;
		this.cost = cost;
	}
	
	/**
	 * Takes in a list of Operands and takes away as many as it needs 
	 * to satisfy blank Operand spaces. Note that the list should be
	 * in an order such that the first one (or two) Operands
	 * correspond to this Instruction
	 * 
	 * @param argOperands
	 */
	public void fillInInstructions(List<Operand> argOperands) {
		int operandsNeeded = op.numberOfOperandsNeeded();
		if (operandsNeeded == 1 && dest == null) {
			dest = argOperands.get(0);
			argOperands.remove(0);
			return;
		}
		if (src == null && operandsNeeded > 0) {
			src = argOperands.get(0);
			argOperands.remove(0);
		}
		if (dest == null && operandsNeeded > 1) {
			dest = argOperands.get(0);
			argOperands.remove(0);
		}
	}
	
	public NormalInstruction getCopyInstruction() {
		NormalInstruction temp = new NormalInstruction();
		temp.op = op;
		if (src != null) {
			temp.src = src.getNewOperand();
		}
		if (dest != null) {
			temp.dest = dest.getNewOperand();
		}
		temp.cost = cost;
		return temp;
	}
	
	public Operation getOp() {
		return op;
	}
	public void setOp(Operation op) {
		this.op = op;
	}
	public Operand getSrc() {
		return src;
	}
	public void setSrc(Operand src) {
		this.src = src;
	}
	public Operand getDest() {
		return dest;
	}
	public void setDest(Operand dest) {
		this.dest = dest;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		if (op == Operation.LABEL) {
			return dest.toString() + ":";
		}
		
		int i = op.numberOfOperandsNeeded();
		if (i == 0) {
			return op.toString();
		}
		else if (i == 1) {
			return op.toString() + "\t" + dest.toString();
		}
		else {
			return op.toString() + "\t" + src.toString() + ", " + dest.toString();
		}
	}
}