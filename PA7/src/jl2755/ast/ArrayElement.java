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
	private int identifier_col;
	private int identifier_line;
	private FunctionCall functionCall;
	private int functionCall_col;
	private int functionCall_line;
	private IndexedBrackets indexedBrackets;
	private ArrayLiteral arrayLiteral;
	private DotableExpr dotableExpr;
	private int arrayLiteral_col;
	private int arrayLiteral_line;
	private boolean isSurroundedByParentheses = false;
	/**
	 * 0 if identifier with indexedBrackets,
	 * 1 if functionCall with indexedBrackets,
	 * 2 if arrayLiteral with indexedBrackets
	 */
	private int index;
	
	public ArrayElement(Identifier id, IndexedBrackets ib) {
		identifier = id;
		identifier_col = id.getColumnNumber();
		identifier_line = id.getLineNumber();
		indexedBrackets = ib;
		index = 0;
	}
	
	public ArrayElement(FunctionCall fc, IndexedBrackets ib) {
		functionCall = fc;
		functionCall_col = fc.getColumnNumber();
		functionCall_line = fc.getLineNumber();
		indexedBrackets = ib;
		index = 1;
	}		
	
	public ArrayElement(ArrayLiteral al, IndexedBrackets ib) {
		arrayLiteral = al;
		arrayLiteral_col = al.getColumnNumber();
		arrayLiteral_line = al.getLineNumber();
		indexedBrackets = ib;
		index = 2;
	}

	public ArrayElement(DotableExpr de, IndexedBrackets ib) {
		dotableExpr = de;
		indexedBrackets = ib;
		index = 3;
		
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0){
			List<Expr> list = indexedBrackets.getContent();
			for (int i = 0; i < indexedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			tempPrinter.printAtom(identifier.toString());
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
		if (index == 1){
			List<Expr> list = indexedBrackets.getContent();
			for (int i = 0; i < indexedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			functionCall.prettyPrintNode();
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
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
		}
	}
	
	public void accept(ASTVisitor v){
		v.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public FunctionCall getFunctionCall() {
		return functionCall;
	}

	public void setFunctionCall(FunctionCall functionCall) {
		this.functionCall = functionCall;
	}

	public IndexedBrackets getIndexedBrackets() {
		return indexedBrackets;
	}

	public void setIndexedBrackets(IndexedBrackets indexedBrackets) {
		this.indexedBrackets = indexedBrackets;
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

	public int getIdentifier_col() {
		return identifier_col;
	}

	public void setIdentifier_col(int identifier_col) {
		this.identifier_col = identifier_col;
	}

	public int getIdentifier_line() {
		return identifier_line;
	}

	public void setIdentifier_line(int identifier_line) {
		this.identifier_line = identifier_line;
	}

	public int getFunctionCall_col() {
		return functionCall_col;
	}

	public void setFunctionCall_col(int functionCall_col) {
		this.functionCall_col = functionCall_col;
	}

	public int getFunctionCall_line() {
		return functionCall_line;
	}

	public void setFunctionCall_line(int functionCall_line) {
		this.functionCall_line = functionCall_line;
	}

	public int getArrayLiteral_col() {
		return arrayLiteral_col;
	}

	public void setArrayLiteral_col(int arrayLiteral_col) {
		this.arrayLiteral_col = arrayLiteral_col;
	}

	public int getArrayLiteral_line() {
		return arrayLiteral_line;
	}

	public void setArrayLiteral_line(int arrayLiteral_line) {
		this.arrayLiteral_line = arrayLiteral_line;
	}

	public DotableExpr getDotableExpr() {
		return dotableExpr;
	}

	public void setDotableExpr(DotableExpr dotableExpr) {
		this.dotableExpr = dotableExpr;
	}

	@Override
	public int getColumnNumber() {
		if (index == 0) {
			return identifier_col;
		}
		if (index == 1) {
			return functionCall_col;
		}
		else {
			return arrayLiteral_col;
		}
	}

	@Override
	public int getLineNumber() {
		if (index == 0) {
			return identifier_line;
		}
		if (index == 1) {
			return functionCall_line;
		}
		else {
			return arrayLiteral_line;
		}
	}

	@Override
	public void setSurroundedParentheses() {
		isSurroundedByParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return isSurroundedByParentheses;
	}
}