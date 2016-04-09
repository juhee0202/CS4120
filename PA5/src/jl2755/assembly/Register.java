package jl2755.assembly;

public class Register implements Operand {

	/** The name of this register. */
	private String name;
	/** The stack address of this register. */
	private int addr;
	
	public Register(String name) {
		this.name = name;
	}
	
	public Register(String name, int addr) {
		this.name = name;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return null;
	}
	
}
