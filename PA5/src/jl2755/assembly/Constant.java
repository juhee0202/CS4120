package jl2755.assembly;

public class Constant implements Operand {

	/** The value of this constant. */
	private long value;
	
	public Constant(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return null;
	}
	
}
