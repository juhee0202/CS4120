package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ArrayElement implements Expr {
	private Identifier identifier;
	private FunctionCall functionCall;
	private IndexedBrackets indexedBrackets;
	private ArrayLiteral arrayLiteral;
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
}