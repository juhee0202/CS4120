package jl2755.exceptions;

public class SemanticError extends RuntimeException {
	private String filename;
	private int line;
	private int column;
	private String description;
	
	public SemanticError(int l, int c, String d) {
		line = l;
		column = c;
		description = d;
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
    		s = "Semantic error beginning at " + line + ":" + column + ":" + description; 
    	} else {
    		s = "Semantic error at " + filename + ":" + line + ":" + column + ": " + description;
    	}
    	
        return s;
    }
}
