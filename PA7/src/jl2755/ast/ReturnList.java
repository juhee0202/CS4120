package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;

/**
 * Class that represents a list of expressions
 * used in a return statement.
 */
public class ReturnList {
	private Expr expr;
	private ReturnList returnList;
	/**
	 * 0 is the base case, 1 is the recursive case
	 */
	private int index;
	
	public ReturnList(Expr e) {
		expr = e;
		returnList = null;
		index = 0;
	}
	
	public ReturnList(Expr e, ReturnList rl) {
		expr = e;
		returnList = rl;
		index = 1;
	}
	
	public void prettyPrintNode() {
		expr.prettyPrintNode();
		if (index == 1) {
			returnList.prettyPrintNode();
		}
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	
	public void setExpr(Expr argExpr, int n) {
		if (n == 0) {
			expr = argExpr;
		} else {
			returnList.setExpr(argExpr,n-1);
		}
	}

	public ReturnList getReturnList() {
		return returnList;
	}

	public void setReturnList(ReturnList returnList) {
		this.returnList = returnList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	public List<Expr> getListOfExpr() {
		List<Expr> tempList = new ArrayList<Expr>();
		if (index == 0) {
			tempList.add(expr);
		}
		if (index == 1) {
			tempList.add(expr);
			tempList.addAll(returnList.getListOfExpr());
		}
		return tempList;
	}

}
