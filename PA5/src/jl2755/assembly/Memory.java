package jl2755.assembly;

public class Memory implements Operand {
	
	/** k(%r): Add r and k */
	private Constant constantOffset;
	/** (%r1,%r2): Add r1 and r2. */
	private Register registerOffset;
	/** (%r1,%r2,w): Multiply r2 and w, add to r1. */
	private Constant constantFactor;
	
	public Memory(Constant co) {
		constantOffset = co;
	}
	
	public Memory(Register ro) {
		registerOffset = ro;
	}
	
	public Memory(Register ro, Constant cf) {
		registerOffset = ro;
		constantFactor = cf;
	}
	
	public Memory(Constant co, Register ro) {
		constantOffset = co;
		registerOffset = ro;
	}
	
	public Memory(Constant co, Register ro, Constant cf) {
		constantOffset = co;
		registerOffset = ro;
		constantFactor = cf;
	}
	
	public Constant getConstantOffset() {
		return constantOffset;
	}

	public void setConstantOffset(Constant constantOffset) {
		this.constantOffset = constantOffset;
	}

	public Register getRegisterOffset() {
		return registerOffset;
	}

	public void setRegisterOffset(Register registerOffset) {
		this.registerOffset = registerOffset;
	}

	public Constant getConstantFactor() {
		return constantFactor;
	}

	public void setConstantFactor(Constant constantFactor) {
		this.constantFactor = constantFactor;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public String getOpType() {
		return "Memory";
	}
	
}
