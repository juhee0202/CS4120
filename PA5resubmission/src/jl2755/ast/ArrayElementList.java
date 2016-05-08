package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;


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
	
	public ArrayElementList(String stringToArray) {
		if (stringToArray.length() == 1) {
			index = 0;
			int charToInt = stringToArray.charAt(0);
			expr = new Literal("" + charToInt);
		}
		if (stringToArray.length() == 0) {
			index = 2;
		}
		if (stringToArray.length() > 1) {
			index = 1;
			int charToInt = stringToArray.charAt(0);
			expr = new Literal("" + charToInt);
			arrElemList = new ArrayElementList(stringToArray.substring(1));
		}
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
	
	public void accept(ASTVisitor v){
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
	
	public static ArrayElementList addTwoArrayElementLists(ArrayElementList leftArray, 
			ArrayElementList rightArray) {
		if (leftArray.getIndex() == 0) {
			ArrayElementList tempNew = new ArrayElementList(leftArray.getExpr(), rightArray);
			return tempNew;
		}
		else if (leftArray.getIndex() == 1) {
			ArrayElementList everythingElse = addTwoArrayElementLists(leftArray.getArrElemList(), rightArray);
			ArrayElementList tempNew = new ArrayElementList(leftArray.getExpr(), everythingElse);
			return tempNew;
		}
		else {
			return rightArray;
		}
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public ArrayElementList getArrElemList() {
		return arrElemList;
	}

	public void setArrElemList(ArrayElementList arrElemList) {
		this.arrElemList = arrElemList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
