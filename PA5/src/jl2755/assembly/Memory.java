package jl2755.assembly;

public class Memory implements Operand {
	
	/** (%r): Use memory address in r */
	private Register registerBase;
	/** k(%r): Add r and k */
	private Constant constantOffset;
	/** (%r1,%r2): Add r1 and r2. */
	private Register registerOffset;
	/** (%r1,%r2,w): Multiply r2 and w, add to r1. */
	private Constant constantFactor;
	
	private static final Constant NO_OFFSET = new Constant(0);
	private static final Constant NO_FACTOR = new Constant(1);
	
	public Memory(Register base) {
		this(NO_OFFSET,base,null,null);
	}
	
	public Memory(Constant co, Register base) {
		this(co,base,null,null);
	}
	
	public Memory(Register base, Register ro) {
		this(NO_OFFSET,base,ro,NO_FACTOR);
	}
	
	public Memory(Register base, Register ro, Constant cf) {
		this(NO_OFFSET,base,ro,cf);
	}
	
	public Memory(Constant co, Register base, Register ro) {
		this(co,base,ro,NO_FACTOR);
	}
	
	public Memory(Constant co, Register base, Register ro, Constant cf) {
		constantOffset = co;
		registerBase = base;
		registerOffset = ro;
		constantFactor = cf;
	}
	
	public Register getRegisterBase() {
		return registerBase;
	}

	public void setRegisterBase(Register registerBase) {
		this.registerBase = registerBase;
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
