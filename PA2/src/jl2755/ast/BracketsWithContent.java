package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class BracketsWithContent {
	private Expr e;
	private BracketsWithContent bracketsWithContent;
	
	public BracketsWithContent(Expr e, 
							   BracketsWithContent bracketsWithContent) {
		this.e = e;
		this.bracketsWithContent = bracketsWithContent;
	}
	
	public BracketsWithContent(Expr e) {
		this.e = e;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("[");
		e.prettyPrintNode();
		tempPrinter.printAtom("]");
		tempPrinter.endList();
	}
}
