package jl2755.ast;

public class InterfaceClassBody {
	private InterfaceFunc intFunc;
	private InterfaceClassBody intClassBody;
	
	public InterfaceClassBody() {}
	
	public InterfaceClassBody(InterfaceFunc iff, InterfaceClassBody icb) {
		setIntFunc(iff);
		intClassBody = icb;
	}

	public InterfaceFunc getIntFunc() {
		return intFunc;
	}

	public void setIntFunc(InterfaceFunc intFunc) {
		this.intFunc = intFunc;
	}

	public InterfaceClassBody getIntClassBody() {
		return intClassBody;
	}

	public void setIntClassBody(InterfaceClassBody intClassBody) {
		this.intClassBody = intClassBody;
	}
}
