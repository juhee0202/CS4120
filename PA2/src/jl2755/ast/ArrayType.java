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
			p_type.prettyPrintNode();
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
				tempPrinter.endList();
				list.get(i).prettyPrintNode();
				
			}
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.endList();
			}
		}
		if (index == 0) {
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} 
	}
}