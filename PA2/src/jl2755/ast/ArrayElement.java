package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ArrayElement implements Expr {
	private Identifier identifier;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
	private int index;
	
	public ArrayElement(Identifier id, 
						BracketsWithContent bwc) {
		identifier = id;
		bracketsWithContent = bwc;
		index = 0;
	}
	
	public ArrayElement(Identifier id,
						Brackets b){
		identifier = id;
		brackets = b;
		index = 1;
	}
		
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0){
			List<Expr> list = bracketsWithContent.getContent();
			for (int i = 0; i < list.size(); i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			tempPrinter.printAtom(identifier.toString());
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.endList();
			}
		}
		if (index == 1){
			for (int i = 0; i < brackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			tempPrinter.printAtom(identifier.toString());
			for (int i = 0; i < brackets.getNumBrackets(); i++){
				tempPrinter.endList();
			}
		}
	}
}