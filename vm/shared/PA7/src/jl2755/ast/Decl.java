package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public interface Decl {
	public void prettyPrintNode();
	public void accept(ASTVisitor v);
}
