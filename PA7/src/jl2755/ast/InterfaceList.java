package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the list of function declarations in an interface file.
 *
 */
public class InterfaceList {
	/** The next function declaration. */
	private InterfaceFunc intFunc;
	/** The rest of the class/function declarations. */
	private InterfaceList intList;
	/** The next class declaration */
	private InterfaceClassDecl intClassDecl;
	/** 
	 *  0: Base case of only 1 function declaration
	 *  1: Recursive case of multiple function declarations
	 *  2: Base case of only 1 class declaration
	 *  3: Recursive case of multiple class declarations
	 */
	private int index;
	
	/**
	 * Constructor for base case with only one function declaration.
	 * 
	 * @param intf	the function declaration
	 */
	public InterfaceList(InterfaceFunc intf) {
		intFunc = intf;
		index = 0;
	}
	
	/**
	 * Constructor for recursive case with multiple function declarations.
	 * 
	 * @param intf	the function declaration
	 * @param il	the rest of the function declarations
	 */
	public InterfaceList(InterfaceFunc intf, InterfaceList il) {
		intFunc = intf;
		intList = il;
		index = 1;
	}
	
	public InterfaceList(InterfaceClassDecl icd) {
		intClassDecl = icd;
		index = 2;
	}
	
	public InterfaceList(InterfaceClassDecl icd, InterfaceList il) {
		intClassDecl = icd;
		intList = il;
		index = 3;
	}
	
	public List<InterfaceFunc> getInterfaceFunctions() {
		List<InterfaceFunc> tempList = new ArrayList<InterfaceFunc>();
		if (index == 0){
			tempList.add(intFunc);
		}
		if (index == 1 || index == 3) {
			if (intFunc != null) {
				tempList.add(intFunc);
			}
			tempList.addAll(intList.getInterfaceFunctions());
		}
		return tempList;
	}

	public List<InterfaceClassDecl> getInterfaceClasses() {
		List<InterfaceClassDecl> tempList = new ArrayList<InterfaceClassDecl>();
		if (index == 2){
			tempList.add(intClassDecl);
		}
		if (index == 1 || index == 3){
			if (intClassDecl != null) {
				tempList.add(intClassDecl);
			}
			tempList.addAll(intList.getInterfaceClasses());
		}
		return tempList;
	}
}
