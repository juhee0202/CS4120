package jl2755.ast;

import jl2755.visitor.ASTVisitor;

public class FieldDecl implements Decl {
	
	public enum Type {
		VAR_DECL, SHORT_TUPLE_DECL
	}
	
	private VarDecl varDecl;
	private ShortTupleDecl shortTupleDecl;
	private Type type;
	
	public FieldDecl(VarDecl vd) {
		varDecl = vd;
		type = Type.VAR_DECL;
	}
	
	public FieldDecl(ShortTupleDecl std) {
		shortTupleDecl = std;
		type = Type.SHORT_TUPLE_DECL;
	}

	public VarDecl getVarDecl() {
		return varDecl;
	}

	public ShortTupleDecl getShortTupleDecl() {
		return shortTupleDecl;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void prettyPrintNode() {
		switch(type) {
		case SHORT_TUPLE_DECL:
			shortTupleDecl.prettyPrintNode();
			break;
		case VAR_DECL:
			varDecl.prettyPrintNode();
			break;
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}
