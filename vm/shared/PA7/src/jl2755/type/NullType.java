package jl2755.type;

public class NullType implements VType {

	
	@Override
	public boolean equals(Object o) {
		return (o instanceof ClassType || o instanceof VarType);
	}
	
	@Override
	public String toString() {
		return "null";
	}

	@Override
	public boolean canDot() {
		return false;
	}

	@Override
	public boolean singleReturn() {
		return false;
	}
}
