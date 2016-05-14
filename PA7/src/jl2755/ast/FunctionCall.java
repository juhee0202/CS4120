package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.type.TupleType;
import jl2755.type.UnitType;
import jl2755.type.VType;
import jl2755.type.VarType;
import jl2755.visitor.ASTVisitor;

/**
 *	Represents the function call
 */
public class FunctionCall implements Expr,NakedStmt {
	private String ABIName;
	private Identifier identifier;
	private int identifier_col;
	private int identifier_line;
	private FunctionArg functionArg;
	private DotableExpr dotableExpr;
	private int dotableExpr_col;
	private int dotableExpr_line;
	private Expr expr;
	private int expr_col;
	private int expr_line;
	private int length_col;
	private int length_line;
	
	/**
	 * 0: id()
	 * 1: id(...)
	 * 2: length(e)
	 * 3: dotableExpr.id()
	 * 4: dotableExpr.id(...)
	 */
    private int index;
    private VType type;
    private boolean isSurroundedByParentheses = false;
    private int numReturns;

    public FunctionCall(Identifier id) {
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
	
	public FunctionCall(DotableExpr de, Identifier id) {
		dotableExpr = de;
		identifier = id;
		index = 3;
		dotableExpr_line = de.getLineNumber();
		dotableExpr_col = de.getColumnNumber();
	}
	
	public FunctionCall(DotableExpr de, Identifier id, FunctionArg fArg) {
		dotableExpr = de;
		identifier = id;
		functionArg = fArg;
		index = 4;
		dotableExpr_line = de.getLineNumber();
		dotableExpr_col = de.getColumnNumber();
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
		} else if (index == 3) {
			dotableExpr.prettyPrintNode();
			tempPrinter.printAtom(".");
			identifier.prettyPrintNode();
		} else if (index == 4) {
			dotableExpr.prettyPrintNode();
			tempPrinter.printAtom(".");
			identifier.prettyPrintNode();
			functionArg.prettyPrintNode();
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

	public VType getType() {
		return type;
	}

	public void setType(VType type) {
		this.type = type;
		if (type instanceof UnitType) {
			setNumReturns(0);
		} else if (type instanceof VarType) {
			setNumReturns(1);
		} else if (type instanceof TupleType) {
			setNumReturns(((TupleType) type).getTypes().size());
		}
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
	public void accept(ASTVisitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		if (index < 2) {
			return identifier_col;
		} else if (index > 2) {
			return dotableExpr_col;
		}
		return length_col;
	}

	@Override
	public int getLineNumber() {
		if (index < 2) {
			return identifier_line;
		} else if (index > 2) {
			return dotableExpr_line;
		}
		return length_line;
	}

	@Override
	public void setSurroundedParentheses() {
		isSurroundedByParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return isSurroundedByParentheses;
	}

	public int getNumReturns() {
		return numReturns;
	}

	public void setNumReturns(int numReturns) {
		this.numReturns = numReturns;
	}

	public DotableExpr getDotableExpr() {
		return dotableExpr;
	}

	public void setDotableExpr(DotableExpr dotableExpr) {
		this.dotableExpr = dotableExpr;
	}

	public int getDotableExpr_col() {
		return dotableExpr_col;
	}

	public void setDotableExpr_col(int dotableExpr_col) {
		this.dotableExpr_col = dotableExpr_col;
	}

	public int getDotableExpr_line() {
		return dotableExpr_line;
	}

	public void setDotableExpr_line(int dotableExpr_line) {
		this.dotableExpr_line = dotableExpr_line;
	}
}
