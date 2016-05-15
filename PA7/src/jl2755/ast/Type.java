package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public interface Type {
	public int getColumnNumber();
	public int getLineNumber();
	public void prettyPrintNode();
	void accept(ASTVisitor v);
}