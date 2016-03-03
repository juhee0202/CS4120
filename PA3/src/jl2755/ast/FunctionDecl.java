package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

/**
 *	Represents function declaration which has the following grammar:
 *	identifier(functionParam)returnType blockStmt
 */
public class FunctionDecl {
	private Identifier identifier;
	private FunctionParam functionParam;
	private ReturnType returnType;
	private BlockStmt blockStmt;
	
	public FunctionDecl(Identifier s, FunctionParam fp, ReturnType rt,
						BlockStmt bs) {
		identifier = s;
		functionParam = fp;
		returnType = rt;
		blockStmt = bs;
	}
	
	public List<Type> getParamTypes(){
		return functionParam.getParamTypes();
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

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public FunctionParam getFunctionParam() {
		return functionParam;
	}

	public void setFunctionParam(FunctionParam functionParam) {
		this.functionParam = functionParam;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}

	public BlockStmt getBlockStmt() {
		return blockStmt;
	}

	public void setBlockStmt(BlockStmt blockStmt) {
		this.blockStmt = blockStmt;
	}
}
