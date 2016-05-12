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
	/** The rest of the function declarations. */
	private InterfaceList intList;
	/** The global const. */
	private GlobalDecl constVar;
	/** 0: Base case of only 1 function declaration
	 *  1: Recursive case of multiple function declarations
	 *  2: Base case of only 1 global constant
	 *  3: Recursive case
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
	
	public InterfaceList(GlobalDecl gd) {
		constVar = gd;
		index = 2;
	}
	
	public InterfaceList(GlobalDecl gd, InterfaceList il) {
		constVar = gd;
		intList = il;
		index = 3;
	}
	
	public List<InterfaceFunc> getInterfaceFunctions() {
		List<InterfaceFunc> tempList = new ArrayList<InterfaceFunc>();
		if (index == 0){
			tempList.add(intFunc);
		}
		if (index == 1){
			tempList.add(intFunc);
			tempList.addAll(intList.getInterfaceFunctions());
		}
		return tempList;
	}
}
