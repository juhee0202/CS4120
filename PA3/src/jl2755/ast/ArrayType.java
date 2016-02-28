package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class ArrayType implements Type{
	
	private PrimitiveType p_type;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
	private ArrayType arrayType;
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
			for (int i = 0; i < bracketsWithContent.getNumBrackets(); i++){
				tempPrinter.endList();
			}
			for (int i = list.size()-1; i >= 0; i--){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
		if (index == 0) {
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} 
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	public PrimitiveType getP_type() {
		return p_type;
	}

	public void setP_type(PrimitiveType p_type) {
		this.p_type = p_type;
	}

	public BracketsWithContent getBracketsWithContent() {
		return bracketsWithContent;
	}

	public void setBracketsWithContent(BracketsWithContent bracketsWithContent) {
		this.bracketsWithContent = bracketsWithContent;
	}

	public Brackets getBrackets() {
		return brackets;
	}

	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
	}

	public ArrayType getArrayType() {
		return arrayType;
	}

	public void setArrayType(ArrayType arrayType) {
		this.arrayType = arrayType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}