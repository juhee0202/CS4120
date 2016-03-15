package jl2755.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Represents a list of variable declaration including UNDERSCORE in a 
 * recursive manner.
 *
 * index 
 * 	- 0: , varDecl
 * 	- 1: , varDecl, tdl
 * 	- 2: , _
 * 	- 3: , _, tdl
 */
public class TupleDeclList {
	private VarDecl varDecl;
	private TupleDeclList tupleDeclList;
	private boolean hasUnderscore;
	/**
	 * 0 if simple VarDecl in this slot and is base case, 1 if 
	 * VarDecl is in this slot and is a recursive case, 2 if
	 * underscore in this slot and is base case, 3 if underscore
	 * in this slot and is a recursive case.
	 */
	private int index;
	
	public TupleDeclList(VarDecl vd, boolean b) {
		varDecl = vd;
		setHasUnderscore(b);
		index = 0;
	}
	
	public TupleDeclList(VarDecl vd, TupleDeclList tdl, boolean b) {
		varDecl = vd;
		tupleDeclList = tdl;
		setHasUnderscore(b);
		index = 1;
	}

	public TupleDeclList(boolean b) {
		setHasUnderscore(b);
		index = 2;
	}

	public TupleDeclList(TupleDeclList tdl, boolean b) {
		setHasUnderscore(b);
		tupleDeclList = tdl;
		index = 3;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0) {
			varDecl.prettyPrintNode();
		} else if (index == 1) {
			varDecl.prettyPrintNode();
			tupleDeclList.prettyPrintNode();
		} else if (index == 2) {
			tempPrinter.printAtom("_");
		} else {
			tempPrinter.printAtom("_");
			tupleDeclList.prettyPrintNode();
		}
	}

	/**
	 * @return the hasUnderscore
	 */
	public boolean isHasUnderscore() {
		return hasUnderscore;
	}

	/**
	 * @param hasUnderscore the hasUnderscore to set
	 */
	public void setHasUnderscore(boolean hasUnderscore) {
		this.hasUnderscore = hasUnderscore;
	}
	
	/**
	 * @return the VarDecl's in the TupleDeclList. Nulls represent
	 * underscores.
	 */
	public List<VarDecl> getVarDecls(){
		List<VarDecl> temp = new ArrayList<VarDecl>();
		if (index == 0){
			temp.add(varDecl);
		}
		if (index == 1){
			temp.add(varDecl);
			temp.addAll(tupleDeclList.getVarDecls());
		}
		if (index == 2){
			temp.add(null);
		}
		if (index == 3){
			temp.add(null);
			temp.addAll(tupleDeclList.getVarDecls());
		}
		return temp;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

	public VarDecl getVarDecl() {
		return varDecl;
	}

	public void setVarDecl(VarDecl varDecl) {
		this.varDecl = varDecl;
	}

	public TupleDeclList getTupleDeclList() {
		return tupleDeclList;
	}

	public void setTupleDeclList(TupleDeclList tupleDeclList) {
		this.tupleDeclList = tupleDeclList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
