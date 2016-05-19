package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that represents a reference to an already declared Variable,
 * functionCall, or an ArrayLiteral that is an array. Must have
 * brackets that all have indices in them.
 */
public class ArrayElement implements Expr {
	private Identifier identifier;
	private int lineNumber;
	private int columnNumber;
	private IndexedBrackets indexedBrackets;
	private DotableExpr dotableExpr;
	private ArrayLiteral arrayLiteral;
	private boolean isSurroundedByParentheses = false;
	/**
	 * 2 if arrayLiteral with indexedBrackets
	 * 3 if dotableExpr with indexedBrackets
	 */
	private int index;

	public ArrayElement(ArrayLiteral al, IndexedBrackets ib) {
		arrayLiteral = al;
		lineNumber = al.getColumnNumber();
		columnNumber = al.getLineNumber();
		indexedBrackets = ib;
		index = 2;
	}

	public ArrayElement(DotableExpr de, IndexedBrackets ib) {
		dotableExpr = de;
		lineNumber = de.getLineNumber();
		columnNumber = de.getColumnNumber();
		indexedBrackets = ib;
		index = 3;
		
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public IndexedBrackets getIndexedBrackets() {
		return indexedBrackets;
	}

	public void setIndexedBrackets(IndexedBrackets indexedBrackets) {
		this.indexedBrackets = indexedBrackets;
	}

	public DotableExpr getDotableExpr() {
		return dotableExpr;
	}

	public void setDotableExpr(DotableExpr dotableExpr) {
		this.dotableExpr = dotableExpr;
	}

	public ArrayLiteral getArrayLiteral() {
		return arrayLiteral;
	}

	public void setArrayLiteral(ArrayLiteral arrayLiteral) {
		this.arrayLiteral = arrayLiteral;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 2){
			List<Expr> list = indexedBrackets.getContent();
			for (int i = 0; i < indexedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			arrayLiteral.prettyPrintNode();
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		} else if (index == 3) {
			// TODO
			List<Expr> list = indexedBrackets.getContent();
			for (int i = 0; i < indexedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			dotableExpr.prettyPrintNode();
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
	}
	
	public void accept(ASTVisitor v){
		v.visit(this);
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
		if (index == 2) {
			return arrayLiteral.toString() + indexedBrackets.toString();
		}
		else {
			return dotableExpr.toString() + indexedBrackets.toString();
		}
	}
}