package jl2755.type;

public class NullType implements VType {

	
	@Override
	public boolean equals(Object o) {
		return (o instanceof VType);
	}
	
	@Override
	public String toString() {
		return "null";
	}

	@Override
	public boolean canDot() {
		return false;
	}
}
