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
	private String ABIName;
	private int identifier_col;
	private int identifier_line;
	private FunctionArg functionArg;
	private Expr expr;
	private int expr_col;
	private int expr_line;
	private int length_col;
	private int length_line;
    private int index;

    public FunctionCall(Identifier id){
        identifier = id;
        identifier_col = id.getColumnNumber();
        identifier_line = id.getLineNumber();
        index = 0;
    }
    
	public FunctionCall(Identifier id, FunctionArg fArg) {
		identifier = id;
        identifier_col = id.getColumnNumber();
        identifier_line = id.getLineNumber();
		functionArg = fArg; 
        index = 1;
	}
	
	public FunctionCall(Expr e, int lleft, int lright) {
		expr = e;
		expr_col = e.getColumnNumber();
		expr_line = e.getLineNumber();
		length_line = lleft;
		length_col = lright;
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

	public String getABIName() {
		return ABIName;
	}

	public void setABIName(String aBIName) {
		ABIName = aBIName;
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

	public int getLength_col() {
		return length_col;
	}

	public void setLength_col(int length_col) {
		this.length_col = length_col;
	}

	public int getLength_line() {
		return length_line;
	}

	public void setLength_line(int length_line) {
		this.length_line = length_line;
	}

	@Override
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		if (index < 2) {
			return identifier_col;
		}
		return length_col;
	}

	@Override
	public int getLineNumber() {
		if (index < 2) {
			return identifier_line;
		}
		return length_line;
	}
}
