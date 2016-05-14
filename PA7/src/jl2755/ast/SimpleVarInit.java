package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class SimpleVarInit {
	private Identifier identifier;
	private Type type;
	private Constant constant;
	
	public SimpleVarInit(Identifier id, Type t, Constant c) {
		identifier = id;
		type = t;
		constant = c;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Type getType() {
		return type;
	}

	public Constant getConstant() {
		return constant;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("=");
		
		tempPrinter.startList();
		tempPrinter.printAtom(identifier.toString());
		type.prettyPrintNode();
		tempPrinter.endList();
		
		constant.prettyPrintNode();
		tempPrinter.endList();
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}
