package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.Visitor;


/**
 * Class that embodies the sequence of expressions
 * inside curly braces, i.e. the content of an
 * ArrayLiteral.
 */
public class ArrayElementList {
	private Expr expr;
	private ArrayElementList arrElemList;
	/**
	 * 0 if base case {..., x},
	 * 1 if recursive case {..., x, ...},
	 * 2 if blank list {}.
	 */
	private int index;
	
    public ArrayElementList(Expr e) {
        expr = e;
        index = 0;
    }

	public ArrayElementList(Expr e, ArrayElementList l) {
		expr = e;
		arrElemList = l;
		index = 1;
	}
	
	public ArrayElementList(){
		index = 2;
	}
	
	public void prettyPrintNode(){
		if (index == 2){
			return;
		}
		expr.prettyPrintNode();
		if (index == 1) {
			arrElemList.prettyPrintNode();
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
	
	public List<Expr> getAllExprInArray(){
		List<Expr> tempExprs = new ArrayList<Expr>();
		if (index == 0){
			tempExprs.add(expr);
		}
		if (index == 1){
			tempExprs.add(expr);
			tempExprs.addAll(arrElemList.getAllExprInArray());
		}
		return tempExprs;
	}
}
