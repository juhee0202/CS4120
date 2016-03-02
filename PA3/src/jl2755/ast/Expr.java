package jl2755.ast;

import jl2755.visitor.Visitor;

public interface Expr {
	public void prettyPrintNode();
	public void accept(Visitor v);
}