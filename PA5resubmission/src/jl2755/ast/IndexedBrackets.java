package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;

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
	
	public IndexedBrackets(List<Expr> exprs) {
		if (exprs.size() == 1) {
			expression = exprs.get(0);
			index = 0;
		} else {
			expression = exprs.get(0);
			indexedBrackets = new IndexedBrackets(exprs.subList(1, exprs.size()));
		}
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
	
	public List<Expr> getAllExprInIndexedBrackets() {
		List<Expr> tempListExpr = new ArrayList<Expr>();
		tempListExpr.add(expression);
		if (index == 1) {
			tempListExpr.addAll(indexedBrackets.getAllExprInIndexedBrackets());
		}
		return tempListExpr;
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	public Expr getExpression() {
		return expression;
	}

	public void setExpression(Expr expression) {
		this.expression = expression;
	}

	public IndexedBrackets getIndexedBrackets() {
		return indexedBrackets;
	}

	public void setIndexedBrackets(IndexedBrackets indexedBrackets) {
		this.indexedBrackets = indexedBrackets;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
