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
		tempPrinter.printAtom("=");
		varDecl.prettyPrintNode();
		expr.prettyPrintNode();
	}
}
