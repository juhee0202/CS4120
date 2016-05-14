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
	private boolean isConst = false;
	
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
	
	public GlobalDecl(VarInit vi, boolean semicolon, boolean cons) {
		varInit = vi;
		hasSemicolon = semicolon;
		type = Type.VAR_INIT;
		setConst(cons);
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

	public VarInit getVarInit() {
		return varInit;
	}

	public ShortTupleDecl getShortTupleDecl() {
		return shortTupleDecl;
	}

	public TupleInit getTupleInit() {
		return tupleInit;
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
		case TUPLE_INIT:
			tupleInit.prettyPrintNode();
			break;
		case VAR_DECL:
			varDecl.prettyPrintNode();
			break;
		case VAR_INIT:
			if (isConst) {
				CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
				tempPrinter.printAtom("const");
			}
			varInit.prettyPrintNode();
			break;
		}
	}
}
