package jl2755.assembly;

import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
		ADDQ, SUBQ, IMULQ, IDIVQ, ANDQ, ORQ, XORQ, NOTQ,
		CMOVE, CMOVNE, CMOVL, CMOVLE, CMOVG, CMOVGE, MOVQ, CMPQ, TESTQ,
		CALLQ, PUSHQ, LABEL, ENTER, LEAVE, POPQ, RET,
		JMP, JE, JNE, JG, JGE, JL, JLE, JZ, JNZ;
		
		
//	    ADD, SUB, IMUL, HMUL, IDIV, MOD, AND, OR, XOR,
//	    EQ, NEQ, LT, GT, LEQ, GEQ, LABEL, MOV, CALL, PUSH, JMP, JE, JNE, 
//	    JG, JGE, JL, JLE, JZ, JNZ, CMP, TEST, 
//	    CMOVE, CMOVNE, CMOVL, CMOVLE, CMOVG, CMOVGE, CMOVS, CMOVNS;

	    @Override
	    public String toString() {
	        switch (this) {
	        case ADDQ:
	        	return "ADDQ";
	        case SUBQ:
	        	return "SUBQ";
	        case IMULQ:
	        	return "IMULQ";
	        case IDIVQ:
	        	return "IDIVQ";
	        case ANDQ:
	        	return "ANDQ";
	        case ORQ:
	        	return "ORQ";
	        case XORQ:
	        	return "XORQ";
	        case NOTQ:
	        	return "NOTQ";
	        case CMOVE:
	        	return "CMOVE";
	        case CMOVNE:
	        	return "CMOVNE";
	        case CMOVL:
	        	return "CMOVL";
	        case CMOVLE:
	        	return "CMOVLE";
	        case CMOVG:
	        	return "CMOVG";
	        case CMOVGE:
	        	return "CMOVGE";
	        case CALLQ:
	        	return "CALLQ";
	        case PUSHQ:
	        	return "PUSHQ";
	        case LABEL:
	        	return "LABEL";
	        case ENTER:
	        	return "ENTER";
	        case LEAVE:
	        	return "LEAVE";
	        case POPQ:
	        	return "POPQ";
	        case RET:
	        	return "RET";
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
	        case MOVQ:
	        	return "MOVQ";
	        }
	        System.out.println(this.name());
	        throw new InternalCompilerError("Unknown op type");
	    }

	    private int numberOfOperandsNeeded() {
	        switch (this) {
	        case ADDQ:
	            return 2;
	        case SUBQ:
	            return 2;
	        case IMULQ:
	            return 2;
	        case IDIVQ:
	            return 2;
	        case ANDQ:
	        	return 2;
	        case ORQ:
	        	return 2;
	        case XORQ:
	        	return 2;
	        case NOTQ:
	        	return 1;
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
	        case CALLQ:
	        	return 1;
	        case PUSHQ:
	        	return 1;
	        case LABEL:
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
	        case RET:
	        	return 0;
	        }
	        System.out.println(this);
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
	
	public Instruction(Operation operation) {
		op = operation;
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
		int i = op.numberOfOperandsNeeded();
		if (i == 0) {
			return op.toString();
		}
		else if (i == 1) {
			return op.toString() + " " + dest.toString();
		}
		else {
			return op.toString() + src.toString() + dest.toString();
		}
	}
}