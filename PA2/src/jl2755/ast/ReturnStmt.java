package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ReturnStmt {
	private ReturnList returnList;
	private int index;
	
	public ReturnStmt() {
		returnList = null;
		index = 0;
	}
	
	public ReturnStmt(ReturnList rl) {
		returnList = rl;
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("return");
		if (index == 1) {
			returnList.prettyPrintNode();
		}
		tempPrinter.endList();
	}
}
