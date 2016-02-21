package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ArrayType implements Type{
	
	private PrimitiveType p_type;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
	private ArrayType arrayType;
	private PureContentArrayType pureArray;
	private int index;
	
	public ArrayType(PrimitiveType p_type, Brackets brackets) {
		this.p_type = p_type;
		this.brackets = brackets;
		index = 0;
	}
	
	public ArrayType(PrimitiveType p_type, 
					 BracketsWithContent bracketsWithContent) {
		this.p_type = p_type;
		this.bracketsWithContent = bracketsWithContent;
		index = 1;
	}
	
	public ArrayType(PrimitiveType p_type, 
					 BracketsWithContent bracketsWithContent,
					 Brackets brackets) {
		this.p_type = p_type;
		this.bracketsWithContent = bracketsWithContent;
		this.brackets = brackets;
		index = 2;
	}
	
	public ArrayType(PureContentArrayType argPure){
		pureArray = argPure;
		index = 3;
	}
	
	public ArrayType(PureContentArrayType argPure, Brackets argBra){
		pureArray = argPure;
		brackets = argBra;
		index = 4;
	}

	
	public void prettyPrintNode() {
		int n = 0;
		List<Expr> list = new ArrayList<Expr>();
		
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0) {
			n = brackets.getNumBrackets();
			for (int i = 0; i < n; i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
		} else if (index == 1) {
			list = bracketsWithContent.getContent();
			for (int i = 0; i < list.size(); i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			p_type.prettyPrintNode();
			for (int i = 0; i < list.size(); i++){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.endList();
			}
		} else if (index == 4) {
			n = brackets.getNumBrackets();
			for (int i = 0; i < n; i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			list = pureArray.getBracketsWithContent();
			for (int i = 0; i < list.size(); i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			pureArray.getPrimitiveType().prettyPrintNode();
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
			for (int i = list.size()-1; i >= 0; i--) {
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			return;
		} else if (index == 3) {
			pureArray.prettyPrintToNode();
			return;
		}
		p_type.prettyPrintNode();
		if (index == 0) {
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} 
	}
}