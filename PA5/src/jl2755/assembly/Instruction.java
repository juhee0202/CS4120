package jl2755.assembly;

import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
	    ADD, SUB, IMUL, HMUL, IDIV, MOD, AND, OR, XOR, LSHIFT, RSHIFT, ARSHIFT,
	    EQ, NEQ, LT, GT, LEQ, GEQ, LABEL, MOV, CALL, PUSH, JMP, JE, JNE, 
	    JG, JGE, JL, JLE, JZ, JNZ, CMP, TEST, 
	    CMOVE, CMOVNE, CMOVL, CMOVLE, CMOVG, CMOVGE, CMOVS, CMOVNS;

	    @Override
	    public String toString() {
	        switch (this) {
	        case ADD:
	            return "ADD";
	        case SUB:
	            return "SUB";
	        case IMUL:
	            return "MUL";
	        case HMUL:
	            return "HMUL";
	        case IDIV:
	            return "DIV";
	        case MOD:
	            return "MOD";
	        case AND:
	            return "AND";
	        case OR:
	            return "OR";
	        case XOR:
	            return "XOR";
	        case LSHIFT:
	            return "LSHIFT";
	        case RSHIFT:
	            return "RSHIFT";
	        case ARSHIFT:
	            return "ARSHIFT";
	        case EQ:
	            return "EQ";
	        case NEQ:
	            return "NEQ";
	        case LT:
	            return "LT";
	        case GT:
	            return "GT";
	        case LEQ:
	            return "LEQ";
	        case GEQ:
	            return "GEQ";
	        case LABEL:
	        	return "";
	        case MOV:
	            return "MOV";
	        case CALL:
	            return "CALL";
	        case PUSH:
	            return "PUSH";
	        case JMP:
	        	return "JMP";
	        case JE:
	        	return "JE";
	        case JNE:
	        	return "JNE";
	        case JG:
	        	return "JG";
	        case JGE:
	        	return "JGE";
	        case JL:
	        	return "JL";
	        case JLE:
	        	return "JLE";
	        case JZ:
	        	return "JZ";
	        case JNZ:
	        	return "JNZ";
	        case CMP:
	        	return "CMP";
	        case TEST:
	        	return "TEST";
	        }
	        throw new InternalCompilerError("Unknown op type");
	    }
	    
	    private int numberOfOperandsNeeded() {
	        switch (this) {
	        case ADD:
	            return 2;
	        case SUB:
	            return 2;
	        case IMUL:
	            return 2;
	        case HMUL:
	            return 2;
	        case IDIV:
	            return 2;
	        case MOD:
	            return 2;
	        case AND:
	            return 2;
	        case OR:
	            return 2;
	        case XOR:
	            return 2;
	        case LSHIFT:
	            return 2;
	        case RSHIFT:
	            return 2;
	        case ARSHIFT:
	            return 2;
	        case EQ:
	            return 2;
	        case NEQ:
	            return 2;
	        case LT:
	            return 2;
	        case GT:
	            return 2;
	        case LEQ:
	            return 2;
	        case GEQ:
	            return 2;
	        case LABEL:
	        	return 0;
	        case MOV:
	            return 2;
	        case CALL:
	            return 0;
	        case PUSH:
	            return 1;
	        case JMP:
	        	return 1;
	        case JE:
	        	return 1;
	        case JNE:
	        	return 1;
	        case JG:
	        	return 1;
	        case JGE:
	        	return 1;
	        case JL:
	        	return 1;
	        case JLE:
	        	return 1;
	        case JZ:
	        	return 1;
	        case JNZ:
	        	return 1;
	        case CMP:
	        	return 2;
	        case TEST:
	        	return 2;
	        case CMOVE:
	        	return 2;
	        case CMOVNE:
	        	return 2;
	        case CMOVL:
	        	return 2;
	        case CMOVLE:
	        	return 2;
	        case CMOVG:
	        	return 2;
	        case CMOVGE:
	        	return 2;
	        case CMOVS:
	        	return 2;
	        case CMOVNS:
	        	return 2;
	        }
	        throw new InternalCompilerError("Unknown op type");
	    }
	}
	
	private Operation op;
	private Operand src;
	private Operand dest;
	private int cost;
	
	/** Only used for copying */
	private Instruction() {
	}
	
	public Instruction(Operation operation, Operand destination) {
		op = operation;
		dest = destination;
		cost = 1;
	}
	
	public Instruction(Operation operation, Operand source, Operand destination) {
		op = operation;
		src = source;
		dest = destination;
		cost = 1;
	}
	
	public Instruction(Operation operation, Operand source, Operand destination,
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
	
	public Instruction getCopyInstruction() {
		Instruction temp = new Instruction();
		temp.op = op;
		temp.src = src.getNewOperand();
		temp.dest = dest.getNewOperand();
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
}
