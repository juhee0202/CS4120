package jl2755.assembly;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
	    ADD, SUB, MUL, HMUL, DIV, MOD, AND, OR, XOR, LSHIFT, RSHIFT, ARSHIFT,
	    EQ, NEQ, LT, GT, LEQ, GEQ, LABEL, MOV, CALL, PUSH, TEST, JMP, JE, JNE,
	    JG, JGE, JL, JLE, JZ, JNZ;

	    @Override
	    public String toString() {
	        switch (this) {
	        case ADD:
	            return "ADD";
	        case SUB:
	            return "SUB";
	        case MUL:
	            return "MUL";
	        case HMUL:
	            return "HMUL";
	        case DIV:
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
	        case TEST:
	        	return "TEST";
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
	        }
	        throw new InternalCompilerError("Unknown op type");
	    }
	}
	
	private Operation op;
	private Operand src;
	private Operand dest;
	private int cost;
	
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
