package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRGlobalReference extends IRExpr {

	public enum GlobalType {
		SIZE, DISPATCHVECTOR, REGULAR, FUNCTION;
	}
	
	private String name;
	private String ABIName;
	private GlobalType typeOfGlobal;
	
	public IRGlobalReference(String argName, GlobalType type) {
		setTypeOfGlobal(type);
		name = argName;
		switch (type) {
		case DISPATCHVECTOR:
			ABIName = "_I_vt_" + name;
			break;
		case FUNCTION:
			ABIName = argName;
			break;
		case REGULAR:
			ABIName = argName;
			break;
		case SIZE:
			ABIName = "_I_size_" + name;
			break;
		}
	}
	
	public IRGlobalReference(String argName, String ABI, GlobalType type) {
		setTypeOfGlobal(type);
		name = argName;
		switch (type) {
		case DISPATCHVECTOR:
			ABIName = ABI;
			break;
		case FUNCTION:
			ABIName = ABI;
			break;
		case REGULAR:
			ABIName = ABI;
			break;
		case SIZE:
			ABIName = ABI;
			break;
		}
	}
	
	private void setTypeOfGlobal(GlobalType type) {
		typeOfGlobal = type;
	}

	@Override
	public IRNode copy() {
		return new IRGlobalReference(name,ABIName,typeOfGlobal);
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
		return name;
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
