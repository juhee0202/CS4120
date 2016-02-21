package jl2755.ast;

public class ArrayType {
	
	private PrimitiveType p_type;
	private BracketsWithContent bracketsWithContent;
	private Brackets brackets;
	
	public ArrayType(PrimitiveType p_type, Brackets brackets) {
		this.p_type = p_type;
		this.brackets = brackets;
	}
	
	public ArrayType(PrimitiveType p_type, 
					 BracketsWithContent bracketsWithContent) {
		this.p_type = p_type;
		this.bracketsWithContent = bracketsWithContent;
	}
	
	public ArrayType(PrimitiveType p_type, 
					 BracketsWithContent bracketsWithContent,
					 Brackets brackets) {
		this.p_type = p_type;
		this.bracketsWithContent = bracketsWithContent;
		this.brackets = brackets;
	}
}