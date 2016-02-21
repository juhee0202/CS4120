package jl2755.ast;

public class Literal implements Constant {
	private Integer intLit;
	private String stringLit;
	private Character charLit;
	private Boolean boolLit;
	private int index;
	
	public Literal(int i) {
		intLit = i;
		index = 0;
	}
	
	public Literal(String s) {
		stringLit = s;
		index = 1;
	}
	
	public Literal(char c) {
		charLit = c;
		index = 2;
	}
	
	public Literal(boolean b) {
		boolLit = b;
		index = 3;
	}
}
