package edu.cornell.cs.cs4120.xic.ir;

import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRGVList extends IRNode {

	private List<IRGlobalVariable> globalVariables;
	
	public IRGVList(List<IRGlobalVariable> gv) {
		globalVariables = gv;
	}
	
	public List<IRGlobalVariable> getGlobalVariables() {
		return globalVariables;
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
