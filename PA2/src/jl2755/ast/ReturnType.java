package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ReturnType {
	private TypeList typeList;
	private int index;
	
	public ReturnType(){
		index = 0;
	}
	
	public ReturnType(TypeList tl) {
		typeList = tl;
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index == 0){
			tempPrinter.endList();
			return;
		}
		typeList.prettyPrintNode();
		tempPrinter.endList();
	}
}
