package jl2755.type;

public class VoidType implements VType {
	public boolean equals(Object o) {
		if (!(o instanceof VoidType)) {
			return false;
		}
		return true;
	}
}
