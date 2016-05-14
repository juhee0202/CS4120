package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a global variable(s) declaration/initialization
 *
 */
public class GlobalDecl implements Decl {
	
	public enum Type {
		VAR_DECL, SIMPLE_VAR_INIT, SHORT_TUPLE_DECL
	}
	
	private VarDecl varDecl;
	private ShortTupleDecl shortTupleDecl;
	private SimpleVarInit simpleVarInit;
	private Type type;
	private boolean hasSemicolon;
	private boolean isConst = false;
	
	public GlobalDecl(VarDecl vd, boolean semicolon) {
		varDecl = vd;
		hasSemicolon = semicolon;
		type = Type.VAR_DECL;
	}
	
	public GlobalDecl(SimpleVarInit svi, boolean semicolon) {
		simpleVarInit = svi;
		hasSemicolon = semicolon;
		type = Type.SIMPLE_VAR_INIT;
	}
	
	public GlobalDecl(ShortTupleDecl std, boolean semicolon) {
		shortTupleDecl = std;
		hasSemicolon = semicolon;
		type = Type.SHORT_TUPLE_DECL;
	}
	
	public boolean isConst() {
		return isConst;
	}

	public void setConst(boolean isConst) {
		this.isConst = isConst;
	}

	public Type getType() {
		return type;
	}

	public VarDecl getVarDecl() {
		return varDecl;
	}

	public SimpleVarInit getSimpleVarInit() {
		return simpleVarInit;
	}

	public ShortTupleDecl getShortTupleDecl() {
		return shortTupleDecl;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	@Override
	public void prettyPrintNode() {
		switch (type) {
		case SHORT_TUPLE_DECL:
			shortTupleDecl.prettyPrintNode();
			break;
		case SIMPLE_VAR_INIT:
			simpleVarInit.prettyPrintNode();
			break;
		case VAR_DECL:
			varDecl.prettyPrintNode();
			break;
		}
	}
}
