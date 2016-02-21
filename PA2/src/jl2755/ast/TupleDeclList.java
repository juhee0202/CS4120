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
		hasUnderscore = b;
		index = 0;
	}
	
	public TupleDeclList(VarDecl vd, TupleDeclList tdl, boolean b) {
		varDecl = vd;
		tupleDeclList = tdl;
		hasUnderscore = b;
		index = 1;
	}

	public TupleDeclList(boolean b) {
		hasUnderscore = b;
		index = 2;
	}

	public TupleDeclList(TupleDeclList tdl, boolean b) {
		hasUnderscore = b;
		tupleDeclList = tdl;
		index = 3;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
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
		tempPrinter.endList();
	}
}
