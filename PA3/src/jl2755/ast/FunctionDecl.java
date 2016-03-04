package jl2755.ast;

import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
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
	private int functionParam_col;
	private int functionParam_line;
	private ReturnType returnType;
	private int returnType_col;
	private int returnType_line;
	private BlockStmt blockStmt;
	private int blockStmt_col;
	private int blockStmt_line;
	
	public FunctionDecl(Identifier s, FunctionParam fp, ReturnType rt, BlockStmt bs,
						int idleft, int idright, int fpleft, int fpright,
						int rtleft, int rtright, int bsleft, int bsright) {
		identifier = s;
		identifier_col = idleft;
		identifier_line = idright;
		functionParam = fp;
		functionParam_col = fpleft;
		functionParam_line = fpright;
		returnType = rt;
		returnType_col = rtleft;
		returnType_line = rtright;
		blockStmt = bs;
		blockStmt_col = bsleft;
		blockStmt_line = bsright;
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

	public int getFunctionParam_col() {
		return functionParam_col;
	}

	public void setFunctionParam_col(int functionParam_col) {
		this.functionParam_col = functionParam_col;
	}

	public int getFunctionParam_line() {
		return functionParam_line;
	}

	public void setFunctionParam_line(int functionParam_line) {
		this.functionParam_line = functionParam_line;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}

	public int getReturnType_col() {
		return returnType_col;
	}

	public void setReturnType_col(int returnType_col) {
		this.returnType_col = returnType_col;
	}

	public int getReturnType_line() {
		return returnType_line;
	}

	public void setReturnType_line(int returnType_line) {
		this.returnType_line = returnType_line;
	}

	public int getBlockStmt_col() {
		return blockStmt_col;
	}

	public void setBlockStmt_col(int blockStmt_col) {
		this.blockStmt_col = blockStmt_col;
	}

	public int getBlockStmt_line() {
		return blockStmt_line;
	}

	public void setBlockStmt_line(int blockStmt_line) {
		this.blockStmt_line = blockStmt_line;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
