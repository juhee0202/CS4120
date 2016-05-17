package jl2755.assembly;

import edu.cornell.cs.cs4120.xic.ir.IRDispatchVector;

/**
 * Class that encapsulates the data section of an assembly file. This is
 * used for declaring and/or initializing certain global variables from the
 * source file, and for internal object oriented architecture.
 */
public class GlobalVariableSection {
	
	public enum GlobalVarType {
		DISPATCHVECTOR, REGULAR, SIZE;
	}
	
	public enum GlobalVarValueType {
		ARRAY, PRIMITIVE, PRIMITIVE_INITIALIZED, NONE;
	}
	
	private String abiVariableName;
	private int variableValue;
	private String className;
	private int classSize;
	private GlobalVarType type;
	private GlobalVarValueType valueType;
	
	public GlobalVariableSection(String argABIVariableName) {
		abiVariableName = argABIVariableName;
		type = GlobalVarType.REGULAR;
		valueType = GlobalVarValueType.PRIMITIVE;
	}
	
	public GlobalVariableSection(String argABIVariableName, int value) {
		abiVariableName = argABIVariableName;
		type = GlobalVarType.REGULAR;
		variableValue = value;
		valueType = GlobalVarValueType.PRIMITIVE_INITIALIZED;
	}
	
	public GlobalVariableSection(IRDispatchVector argDV, GlobalVarType argType) {
		if (argType == GlobalVarType.DISPATCHVECTOR) {
			className = argDV.getClassName();
			valueType = GlobalVarValueType.NONE;
			return;
		}
		if (argType == GlobalVarType.SIZE) {
			className = argDV.getClassName();
			classSize = 8*(1+argDV.getNumberOfFields());
			valueType = GlobalVarValueType.NONE;
			return;
		}
		// Someone called this constructor using regular var type enum
		assert(false);
	}
	
	public String generateInitialization() {
		String s = "\n";
		if (type == GlobalVarType.DISPATCHVECTOR) {
			s += "\t.bss\n";
			s += "\t.align 8\n";
			s += ".globl _I_vt_" + className + "\n";
			s += "_I_vt_" + className + ":\n";
			s += "\t.zero " + classSize + "\n";
			s += "\t.text\n\n";
			s += ".section .ctors\n";
			s += "\t.align 8\n";
			s += "\t.quad _I_init_" + className + "\n";
			s += "\t.text";
		}
		if (type == GlobalVarType.REGULAR) {
			if (valueType == GlobalVarValueType.PRIMITIVE_INITIALIZED) {
				s += "\t.section .data\n";
				s += "\t.align 8\n";
				s += ".globl _I_g_" + abiVariableName + "\n";
				s += "_I_g_" + abiVariableName + ":\n";
				s += "\t.quad " +  variableValue + "\n";
				s += "\t.text";
			}
			else if (valueType == GlobalVarValueType.PRIMITIVE) {
				s += "\t.section .data\n";
				s += "\t.align 8\n";
				s += ".globl _I_g_" + abiVariableName + "\n";
				s += "_I_g_" + abiVariableName + "_i:\n";
				s += "\t.zero " +  8 + "\n";
				s += "\t.text";
			}
			else if (valueType == GlobalVarValueType.ARRAY) {
				s += "\t.section .data\n";
				s += "\t.align 8\n";
				s += ".globl _I_g_" + abiVariableName + "\n";
				s += "_I_g_" + abiVariableName + ":\n";
				s += "\t.zero " +  8 + "\n";
				s += "\t.text\n\n";
				s += ".section .ctors\n";
				s += "\t.align 8\n";
				s += "\t.quad " + abiVariableName + "\n";
				s += "\t.text";
			}
			else {
				// Shouldn't be regular and have value type be NONE.
				assert(false);
			}
		}
		if (type == GlobalVarType.SIZE) {
			s += "\t.bss\n";
			s += "\t.align 8\n";
			s += ".globl _I_size_" + className + "\n";
			s += "_I_size_" + className + ":\n";
			s += "\t.zero 8\n";
			s += "\t.text";
		}
		return s;
	}
}
