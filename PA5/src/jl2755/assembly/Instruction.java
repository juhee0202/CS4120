package jl2755.assembly;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Instruction {
	
	// TODO: complete this
	public enum Operation {
	    ADD, SUB, MUL, HMUL, DIV, MOD, AND, OR, XOR, LSHIFT, RSHIFT, ARSHIFT,
	    EQ, NEQ, LT, GT, LEQ, GEQ;

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
	        }
	        throw new InternalCompilerError("Unknown op type");
	    }
	}
	
	private Operation op;
	private String src;
	private String dest;
	private int cost;
	
	public Instruction(Operation operation, String source, String destination) {
		op = operation;
		src = source;
		dest = destination;
		cost = 0;
	}
	
	public Instruction(Operation operation, String source, String destination,
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
