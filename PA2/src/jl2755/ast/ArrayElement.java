package jl2755.ast;

public class ArrayElement implements Expr {
	private Identifier identifier;
	private BracketsWithContent bracketsWithContent;
	
	public ArrayElement(Identifier id, 
						BracketsWithContent bwc) {
		identifier = id;
		bracketsWithContent = bwc;
	}
}
