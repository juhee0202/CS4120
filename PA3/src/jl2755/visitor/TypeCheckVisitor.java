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

public class TypeCheckVisitor implements Visitor {

	/** HashMap of all declared variables */
	private HashMap<String, VType> env;
	private HashMap<String, VType> if_env;
	private Stack<String> stack;	// "_": special marker
	private VType tempType;
	
	public TypeCheckVisitor(Program p){
		env = new HashMap<String, VType>();
		// add print(s) to the environment 
		FunType printType = new FunType();
		printType.setParamTypes(new VarType(false, 1));
		printType.setReturnTypes(new UnitType());
		env.put("print", printType);
		if_env = new HashMap<String, VType>();
		stack = new Stack<String>();
	}
	
	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(ArrayElement ae) {
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
		tempType = new VarType(tempTypesOfExprs.get(0).getIsBool(), tempTypesOfExprs.get(0).getNumBrackets());
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
				// TODO error handling
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
				// TODO error handling
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
				// TODO: error handling
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
				// TODO error handling
				String s = "Name " + id + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getIdentifier().getLineNumber(), 
											as.getIdentifier().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			VarType idType = (VarType)env.get(id);
			
			VType elementType = new VarType(idType,as.getIndexedBrackets());

			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				// TODO: error handling
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
		} else {
			as.getFunctionCall().accept(this);
			VType functionCallType = tempType;
			if (!(functionCallType instanceof VarType)) {
				//TODO error handling
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
			VType elementType = new VarType (funcCallType, as.getIndexedBrackets());
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				// TODO error handling
				String s = "Expected " + elementType.toString() 
				+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
											as.getExpr().getLineNumber(), 
											as.getExpr().getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
		}
	}

