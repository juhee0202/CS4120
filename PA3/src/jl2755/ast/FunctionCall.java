package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

/**
 *	Represents the function call
 *	index
 *	- 0: identifier()
 *	- 1: identifier(functionArg)
 *	- 2: length(identifier)
 *	- 3: length(arrayElement)
 */
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
            setLength(true);
        }
    }

    public FunctionCall(ArrayElement ae){
        arrayElement = ae;
        index = 3;
        setLength(true);
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

	/**
	 * @return the isLength
	 */
	public boolean isLength() {
		return isLength;
	}

	/**
	 * @param isLength the isLength to set
	 */
	public void setLength(boolean isLength) {
		this.isLength = isLength;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public FunctionArg getFunctionArg() {
		return functionArg;
	}

	public void setFunctionArg(FunctionArg functionArg) {
		this.functionArg = functionArg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayElement getArrayElement() {
		return arrayElement;
	}

	public void setArrayElement(ArrayElement arrayElement) {
		this.arrayElement = arrayElement;
	}
}
