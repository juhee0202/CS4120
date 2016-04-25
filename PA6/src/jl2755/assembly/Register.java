package jl2755.assembly;

import edu.cornell.cs.cs4120.util.InternalCompilerError;

public class Register implements Operand {
	

	public enum RegisterName {
		RAX, RBX, RCX, RDX, RSI, RDI, RSP, RBP,
		R8, R9, R10, R11, R12, R13, R14, R15,
		EAX, EBX, ECX, EDX, ESI, EDI, ESP, EBP,
		R8D, R9D, R10D, R11D, R12D, R13D, R14D, R15D,
		AX, BX, CX, DX, SI, DI, BP, SP,
		R8W, R9W, R10W, R11W, R12W, R13W, R14W, R15W,
		AH, BH, CH, DH,
		AL, BL, CL, DL, SIL, DIL, BPL, SPL,
		R8B, R9B, R10B, R11B, R12B, R13B, R14B, R15B,
		TEMP;

		public static RegisterName convertToName(String name) {
			switch(name) {
			case "ah":
				return AH;
			case "al":
				return AL;
			case "ax":
				return AX;
			case "bh":
				return BH;
			case "bl":
				return BL;
			case "bp":
				return BP;
			case "bpl":
				return BPL;
			case "bx":
				return BX;
			case "ch":
				return CH;
			case "cl":
				return CL;
			case "cx":
				return CX;
			case "dh":
				return DH;
			case "di":
				return DI;
			case "dil":
				return DIL;
			case "dl":
				return DL;
			case "dx":
				return DX;
			case "eax":
				return EAX;
			case "ebp":
				return EBP;
			case "ebx":
				return EBX;
			case "ecx":
				return ECX;
			case "edi":
				return EDI;
			case "edx":
				return EDX;
			case "esi":
				return ESI;
			case "esp":
				return ESP;
			case "r8":
				return R8;
			case "r8b":
				return R8B;
			case "r8d":
				return R8D;
			case "r8w":
				return R8W;
			case "r9":
				return R9;
			case "r9b":
				return R9B;
			case "r9d":
				return R9D;
			case "r9w":
				return R9W;
			case "r10":
				return R10;
			case "r10b":
				return R10B;
			case "r10d":
				return R10D;
			case "r10w":
				return R10W;
			case "r11":
				return R11;
			case "r11b":
				return R11B;
			case "r11d":
				return R11D;
			case "r11w":
				return R11W;
			case "r12":
				return R12;
			case "r12b":
				return R12B;
			case "r12d":
				return R12D;
			case "r12w":
				return R12W;
			case "r13":
				return R13;
			case "r13b":
				return R13B;
			case "r13d":
				return R13D;
			case "r13w":
				return R13W;
			case "r14":
				return R14;
			case "r14b":
				return R14B;
			case "r14d":
				return R14D;
			case "r14w":
				return R14W;
			case "r15":
				return R15;
			case "r15b":
				return R15B;
			case "r15d":
				return R15D;
			case "r15w":
				return R15W;
			case "rax":
				return RAX;
			case "rbp":
				return RBP;
			case "rbx":
				return RBX;
			case "rcx":
				return RCX;
			case "rdi":
				return RDI;
			case "rdx":
				return RDX;
			case "rsi":
				return RSI;
			case "rsp":
				return RSP;
			case "si":
				return SI;
			case "sil":
				return SIL;
			case "sp":
				return SP;
			case "spl":
				return SPL;
			default:
				return TEMP;
			}
		}
		
