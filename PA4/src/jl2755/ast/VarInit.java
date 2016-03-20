package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates the initialization of a variable,
 * which includes the declaration and an assignment of an
 * expression to it.
 */
public class VarInit implements NakedStmt {
	private VarDecl varDecl;
	private Expr expr;
	private Identifier id;
	private Type type;
	
	public VarInit(Identifier identifier, Type t, Expr e) {
		id = identifier;
		type = t;
		expr = e;
	}
	
//	public void prettyPrintNode(){
//		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
//		tempPrinter.startList();
//		tempPrinter.printAtom("=");
//		varDecl.prettyPrintNode();
//		expr.prettyPrintNode();
//		tempPrinter.endList();
//	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		
		//"var decl"
		tempPrinter.startList();
		tempPrinter.printAtom(id.toString());
		type.prettyPrintNode();
		tempPrinter.endList();
		
		//expr
		expr.prettyPrintNode();
		tempPrinter.endList();
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
	
	@Override
	public void accept(ASTVisitor v){
		v.visit(this);
	}
}
