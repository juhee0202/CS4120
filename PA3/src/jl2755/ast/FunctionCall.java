package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 *	Represents the function call
 *	index
 *	- 0: identifier()
 *	- 1: identifier(functionArg)
 *	- 2: length(e)
 */
public class FunctionCall implements Expr,NakedStmt {
	private Identifier identifier;
	private int identifier_col;
	private int identifier_line;
	private FunctionArg functionArg;
	private int functionArg_col;
	private int functionArg_line;
	private Expr expr;
	private int expr_col;
	private int expr_line;
    private int index;

    public FunctionCall(Identifier id, int idleft, int idright){
        identifier = id;
        identifier_col = idleft;
        identifier_line = idright;
        index = 0;
    }
    
	public FunctionCall(Identifier id, FunctionArg fArg, 
						int idleft, int idright,
						int faleft, int faright) {
		identifier = id;
        identifier_col = idleft;
        identifier_line = idright;
		functionArg = fArg; 
		functionArg_col = faleft;
		functionArg_line = faright;
        index = 1;
	}
	
	public FunctionCall(Expr e, int eleft, int eright) {
		expr = e;
		expr_col = eleft;
		expr_line = eright;
		index = 2;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.printAtom(identifier.toString());
		} else if (index == 1) {
			tempPrinter.printAtom(identifier.toString());
			functionArg.prettyPrintNode();
		} else if (index == 2) {
			tempPrinter.printAtom("length");
//			identifier.prettyPrintNode();
			expr.prettyPrintNode();		// TODO revisit. NOT TOO SURE
		}
		tempPrinter.endList();
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public FunctionArg getFunctionArg() {
		return functionArg;
	}

	public void setFunctionArg(FunctionArg functionArg) {
		this.functionArg = functionArg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public int getIdentifier_col() {
		return identifier_col;
	}

	public void setIdentifier_col(int identifier_col) {
		this.identifier_col = identifier_col;
	}

	public int getIdentifier_line() {
		return identifier_line;
	}

	public void setIdentifier_line(int identifier_line) {
		this.identifier_line = identifier_line;
	}

	public int getFunctionArg_col() {
		return functionArg_col;
	}

	public void setFunctionArg_col(int functionArg_col) {
		this.functionArg_col = functionArg_col;
	}

	public int getFunctionArg_line() {
		return functionArg_line;
	}

	public void setFunctionArg_line(int functionArg_line) {
		this.functionArg_line = functionArg_line;
	}

	public int getExpr_col() {
		return expr_col;
	}

	public void setExpr_col(int expr_col) {
		this.expr_col = expr_col;
	}

	public int getExpr_line() {
		return expr_line;
	}

	public void setExpr_line(int expr_line) {
		this.expr_line = expr_line;
	}

	@Override
	public void accept(Visitor v){
		v.visit(this);
	}
}
