package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
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
	
	public int getFieldSize() {
		return fields.size();
	}
	
	public int getMethodSize() {
		return methods.size();
	}
		
	public int getNumberOfMethods() {
		return methods.size();
	}
	
	public int getNumberOfFields() {
		return fields.size();
	}

	@Override
	public IRNode copy() {
		List<String> copyMethods = new ArrayList<String>();
		copyMethods.addAll(methods);
		List<String> copyFields = new ArrayList<String>();
		copyFields.addAll(fields);
		return new IRDispatchVector(className, copyMethods, copyFields);
	}

	@Override
	public String label() {
		String s = "DISPATCH VECTOR";
		s += "CLASSNAME: " + className;
		s += "METHODS: " + methods.toString();
		s += "FIELDS: " + fields.toString();
		return s;
	}

	@Override
	public void accept(IRTreeVisitor irv) {
		irv.visit(this);
	}
	
	@Override
	public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom("DISPATCH VECTOR");
        p.printAtom("CLASSNAME: " + className);
        p.printAtom("METHODS: " + methods.toString());
        p.printAtom("FIELDS: " + fields.toString());
        p.endList();
	}

	@Override
	public void addLeft(IRNode irn) {
		// This should never be called for IRDispatchVector
		assert(false);
	}

	@Override
	public void addRight(IRNode irn) {
		// This should never be called for IRDispatchVector
		assert(false);
	}

	@Override
	public ChildType getLeftChildEnumType() {
		// This should never be called for IRDispatchVector
		assert(false);
		return null;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
		// This should never be called for IRDispatchVector
		assert(false);
	}

	@Override
	public ChildType getRightChildEnumType() {
		// This should never be called for IRDispatchVector
		assert(false);
		return null;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
		// This should never be called for IRDispatchVector
		assert(false);
	}

}
