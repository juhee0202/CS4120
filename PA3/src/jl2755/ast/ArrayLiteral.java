package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;


/**
 * Class that encapsulates a list of array elements closed
 * by one set of curly braces.
 */
public class ArrayLiteral implements Constant {
	private ArrayElementList arrElemList;
	
	public ArrayLiteral(ArrayElementList l) {
		arrElemList = l;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		arrElemList.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	public ArrayElementList getArrElemList() {
		return arrElemList;
	}

	public void setArrElemList(ArrayElementList arrElemList) {
		this.arrElemList = arrElemList;
	}
}
