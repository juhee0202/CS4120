package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public class Null implements Expr {

	@Override
	public void prettyPrintNode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLineNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSurroundedParentheses() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSurroundedParentheses() {
		// TODO Auto-generated method stub
		return false;
	}

}
