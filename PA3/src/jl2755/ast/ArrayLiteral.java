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
	private int openBracket_col;
	private int openBracket_line;
	
	public ArrayLiteral(ArrayElementList l, int obleft, int obright) {
		arrElemList = l;
		openBracket_col = obleft;
		openBracket_line = obright;
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

	public int getOpenBracket_col() {
		return openBracket_col;
	}

	public void setOpenBracket_col(int openBracket_col) {
		this.openBracket_col = openBracket_col;
	}

	public int getOpenBracket_line() {
		return openBracket_line;
	}

	public void setOpenBracket_line(int openBracket_line) {
		this.openBracket_line = openBracket_line;
	}

	@Override
	public int getColumnNumber() {
		return openBracket_col;
	}

	@Override
	public int getLineNumber() {
		return openBracket_line;
	}
}
