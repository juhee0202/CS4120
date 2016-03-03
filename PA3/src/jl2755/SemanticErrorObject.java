package jl2755;

public class SemanticErrorObject {
	private int lineNumber;
	private int colNumber;
	private String description;
	
	public SemanticErrorObject(int l, int c, String d) {
		lineNumber = l;
		colNumber = c;
		description = d;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColNumber() {
		return colNumber;
	}

	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return lineNumber + ":" + colNumber + " error:" + description;
	}
}
