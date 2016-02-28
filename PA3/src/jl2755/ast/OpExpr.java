package jl2755.ast;

import jl2755.visitor.Visitor;

public interface OpExpr extends Expr {
	public void accept(Visitor v);
	public void prettyPrintNode();
}
