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
	public boolean canDot() {
		// TODO Auto-generated method stub
		return false;
	}
}
