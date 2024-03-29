package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class UseId {
	private Identifier identifier;
	private UseId useId;
	private boolean hasSemicolon;
	private int index;
	
	public UseId(Identifier s, boolean b) {
		identifier = s;
		hasSemicolon = b;
		index = 0;
	}
	
	public UseId(Identifier s, UseId ui, boolean b) {
		identifier = s;
		useId = ui;
		hasSemicolon = b;
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("use");
		tempPrinter.printAtom(identifier.toString());
		tempPrinter.endList();
		if (index == 1) {
			useId.prettyPrintNode();
		}
	}	
}
