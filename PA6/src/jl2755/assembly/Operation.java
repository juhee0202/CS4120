package jl2755.assembly;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public enum Operation {
	ADDQ, SUBQ, IMULQ1, IMULQ2, IDIVQ, ANDQ, ORQ, XORQ, NOTQ,
	CMOVE, CMOVNE, CMOVL, CMOVLE, CMOVG, CMOVGE, MOVQ, CMPQ, TESTQ,
	CALLQ, PUSHQ, LABEL, ENTER, LEAVE, POPQ, RET,
	JMP, JE, JNE, JG, JGE, JL, JLE, JZ, JNZ;

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

    public int numberOfOperandsNeeded() {
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