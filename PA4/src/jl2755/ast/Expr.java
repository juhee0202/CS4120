package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public interface Expr {
	public void prettyPrintNode();
	public void accept(ASTVisitor v);
	public int getColumnNumber();
	public int getLineNumber();
}