package edu.cornell.cs.cs4120.xic.ir;


import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * 
 * Represents a phi-function statement
 * a = phi(a1...an)
 *
 */
public class IRPhiFunction extends IRStmt {
	
	private String var;
	private String[] operands;
	
	public IRPhiFunction(String argVar, int n) {
		var = argVar;
		operands = new String[n];
	}
	
	public String getVar() {
		return var;
	}
	
	public void setOperand(int i, String operand) {
		operands[i] = operand;
	}
	
	@Override
	public IRNode copy() {
		return null;
	}

	@Override
	public String label() {
		return null;
	}

	@Override
	public void printSExp(SExpPrinter p) {
	}

	@Override
	public void accept(IRTreeVisitor irv) {
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
