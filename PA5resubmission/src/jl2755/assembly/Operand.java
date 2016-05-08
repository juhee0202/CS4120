package jl2755.assembly;

public interface Operand {

	public String getOpType();
	
	@Override
	public String toString();
	
	public Operand getNewOperand();
	
	public boolean isConstOffset();
	public boolean isRegFactorOffset();
	public boolean isConstFactor();
	public boolean isRegBase();
}