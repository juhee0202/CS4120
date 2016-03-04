package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

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

	public int getNumBrackets() {
		return getContent().size();
	}
}
