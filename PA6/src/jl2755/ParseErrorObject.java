package jl2755;

public class ParseErrorObject {
	private int lineNumber;
	private int colNumber;
	
	public ParseErrorObject(int l, int c) {
		setLineNumber(l);
		setColNumber(c);
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
}
