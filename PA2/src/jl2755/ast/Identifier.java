package jl2755.ast;

public class Identifier implements Expr{
	private String theValue;
	
	public Identifier(String argValue){
		theValue = argValue;
	}
}
