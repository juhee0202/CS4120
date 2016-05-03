package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;


/**
 * Class that encapsulates a list of array elements closed
 * by one set of curly braces.
 */
public class ArrayLiteral implements Constant {
	private ArrayElementList arrElemList;
	private int openBracket_col;
	private int openBracket_line;
	private boolean isSurroundedByParentheses = false;
	
	public ArrayLiteral(ArrayElementList l, int obleft, int obright) {
		arrElemList = l;
		openBracket_line = obleft;
		openBracket_col = obright;
	}
	
	public ArrayLiteral(String stringToArray) {
		arrElemList = new ArrayElementList(stringToArray);
	}
	
	public ArrayLiteral(ArrayElementList l) {
		arrElemList = l;
	}
	
	public static ArrayLiteral addTwoArrays(ArrayLiteral leftArray, ArrayLiteral rightArray) {
		return new ArrayLiteral(ArrayElementList.addTwoArrayElementLists(leftArray.getArrElemList(),
				rightArray.getArrElemList()));
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		arrElemList.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(ASTVisitor v){
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

	@Override
	public void setSurroundedParentheses() {
		isSurroundedByParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return isSurroundedByParentheses;
	}
	
	@Override
	public String toString() {
		// TODO: pretty printing array literal
		return "array literal";
	}
}
