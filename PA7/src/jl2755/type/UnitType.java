package jl2755.type;

public class UnitType implements VType {
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof UnitType)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "unit";
	}

	@Override
	public boolean canDot() {
		return false;
	}
}
