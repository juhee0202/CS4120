package jl2755.assembly;

import java.util.Set;

public class Star implements Operand {

	private Memory underlyingMemory;
	
	
	public Star(Memory argMemory) {
		underlyingMemory = argMemory;
	}
	
	@Override
	public String getOpType() {
		return "Star";
	}

	@Override
	public Operand getNewOperand() {
		return new Star((Memory) underlyingMemory.getNewOperand());
	}

	@Override
	public boolean isConstOffset() {
		return false;
	}

	@Override
	public boolean isRegFactorOffset() {
		return false;
	}

	@Override
	public boolean isConstFactor() {
		return false;
	}

	@Override
	public boolean isRegBase() {
		return false;
	}

	@Override
	public boolean isFuncLabel() {
		return false;
	}

	@Override
	public Set<Register> getRegistersUsed() {
		return underlyingMemory.getRegistersUsed();
	}
	
	@Override
	public String toString() {
		return "*" + underlyingMemory.toString();
	}

}
