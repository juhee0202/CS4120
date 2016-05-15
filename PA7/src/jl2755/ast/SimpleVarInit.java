package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class SimpleVarInit {
	private Identifier identifier;
	private PrimitiveType primitiveType;
	private Literal literal;
	
	public SimpleVarInit(Identifier id, PrimitiveType pt, Literal l) {
		identifier = id;
		primitiveType = pt;
		literal = l;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	public Literal getLiteral() {
		return literal;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		primitiveType.prettyPrintNode();
		tempPrinter.endList();
		literal.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}
