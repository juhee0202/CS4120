package jl2755.ast;

public enum UnaryOp {
	INT_NEG,
	LOG_NEG;
	
	public String toString() {
		String ret = "";
		
		switch (this) {
			case INT_NEG: ret = "-"; break;
			case LOG_NEG: ret = "!"; break;
		}
		
		return ret;
	}
}