package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRGlobalReference extends IRExpr {

	public enum GlobalType {
		SIZE, DISPATCHVECTOR
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
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
		p.printAtom("GLOBAL VARIABLE " + ABIName);
	}

	@Override
	public void accept(IRTreeVisitor irv) {
		irv.visit(this);
	}

	@Override
	public void addLeft(IRNode irn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRight(IRNode irn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChildType getLeftChildEnumType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChildType getRightChildEnumType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
		// TODO Auto-generated method stub
		
	}

}
