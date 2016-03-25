package jl2755.exceptions;

public class SyntaxError extends RuntimeException {
	private String filename;
	private int line;
	private int column;
	private String description;
	
	public SyntaxError(int l, int c, String d) {
		line = l;
		column = c;
		description = d;
	}
	
	public void setFilename(String fn) {
		filename = fn;
	}
	
    public String message() {
    	String s;
    	
    	if (filename == null) {
    		s = "Syntax error beginning at " + line + ":" + column + ":" + description; 
    	} else {
    		s = "Syntax error at " + filename + ":" + line + ":" + column + ":" + description;
    	}
    	
        return s;         		
    }
	
    @Override
    public String getMessage() {
        return message();
    }
}