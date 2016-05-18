package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

public class IRFuncDeclList extends IRNode{

	
	private List<IRFuncDecl> funcDecls;

	public IRFuncDeclList() {
		funcDecls = new ArrayList<IRFuncDecl>();
	}
	
	public IRFuncDeclList(IRFuncDecl argInit) {
		funcDecls = new ArrayList<IRFuncDecl>();
		funcDecls.add(argInit);
	}
	
	public IRFuncDeclList(Collection<IRFuncDecl> argInitCollection) {
		funcDecls = new ArrayList<IRFuncDecl>();
		funcDecls.addAll(argInitCollection);
	}
	
	public List<IRFuncDecl> getDecls() {
		return funcDecls;
	}
	
	public void addDecl(IRFuncDecl argDecl) {
		funcDecls.add(argDecl);
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
	public void printSExp(SExpPrinter p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(IRTreeVisitor irv) {
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
