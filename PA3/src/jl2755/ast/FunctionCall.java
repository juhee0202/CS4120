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
	private FunctionArg functionArg;
	private Expr expr;
    private int index;

    public FunctionCall(Identifier id){
        identifier = id;
        index = 0;
    }
    
	public FunctionCall(Identifier id, FunctionArg fArg) {
		identifier = id;
		functionArg = fArg; 
        index = 1;
	}
	
	public FunctionCall(Expr e) {
		expr = e;
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

	@Override
	public void accept(Visitor v){
		v.visit(this);
	}
}
