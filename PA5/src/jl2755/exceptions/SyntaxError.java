package jl2755.exceptions;

public class SyntaxError extends RuntimeException {
	private int line;
	private int column;
	private String description;
	private String unexpectedToken;
	private String filename;
	
	public SyntaxError(int l, int c, String d, String token) {
		line = l;
		column = c;
		description = d;
		unexpectedToken = token;
	}
	
	public void setFilename(String fn) {
		if (filename == null) {
			filename = fn;
		}
	}
	
    @Override
    public String getMessage() {
    	String s;
    	
    	if (filename == null) {
    		s = "Syntactic error beginning at " + line + ":" + column + ":" + 
    				description + " " + unexpectedToken; 
    	} else {
    		s = "Syntactic error at " + filename + ":" + line + ":" + 
    				column + ": " + description;
    	}
   
        return s;  
    }
}