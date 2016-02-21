package jl2755.ast;

public class PrimitiveType implements Type{
	
	/** 0 for INT and 1 for BOOL */
	private int index;
	
	public PrimitiveType(int index){
		this.index = index;
	}
}
