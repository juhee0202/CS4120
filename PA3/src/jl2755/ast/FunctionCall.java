package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class FunctionCall implements Expr,NakedStmt {
	private Identifier identifier;
	private FunctionArg functionArg;
    private int index;
    private boolean isLength = false;
    private ArrayElement arrayElement;
	
	public FunctionCall(Identifier id, FunctionArg fArg) {
		identifier = id;
		functionArg = fArg; 
        index = 1;
	}

    public FunctionCall(Identifier id, boolean argLength){
        identifier = id;
        index = 0;
        if (argLength){
            index = 2;
            isLength = true;
        }
    }

    public FunctionCall(ArrayElement ae){
        arrayElement = ae;
        index = 3;
        isLength = true;
    }
    
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.printAtom(identifier.toString());
		} else if (index == 1) {
			tempPrinter.printAtom(identifier.toString());
			functionArg.prettyPrintNode();
		} else if (index == 2) {
			tempPrinter.printAtom("length");
			identifier.prettyPrintNode();
		} else if (index == 3) {
			tempPrinter.printAtom("length");
			arrayElement.prettyPrintNode();
		}
		tempPrinter.endList();
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
