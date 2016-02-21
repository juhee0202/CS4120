package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ArrayType implements Type{
	
	private PrimitiveType p_type;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
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
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom(p_type.toString());
		if (index == 0) {
			brackets.prettyPrintNode();
		} else if (index == 1) {
			bracketsWithContent.prettyPrintNode();
		} else {
			brackets.prettyPrintNode();
			bracketsWithContent.prettyPrintNode();
		}
		tempPrinter.endList();
	}
}