package jl2755.ast;

import java.util.List;

/**
 * The class to represent Xi interface files denoted by .ixi
 *
 */
public class Interface extends XiFile {
	/** The list of function declarations in this interface file. */
	private InterfaceList intList;
	private UseId useId;
	
	public Interface(InterfaceList il) {
		intList = il;
	}
	
	public Interface(InterfaceList il, UseId ui) {
		intList = il;
		useId = ui;
	}
	
	public List<InterfaceFunc> getInterfaceFuncs() {
		return intList.getInterfaceFunctions();
	}
	
	public List<InterfaceClassDecl> getInterfaceClasses() {
		return intList.getInterfaceClasses();
	}

	public UseId getUseId() {
		return useId;
	}
}
