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
		VAR_DECL, VAR_INIT, SHORT_TUPLE_DECL, TUPLE_INIT
	}
	
	private VarDecl varDecl;
	private VarInit varInit;
	private ShortTupleDecl shortTupleDecl;
	private TupleInit tupleInit;
	private Type type;
	private boolean hasSemicolon;
	
	public GlobalDecl(VarDecl vd, boolean semicolon) {
		varDecl = vd;
		hasSemicolon = semicolon;
		type = Type.VAR_DECL;
	}
	
	public GlobalDecl(VarInit vi, boolean semicolon) {
		varInit = vi;
		hasSemicolon = semicolon;
		type = Type.VAR_INIT;
	}
	
	public GlobalDecl(ShortTupleDecl std, boolean semicolon) {
		shortTupleDecl = std;
		hasSemicolon = semicolon;
		type = Type.SHORT_TUPLE_DECL;
	}
	
	public GlobalDecl(TupleInit ti, boolean semicolon) {
		tupleInit = ti;
		hasSemicolon = semicolon;
		type = Type.TUPLE_INIT;
	}
	
	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		
		tempPrinter.startList();
		switch (type) {
		case SHORT_TUPLE_DECL:
			shortTupleDecl.prettyPrintNode();
			break;
		case TUPLE_INIT:
			tupleInit.prettyPrintNode();
			break;
		case VAR_DECL:
			varDecl.prettyPrintNode();
			break;
		case VAR_INIT:
			varInit.prettyPrintNode();
			break;
		}
	}
}
