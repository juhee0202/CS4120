package jl2755.exceptions;

public class SyntaxError extends RuntimeException {
	private int line;
	private int column;
	private String errorMessage;
	
	public SyntaxError(int l, int c, String e) {
		line = l;
		column = c;
		errorMessage = e;
	}
	
    @Override
    public String getMessage() {
        return errorMessage;
    }
}