package jl2755.type;

public class UnitType implements VType {
	public boolean equals(Object o) {
		if (!(o instanceof UnitType)) {
			return false;
		}
		return true;
	}
}
