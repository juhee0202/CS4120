package jl2755.exceptions;

public class SyntaxError extends RuntimeException {
	private int line;
	private int column;
	private String errorMessage;
	private String filename;
	
	public SyntaxError(int l, int c, String e) {
		line = l;
		column = c;
		errorMessage = e;
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
    		s = errorMessage;
    	} else {
    		s = filename + "\n\t" + errorMessage;
    	}
   
        return s;  
    }
}