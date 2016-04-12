package jl2755.assembly;

public class Label implements Operand{

	private String labelName;
	
	public Label(String s) {
		labelName = s;
	}
	
	@Override
	public String toString() {
		return labelName;
	}

	@Override
	public String getOpType() {
		return "Label";
	}

	@Override
	public Operand getNewOperand() {
		return new Label(labelName);
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
