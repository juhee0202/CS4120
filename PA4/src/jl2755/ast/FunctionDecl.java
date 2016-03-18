package jl2755.ast;

import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.type.FunType;
import jl2755.type.TupleType;
import jl2755.type.UnitType;
import jl2755.type.VType;
import jl2755.type.VarType;
import jl2755.visitor.Visitor;

/**
 *	Represents function declaration which has the following grammar:
 *	identifier(functionParam)returnType blockStmt
 */
public class FunctionDecl {
	private Identifier identifier;
	private int identifier_col;
	private int identifier_line;
	private FunctionParam functionParam;
	private ReturnType returnType;
	private BlockStmt blockStmt;
	private String ABIName;
	
	public FunctionDecl(Identifier s, FunctionParam fp, ReturnType rt, BlockStmt bs) {
		identifier = s;
		identifier_col = s.getColumnNumber();
		identifier_line = s.getLineNumber();
		functionParam = fp;
		returnType = rt;
		blockStmt = bs;
	}
	
	public Map<String, Type> getParamsWithTypes() {
		return functionParam.getParamsWithTypes();
	}
	
	public List<String> getParams() {
		return functionParam.getParams();
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

	public BlockStmt getBlockStmt() {
		return blockStmt;
	}

	public void setBlockStmt(BlockStmt blockStmt) {
		this.blockStmt = blockStmt;
	}
	
	public int getIdentifier_col() {
		return identifier_col;
	}

	public void setIdentifier_col(int identifier_col) {
		this.identifier_col = identifier_col;
	}

	public int getIdentifier_line() {
		return identifier_line;
	}

	public void setIdentifier_line(int identifier_line) {
		this.identifier_line = identifier_line;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}
	
	public String getABIName() {
		return ABIName;
	}

	public void setABIName(String aBIName) {
		ABIName = aBIName;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
