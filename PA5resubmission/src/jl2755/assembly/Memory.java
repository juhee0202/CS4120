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
	
	int index;
	
	/** Only used for cloning */
	private Memory() {
	}
	
//	public Memory(Constant co) {
//		constantOffset = co;
//	}
	
	private static final Constant NO_OFFSET = new Constant(0);
	private static final Constant NO_FACTOR = new Constant(1);
	
	public Memory(Register base) {
		this(NO_OFFSET,base,null,null);
		index = 0;
	}
	
	public Memory(Constant co, Register base) {
		this(co,base,null,null);
		index = 1;
	}
	
	public Memory(Register base, Register ro) {
		this(NO_OFFSET,base,ro,NO_FACTOR);
		index = 2;
	}
	
	public Memory(Register base, Register ro, Constant cf) {
		this(NO_OFFSET,base,ro,cf);
		index = 3;
	}
	
	public Memory(Constant co, Register base, Register ro) {
		this(co,base,ro,NO_FACTOR);
		index = 4;
	}
	
	public Memory(Constant co, Register base, Register ro, Constant cf) {
		constantOffset = co;
		registerBase = base;
		registerOffset = ro;
		constantFactor = cf;
		index = 5;
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
		String s = "";
		if (index == 0) {
			s += "(" + registerBase.toString() + ")";
		} else if (index == 1) {
			s += constantOffset.getValue() + "(" + registerBase.toString() + ")";
		} else if (index == 2) {
			s += "(" + registerBase.toString() + "," + registerOffset.toString() + ")";
		} else if (index == 3) {
			s += "(" + registerBase.toString() + "," + registerOffset.toString()
					+ "," + constantFactor.getValue() + ")";
		} else if (index == 4) {
			s += constantOffset.getValue() + "(" + registerBase.toString()
					+ ","  + registerOffset.toString() + ")";
		} else if (index == 5) {
			s += constantOffset.getValue() + "(" + registerBase.toString()
					+ "," + registerOffset.toString() + "," + constantFactor.getValue()
					+ ")";
		}
		return s;
	}

	@Override
	public String getOpType() {
		return "Memory";
	}

	@Override
	public Operand getNewOperand() {
		Memory temp = new Memory();
		if (registerBase != null) {
			temp.registerBase = (Register) registerBase.getNewOperand();
		}
		if (constantOffset != null) {
			temp.constantOffset = (Constant) constantOffset.getNewOperand();
		}
		if (registerOffset != null) {
			temp.registerOffset = (Register) registerOffset.getNewOperand();
		}
		if (constantFactor != null) {
			temp.constantFactor = (Constant) constantFactor.getNewOperand();
		}
		temp.index = index;
		return temp;
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
	
}