		@Override
		public String toString() {
			switch(this) {
			case AH:
				return "ah";
			case AL:
				return "al";
			case AX:
				return "ax";
			case BH:
				return "bh";
			case BL:
				return "bl";
			case BP:
				return "bp";
			case BPL:
				return "bpl";
			case BX:
				return "bx";
			case CH:
				return "ch";
			case CL:
				return "cl";
			case CX:
				return "cx";
			case DH:
				return "dh";
			case DI:
				return "di";
			case DIL:
				return "dil";
			case DL:
				return "dl";
			case DX:
				return "dx";
			case EAX:
				return "eax";
			case EBP:
				return "ebp";
			case EBX:
				return "ebx";
			case ECX:
				return "ecx";
			case EDI:
				return "edi";
			case EDX:
				return "edx";
			case ESI:
				return "esi";
			case ESP:
				return "esp";
			case R8:
				return "r8";
			case R8B:
				return "r8b";
			case R8D:
				return "r8d";
			case R8W:
				return "r8w";
			case R9:
				return "r9";
			case R9B:
				return "r9b";
			case R9D:
				return "r9d";
			case R9W:
				return "r9w";
			case R10:
				return "r10";
			case R10B:
				return "r10b";
			case R10D:
				return "r10d";
			case R10W:
				return "r10w";
			case R11:
				return "r11";
			case R11B:
				return "r11b";
			case R11D:
				return "r11d";
			case R11W:
				return "r11w";
			case R12:
				return "r12";
			case R12B:
				return "r12b";
			case R12D:
				return "r12d";
			case R12W:
				return "r12w";
			case R13:
				return "r13";
			case R13B:
				return "r13b";
			case R13D:
				return "r13d";
			case R13W:
				return "r13w";
			case R14:
				return "r14";
			case R14B:
				return "r14b";
			case R14D:
				return "r14d";
			case R14W:
				return "r14w";
			case R15:
				return "r15";
			case R15B:
				return "r15b";
			case R15D:
				return "r15d";
			case R15W:
				return "r15w";
			case RAX:
				return "rax";
			case RBP:
				return "rbp";
			case RBX:
				return "rbx";
			case RCX:
				return "rcx";
			case RDI:
				return "rdi";
			case RDX:
				return "rdx";
			case RSI:
				return "rsi";
			case RSP:
				return "rsp";
			case SI:
				return "si";
			case SIL:
				return "sil";
			case SP:
				return "sp";
			case SPL:
				return "spl";
			case TEMP:
				return "temp";
			}
			System.out.println("Invalid register name!");
			return null;
		}
	}
	
	/** The name of this register. */
	private String name;
	/** The enum value of this register. */
	private RegisterName type;
	
	private boolean isBase;
	private boolean isFactor;
	
	/** Used for register allocation. */
	private boolean isMoveRelated = false;
	
	/** Only used for copying */
	private Register() {
	}
	
	public Register(RegisterName name) {
		type = name;
		this.name = name.toString();
	}
	
	public Register(String name) {
		this.name = name;
		type = RegisterName.convertToName(name);
	}
	
	public Operand getNewOperand() {
		Register temp = new Register();
		temp.name = name;
		temp.type = type;
		return temp;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RegisterName getType() {
		return type;
	}

	public void setType(RegisterName type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "%" + name;
	}

	@Override
	public String getOpType() {
		return "Register";
	}

	@Override
	public boolean isConstOffset() {
		return false;
	}

	@Override
	public boolean isRegFactorOffset() {
		return isFactor;
	}
	
	public void setRegFactorOffset() {
		isFactor = true;
	}

	@Override
	public boolean isConstFactor() {
		return false;
	}

	@Override
	public boolean isRegBase() {
		return isBase;
	}
	
	public void setRegBase() {
		isBase = true;
	}

	public boolean isMoveRelated() {
		return isMoveRelated;
	}

	public void setMoveRelated(boolean isMoveRelated) {
		this.isMoveRelated = isMoveRelated;
	}
	
	public boolean isBuiltIn() {
		return type != RegisterName.TEMP;
	}
	
	@Override
	public boolean equals(Object o) {
		Register reg = (Register) o;
		if (type == reg.type && name == reg.name) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() + type.hashCode();
	}
	
}
