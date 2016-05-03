package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public interface NakedStmt {
	public void prettyPrintNode();
	
	/** Accept method to be overridden in each implementation of NakedStmt 
	 * @param v TODO*/
	public void accept(ASTVisitor v);
}
