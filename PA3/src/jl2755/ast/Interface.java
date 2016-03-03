package jl2755.ast;

/**
 * The class to represent Xi interface files denoted by .ixi
 *
 */
public class Interface extends XiFile {
	/** The list of function declarations in this interface file. */
	private InterfaceList intList;
	
	public Interface(InterfaceList il) {
		intList = il;
	}
}
