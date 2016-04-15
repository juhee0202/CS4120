package jl2755.ast;

import jl2755.visitor.ASTVisitor;

/**
 * Class that holds a nakedStmt but also keeps
 * track of whether there's a semi colon in this statement.
 */
public class Stmt {
	private NakedStmt nakedStmt;
	private boolean hasSemicolon;
	private int column;
	private int line;
	
	public Stmt(NakedStmt ns, boolean b, int nsleft, int nsright) {
		nakedStmt = ns;
		setHasSemicolon(b);
		column = nsright;
		line = nsleft;
	}
	
	public void prettyPrintNode() {
		nakedStmt.prettyPrintNode();
	}

	/**
	 * @return the hasSemicolon
	 */
	public boolean isHasSemicolon() {
		return hasSemicolon;
	}

	/**
	 * @param hasSemicolon the hasSemicolon to set
	 */
	public void setHasSemicolon(boolean hasSemicolon) {
		this.hasSemicolon = hasSemicolon;
	}

	public NakedStmt getNakedStmt() {
		return nakedStmt;
	}

	public void setNakedStmt(NakedStmt nakedStmt) {
		this.nakedStmt = nakedStmt;
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void accept(ASTVisitor v){
		v.visit(this);
	}
}