	@Override
	public void visit(BinaryExpr be) {
		Expr left = be.getLeftExpr();
		Expr right = be.getRightExpr();
		left.accept(this);
		
		if (!(tempType instanceof VarType)) {
			// TODO: error
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
		right.accept(this);
		
		if (!(tempType instanceof VarType)) {
			// TODO: error
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

		// check that left & right expr have the same type
		if (!leftType.equals(rightType)) {
			String s = "Mismatched types for binary operation.";
			SemanticErrorObject seo = new SemanticErrorObject(
					be.getLineNumber(),
					be.getColumnNumber(), 
					s
					);
			Main.handleSemanticError(seo);
		}
		
		// if "+": int or array type
		BinaryOp op = be.getBinaryOp();
		if (op.toString().equals("+")) {
			if (leftType.isInt()) {
				if (!rightType.isInt()) {
					//TODO error handling
					String s = "Mismatched types for + operation.";
					SemanticErrorObject seo = new SemanticErrorObject(
							be.getLineNumber(),
							be.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(false, 0);
			}
			else if (leftType.isArray()) {
				if (!leftType.equals(rightType)) {
					// TODO: error handling
					String s = "Mismatched types for + operation.";
					SemanticErrorObject seo = new SemanticErrorObject(
							be.getLineNumber(),
							be.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(leftType.getIsBool(), 
						leftType.getNumBrackets());
			}
			else {
				//TODO error handling
				String s = "Invalid types for + operation.";
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
		}
		// if !=, == (i) both are int/bool (ii) both are arrays with same element type
		else if (op.toString().equals("!=") || op.toString().equals("==")) {
			if (leftType.isBool()) {
				if (!rightType.isBool()) {
					// TODO: error handling
					String s = "Mismatched types for " + op.toString() 
								+ " operation.";
					SemanticErrorObject seo = new SemanticErrorObject(
							be.getLineNumber(),
							be.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(true, 0);
			}
			if (leftType.isInt()) {
				if (!rightType.isInt()) {
					//TODO error handling
					String s = "Mismatched types for " + op.toString() 
								+ " operation.";
					SemanticErrorObject seo = new SemanticErrorObject(
							be.getLineNumber(),
							be.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(true, 0);
			}
			else if (leftType.isArray()) {
				if (!rightType.isArray()) {
					//TODO error handling
					String s = "Mismatched types for " + op.toString() 
								+ " operation.";
					SemanticErrorObject seo = new SemanticErrorObject(
							be.getLineNumber(),
							be.getColumnNumber(), 
							s
							);
					Main.handleSemanticError(seo);
				}
				tempType = new VarType(true,0);
			}
			else {
				//TODO error handling
				String s = "Invalid types for " + op.toString() + " operation.";
				SemanticErrorObject seo = new SemanticErrorObject(
						be.getLineNumber(),
						be.getColumnNumber(), 
						s
						);
				Main.handleSemanticError(seo);
			}
		}
		
		else if (op.toString().equals("<") || 
			op.toString().equals("<=") ||
			op.toString().equals(">") ||
			op.toString().equals(">=") ||
			op.toString().equals("&") ||
			op.toString().equals("|")) {
			
			tempType = new VarType(true,0);
		}
		else {
			tempType = new VarType(false,0);
		}

	}
		
	@Override
	public void visit(BlockStmt bs) {
		// Start of scope
		stack.add("_");
		
		if (bs.getIndex() != 0 && bs.getIndex() != 3) { 
			// Check stmt list
			(bs.getStmtList()).accept(this);
		}
		
		// Check return stmt
		if (bs.getIndex() >= 2) {
			(bs.getReturnStmt()).accept(this);
		} else {
		// Set tempType to unit
			tempType = new UnitType();
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
		}
		else {
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
			tempType = arrayType.getPrimitiveType();
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
	}

	/**
	 * 1) update env
	 * 2) typecheck the function body
	 * 3) restore the original env
	 * @param FunctionDecl fd
	 */
	@Override
	public void visit(FunctionDecl fd) {
		/* Update the function scope env */
		Map<String, Type> paramToType = fd.getParamsWithTypes();
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			String id = entry.getKey();
			VType type = new VarType(entry.getValue());
			if (env.containsKey(id)) {
				// TODO error handling
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
		fd.getBlockStmt().accept(this);
		VType bodyReturnType = tempType;
		
		String id = fd.getIdentifier().toString();
		FunType funType = (FunType) env.get(id);	// safe
		VType returnTypes = funType.getReturnTypes();
				
		if (!returnTypes.equals(bodyReturnType)) {
			String s = "Expected " + returnTypes.toString() 
						+ ", but found " + bodyReturnType.toString();
			ReturnStmt rs = fd.getBlockStmt().getReturnStmt();
			SemanticErrorObject seo;
			if (fd.getBlockStmt().getIndex() < 2) {
				seo = new SemanticErrorObject(
						fd.getIdentifier_line(), fd.getIdentifier_col(), s);
			} else {
				seo = new SemanticErrorObject(
						rs.getReturn_line(), rs.getReturn_col(), s);
			}
			Main.handleSemanticError(seo);
		}
		
		/* Restore the parent scope */
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			env.remove(entry.getKey());
		}
	}

	/**
	 * recursively typecheck function declarations
	 * @param FunctionDeclList fdl
	 */
	@Override
	public void visit(FunctionDeclList fdl) {
		List<FunctionDecl> functionDecls = fdl.getFunctionDecls();
		for (FunctionDecl fd : functionDecls) {
			fd.accept(this);
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
	}
	
	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(IfStmt is) {
		is.getExpr().accept(this);
		VType exprType = tempType;
		VType bType = new VarType(true,0);
		
		// Check type of conditional
		if (!exprType.equals(bType)) {
			// TODO: error handling
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
		
	}

	/** 
	 * Dirties tempType
	 */
	@Override
	public void visit(Literal l) {
		int index = l.getIndex();
		switch (index) {
			case 0: tempType = new VarType(false, 0); break;		// int
			case 1: tempType = new VarType(false, 1); break;		// string
			case 2: tempType = new VarType(false, 0); break;		// char
			case 3: tempType = new VarType(true, 0);  break;		// boolean
			default: // TODO error handling
		}
	}
	
	/**
	 * 1) Update if_env with function decls from interface files
	 * 2) Update env with function decls from the file.
	 * 
	 * @param Program p
	 */
	@Override
	public void visit(Program p) {
		/* Update if_env if interface files are used */
		if (p.getIndex() == 1) {
			p.getUseId().accept(this);
		}
		
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
					String s = "Multiple declaration found for function " + id;
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
		(p.getFunctionDeclList()).accept(this);
	}

	/**
	 * DIRTIES tempType to the return type
	 */
	@Override
	public void visit(ReturnStmt rs) {
		VType returnType;
		if (rs.getIndex() == 1) {
			List<Expr> returnExprs = rs.getReturnList().getListOfExpr();
			if (returnExprs.size() == 1) {
				returnExprs.get(0).accept(this);
				returnType = tempType;
			}
			else {
				returnType = new TupleType();
				for (int i = 0; i < returnExprs.size(); i++){
					returnExprs.get(i).accept(this);
					((TupleType)returnType).addToTypes(tempType);
				}	
			}
		}
		else {
			returnType = new UnitType();
		}
		
		tempType = returnType;
	}

	@Override
	public void visit(Stmt s) {
		(s.getNakedStmt()).accept(this);
	}

	@Override
	public void visit(StmtList sl) {
		// Check stmt
		(sl.getStmt()).accept(this);
		
		
		// Check stmt list
		if (sl.getIndex() == 1) {
			(sl.getStmtList()).accept(this);
		}
		
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
		// TODO need refactoring
		if (index == 1) {
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
				VType type = new VarType(vd);
				env.put(id, type);
				stack.push(id);
			}
		}
	}

	@Override
	public void visit(UnaryExpr ue) {
		// TODO Auto-generated method stub
		ue.getExpr().accept(this);
		
		if (!(tempType instanceof VarType)) {
			//TODO error handling
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
				//TODO error handling
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
		}
		else if (op.toString().equals("-")) {
			if (!exprType.isInt()) {
				//TODO error handling
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
		}
	}

	/**
	 * Update if_env with function declarations from the interface files
	 */
	@Override
	public void visit(UseId ui) {
		List<String> listOfUses = ui.getUseFiles();
		for (int i = 0; i < listOfUses.size(); i++){
			String fileName = listOfUses.get(i);
			Map<String, VType> tempMap = Main.checkInterface(fileName);
			Iterator<String> itFuncNames = tempMap.keySet().iterator();
			while (itFuncNames.hasNext()){
				String tempFuncNames = itFuncNames.next();
				if (if_env.containsKey(tempFuncNames)) {
					VType existingType = if_env.get(tempFuncNames);
					if (!(existingType.equals(tempMap.get(tempFuncNames)))){
						// TODO: ERROR HANDLINGGDIGNDIGNDINGDNIGDIGNDIGN
						String s = tempFuncNames + "is already declared";
						SemanticErrorObject seo = new SemanticErrorObject(
								ui.getIdentifier().getLineNumber(), 
								ui.getIdentifier().getColumnNumber(),
								s);
						Main.handleSemanticError(seo);
					}
				}
				else{
					if_env.put(tempFuncNames, tempMap.get(tempFuncNames));
				}
			}
		}
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(VarDecl vd) {
		// Check if predeclared
		if (env.containsKey(vd.getIdentifier().toString())){
			// TODO: ERROR HaNdLiNG
			String s = vd.getIdentifier().toString() + " is already declared";
			SemanticErrorObject seo = new SemanticErrorObject(
										vd.getIdentifier().getLineNumber(), 
										vd.getIdentifier().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		else{
			String id = vd.getIdentifier().toString();
			env.put(id, new VarType(vd));
			tempType = env.get(id);
			stack.push(id);
		}
	}

	@Override
	public void visit(VarInit vi) {
		// Check if predeclared
		vi.getVarDecl().accept(this);
		VType tempLeftType = tempType;
		vi.getExpr().accept(this);
		VType tempRightType = tempType;
		if (!(tempLeftType.equals(tempRightType))){
			// TODO: ERROR HANDLING
			String s = "Expected " + tempLeftType.toString() 
						+ ", but found " + tempRightType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
										vi.getExpr().getLineNumber(), 
										vi.getExpr().getColumnNumber(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(WhileStmt ws) {
		ws.getExpr().accept(this);
		VType exprType = tempType;
		Type b = new PrimitiveType(1);
		VType bType = new VarType(b);
		
		// Check type of conditional
		if (!exprType.equals(bType)) {
			// TODO: error handling
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
}
