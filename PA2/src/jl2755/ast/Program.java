package jl2755.ast;

public class Program {
	private UseId useId;
	private FunctionDeclList functionDeclList;
	private int index;
	
	public Program(FunctionDeclList fdl) {
		functionDeclList = fdl;
		index = 0;
	}
	
	public Program(UseId ui, FunctionDeclList fdl) {
		useId = ui;
		functionDeclList = fdl;
		index = 1;
	}
}
