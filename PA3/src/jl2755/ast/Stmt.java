package jl2755.ast;

public class Stmt {
	private NakedStmt nakedStmt;
	private boolean hasSemicolon;
	
	public Stmt(NakedStmt ns, boolean b) {
		nakedStmt = ns;
		setHasSemicolon(b);
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
}
