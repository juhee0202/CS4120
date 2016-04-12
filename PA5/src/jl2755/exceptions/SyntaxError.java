package jl2755.exceptions;

public class SyntaxError extends RuntimeException {
	private String filename;
	private int line;
	private int column;
	private String errorMessage;
	
	public SyntaxError(int l, int c, String e) {
		line = l;
		column = c;
		errorMessage = e;
	}
	
	public void setFilename(String fn) {
		filename = fn;
	}
	
    public String message() {
    	String s;
    	
    	if (filename == null) {
    		s = errorMessage; 
    	} else {
    		s = filename + ": " + errorMessage;
    	}
    	
        return s;         		
    }
	
    @Override
    public String getMessage() {
        return message();
    }
}