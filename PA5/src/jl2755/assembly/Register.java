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
			case "AH":
				return AH;
			case "AL":
				return AL;
			case "AX":
				return AX;
			case "BH":
				return BH;
			case "BL":
				return BL;
			case "BP":
				return BP;
			case "BPL":
				return BP;
			case "BX":
				return BX;
			case "CH":
				return CH;
			case "CL":
				return CL;
			case "CX":
				return CX;
			case "DH":
				return DH;
			case "DI":
				return DI;
			case "DIL":
				return DIL;
			case "DL":
				return DL;
			case "DX":
				return DX;
			case "EAX":
				return EAX;
			case "EBP":
				return EBP;
			case "EBX":
				return EBX;
			case "ECX":
				return ECX;
			case "EDI":
				return EDI;
			case "EDX":
				return EDX;
			case "ESI":
				return ESI;
			case "ESP":
				return ESP;
			case "R8":
				return R8;
			case "R8B":
				return R8B;
			case "R8D":
				return R8D;
			case "R8W":
				return R8W;
			case "R9":
				return R9;
			case "R9B":
				return R9B;
			case "R9D":
				return R9D;
			case "R9W":
				return R9W;
			case "R10":
				return R10;
			case "R10B":
				return R10B;
			case "R10D":
				return R10D;
			case "R10W":
				return R10W;
			case "R11":
				return R11;
			case "R11B":
				return R11B;
			case "R11D":
				return R11D;
			case "R11W":
				return R11W;
			case "R12":
				return R12;
			case "R12B":
				return R12B;
			case "R12D":
				return R12D;
			case "R12W":
				return R12W;
			case "R13":
				return R13;
			case "R13B":
				return R13B;
			case "R13D":
				return R13D;
			case "R13W":
				return R13W;
			case "R14":
				return R14;
			case "R14B":
				return R14B;
			case "R14D":
				return R14D;
			case "R14W":
				return R14W;
			case "R15":
				return R15;
			case "R15B":
				return R15B;
			case "R15D":
				return R15D;
			case "R15W":
				return R15W;
			case "RAX":
				return RAX;
			case "RBP":
				return RBP;
			case "RBX":
				return RBX;
			case "RCX":
				return RCX;
			case "RDI":
				return RDI;
			case "RDX":
				return RDX;
			case "RSI":
				return RSI;
			case "RSP":
				return RSP;
			case "SI":
				return SI;
			case "SIL":
				return SIL;
			case "SP":
				return SP;
			case "SPL":
				return SPL;
			default:
				return TEMP;
			}
		}
		
		@Override
		public String toString() {
			switch(this) {
			case AH:
				return "AH";
			case AL:
				return "AL";
			case AX:
				return "AX";
			case BH:
				return "BH";
			case BL:
				return "BL";
			case BP:
				return "BP";
			case BPL:
				return "BPL";
			case BX:
				return "BX";
			case CH:
				return "CH";
			case CL:
				return "CL";
			case CX:
				return "CX";
			case DH:
				return "DH";
			case DI:
				return "DI";
			case DIL:
				return "DIL";
			case DL:
				return "DL";
			case DX:
				return "DX";
			case EAX:
				return "EAX";
			case EBP:
				return "EBP";
			case EBX:
				return "EBX";
			case ECX:
				return "ECX";
			case EDI:
				return "EDI";
			case EDX:
				return "EDX";
			case ESI:
				return "ESI";
			case ESP:
				return "ESP";
			case R8:
				return "R8";
			case R8B:
				return "R8B";
			case R8D:
				return "R8D";
			case R8W:
				return "R8W";
			case R9:
				return "R9";
			case R9B:
				return "R9B";
			case R9D:
				return "R9D";
			case R9W:
				return "R9W";
			case R10:
				return "R10";
			case R10B:
				return "R10B";
			case R10D:
				return "R10D";
			case R10W:
				return "R10W";
			case R11:
				return "R11";
			case R11B:
				return "R11B";
			case R11D:
				return "R11D";
			case R11W:
				return "R11W";
			case R12:
				return "R12";
			case R12B:
				return "R12B";
			case R12D:
				return "R12D";
			case R12W:
				return "R12W";
			case R13:
				return "R13";
			case R13B:
				return "R13B";
			case R13D:
				return "R13D";
			case R13W:
				return "R13W";
			case R14:
				return "R14";
			case R14B:
				return "R14B";
			case R14D:
				return "R14D";
			case R14W:
				return "R14W";
			case R15:
				return "R15";
			case R15B:
				return "R15B";
			case R15D:
				return "R15D";
			case R15W:
				return "R15W";
			case RAX:
				return "RAX";
			case RBP:
				return "RBP";
			case RBX:
				return "RBX";
			case RCX:
				return "RCX";
			case RDI:
				return "RDI";
			case RDX:
				return "RDX";
			case RSI:
				return "RSI";
			case RSP:
				return "RSP";
			case SI:
				return "SI";
			case SIL:
				return "SIL";
			case SP:
				return "SP";
			case SPL:
				return "SPL";
			case TEMP:
				return "TEMP";
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
	
}
