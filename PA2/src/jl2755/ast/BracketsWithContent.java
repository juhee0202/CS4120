package jl2755.ast;

public class BracketsWithContent {
	private Expr e;
	private BracketsWithContent bracketsWithContent;
	
	public BracketsWithContent(Expr e, 
							   BracketsWithContent bracketsWithContent) {
		this.e = e;
		this.bracketsWithContent = bracketsWithContent;
	}
	
	public BracketsWithContent(Expr e) {
		this.e = e;
	}
}
