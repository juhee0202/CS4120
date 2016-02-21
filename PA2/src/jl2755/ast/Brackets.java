package jl2755.ast;

public class Brackets {

	private Brackets brackets;
	private int index;
	
	public Brackets() {
		index = 0;
	}
	
	public Brackets(Brackets brackets) {
		this.brackets = brackets;
		index = 1;
	}
}
