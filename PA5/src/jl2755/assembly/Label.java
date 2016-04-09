package jl2755.assembly;

public class Label implements Operand{

	private String labelName;
	
	public Label(String s) {
		labelName = s;
	}
	
	@Override
	public String toString() {
		return labelName + ":";
	}
}
