package edu.cornell.cs.cs4120.xic.ir;

import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRDispatchVector extends IRNode {

	/** The class name that this method table refers to. */
	private String className;
	
	/** The list of method ABI names for this dispatch vector in order. */
	private List<String> methods;
	
	/** The list of field names for the class of this dispatch vector. */
	private List<String> fields;
	
	public IRDispatchVector(String name, List<String> dispatch, List<String> fs) {
		className = name;
		methods = dispatch;
		fields = fs;
	}

	public String getClassName() {
		return className;
	}

	public List<String> getMethods() {
		return methods;
	}
	
	public List<String> getFields() {
		return fields;
	}

	@Override
	public IRNode copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(IRTreeVisitor irv) {
		irv.visit(this);
	}
	
	@Override
	public void printSExp(SExpPrinter p) {
		// TODO Auto-generated method stub

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
