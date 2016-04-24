package jl2755.assembly;

public abstract class Instruction {
	protected int cost;
	
	public abstract Instruction getCopyInstruction();
	public abstract int getCost();
}
