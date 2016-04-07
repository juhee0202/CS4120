package jl2755.assembly;

public class Constant implements Operand {

	/** The value of this constant. */
	private int value;
	
	public Constant(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return null;
	}
	
}
