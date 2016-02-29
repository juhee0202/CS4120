package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class PrimitiveType implements Type{
	
	/** 0 for INT and 1 for BOOL */
	private int index;
	
	public PrimitiveType(int index){
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0) {
			tempPrinter.printAtom("int");
		} else {
			tempPrinter.printAtom("bool");
		}
	}
}
