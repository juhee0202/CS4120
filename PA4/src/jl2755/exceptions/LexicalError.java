package jl2755.exceptions;


public class LexicalError extends RuntimeException {
	
	private String filename;
	private int line;
	private int column;
	private String description;
	
	public LexicalError(int l, int c, String d) {
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
    		s = "Lexical error beginning at " + line + ":" + column + ":" + ": " + description; 
    	} else {
    		s = "Lexical error at " + filename + ":" + line + ":" + column + ":" + ": " + description;
    	}
    	
        return s;         		
    }
	
    @Override
    public String getMessage() {
        return message();
    }

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}
}
