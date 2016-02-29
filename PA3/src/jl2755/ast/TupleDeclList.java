package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class TupleDeclList {
	private VarDecl varDecl;
	private TupleDeclList tupleDeclList;
	private boolean hasUnderscore;
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
}
