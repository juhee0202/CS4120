package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class FunctionDecl {
	private Identifier identifier;
	private FunctionParam functionParam;
	private ReturnType returnType;
	private BlockStmt blockStmt;
	
	public FunctionDecl(Identifier s, 
						FunctionParam fp,
						ReturnType rt,
						BlockStmt bs) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
		blockStmt = bs;
	}
	
	public List<Type> getParams(){
		return functionParam.getParams();
	}
	public List<Type> getReturnTypes() {
		return returnType.getReturnTypes();
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(identifier.toString());
		tempPrinter.startList();
		functionParam.prettyPrintNode();
		tempPrinter.endList();
		if (returnType != null){
			returnType.prettyPrintNode();
		}
		blockStmt.prettyPrintNode();
	}
}
