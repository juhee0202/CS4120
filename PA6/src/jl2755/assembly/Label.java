package jl2755.assembly;

import java.util.HashSet;
import java.util.Set;

public class Label implements Operand{

	private String labelName;
	private boolean isFuncLabel;
	
	public Label(String s) {
		this(s,false);
	}
	
	public Label(String s, boolean isFunc) {
		labelName = s;
		isFuncLabel = isFunc;
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

	@Override
	public boolean isConstOffset() {
		return false;
	}

	@Override
	public boolean isRegFactorOffset() {
		return false;
	}

	@Override
	public boolean isConstFactor() {
		return false;
	}

	@Override
	public boolean isRegBase() {
		return false;
	}

	@Override
	public Set<Register> getRegistersUsed() {
		return new HashSet<Register>();
	}
	
	public boolean isFuncLabel() {
		return isFuncLabel;
	}
}
