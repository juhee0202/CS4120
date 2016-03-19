package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public interface Type {
	public void prettyPrintNode();
	void accept(ASTVisitor v);
}