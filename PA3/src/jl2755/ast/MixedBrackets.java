package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class MixedBrackets {
	private Expr e;
	private MixedBrackets mixedBrackets;
	private Brackets brackets;
	private int index;
	
	public MixedBrackets(Expr e, 
							   MixedBrackets mb) {
		this.e = e;
		this.mixedBrackets = mb;
		setIndex(0);
	}
	
	public MixedBrackets(Expr e) {
		this.e = e;
		setIndex(1);
	}
	
	public MixedBrackets(Expr e, Brackets b) {
		this.e = e;
		brackets = b;
		setIndex(2);
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
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("[");
		tempPrinter.printAtom("]");
		e.prettyPrintNode();
		tempPrinter.endList();
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
