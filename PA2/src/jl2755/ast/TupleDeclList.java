package jl2755.ast;

public class TupleDeclList {
	private VarDecl varDecl;
	private TupleDeclList tupleDeclList;
	private boolean hasUnderscore;
	private int index;
	
	public TupleDeclList(VarDecl vd, boolean b) {
		varDecl = vd;
		hasUnderscore = b;
		index = 0;
	}
	
	public TupleDeclList(VarDecl vd, 
						 TupleDeclList tdl, 
						 boolean b) {
		varDecl = vd;
		tupleDeclList = tdl;
		hasUnderscore = b;
		index = 1;
	}
}
