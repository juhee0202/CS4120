package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents brackets with contents.
 * index
 * 	- 0: [e]
 * 	- 1: [e]indexedBrackets
 */
public class IndexedBrackets {

	private Expr expression;
	private IndexedBrackets indexedBrackets;
	private int index;
	
	public IndexedBrackets(Expr e) {
		expression = e;
		index = 0;
	}
	
	public IndexedBrackets(Expr e, IndexedBrackets ib) {
		expression = e;
		indexedBrackets = ib;
		index = 1;
	}
	
	/**
	 * @return a List of expression inside each bracket pair
	 */
	public List<Expr> getContent() {
		List<Expr> tempList = new ArrayList<Expr>();
		if (index == 0){
			tempList.add(expression);
			return tempList;
		}
		else{
			tempList.add(expression);
			tempList.addAll(indexedBrackets.getContent());
			return tempList;
		}
	}

	/**
	 * @return a total number of bracket pairs
	 */
	public int getNumBrackets() {
		return getContent().size();
	}
}
