package jl2755.ast;

/**
 * Class for the list of function declarations in an interface file.
 *
 */
public class InterfaceList {
	/** The next function declaration. */
	private InterfaceFunc intFunc;
	/** The rest of the function declarations. */
	private InterfaceList intList;
	/** 0: Base case of only 1 function declaration
	 *  1: Recursive case of multiple function declarations
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
}
