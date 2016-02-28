package jl2755.ast;

import jl2755.visitor.Visitor;

public interface NakedStmt {
	public void accept(Visitor v);
	public void prettyPrintNode();
}
