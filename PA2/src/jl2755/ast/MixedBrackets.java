package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class MixedBrackets {
	private Expr e;
	private MixedBrackets mixedBrackets;
	private Brackets brackets;
	/** 0 in recursive case of MixedBrackets, 1 in base
	 * case of MixedBrackets without Brackets, and 2 in base case of MixedBrackets
	 * with Brackets.
	 */
	private int index;
	
	public MixedBrackets(Expr e, 
							   MixedBrackets mb) {
		this.e = e;
		this.mixedBrackets = mb;
		index = 0;
	}
	
	public MixedBrackets(Expr e) {
		this.e = e;
		index = 1;
	}
	
	public MixedBrackets(Expr e, Brackets b) {
		this.e = e;
		brackets = b;
		index = 2;
	}
	
	public List<Expr> getContent() {
		List<Expr> list = new ArrayList<Expr>();
		list.add(e);
		if (mixedBrackets != null) {
			list.addAll(mixedBrackets.getContent());
		}
		return list;
	}
	
	public int getNumBrackets(){
		if (brackets == null){
			return 0;
		}
		return brackets.getNumBrackets();
	}
	
	public int getTotalNumBrackets(){
		if (index == 0){
			return 1 + mixedBrackets.getTotalNumBrackets();
		}
		else if (index == 1){
			return 1;
		}
		else{
			return 1 + getNumBrackets();
		}
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
