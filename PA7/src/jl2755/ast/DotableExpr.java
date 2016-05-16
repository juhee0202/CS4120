package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class DotableExpr implements Expr {
	
	public enum Type {
		IDENTIFIER, FUNCTION_CALL, DOT, NEW, THIS, PAREN 
	}
	
	private Type type;
	private Identifier id;
	private FunctionCall functionCall;
	private DotableExpr dotableExpr;
	private int col;
	private int line;
	private boolean isSurroundedByParentheses = false;
	
	/**
	 * Constructor for "this"
	 */
	public DotableExpr(int this_left, int this_right) {
		type = Type.THIS;
		line = this_left;
		col = this_right;
	}
	
	/**
	 * Constructor for "id" or "new id"
	 * @param id
	 * @param isNew
	 * @param idleft
	 * @param idright
	 */
	public DotableExpr(Identifier id, boolean isNew) {
		if (isNew) {
			type = Type.NEW;
		} else {
			type = Type.IDENTIFIER;
		}
		this.id = id;
		line = id.getLineNumber();
		col = id.getColumnNumber();
	}
	
	/**
	 * Constructor for "f(...)"
	 * @param fc
	 */
	public DotableExpr(FunctionCall fc) {
		type = Type.FUNCTION_CALL;
		functionCall = fc;
		col = fc.getIdentifier_col();
		line = fc.getIdentifier_line();
	}

	
	/**
	 * Constructor for "(de)"
	 * @param de
	 */
	public DotableExpr(DotableExpr de) {
		type = Type.PAREN;
		isSurroundedByParentheses = true;
		dotableExpr = de;
		col = de.col;
		line = de.line;
	}
	
	/**
	 * Constructor for "de.i"
	 * @param de
	 * @param id
	 */
	public DotableExpr(DotableExpr de, Identifier id) {
		type = Type.PAREN;
		dotableExpr = de;
		this.id = id;
		col = de.col;
		line = de.line;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public FunctionCall getFunctionCall() {
		return functionCall;
	}

	public void setFunctionCall(FunctionCall functionCall) {
		this.functionCall = functionCall;
	}

	public DotableExpr getDotableExpr() {
		return dotableExpr;
	}

	public void setDotableExpr(DotableExpr dotableExpr) {
		this.dotableExpr = dotableExpr;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return col;
	}

	@Override
	public int getLineNumber() {
		return line;
	}

	@Override
	public void setSurroundedParentheses() {
		isSurroundedByParentheses = true;
	}

	@Override
	public boolean isSurroundedParentheses() {
		return isSurroundedByParentheses;
	}

	// TODO: not too sure
	@Override
	public void prettyPrintNode() {
		if (type == Type.IDENTIFIER) {
			id.prettyPrintNode();
		} else if (type == Type.FUNCTION_CALL) {
			functionCall.prettyPrintNode();
		} else if (type == Type.NEW) {
			CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
			tempPrinter.startList();
			tempPrinter.printAtom("new ");
			id.prettyPrintNode();
			tempPrinter.endList();
		} else if (type == Type.DOT) {
			CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
			dotableExpr.prettyPrintNode();
			tempPrinter.printAtom(".");
			id.prettyPrintNode();
		} else if (type == Type.THIS) {
			CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
			tempPrinter.printAtom("this");
		} else {	// Type.PAREN
			// TODO: not sure
			CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
			tempPrinter.startList();
			dotableExpr.prettyPrintNode();
			tempPrinter.endList();
		}
	}
}
