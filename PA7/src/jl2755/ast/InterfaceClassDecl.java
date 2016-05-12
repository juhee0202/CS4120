package jl2755.ast;

public class InterfaceClassDecl {
	private Identifier className;
	private Identifier superclassName;
	private InterfaceClassBody intClassBody;
	
	public InterfaceClassDecl(Identifier id, InterfaceClassBody icb) {
		className = id;
		intClassBody = icb;
	}
	
	public InterfaceClassDecl(Identifier id1, Identifier id2, InterfaceClassBody icb) {
		className = id1;
		superclassName = id2;
		intClassBody = icb;
	}

	public Identifier getClassName() {
		return className;
	}

	public void setClassName(Identifier className) {
		this.className = className;
	}

	public Identifier getSuperclassName() {
		return superclassName;
	}

	public void setSuperclassName(Identifier superclassName) {
		this.superclassName = superclassName;
	}

	public InterfaceClassBody getIntClassBody() {
		return intClassBody;
	}

	public void setIntClassBody(InterfaceClassBody intClassBody) {
		this.intClassBody = intClassBody;
	}
}
