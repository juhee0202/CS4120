package jl2755.assembly;

import java.util.ArrayList;

public class Tile {

	private Pattern pattern;
	private int cost;
	private String opcode;
	private Operand src;
	private Operand dest;
	
	public Tile(Pattern argPattern, int argCost, String argOpcode, Operand argSrc, Operand argDest) {
		pattern = argPattern;
		cost = argCost;
		opcode = argOpcode;
		src = argSrc;
		dest = argDest;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
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
	
}
