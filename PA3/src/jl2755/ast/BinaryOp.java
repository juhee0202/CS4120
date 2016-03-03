package jl2755.ast;

public enum BinaryOp {
	PLUS,
	MINUS,
	TIMES,
	HIGH_MULT,
	DIVIDE,
	MODULO,
	LT,
	LEQ,
	GT,
	GEQ,
	AND,
	OR,
	EQUAL,
	NOT_EQUAL;
	

	public String toString() {
		String ret = "";
		
		switch(this) {
			case PLUS: ret = "+"; break;
			case MINUS: ret = "-"; break;
			case TIMES: ret = "*"; break;
			case HIGH_MULT: ret = "*>>"; break;
			case DIVIDE: ret = "/"; break;
			case MODULO: ret = "%"; break;
			case LT: ret = "<"; break;
			case LEQ: ret = "<="; break;
			case GT: ret = ">"; break;
			case GEQ: ret = ">="; break;
			case AND: ret = "&"; break;
			case OR: ret = "|"; break;
			case EQUAL: ret = "=="; break;
			case NOT_EQUAL: ret = "!="; break;
		}
		
		return ret;
	}
}