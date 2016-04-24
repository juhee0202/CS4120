package jl2755.assembly;

import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
		ADDQ, SUBQ, IMULQ1, IMULQ2, IDIVQ, ANDQ, ORQ, XORQ, NOTQ,
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
	        	return "addq";
	        case SUBQ:
	        	return "subq";
	        case IMULQ1:
	        	return "imulq";
	        case IMULQ2:
	        	return "imulq";
	        case IDIVQ:
	        	return "idivq";
	        case ANDQ:
	        	return "andq";
	        case ORQ:
	        	return "orq";
	        case XORQ:
	        	return "xorq";
	        case NOTQ:
	        	return "notq";
	        case CMOVE:
	        	return "cmove";
	        case CMOVNE:
	        	return "cmovne";
	        case CMOVL:
	        	return "cmovl";
	        case CMOVLE:
	        	return "cmovle";
	        case CMOVG:
	        	return "cmovg";
	        case CMOVGE:
	        	return "cmovge";
	        case CALLQ:
	        	return "callq";
	        case PUSHQ:
	        	return "pushq";
	        case LABEL:
	        	return "";
	        case ENTER:
	        	return "enter";
	        case LEAVE:
	        	return "leave";
	        case POPQ:
	        	return "popq";
	        case RET:
	        	return "ret";
	        case JMP:
	        	return "jmp";
	        case JE:
	        	return "je";
	        case JNE:
	        	return "jne";
	        case JG:
	        	return "jg";
	        case JGE:
	        	return "jge";
	        case JL:
	        	return "jl";
	        case JLE:
	        	return "jle";
	        case JZ:
	        	return "jz";
	        case JNZ:
	        	return "jnz";
	        case MOVQ:
	        	return "movq";
			case CMPQ:
				return "cmpq";
			case TESTQ:
				return "testq";
			default:
				break;
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
	        case IMULQ1:
	            return 1;
	        case IMULQ2:
	        	return 2;
	        case IDIVQ:
	            return 1;
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
			case CMPQ:
				return 2;
			case ENTER:
				return 2;
			case LEAVE:
				return 0;
			case MOVQ:
				return 2;
			case POPQ:
				return 1;
			case TESTQ:
				return 2;
			default:
				break;
	        }
	        System.out.println(this);
	        throw new InternalCompilerError("Unknown op type");
	    }
	}
	
	private Operation op;
	private Operand src;
	private Operand dest;
	private int cost;
	
	/** Used for register allocation. */
	private boolean isMoveWithTwoRegs = false;
	
	/** Only used for copying */
	private Instruction() {
	}
	
	public Instruction(Operation operation) {
		this(operation,null,null,0);
	}
	
	public Instruction(Operation operation, Operand destination) {
		this(operation,null,destination,1);
	}
	
	public Instruction(Operation operation, Operand source, Operand destination) {
		this(operation,source,destination,1);
	}
	
	public Instruction(Operation operation, Operand source, Operand destination,
						int cost) {
		op = operation;
		src = source;
		dest = destination;
		this.cost = cost;
		if (operation == Operation.MOVQ) {
			if (source instanceof Register && destination instanceof Register) {
				((Register) source).setMoveRelated(true);
				((Register) destination).setMoveRelated(true);
				isMoveWithTwoRegs = true;
			}
//			if (source instanceof Register) {
//				((Register) source).setMoveRelated(true);
//			}
//			if (destination instanceof Register) {
//				((Register) destination).setMoveRelated(true);
//			}
		}
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
	
	public boolean isMoveWithTwoRegs() {
		return isMoveWithTwoRegs;
	}

	public void setMoveWithTwoRegs(boolean isMoveWithTwoRegs) {
		this.isMoveWithTwoRegs = isMoveWithTwoRegs;
	}

	@Override
	public String toString() {
		if (op == Operation.LABEL) {
			return dest.toString();
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