package jl2755.ast;

public class ArrayElement implements Expr {
	private String identifier;
	private BracketsWithContent bracketsWithContent;
	
	public ArrayElement(String id, 
						BracketsWithContent bwc) {
		identifier = id;
		bracketsWithContent = bwc;
	}
}
