package jl2755.assembly;

import java.util.HashSet;
import java.util.Set;

public class Constant implements Operand {

	/** The value of this constant. */
	private long value;
	
	private boolean isOffset;
	private boolean isFactor;
	
	public Constant(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "$" + value;
	}

	@Override
	public String getOpType() {
		return "Constant";
	}

	@Override
	public Operand getNewOperand() {
		return new Constant(value);
	}

	@Override
	public boolean isConstOffset() {
		return isOffset;
	}
	
	public void setConstOffset() {
		isOffset = true;
	}

	@Override
	public boolean isRegFactorOffset() {
		return false;
	}

	@Override
	public boolean isConstFactor() {
		return isFactor;
	}
	
	public void setConstFactor() {
		isFactor = true;
	}

	@Override
	public boolean isRegBase() {
		return false;
	}

	@Override
	public Set<Register> getRegistersUsed() {
		return new HashSet<Register>();
	}
	
}
