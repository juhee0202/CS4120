package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

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

	public List<InterfaceFunc> getMethods() {
		List<InterfaceFunc> methods = new ArrayList<InterfaceFunc>();
		methods.add(intFunc);
		if (intClassBody != null) {
			methods.addAll(intClassBody.getMethods());
		}
		return methods;
	}
}
