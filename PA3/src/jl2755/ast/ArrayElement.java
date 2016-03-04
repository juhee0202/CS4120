package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Class that represents a reference to an already declared Variable,
 * functionCall, or an ArrayLiteral that is an array. Must have
 * brackets that all have indices in them.
 */
public class ArrayElement implements Expr {
	private Identifier identifier;
	private FunctionCall functionCall;
	private IndexedBrackets indexedBrackets;
	private ArrayLiteral arrayLiteral;
	/**
	 * 0 if identifier with indexedBrackets,
	 * 1 if functionCall with indexedBrackets,
	 * 2 if arrayLiteral with indexedBrackets
	 */
	private int index;
	
	public ArrayElement(Identifier id, IndexedBrackets ib) {
		identifier = id;
		indexedBrackets = ib;
		index = 0;
	}
	
	public ArrayElement(FunctionCall fc, IndexedBrackets ib) {
		functionCall = fc;
		indexedBrackets = ib;
		index = 1;
	}		
	
	public ArrayElement(ArrayLiteral al, IndexedBrackets ib) {
		arrayLiteral = al;
		indexedBrackets = ib;
		index = 2;
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
	
	public void accept(Visitor v){
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
}