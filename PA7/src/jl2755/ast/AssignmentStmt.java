package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates an assignment of an expression
 * to an already declared variable.
 */
public class AssignmentStmt implements NakedStmt {
	
	/**
	 * 0 if left side is dotable_expr = e
	 * 1 if left side is dotable_expr[i] = e
	 */
	private int index;
	private DotableExpr dotableExpr;
	private IndexedBrackets indexedBrackets;
	private Expr expr;
	
	public AssignmentStmt(DotableExpr de, Expr e) {
		dotableExpr = de;
		expr = e;
		index = 0;
	}
	
	public AssignmentStmt(DotableExpr de, IndexedBrackets ib, Expr e) {
		dotableExpr = de;
		indexedBrackets = ib;
		expr = e;
		index = 1;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public DotableExpr getDotableExpr() {
		return dotableExpr;
	}

	public void setDotableExpr(DotableExpr dotableExpr) {
		this.dotableExpr = dotableExpr;
	}

	public IndexedBrackets getIndexedBrackets() {
		return indexedBrackets;
	}

	public void setIndexedBrackets(IndexedBrackets indexedBrackets) {
		this.indexedBrackets = indexedBrackets;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		if (index == 0) {
			dotableExpr.prettyPrintNode();
			expr.prettyPrintNode();
		} else if (index == 1){
			int numbBrackets = indexedBrackets.getNumBrackets();
			List<Expr> bracketContents = indexedBrackets.getContent();
			for (int i = 0; i < numbBrackets; i ++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			dotableExpr.prettyPrintNode();
			for (int i = 0; i < numbBrackets; i++){
				bracketContents.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
			expr.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}
