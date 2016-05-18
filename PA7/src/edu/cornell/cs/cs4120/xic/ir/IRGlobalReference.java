package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRGlobalReference extends IRExpr {

	public enum GlobalType {
		SIZE, DISPATCHVECTOR, REGULAR, FUNCTION;
	}
	
	private String className;
	private String ABIName;
	private GlobalType typeOfGlobal;
	
	public IRGlobalReference(String argClassName, GlobalType type) {
		setTypeOfGlobal(type);
		className = argClassName;
		if (type == GlobalType.SIZE) {
			ABIName = "_I_size_" + className;
		}
		else {
			ABIName = "_I_vt_" + className;
		}
	}
	
	private void setTypeOfGlobal(GlobalType type) {
		typeOfGlobal = type;
	}

	@Override
	public IRNode copy() {
		return new IRGlobalReference(className,typeOfGlobal);
	}

	@Override
	public int hashCode() {
		return ABIName.hashCode();
	}

	@Override
	public String label() {
		return null;
	}

	public GlobalType getTypeOfGlobal() {
		return typeOfGlobal;
	}
	
	public String getClassName() {
		return className;
	}

	public String getABIName() {
		return ABIName;
	}

	@Override
	public void printSExp(SExpPrinter p) {
		p.startList();
		if (typeOfGlobal == GlobalType.FUNCTION) {
			p.printAtom("METHOD " + ABIName);
		}
		else {
			p.printAtom("GLOBAL VARIABLE " + ABIName);
		}
		p.endList();
	}

	@Override
	public void accept(IRTreeVisitor irv) {
		irv.visit(this);
	}

	@Override
	public void addLeft(IRNode irn) {
	}

	@Override
	public void addRight(IRNode irn) {
	}

	@Override
	public ChildType getLeftChildEnumType() {
		return null;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
	}

	@Override
	public ChildType getRightChildEnumType() {
		return null;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
	}

}
