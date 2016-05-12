package jl2755.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import jl2755.Main;
import jl2755.SemanticErrorObject;
import jl2755.ast.*;
import jl2755.type.FunType;
import jl2755.type.TupleType;
import jl2755.type.UnitType;
import jl2755.type.VType;
import jl2755.type.VarType;
import jl2755.type.VoidType;

public class TypeCheckVisitor implements ASTVisitor {

	/** HashMap for the object oriented Xi */
	private HashMap<String, VType> globalEnv;
	
	/** HashMap of all declared variables */
	private HashMap<String, VType> env;
	private HashMap<String, VType> if_env;
	private Stack<String> stack;	// "_": special marker
	private VType tempType;
	private VType stmtType;	// either UnitType or VoidType
	private VType functionReturnType;
	private boolean negativeNumber = false; // needed for UnaryExpr, Literal
	
	public TypeCheckVisitor(){
		globalEnv = new HashMap<String, VType>();
//		env = new HashMap<String, VType>();
//		if_env = new HashMap<String, VType>();
		stack = new Stack<String>();
	}
	
	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(ArrayElement ae) {
		// check if the variables inside indexed brackets have been declared
		IndexedBrackets ib= ae.getIndexedBrackets();
		for (Expr exp: ib.getContent()) {
			exp.accept(this);

			// check that the index in [index] is an int
			if (!(tempType instanceof VarType && 
					((VarType) tempType).isInt())) {
				String s = "Expected int, but found " + tempType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						exp.getLineNumber(),
						exp.getColumnNumber(),
						s
						);
				Main.handleSemanticError(seo);
			}
		}
		
