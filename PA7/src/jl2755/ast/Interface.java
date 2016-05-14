package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.type.ClassType;
import jl2755.type.EmptyClassType;

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
	
	/**
	 * @return a List of all the Files that are imported by
	 * this xi file
	 */
	public List<String> getUseFiles() {
		List<String> tempList = new ArrayList<String>();
		if (useId != null){
			tempList.addAll(useId.getUseFiles());
		}
		return tempList;
	}

	public void replaceAll(EmptyClassType ect, ClassType classType) {
		List<InterfaceFunc> funcs = intList.getInterfaceFunctions();
		for (InterfaceClassDecl icd : intList.getInterfaceClasses()) {
			funcs.addAll(icd.getMethods());
		}
		for (InterfaceFunc func : funcs) {
			
		}
		
	}
}
