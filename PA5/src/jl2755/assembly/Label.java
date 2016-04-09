package jl2755.assembly;

public class Label implements Operand {
	
	/** The name of this label. */
	private String name;
	
	public Label(String nam) {
		name = nam;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nam) {
		name = nam;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
