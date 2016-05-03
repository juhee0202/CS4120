package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

/**
 * Represents indexed brackets & indexed brackets followed by empty brackets
 * index
 * 	- 0: [e]
 * 	- 1: [e]mixedBrackets
 * 	- 2: [e]brackets
 */
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
	
	/**
	 * @return a List containing expressions of only indexed brackets
	 * ex) [e1][e2][][][] -> {e1,e2}
	 */
	public List<Expr> getContent() {
		List<Expr> list = new ArrayList<Expr>();
		list.add(e);
		if (mixedBrackets != null) {
			list.addAll(mixedBrackets.getContent());
		}
		return list;
	}
	
	/**
	 * @return a total number of bracket pairs (indexed+empty)
	 */
	public int getNumBrackets(){
		if (index == 0) {
			return 1 + mixedBrackets.getNumBrackets();
		}
		if (index == 1) {
			return 1;
		}
		else {
			return 1 + brackets.getNumBrackets();
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
