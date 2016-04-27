package jl2755.assembly;

import java.util.HashSet;
import java.util.Set;

public class Memory implements Operand {
	
	/**
	 * 
	 * 	Addressing Modes
	 * 		movq    (%ecx), %edx             # Register mode
	 * 		movq    -4(%ebp), %eax           # Typical example: load a stack variable into eax
	 * 		movq    8(%eax,4), %eax          # R*W + Off
	 *		movq    -4(%ebp, %edx, 4), %eax  # Full example: load *(ebp - 4 + (edx * 4)) into eax
	 */
	
	/** (%r): Use memory address in r */
	private Register registerBase;
	
	/** k(%r): Add r and k */
	private Constant constantOffset;
	
	/** (%r1,%r2): Add r1 and r2. */
	private Register registerOffset;
	
	/** (%r1,%r2,w): Multiply r2 and w, add to r1. */
	private Constant constantFactor;
	
//	/** 
//	 * 0: (%r)
//	 * 1: constantOffset(%base)
//	 * 2: constantOffset(%registerOffset, constantFactor)
//	 * 3: k(%base, %registerOffset, constantFactor)		(NOTE: if k=0, then k is excluded from assembly)
//	 * 6: ? ask JP
//	 */
//	int index;
	
	/** Only used for cloning */
	private Memory() {}
	
	public Memory(Register base) {
		this(null,base,null,null);
	}
	
	public Memory(Constant co, Register base) {
		this(co,base,null,null);
	}
	
	public Memory(Constant co, Register ro, Constant cf) {
		this(co,null,ro,cf);
	}
	
	public Memory(Constant co, Register base, Register ro, Constant cf) {
		constantOffset = co;
		registerBase = base;
		registerOffset = ro;
		constantFactor = cf;
	}
	
//	public Memory(Constant co, Register base, Register ro, Constant cf, int i) {
//		constantOffset = co;
//		registerBase = base;
//		registerOffset = ro;
//		constantFactor = cf;
//		index = i;
//	}
	
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

//	/** 
//	 * 0: (%r)
//	 * 1: constantOffset(%base)
//	 * 2: constantOffset(%registerOffset, constantFactor)
//	 * 3: k(%base, %registerOffset, constantFactor)		(NOTE: if k=0, then k is excluded from assembly)
	
	@Override
	public String toString() {
		String s = "(";
//		if (index == 0) {
//			s += "(" + registerBase.toString() + ")";
//		} else if (index == 1) {
//			s += constantOffset.getValue() + "(" + registerBase.toString() + ")";
//		} else if (index == 2) {
//			s += constantOffset.getValue() + "(" + registerOffset.toString() + "," + constantFactor.getValue() + ")";
//		} else if (index == 3) {
//			String constOffset = (constantOffset.getValue() == 0)? "": String.valueOf(constantOffset.getValue());
//			s += constOffset + "(" + registerBase.toString()
//					+ "," + registerOffset.toString() + "," + constantFactor.getValue()
//					+ ")";
//		} else if (index == 6) {
//			s += "(" + registerBase.toString();
//			if (constantOffset != null) {
//				s = constantOffset.getValue() + s;
//			}
//			if (registerOffset != null) {
//				s += "," + registerOffset.toString();
//			}
//			if (constantFactor != null) {
//				s += "," + constantFactor.getValue();
//			}
//			s += ")";
//		}
		// Universal printer
		if (constantOffset != null && constantOffset.getValue() != 0) {
			s = constantOffset.getValue() + s;
		}
		if (registerBase != null) {
			s += registerBase.toString();
		}
		if (registerOffset != null) {
			s += "," + registerOffset.toString();
		}
		if (constantFactor != null && constantFactor.getValue() != 1) {
			s += "," + constantFactor.getValue();
		}
		s += ")";
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
//		temp.index = index;
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

	@Override
	public Set<Register> getRegistersUsed() {
		Set<Register> returnSet = new HashSet<Register>();
//		if (index == 0) {
//			returnSet.add(registerBase);
//		}
//		else if (index == 1) {
//			returnSet.add(registerBase);
//		}
//		else if (index == 2) {
//			returnSet.add(registerBase);
//			returnSet.add(registerOffset);
//		}
//		else if (index == 3) {
//			returnSet.add(registerBase);
//			returnSet.add(registerOffset);
//		}
//		else if (index == 4) {
//			returnSet.add(registerBase);
//			returnSet.add(registerOffset);
//		}
//		else if (index == 5) {
//			returnSet.add(registerBase);
//			returnSet.add(registerOffset);
//		}
		if (registerBase != null) {
			returnSet.add(registerBase);
		}
		if (registerOffset != null) {
			returnSet.add(registerOffset);
		}
		return returnSet;
	}
	
}