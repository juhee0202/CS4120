package jl2755.ast;

public class UseId {
	private String identifier;
	private UseId useId;
	private boolean hasSemicolon;
	private int index;
	
	public UseId(String s, boolean b) {
		identifier = s;
		hasSemicolon = b;
		index = 0;
	}
	
	public UseId(String s, UseId ui, boolean b) {
		identifier = s;
		useId = ui;
		hasSemicolon = b;
		index = 1;
	}
}
