package jl2755.assembly;

import java.util.Set;

public interface Operand {

	public String getOpType();
	
	@Override
	public String toString();
	
	public Operand getNewOperand();
	
	public boolean isConstOffset();
	public boolean isRegFactorOffset();
	public boolean isConstFactor();
	public boolean isRegBase();
	public boolean isFuncLabel();
	
	public Set<Register> getRegistersUsed();
}