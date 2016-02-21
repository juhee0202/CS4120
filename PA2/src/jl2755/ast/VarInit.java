package jl2755.ast;

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
		tempPrinter.printAtom(VarInit.toString(this));
		varDecl.prettyPrintNode();
		expr.prettyPrintNode();
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "=";
	}
}