		int index = ae.getIndex();
		if (index == 0){
			if (!(env.containsKey(ae.getIdentifier().toString()))){
				String errorDesc = "Name " + ae.getIdentifier().toString() +
						" cannot be resolved.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getIdentifier_line(),
						ae.getIdentifier_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			VType tempScopeType = env.get(ae.getIdentifier().toString());
			if (!(tempScopeType instanceof VarType)){
				String errorDesc = "Name " + ae.getIdentifier().toString() +
						" is not of variable type.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getIdentifier_line(),
						ae.getIdentifier_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			VarType varTypeView = (VarType) tempScopeType;
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (numberOfBrackets > varTypeView.getNumBrackets()){
				String errorDesc = "Array dimensions don't match.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getIdentifier_line(),
						ae.getIdentifier_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(varTypeView.getIsBool(), varTypeView.getNumBrackets() - numberOfBrackets);
		}
		else if (index == 1){
			ae.getFunctionCall().accept(this);
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (!(tempType instanceof VarType)){
				String errorDesc = "Name " + ae.getIdentifier().toString() +
						" is not of variable type.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getFunctionCall_line(),
						ae.getFunctionCall_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			VarType arrayTypeAfterVisit = (VarType) tempType;
			if (numberOfBrackets > arrayTypeAfterVisit.getNumBrackets()){
				String errorDesc = "Array dimensions don't match.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getFunctionCall_line(),
						ae.getFunctionCall_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			boolean oldIsBool = arrayTypeAfterVisit.getIsBool();
			int oldNumBrackets = arrayTypeAfterVisit.getNumBrackets();
			tempType = new VarType(oldIsBool, oldNumBrackets - numberOfBrackets);
		}
		else if (index == 2){
			ae.getArrayLiteral().accept(this);
			if (!(tempType instanceof VarType)){
				String errorDesc = "Name " + ae.getIdentifier().toString() +
						" is not of variable type.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getArrayLiteral_line(),
						ae.getArrayLiteral_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			VarType arrayTypeAfterVisit = (VarType) tempType;
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (numberOfBrackets > arrayTypeAfterVisit.getNumBrackets()){
				String errorDesc = "Array dimensions don't match.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getArrayLiteral_line(),
						ae.getArrayLiteral_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			boolean oldIsBool = arrayTypeAfterVisit.getIsBool();
			int oldNumBrackets = arrayTypeAfterVisit.getNumBrackets();
			tempType = new VarType(oldIsBool, oldNumBrackets - numberOfBrackets);
		}
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(ArrayElementList ael) {
		List<Expr> tempExprs = ael.getAllExprInArray();
		List<VarType> tempTypesOfExprs = new ArrayList<VarType>();
		for (int i = 0; i < tempExprs.size(); i++){
			tempExprs.get(i).accept(this);
			if (!(tempType instanceof VarType)){
				String errorDesc = "Name " + tempExprs.get(i).toString() +
						" is not of VarType";
				SemanticErrorObject seo = new SemanticErrorObject(
						tempExprs.get(i).getLineNumber(),
						tempExprs.get(i).getColumnNumber(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			tempTypesOfExprs.add((VarType)tempType);
		}
		for (int i = 0; i < tempTypesOfExprs.size() - 1; i++){
			if (!(tempTypesOfExprs.get(i).equals(tempTypesOfExprs.get(i+1)))){
				String errorDesc = "Expected " + tempTypesOfExprs.get(i).toString() + ", but found "
						+ tempTypesOfExprs.get(i+1).toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						tempExprs.get(i+1).getLineNumber(),
						tempExprs.get(i+1).getColumnNumber(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
		}
		if (tempTypesOfExprs.size() == 0) {
			tempType = new VarType(false,0);
		}
		else {
			tempType = new VarType(tempTypesOfExprs.get(0).getIsBool(), tempTypesOfExprs.get(0).getNumBrackets());
		}
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(ArrayLiteral al) {
		al.getArrElemList().accept(this);
		if (!(tempType instanceof VarType)){
			String errorDesc = "Name " + tempType.toString() +
					" is not of VarType";
			SemanticErrorObject seo = new SemanticErrorObject( 
					al.getLineNumber(),
					al.getColumnNumber(),
					errorDesc
					);
			Main.handleSemanticError(seo);
		}
		VarType tempVarView = (VarType) tempType;
		boolean oldIsBool = tempVarView.getIsBool();
		int oldNumBrackets = tempVarView.getNumBrackets();
		tempType = new VarType(oldIsBool, oldNumBrackets + 1);
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(AssignmentStmt as) {
		int index = as.getIndex();
		
		// ex: a = 3
		if (index == 0) {
			// Identifier visit checks if its in env
			String id = as.getIdentifier().toString();
			if (!env.containsKey(id)) {
				String s = "Name " + id + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						as.getIdentifier().getLineNumber(), 
						as.getIdentifier().getColumnNumber(),
						s);
				Main.handleSemanticError(seo);
			}
			
			VType idType = env.get(id);
			
			//check that identifier is a var type
			if (!(idType instanceof VarType)) {
				String s = "Expected variable type, but found function type.";
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getIdentifier().getLineNumber(), 
											as.getIdentifier().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			// Check types
			if (!idType.equals(exprType)) {
				String s = "Expected " + idType.toString() 
							+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getExpr().getLineNumber(), 
											as.getExpr().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
		//ex: arr[2] = 3;
		} else if (index == 1) {
			
			// check if identifier is in env
			String id = as.getIdentifier().toString();
			if (!env.containsKey(id)) {
				String s = "Name " + id + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getIdentifier().getLineNumber(), 
											as.getIdentifier().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			VarType idType = (VarType)env.get(id);
			
			VType elementType = new VarType(idType,as.getIndexedBrackets(),as.getIdentifier());

			// check that all the indices inside indexedBrackets are ints
			List<Expr> exprs = as.getIndexedBrackets().getContent();
			for (Expr e: exprs) {
				e.accept(this);
				VarType exprType = (VarType) tempType;
				if (!exprType.isInt()) {
					String s = "Expected an int, but found " + 
							exprType.toString();
					SemanticErrorObject seo = new SemanticErrorObject(
							e.getLineNumber(),
							e.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
			}
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				String s = "Expected " + elementType.toString() 
				+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getExpr().getLineNumber(), 
											as.getExpr().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
		
		//ex: f(3)[0] = "herro"
		} else if (index == 2){
			as.getFunctionCall().accept(this);
			VType functionCallType = tempType;
			if (!(functionCallType instanceof VarType)) {
				String s;
				if (functionCallType instanceof TupleType) {
					s = "Expected variable type, but found tuple type";
				} else if (functionCallType instanceof UnitType) {
					 s = "Expected variable type, but found unit type";
				} else {
					 s = "Expected variable type, but found incompatible type";
				}
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getFunctionCall().getLineNumber(), 
											as.getFunctionCall().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			VarType funcCallType = (VarType) functionCallType;
			VType elementType = new VarType (funcCallType, as.getIndexedBrackets(), as.getFunctionCall());
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				String s = "Expected " + elementType.toString() 
				+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getExpr().getLineNumber(), 
											as.getExpr().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			// check that all the indices inside indexedBrackets are ints
			List<Expr> exprs = as.getIndexedBrackets().getContent();
			for (Expr e: exprs) {
				e.accept(this);
				VarType eType = (VarType) tempType;
				if (!eType.isInt()) {
					String s = "Expected an int, but found " + 
							eType.toString();
					SemanticErrorObject seo = new SemanticErrorObject(
							e.getLineNumber(),
							e.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
			}
		}
		// index = 3 array literal assignment statement
		else {
			ArrayLiteral al = as.getArrayLiteral();
			al.accept(this);
			VType arrayLiteralType = tempType;
			
			if (!(arrayLiteralType instanceof VarType)) {
				String s;
				if (arrayLiteralType instanceof TupleType) {
					s = "Expected variable type, but found tuple type";
				} else if (arrayLiteralType instanceof UnitType) {
					 s = "Expected variable type, but found unit type";
				} else {
					 s = "Expected variable type, but found incompatible type";
				}
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getFunctionCall().getLineNumber(), 
											as.getFunctionCall().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			VarType arrayLitType = (VarType) arrayLiteralType;
			VType elementType = new VarType (arrayLitType, as.getIndexedBrackets(), as.getArrayLiteral());
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				String s = "Expected " + elementType.toString() 
				+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getExpr().getLineNumber(), 
											as.getExpr().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}

			// check that all the indices inside indexedBrackets are ints
			List<Expr> exprs = as.getIndexedBrackets().getContent();
			for (Expr e: exprs) {
				e.accept(this);
				VarType eType = (VarType) tempType;
				if (!eType.isInt()) {
					String s = "Expected an int, but found " + 
							eType.toString();
					SemanticErrorObject seo = new SemanticErrorObject(
							e.getLineNumber(),
							e.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
			}
		}
		tempType = new UnitType();
		stmtType = new UnitType();
	}

	@Override
	public void visit(BinaryExpr be) {
		Expr left = be.getLeftExpr();
		Expr right = be.getRightExpr();
		
		// check that leftExpr is of VarType
		left.accept(this);
		if (!(tempType instanceof VarType)) {
			String s = "Expected a variable type, but found " + 
					tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					be.getLineNumber(),
					be.getColumnNumber(), 
					s
					);
			Main.handleSemanticError(seo);
		}
		VarType leftType = (VarType) tempType;
		// check that rightExpr is of VarType
		right.accept(this);
		if (!(tempType instanceof VarType)) {
			String s = "Expected a variable type, but found " + 
					tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					be.getLineNumber(),
					be.getColumnNumber(), 
					s
					);
			Main.handleSemanticError(seo);
		}
		VarType rightType = (VarType) tempType;
		
		BinaryOp op = be.getBinaryOp();
		
		// check that left & right expr have the same type
		if (!leftType.equals(rightType)) {
			String s = "Mismatched types for binary operation " + op.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					be.getLineNumber(),
					be.getColumnNumber(), 
					s
					);
			Main.handleSemanticError(seo);
		}
		
		/* + operator
		 * 		(i) both int
		 * 		(ii) both int arrays
		 */
		if (op.toString().equals("+")) {
			if (leftType.isInt()) {
				tempType = new VarType(false, 0);
			} else if (leftType.isArray()) {
				tempType = new VarType(leftType.getIsBool(), 
						leftType.getNumBrackets());
			} else {
				String s = "Invalid expression types for + operation.";
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
		}
		/* !=, == operator
		 * 		(i) both are int/bool 
		 * 		(ii) both are arrays with same element type
		 */
		else if (op.toString().equals("!=") || op.toString().equals("==")) {
			if (leftType.isBool() ||leftType.isInt() || leftType.isArray()) {	
				tempType = new VarType(true, 0);
			} else {
				String s = "Invalid types for " + op.toString() + " operation.";
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
		}
		/* 
		 * <, <=, >, >= operator
		 * 		(i) only allow int types
		 * 		entire BinaryExpr evaluates to bool
		 */
		else if (op.toString().equals("<") || 
				 op.toString().equals("<=") ||
				 op.toString().equals(">") ||
				 op.toString().equals(">=")) 
		{
			if (!leftType.equals(new VarType(false,0))) {
				String s = "Expected int for " + op.toString() + " operation, " +
						"but found " + leftType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(true,0);
		}
		/*
		 * &, | operator
		 * 		(i) only allows bool types
		 * 		entire BinaryExpr evaluates to bool
		 */
		else if (op.toString().equals("&") ||
				 op.toString().equals("|")) {
			
			if (!leftType.equals(new VarType(true,0))) {
				String s = "Expected bool for " + op.toString() + " operation, " +
						"but found " + leftType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(true,0);
		}
		/*
		 * -, *, *<<, /, %
		 * 		(i) only allows ints
		 * 		entire BinaryExpr evaluates to int
		 */
		else {
			if (!leftType.equals(new VarType(false,0))) {
				String s = "Expected int for " + op.toString() + " operation, " +
						"but found " + leftType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(false,0);
		}
		be.setType(tempType);
	}
	
	@Override
	public void visit(BlockStmt bs) {		
		int index = bs.getIndex();
		
		// empty block statement
		if (index == 0) {
			tempType = new UnitType();
			stmtType = new UnitType();
			return;
		}
		
		// Start of scope
		stack.add("_");
		
		// stmt list without an ending return stmt
		// could have return stmts inside the stmt list		
		if (index == 1) {
			// if there is an ending return stmt inside the stmt list
			// then stmtType will be set to VoidType by it
			bs.getStmtList().accept(this);
		} 
		// both stmt list and an ending return stmt		
		else if (index == 2) {
			bs.getStmtList().accept(this);
			bs.getReturnStmt().accept(this);
		} 
		// only return stmt
		else if (index == 3) {
			bs.getReturnStmt().accept(this);
		}
		
		// Pop out of scope
		String id = stack.pop();
		while (!id.equals("_")) {
			env.remove(id);
			id = stack.pop();
		}
	}

	/**
	 * DIRTIES tempType to function argument type (VarType or TupleType)
	 * @param FunctionArg fa
	 */
	@Override
	public void visit(FunctionArg fa) {
		int index = fa.getIndex();
		if (index == 0) {
			fa.getExpr().accept(this);
		} else {
			List<Expr> argExprs = fa.getArgExprs();
			TupleType argType = new TupleType();
			for (Expr e : argExprs) {
				e.accept(this);
				argType.addToTypes((VarType)tempType);
			}
			tempType = argType;
		}
	}

	/**
	 * DIRTIES tempType = return type of the function call
	 * 1) check if the function call's arguments match with the one in env
	 * 2) set tempType to the return type of the function call
	 * @param FunctionCall fc
	 */
	@Override
	public void visit(FunctionCall fc) {
		int index = fc.getIndex();
		
		/* Special Case: length(expr) */
		if (index == 2) {
			fc.getExpr().accept(this);
			if (!(tempType instanceof VarType)) {
				String s = "Argument to the length method should be an array type";
				SemanticErrorObject seo = new SemanticErrorObject(
						fc.getExpr_line(), fc.getExpr_col(), s);
				Main.handleSemanticError(seo);
			}
			VarType arrayType = (VarType)tempType;
			if (!arrayType.isArray()) {
				String s = "Argument to the length method should be an array type";
				SemanticErrorObject seo = new SemanticErrorObject(
						fc.getExpr_line(), fc.getExpr_col(), s);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(false, 0);
			fc.setType(tempType);
			return;
		}
		
		String id = fc.getIdentifier().toString();
		
		/* Check if the function is declared */
		if (!env.containsKey(id)) {
			String s = "Name " + id.toString() + " cannot be resolved";
			SemanticErrorObject seo = new SemanticErrorObject(
					fc.getIdentifier_line(), fc.getIdentifier_col(), s);
			Main.handleSemanticError(seo);
		}
		
		VType temp = env.get(id);
		if (!(temp instanceof FunType)) {
			String s = "Expected function type, but found " + temp.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					fc.getIdentifier_line(), fc.getIdentifier_col(), s);
			Main.handleSemanticError(seo);
		}
		FunType funType = (FunType)temp;
		String ABIName = functionToABIName(id, funType);
		fc.setABIName(ABIName);
		VType paramType = funType.getParamTypes();
		VType args;
		
		/* Case: id() */
		if (index == 0) {
			args = new UnitType();
			if (!args.equals(paramType)) {
				String s = "Expected " + paramType.toString() + ", but found unit";
				SemanticErrorObject seo = new SemanticErrorObject(
						fc.getIdentifier_line(), fc.getIdentifier_col(), s);
				Main.handleSemanticError(seo);
			}
		}
		/* Case: id(funtionArg) */
		else if (index == 1) {
			fc.getFunctionArg().accept(this);
			args = tempType;
			if (!args.equals(paramType)) {
				String s = "Expected " + paramType.toString() + ", but found " + args.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						fc.getIdentifier_line(), fc.getIdentifier_col(), s);
				Main.handleSemanticError(seo);
			}
		}
		
		tempType = funType.getReturnTypes();
		fc.setType(tempType);
		
		stmtType = new UnitType();
	}

	/**
	 * 1) update env
	 * 2) typecheck the function body
	 * 3) restore the original env
	 * @param FunctionDecl fd
	 */
	@Override
	public void visit(FunctionDecl fd) {		
		String funId = fd.getIdentifier().toString();
		FunType funType = (FunType) env.get(funId);
		String ABIName = functionToABIName(funId, funType);
		fd.setABIName(ABIName);
				
		/* Update the function scope env */
		Map<String, Type> paramToType = fd.getParamsWithTypes();
		
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			String id = entry.getKey();
			VType type = new VarType(entry.getValue());
			if (env.containsKey(id)) {
				String s = id + " is already declared";
				SemanticErrorObject seo = new SemanticErrorObject(
						fd.getIdentifier_line(), fd.getIdentifier_col(), s);
				Main.handleSemanticError(seo);
			} else {
				env.put(id, type);
				stack.push(id);
			}
		}
		
		/* Typecheck function body */
		// comparing the expected and actual return type is done inside returnStmt
		String id = fd.getIdentifier().toString();
		funType = (FunType) env.get(id);	// safe
		functionReturnType = funType.getReturnTypes();
		fd.getBlockStmt().accept(this);
		// check the return type using tempType
		if (!tempType.equals(functionReturnType)) {
			String s = "Expected " + functionReturnType.toString() + ", but found " + tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(fd.getIdentifier_line(), fd.getIdentifier_col(), s);
			Main.handleSemanticError(seo);			
		}
		
		/* Restore the parent scope */
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			env.remove(entry.getKey());
		}
	}
	
	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(Identifier id) {
		if (!(env.containsKey(id.toString()))){
			String s = "Name " + id.toString() + " cannot be resolved.";
			SemanticErrorObject seo = new SemanticErrorObject(
					id.getLineNumber(), 
					id.getColumnNumber(),
					s
					);
			Main.handleSemanticError(seo);
		}
		tempType = env.get(id.toString());
		id.setType(tempType);
	}
	
	/**
	 * Dirties tempType
	 * IfStmt's stmtType is set by its children stmt
	 */
	@Override
	public void visit(IfStmt is) {
		is.getExpr().accept(this);
		VType exprType = tempType;
		VType bType = new VarType(true,0);
		
		// Check type of conditional
		if (!exprType.equals(bType)) {
			String s = "Expected bool, but found " + exprType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
										is.getExpr().getLineNumber(), 
										is.getExpr().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		// Start new scope
		if (!(is.getStmt1().getNakedStmt() instanceof BlockStmt)) {
			stack.push("_");
		}
		
		// Check if-then stmt
		(is.getStmt1()).accept(this);
		
		// Pop out of scope
		if (!(is.getStmt1().getNakedStmt() instanceof BlockStmt)) {
			String id = stack.pop();
			while (!id.equals("_")) {
				env.remove(id);
				id = stack.pop();
			}
		}
		
		VType ifStmtType = stmtType;
		stmtType = new UnitType();
		
		// Check else stmt
		if (is.getIndex() == 1) {
			// Start new scope
			if (!(is.getStmt2().getNakedStmt() instanceof BlockStmt)) {
				stack.push("_");
			}
			
			(is.getStmt2()).accept(this);
			
			if (!(is.getStmt2().getNakedStmt() instanceof BlockStmt)) {
				// Pop out of scope
				String id = stack.pop();
				while (!id.equals("_")) {
					env.remove(id);
					id = stack.pop();
				}
			}
		}
		
		VType elseStmtType = stmtType;
		
		if (!(ifStmtType instanceof VoidType && elseStmtType instanceof VoidType)) {
			stmtType = new UnitType();
		}
	}
	
	@Override
	public void visit(IndexedBrackets ib) {
		List<Expr> exprs = ib.getContent();
		for (Expr e: exprs) {
			e.accept(this);
			VarType exprType = (VarType) tempType;
			if (!exprType.isInt()) {
				String s = "Expected an int, but found " + 
						exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						e.getLineNumber(),
						e.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
		}
	}

	/** 
	 * Dirties tempType
	 */
	@Override
	public void visit(Literal l) {
		int index = l.getIndex();
		switch (index) {
			case 0: 												// int
				String intLiteral = l.getIntLit();
				try {
					if (negativeNumber && !l.isSurroundedParentheses()) {
						Long.parseLong("-" + intLiteral);
					} else {
						Long.parseLong(intLiteral);
					}
				} catch (NumberFormatException e) {
					String s = "Integer out of bounds";
					SemanticErrorObject seo = new SemanticErrorObject(
							l.getLineNumber(), l.getColumnNumber(), s);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(false, 0); 
				break;		
			case 1: tempType = new VarType(false, 1); break;		// string
			case 2: tempType = new VarType(false, 0); break;		// char
			case 3: tempType = new VarType(true, 0);  break;		// boolean
		}
	}
	
	@Override
	public void visit(Null n) {
		// TODO: Is this needed
	}
	
	/**
	 * PA7 note: 
	 * 	handle module list here (should use p.getAllDecls())
	 * 
	 * 1) Update if_env with function decls from interface files
	 * 2) Update env with function decls from the file.
	 * 
	 * @param Program p
	 */
	@Override
	public void visit(Program p) {
		// Interface files are used => fill global environment with
		if (p.getIndex() == 1) {
			p.getUseId().accept(this);
		}
		
		// TODO If interface with same name exists, then use it!
		String fileName = Main.getSrcFile();
		if (Main.checkInterfaceExists(fileName)) {
			Map<String, VType> headerMap = Main.checkInterface(fileName);
			Iterator<String> mapIter = headerMap.keySet().iterator();

			while (mapIter.hasNext()){
				String moduleMemberName = mapIter.next();

				// if multiple function declarations, then check that
				// the types are the same
				if (if_env.containsKey(moduleMemberName)) {
					VType existingType = if_env.get(moduleMemberName);
					VType newType = headerMap.get(moduleMemberName);

					if (!(existingType.equals(newType))) {
						String s = "Conflicting declarations on " 
								+ moduleMemberName + ": "
								+ "trying to add " + newType.toString() + " when "
								+ existingType.toString() + " already exists.";
						SemanticErrorObject seo = new SemanticErrorObject(
								1,1, s);
						Main.handleSemanticError(seo);
					}
				}
				// no multiple declarations: add module member to global env
				else{
					globalEnv.put(moduleMemberName, headerMap.get(moduleMemberName));
				}
			}
		}
		
		
		/* Add module members in current program to the environment */
		ModuleList ml = p.getModuleList();
		List<Decl> decls = ml.getAllDecls();
		for (Decl decl: decls) {
			// (i) make sure that global variables do not conflict
		}
		
		///////////////////////////////////////////////////
		
		/* Add the function declarations to the environment */
		List<FunctionDecl> functionDecls = p.getFunctionDecls();
		for (FunctionDecl fd : functionDecls) {
			String id = fd.getIdentifier().toString();
			FunType funType = new FunType(fd);
			
			// if the function was declared in interface files
			// OK if same FunType
			if (if_env.containsKey(id)) {
				FunType ifFunType = (FunType) if_env.get(id); // safe
				if (!funType.equals(ifFunType)) {
					String s = "Conflicting declarations on " + id + ": " 
							+ "trying to add " + funType.toString() + " when "
							+ ifFunType.toString() + " already exists.";
					SemanticErrorObject seo = new SemanticErrorObject(
							fd.getIdentifier_line(), fd.getIdentifier_col(), s);
					Main.handleSemanticError(seo);
				}
			}
			// if the function was declared before
			if (env.containsKey(id)) {
				String s = "Multiple declaration found for function " + id;
				SemanticErrorObject seo = new SemanticErrorObject(
						fd.getIdentifier_line(), fd.getIdentifier_col(), s);
				Main.handleSemanticError(seo);
			}
			env.put(id, funType);
			stack.push(id);
		}
		
		/* Merge if_env with env */
		env.putAll(if_env);
		
		// Check functions
		for (FunctionDecl fd : functionDecls) {
			fd.accept(this);
		}
	}
//	@Override
//	public void visit(Program p) {
//		/* Update if_env if interface files are used */
//		if (p.getIndex() == 1) {
//			p.getUseId().accept(this);
//		}
//		
//		/* Add the function declarations to the environment */
//		List<FunctionDecl> functionDecls = p.getFunctionDecls();
//		for (FunctionDecl fd : functionDecls) {
//			String id = fd.getIdentifier().toString();
//			FunType funType = new FunType(fd);
//			
//			// if the function was declared in interface files
//			// OK if same FunType
//			if (if_env.containsKey(id)) {
//				FunType ifFunType = (FunType) if_env.get(id); // safe
//				if (!funType.equals(ifFunType)) {
//					String s = "Conflicting declarations on " + id + ": " 
//							+ "trying to add " + funType.toString() + " when "
//							+ ifFunType.toString() + " already exists.";
//					SemanticErrorObject seo = new SemanticErrorObject(
//							fd.getIdentifier_line(), fd.getIdentifier_col(), s);
//					Main.handleSemanticError(seo);
//				}
//			}
//			// if the function was declared before
//			if (env.containsKey(id)) {
//				String s = "Multiple declaration found for function " + id;
//				SemanticErrorObject seo = new SemanticErrorObject(
//						fd.getIdentifier_line(), fd.getIdentifier_col(), s);
//				Main.handleSemanticError(seo);
//			}
//			env.put(id, funType);
//			stack.push(id);
//		}
//		
//		/* Merge if_env with env */
//		env.putAll(if_env);
//		
//		// Check functions
//		for (FunctionDecl fd : functionDecls) {
//			fd.accept(this);
//		}
//	}

	/**
	 * DIRTIES tempType to the return type
	 * check if the return type is equal to functionReturnType
	 * stmtType is UnitType if no return value and VoidType otherwise
	 */
	@Override
	public void visit(ReturnStmt rs) {
		VType returnType;
		
		 // index = 1: function call (should have a return value)
		if (rs.getIndex() == 1) {
			List<Expr> returnExprs = rs.getReturnList().getListOfExpr();
			// one return value
			if (returnExprs.size() == 1) {
				Expr returnExpr = returnExprs.get(0);
				returnExpr.accept(this);
				returnType = tempType;
				
				// if return obj is a procedure call, then error!
				// ex: foo() { return foo() }
				if (returnType instanceof UnitType) {
					String errMsg = 
							((FunctionCall)returnExpr).getIdentifier().toString() +
							" is not a function that returns a value. ";
					SemanticErrorObject seo = new SemanticErrorObject(
							returnExpr.getLineNumber(),
							returnExpr.getColumnNumber(),
							errMsg);
					Main.handleSemanticError(seo);	
				}
			}
			// multiple return values
			else {
				returnType = new TupleType();
				for (int i = 0; i < returnExprs.size(); i++){
					Expr e = returnExprs.get(i);
					e.accept(this);
					VType exprType = tempType;
					
					// if expr is a procedure call, then error!
					if (exprType instanceof UnitType) {
						String errMsg = 
								((FunctionCall)e).getIdentifier().toString() +
								" is not a function that returns a value. ";
						SemanticErrorObject seo = new SemanticErrorObject(
								e.getLineNumber(),
								e.getColumnNumber(),
								errMsg);
						Main.handleSemanticError(seo);	
					}
					
					((TupleType)returnType).addToTypes(tempType);
				}	
			}
		}
		// index = 0: return type is void
		else {
			returnType = new UnitType();
		}
		
		// compare the return type with the function's return type
		if (!returnType.equals(functionReturnType)) {
			String s = "Expected " + functionReturnType.toString() + ", but found " + returnType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(rs.getReturn_line(), rs.getReturn_col(), s);
			Main.handleSemanticError(seo);			
		}
		
		tempType = returnType;
		stmtType = new VoidType();
	}

	@Override
	public void visit(Stmt s) {
		(s.getNakedStmt()).accept(this);
		
		/* check that the statement is not a function call with return value
		 * only allow procedure call statements */
		if (s.getNakedStmt() instanceof FunctionCall) {
			FunctionCall fc = (FunctionCall) s.getNakedStmt();

			if (!tempType.toString().equals("unit")) {
				String errMsg = fc.getIdentifier().toString() + " is not a procedure";
				SemanticErrorObject seo = new SemanticErrorObject(
						fc.getIdentifier_line(), 
						fc.getIdentifier_col(),
						errMsg);
				Main.handleSemanticError(seo);	
			}
		}
	}

	/**
	 * checks if s_(i-1) before s_i is UnitType
	 */
	@Override
	public void visit(StmtList sl) {
		List<Stmt> stmtList = sl.getAllStmt();
		int n = stmtList.size();	
		for (int i = 0; i < n-1; i++) {
			Stmt stmt = stmtList.get(i);
			stmt.accept(this);
			if (stmtType instanceof VoidType) {
				Stmt nextStmt = stmtList.get(i+1);
				String errMsg = "Unreachable code.";
				SemanticErrorObject seo = new SemanticErrorObject(
						nextStmt.getLine(),
						nextStmt.getColumn(),
						errMsg);
				Main.handleSemanticError(seo);	
			}
		}
		Stmt stmt = stmtList.get(n-1);
		stmt.accept(this);
	}
	
	/**
	 * Similar to typechecking VarInit 
	 * @param TupleInit ti
	 */
	@Override
	public void visit(TupleInit ti) {
		int index = ti.getIndex();
		ti.getFunctionCall().accept(this);
		VType returnType = tempType;
		/* Case: _ = f() */
		if (index == 0) {
			if (!(returnType instanceof VarType)) {
				if (returnType instanceof UnitType) {
					String id = ti.getFunctionCall().getIdentifier().toString();
					String s = id + " does not have a return value";
					SemanticErrorObject seo = new SemanticErrorObject(
							ti.getFunctionCall_line(), ti.getFunctionCall_col(), 
							s);
					Main.handleSemanticError(seo);
				} else {
					String s = "Mismatched number of values";
					SemanticErrorObject seo = new SemanticErrorObject(
							ti.getFunctionCall_line(), ti.getFunctionCall_col(), 
							s);
					Main.handleSemanticError(seo);	
				}
			}
		}
		/* Case: _, tdl = f() */
		else if (index == 1) {
			// typecheck
			if (!(returnType instanceof TupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), s);
				Main.handleSemanticError(seo);	
			}
			returnType = (TupleType)returnType;
			TupleType tupleType = new TupleType(ti);
			if (!returnType.equals(tupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), s);
				Main.handleSemanticError(seo);	
			}
			
			// add to env
			List<VarDecl> varDecls = ti.getVarDecls();
			for (VarDecl vd : varDecls) {
				if (vd == null) {
					continue;
				}
				String id = vd.getIdentifier().toString();
				if (env.containsKey(id)) {
					String s = id + " is already declared";
					SemanticErrorObject seo = new SemanticErrorObject(
							vd.getIdentifier().getLineNumber(), 
							vd.getIdentifier().getColumnNumber(), 
							s);
					Main.handleSemanticError(seo);	
				}
				VType type = new VarType(vd);
				env.put(id, type);
				stack.push(id);
			}
		}
		/* Case: vd, tdl = f() */
		else {
			// typecheck
			if (!(returnType instanceof TupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), s);
				Main.handleSemanticError(seo);	
			}
			returnType = (TupleType)returnType;
			TupleType tupleType = new TupleType(ti);
			if (!returnType.equals(tupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getVarDecl().getIdentifier().getLineNumber(), 
						ti.getVarDecl().getIdentifier().getColumnNumber(), s);
				Main.handleSemanticError(seo);	
			}
			
			// add to env
			List<VarDecl> varDecls = ti.getVarDecls();
			for (VarDecl vd : varDecls) {
				if (vd == null) {
					continue;
				}
				String id = vd.getIdentifier().toString();
				if (env.containsKey(id)) {
					String s = id + " is already declared";
					SemanticErrorObject seo = new SemanticErrorObject(
							vd.getIdentifier().getLineNumber(), 
							vd.getIdentifier().getColumnNumber(), 
							s);
					Main.handleSemanticError(seo);	
				}
				VType type = new VarType(vd);
				env.put(id, type);
				stack.push(id);
			}
		}
		
		stmtType = new UnitType();
		tempType = new UnitType();
	}

	@Override
	public void visit(UnaryExpr ue) {
		if ((ue.getExpr() instanceof Literal 
				|| ue.getExpr() instanceof UnaryExpr)
				&& !ue.isSurroundedParentheses()) {
				negativeNumber = (!negativeNumber);
			} else {
				negativeNumber = false;
			}
		ue.getExpr().accept(this);
		
		if (!(tempType instanceof VarType)) {
			String s = "Expected a variable type, but found " + 
					tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					ue.getLineNumber(),
					ue.getColumnNumber(), 
					s
					);
			Main.handleSemanticError(seo);
		}
		
		VarType exprType = (VarType) tempType;
		
		UnaryOp op = ue.getUnaryOp();
		if (op.toString().equals("!")) {
			if (!exprType.isBool()) {
				String s = "Expected a boolean, but found " + 
						exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						ue.getLineNumber(),
						ue.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(true, 0);
			ue.setType(tempType);
		}
		else if (op.toString().equals("-")) {
			if (!exprType.isInt()) {
				String s = "Expected an int, but found " + 
						exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						ue.getLineNumber(),
						ue.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
			tempType = new VarType(false, 0);
			ue.setType(tempType);
		}
		
		if (ue.getExpr() instanceof Literal) {
			negativeNumber = false;
		}
	}

	/**
	 * Update global environment with functions, global variables, and
	 * classes from the interface files
	 */
	@Override
	public void visit(UseId ui) {
		List<String> listOfUses = ui.getUseFiles();
		
		// iterate through each of the uses
		for (int i = 0; i < listOfUses.size(); i++){
			String fileName = listOfUses.get(i);
			
			Map<String, VType> tempMap = Main.checkInterface(fileName);
			Iterator<String> mapIter = tempMap.keySet().iterator();
			
			while (mapIter.hasNext()){
				String moduleMemberName = mapIter.next();
				
				// if multiple function declarations, then check that
				// the types are the same
				if (if_env.containsKey(moduleMemberName)) {
					VType existingType = if_env.get(moduleMemberName);
					VType newType = tempMap.get(moduleMemberName);

					if (!(existingType.equals(newType))) {
						String s = "Conflicting declarations on " 
								+ moduleMemberName + ": "
								+ "trying to add " + newType.toString() + " when "
								+ existingType.toString() + " already exists.";
						SemanticErrorObject seo = new SemanticErrorObject(
								ui.getIdentifier().getLineNumber(), 
								ui.getIdentifier().getColumnNumber(),
								s);
						Main.handleSemanticError(seo);
					}
				}
				// no multiple declarations: add module member to global env
				else{
					globalEnv.put(moduleMemberName, tempMap.get(moduleMemberName));
				}
			}
		}
	}
	
//	@Override
//	public void visit(UseId ui) {
//		List<String> listOfUses = ui.getUseFiles();
//		for (int i = 0; i < listOfUses.size(); i++){
//			String fileName = listOfUses.get(i);
//			Map<String, VType> tempMap = Main.checkInterface(fileName);
//			Iterator<String> funcNameIter = tempMap.keySet().iterator();
//			while (funcNameIter.hasNext()){
//				String funcName = funcNameIter.next();
//				
//				// if multiple function declarations, then check that
//				// the types are the same
//				if (if_env.containsKey(funcName)) {
//					VType existingType = if_env.get(funcName);
//					VType newType = tempMap.get(funcName);
//
//					if (!(existingType.equals(newType))) {
//						String s = "Conflicting declarations on " + funcName + ": "
//								+ "trying to add " + newType.toString() + " when "
//								+ existingType.toString() + " already exists.";
//						SemanticErrorObject seo = new SemanticErrorObject(
//								ui.getIdentifier().getLineNumber(), 
//								ui.getIdentifier().getColumnNumber(),
//								s);
//						Main.handleSemanticError(seo);
//					}
//				}
//				// no multiple declarations: add function to env
//				else{
//					if_env.put(funcName, tempMap.get(funcName));
//				}
//			}
//		}
//	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(VarDecl vd) {
		// Check if identifier is predeclared
		if (env.containsKey(vd.getIdentifier().toString())) {
			String s = vd.getIdentifier().toString() + " is already declared";
			SemanticErrorObject seo = new SemanticErrorObject(
										vd.getIdentifier().getLineNumber(), 
										vd.getIdentifier().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		else {
			String id = vd.getIdentifier().toString();
			env.put(id, new VarType(vd));
			tempType = env.get(id);
			stack.push(id);
		}
		
		// 0: MixedArrayType
		// must check that all the indices have been declared
		if (vd.getIndex() == 0) {
			MixedBrackets mb= vd.getMixedArrayType().getMixedBrackets();
			if (vd.getMixedArrayType().getIndex() == 1) {
				for (Expr idx: mb.getContent()) {
					idx.accept(this);
					
					// check that the expr inside [] is of type int
					if (!(tempType instanceof VarType && 
							((VarType) tempType).isInt())) {
						String s = "Expected int, but found " + tempType.toString();
						SemanticErrorObject seo = new SemanticErrorObject(
													idx.getLineNumber(), 
													idx.getColumnNumber(),
													s
													);
						Main.handleSemanticError(seo);
					}
				}
			}
		}
		// 1: PrimitiveArrayType
		if (vd.getIndex() == 1) {
			vd.getPrimitiveType().accept(this);
			env.put(vd.getIdentifier().toString(), tempType);
		}
		
		stmtType = new UnitType();
		tempType = new UnitType();
	}
	
	@Override
	public void visit(VarInit vi) {
		// Check if identifier is predeclared
		if (env.containsKey(vi.getId().toString())){
			String s = vi.getId().toString() + " is already declared";
			SemanticErrorObject seo = new SemanticErrorObject(
										vi.getId().getLineNumber(), 
										vi.getId().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		tempType = new VarType(vi.getType());	// set tempType to this varType
		
		vi.getType().accept(this);
		VType tempLeftType = tempType;
		vi.getExpr().accept(this);
		VType tempRightType = tempType;

		if (!(tempLeftType.equals(tempRightType))){
			String s = "Expected " + tempLeftType.toString() 
						+ ", but found " + tempRightType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
										vi.getExpr().getLineNumber(), 
										vi.getExpr().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		// add variable to the env
		String id = vi.getId().toString();
		env.put(id, new VarType(vi.getType()));
		tempType = env.get(id);
		stack.push(id);
		
		stmtType = new UnitType();
		tempType = new UnitType();
	}

	/**
	 * Dirties tempType
	 * stmtType is set by the children stmt
	 */
	@Override
	public void visit(WhileStmt ws) {
		ws.getExpr().accept(this);
		VType exprType = tempType;
		Type b = new PrimitiveType(1);
		VType bType = new VarType(b);
		
		// Check type of conditional
		if (!exprType.equals(bType)) {
			String s = "Expected bool, but found " + exprType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
										ws.getExpr().getLineNumber(), 
										ws.getExpr().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		// Start new scope
		if (!(ws.getStmt().getNakedStmt() instanceof BlockStmt)) {
			stack.push("_");
		}

		// Check stmt
		(ws.getStmt()).accept(this);
		
		// Pop out of scope
		if (!(ws.getStmt().getNakedStmt() instanceof BlockStmt)) {
			String id = stack.pop();
			while (!id.equals("_")) {
				env.remove(id);
				id = stack.pop();
			}
		}

	}

	@Override
	public void visit(ClassBody cb) {
		List<Decl> decls = cb.getAllDecls();
		for (Decl decl : decls) {
			decl.accept(this);
		}
	}

	@Override
	public void visit(ClassDecl cd) {
		cd.getClassBody().accept(this);
	}

	@Override
	public void visit(DotableExpr de) {
		DotableExpr.Type type = de.getType();
		switch(type) {
		case DOT: // 
			
			break;
		case FUNCTION_CALL:
			
			break;
		case IDENTIFIER:
			Identifier id = de.getId();
			id.accept(this);
			break;
		case NEW:
			// make sure that the className is a valid class name
			Identifier className = de.getId();
			break;
		case PAREN:
			de.getDotableExpr().accept(this);
			break;
		case THIS:
			// make sure that we are in class scope
			break;
		default:
			break;
		}
	}

	@Override
	public void visit(GlobalDecl gd) {
		// TODO switch case to visit different types of gd 
		
	}

	@Override
	public void visit(ShortTupleDecl std) {
		// TODO 
		// 1) make sure the variables are never declared before
		// 2) make sure type is a valid type (primitive,array,class)
		
	}
	
	/**
	 * Convert a VType to ABI string
	 * Used for converting function params/returns
	 * @param VType t (t cannot be of FunType)
	 * @return ABI string translation of t
	 */
	private String translateVTypeToABIString(VType t) {
		String ABIString = "";
		if (t instanceof VarType) {
			if (((VarType) t).isArray()) {		// array
				int numBrackets = ((VarType) t).getNumBrackets();
				for (int i = 0; i < numBrackets; i++) {
					ABIString += "a";
				}
				ABIString += ((VarType) t).getIsBool() ? "b" : "i";
			} else if (((VarType) t).isInt()) {	// int
				ABIString = "i";
			} else {							// bool
				ABIString = "b";
			}
		} else if (t instanceof TupleType) {
			List<VType> tList = ((TupleType) t).getTypes();
			for (VType tt : tList) {
				assert(!(tt instanceof UnitType));
				ABIString += translateVTypeToABIString(tt);
			}
		}
		
		return ABIString;
	}
	
	/**
	 * @param fnName
	 * @param fnType
	 * @return ABI string translation of the function
	 */
	private String functionToABIName(String fnName, FunType fnType) {
		String ABIName = "_I" + fnName + "_";
		
		String returnTypeString = "";
		VType returnTypes = fnType.getReturnTypes();
		if (returnTypes instanceof UnitType) {
			returnTypeString = "p";
		} else if (returnTypes instanceof VarType) {
			returnTypeString = translateVTypeToABIString(returnTypes);
		} else if (returnTypes instanceof TupleType) {
			int numTypes = ((TupleType) returnTypes).numTypes();
			returnTypeString = "t" + numTypes + translateVTypeToABIString(returnTypes);
		}
		
		VType paramTypes = fnType.getParamTypes();
		String paramTypesString = translateVTypeToABIString(paramTypes);
		
		return ABIName + returnTypeString + paramTypesString;
	}
	
}
