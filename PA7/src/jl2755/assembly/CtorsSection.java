package jl2755.assembly;

import java.util.List;

/**
 * Class that encapsulates the .ctors section of an assembly file.
 * This section is used for initializing the object layout by calling
 * functions that take into account superclass sizes, and concatenates
 * its own fields onto the object layout vector.
 */
public class CtorsSection {
	
	private String className;
	private String superClassName;
	private List<String> methods;
	private List<String> fields;
	
	public CtorsSection(String argClassName, List<String> argMethods, List<String> argFields) {
		className = argClassName;
		superClassName = null;
		methods = argMethods;
		fields = argFields;
	}
	
	public CtorsSection(String argClassName, String argSuperClassName, List<String> argMethods,
			List<String> argFields) {
		className = argClassName;
		superClassName = argSuperClassName;
		methods = argMethods;
		fields = argFields;
	}
	
	/**
	 * @return the String that, when run in assembly, represents the object layout
	 * initialization 
	 */
	public String generateInitialization() {
		String s = "";
		s += ".section .ctors\n";
		s += "\t.align 8";
		s += "\t.quad _I_init_" + className;
		s += ".text";
		return s;
	}
}
