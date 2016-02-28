package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class BracketsWithContent {
	private Expr e;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
	private int index;
	
	public BracketsWithContent(Expr e, 
							   BracketsWithContent bracketsWithContent) {
		this.e = e;
		this.bracketsWithContent = bracketsWithContent;
		index = 0;
	}
	
	public BracketsWithContent(Expr e) {
		this.e = e;
		index = 1;
	}
	
	public BracketsWithContent(Expr e, Brackets b) {
		this.e = e;
		brackets = b;
	}
	
	public List<Expr> getContent() {
		List<Expr> list = new ArrayList<Expr>();
		list.add(e);
		if (bracketsWithContent != null) {
			list.addAll(bracketsWithContent.getContent());
		}
		return list;
	}
	
	public int getNumBrackets() {
		if (brackets == null){
			return 0;
		}
		return brackets.getNumBrackets();
	}
	
	public int getTotalNumBrackets() {
		return this.getContent().size() + this.getNumBrackets();
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("[");
		tempPrinter.printAtom("]");
		e.prettyPrintNode();
		tempPrinter.endList();
	}
}
