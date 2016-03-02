package jl2755.ast;

import jl2755.visitor.Visitor;

public interface Type {
	public void prettyPrintNode();
	void accept(Visitor v);
}