package jl2755.assembly;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
	    ADD, SUB, MUL, HMUL, DIV, MOD, AND, OR, XOR, LSHIFT, RSHIFT, ARSHIFT,
	    EQ, NEQ, LT, GT, LEQ, GEQ,
	    MOV,CALL,PUSH,LABEL;

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
	        case MOV:
	            return "MOV";
	        case CALL:
	            return "CALL";
	        case PUSH:
	            return "PUSH";
	        case LABEL:
	            return "LABEL";
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
