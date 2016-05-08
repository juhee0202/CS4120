package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a break statement
 *
 */
public class Break implements NakedStmt {
	
	@Override
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom("break");
	}

	@Override
	public void accept(ASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
