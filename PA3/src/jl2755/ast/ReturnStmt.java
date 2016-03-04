package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class ReturnStmt {
	private ReturnList returnList;
	/**
	 * 0 if it's a procedural return, 1 if it returns something
	 */
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

	public ReturnList getReturnList() {
		return returnList;
	}

	public void setReturnList(ReturnList returnList) {
		this.returnList = returnList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
