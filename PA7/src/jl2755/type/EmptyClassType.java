package jl2755.type;

public class EmptyClassType implements VType {

	private String name;
	
	public EmptyClassType(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof EmptyClassType)) {
			return false;
		}
		return name.equals(((EmptyClassType)o).name);
	}
	
	@Override
	public boolean canDot() {
		return true;
	}

	@Override
	public boolean singleReturn() {
		return true;
	}
}
