package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Represents brackets ([ ]) in a recursive manner
 * index 
 * 	- 0: [ ]
 * 	- 1: [ ] brackets
 */
public class Brackets {

	private Brackets brackets;
	private int index;
	
	public Brackets() {
		index = 0;
	}
	
	public Brackets(Brackets brackets) {
		this.brackets = brackets;
		index = 1;
	}
	
	/**
	 * @return total number of brackets
	 */
	public int getNumBrackets() {
		if (brackets == null) {
			return 1;
		} 
		
		return brackets.getNumBrackets() + 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("[]");
		if (index == 1) {
			brackets.prettyPrintNode();
		}
		tempPrinter.endList();
	}
}
