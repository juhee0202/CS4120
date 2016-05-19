package edu.cornell.cs.cs4120.xic.ir;

import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRGlobalVariable extends IRNode {

	/** The name of this global variable. */
	private String name;
	/** The ABI name of this global variable. */
	private String ABIName;
	
	/** The value of this global variable if not an array. */
	private long value;
	
	private IRExpr expr;
	
	private boolean isInitialized;
	
	/** Whether this variable is an array or not. */
	private boolean isArray;
	
	/** The IRSeq representing the creation of this array. 
	 * 		null: if not array or array without size
	 * 		not null: if initialized with size
	 */
	private IRSeq createArray;
	
	public IRGlobalVariable(String n, String abi) {
		name = n;
		ABIName = abi;
		isInitialized = false;
		isInitialized = false;
	}
	
	public IRGlobalVariable(String n, String abi, long v) {
		name = n;
		ABIName = abi;
		value = v;
		isInitialized = true;
		isArray = false;
	}

	public IRGlobalVariable(String n, String abi, IRExpr v, IRSeq s) {
		name = n;
		ABIName = abi;
		expr = v;
		createArray = s;
		isInitialized = true;
		isArray = true;
	}

	public String getName() {
		return name;
	}
	
	public String getABIName() {
		return ABIName;
	}
	
	public IRSeq getCreateArray() {
		return createArray;
	}
	
	public void setCreateArray(IRSeq translated) {
		createArray = translated;
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	public boolean isArray() {
		return isArray;
	}
	
	public long getValue() {
		return value;
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
		p.startList();
		p.printAtom(ABIName);
		p.printAtom("= "+value);
		p.endList();
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
