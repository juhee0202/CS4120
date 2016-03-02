package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class VarInit implements NakedStmt {
	private VarDecl varDecl;
	private Expr expr;
	
	public VarInit(VarDecl vd, Expr e) {
		varDecl = vd;
		expr = e;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		varDecl.prettyPrintNode();
		expr.prettyPrintNode();
		tempPrinter.endList();
	}

	public VarDecl getVarDecl() {
		return varDecl;
	}

	public void setVarDecl(VarDecl varDecl) {
		this.varDecl = varDecl;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
}
