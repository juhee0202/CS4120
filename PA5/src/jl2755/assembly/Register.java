package jl2755.assembly;

public class Register implements Operand {

	/** The name of this register. */
	private String name;
	/** The stack address of this register. */
	private int addr;
	
	/** Only used for copying */
	private Register() {
	}
	
	public Register(String name) {
		this.name = name;
	}
	
	public Register(String name, int addr) {
		this.name = name;
		this.addr = addr;
	}
	
	public Operand getNewOperand() {
		Register temp = new Register();
		temp.name = name;
		temp.addr = addr;
		return temp;
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

	@Override
	public String getOpType() {
		return "Register";
	}
	
}
