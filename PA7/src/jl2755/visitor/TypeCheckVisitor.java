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
import jl2755.type.ClassType;
import jl2755.type.FunType;
import jl2755.type.NullType;
import jl2755.type.TupleType;
import jl2755.type.UnitType;
import jl2755.type.VType;
import jl2755.type.VarType;
import jl2755.type.VoidType;

public class TypeCheckVisitor implements ASTVisitor {

	/** Environment of the program */
	private Environment env;
	
	/** HashMap of all declared variables */
	private Stack<String> stack;		// "_": special marker
	private VType tempType;
	private VType stmtType;				// either UnitType or VoidType
	private ClassType classEnv; 		// current class's type that we're typechecking 
										// -> dirtied by visit(ClassDecl)
	private VType functionReturnType;
	private boolean negativeNumber = false; // needed for UnaryExpr, Literal
	private boolean isInClass = false;	// Needed for this keyword
	private boolean isInFunctionDecl = false; // Needed for this keyword
	
	/**
	 * Creates a TypeCheckVisitor instance
	 * @param initial_env is the initial environment that the program can use
	 */
	public TypeCheckVisitor(Environment initial_env){
		env = initial_env;
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
			if (!(env.containsVar(ae.getIdentifier().toString()))){
				String errorDesc = "Name " + ae.getIdentifier().toString() +
						" cannot be resolved.";
				SemanticErrorObject seo = new SemanticErrorObject(
						ae.getIdentifier_line(),
						ae.getIdentifier_col(), 
						errorDesc
						);
				Main.handleSemanticError(seo);
			}
			VType tempScopeType = env.getVarType(ae.getIdentifier().toString());
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
		
		if (index == 0) { 									// ex: a = 3
			// Identifier visit checks if its in env
			String id = as.getIdentifier().toString();
			if (!(env.containsVar(id))) {
				String s = "Name " + id + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						as.getIdentifier().getLineNumber(), 
						as.getIdentifier().getColumnNumber(),
						s);
				Main.handleSemanticError(seo);
			}
			
			// get the VType of left hand side
			VType idType = env.getVarType(id);
			
			// get the VType of right hand side
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			// Check types
			if (!idType.equals(exprType)) {
				String s = "Expected " + idType.toString() 
							+ ", but found " + exprType.toString();
				SemanticErrorObject seo = new SemanticErrorObject(
						as.getExpr().getLineNumber(), 
						as.getExpr().getColumnNumber(),
						s);
				Main.handleSemanticError(seo);
			}
		} else if (index == 1) {							//ex: arr[2] = 3;
			// check if identifier is in env
			String id = as.getIdentifier().toString();
			if (!env.containsVar(id)) {
				String s = "Name " + id + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						as.getIdentifier().getLineNumber(), 
						as.getIdentifier().getColumnNumber(),
						s);
				Main.handleSemanticError(seo);
			}
			
			VarType idType = (VarType)env.getVarType(id);
			
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
						s);
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
						s);
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
							s);
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
					be.getLineNumber(), be.getColumnNumber(), s);
			Main.handleSemanticError(seo);
		}
		VarType leftType = (VarType) tempType;
		// check that rightExpr is of VarType
		right.accept(this);
		if (!(tempType instanceof VarType)) {
			String s = "Expected a variable type, but found " + 
					tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					be.getLineNumber(), be.getColumnNumber(), s);
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
			env.removeVar(id);
			id = stack.pop();
		}
	}

	/**
	 * DIRTIES tempType to function argument type (VarType, ClassType, or TupleType)
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
				argType.addToTypes(tempType);
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
		if (!env.containsFun(id)) {
			String s = "Name " + id.toString() + " cannot be resolved";
			SemanticErrorObject seo = new SemanticErrorObject(
					fc.getIdentifier_line(), fc.getIdentifier_col(), s);
			Main.handleSemanticError(seo);
		}
		
		FunType funType = env.getFunType(id);
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
		// get ABIName
		String funId = fd.getIdentifier().toString();
		FunType funType = env.getFunType(funId);
		String ABIName = functionToABIName(funId, funType);
		fd.setABIName(ABIName);
				
		/* Update the function scope by putting arguments to env */
		Map<String, Type> paramToType = fd.getParamsWithTypes();
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			// get id
			String id = entry.getKey();
			
			// get type
			Type type = entry.getValue();
			VType varType;
			if (type instanceof Identifier) {
				Identifier classId = (Identifier)type;
				String className = classId.getTheValue();
				if (!env.containsClass(className)) {
					String s = "Type " + className + " cannot be resolved";
					SemanticErrorObject seo = new SemanticErrorObject(
							classId.getLineNumber(), classId.getColumnNumber(), s);
					Main.handleSemanticError(seo);
				}
				varType = env.getClassType(className);
			} else {
				varType = new VarType(entry.getValue());	
			}
			
			// typecheck
			if (env.containsVar(id)) {
				String s = id + " is already declared";
				SemanticErrorObject seo = new SemanticErrorObject(
						fd.getIdentifier_line(), fd.getIdentifier_col(), s);
				Main.handleSemanticError(seo);
			} else {
				env.put(id, varType);
				stack.push(id);
			}
		}
		
		/* Typecheck function body */
		String id = fd.getIdentifier().toString();
		funType = env.getFunType(id);
		functionReturnType = funType.getReturnTypes();
		isInFunctionDecl = true;
		fd.getBlockStmt().accept(this);
		isInFunctionDecl = false;
		
		// check the return type using tempType
		if (!tempType.equals(functionReturnType)) {
			String s = "Expected " + functionReturnType.toString() + ", but found " + tempType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					fd.getIdentifier_line(), fd.getIdentifier_col(), s);
			Main.handleSemanticError(seo);			
		}
		
		/* Restore the parent scope by removing arguments from env */
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			env.removeVar(entry.getKey());
		}
	}
	
	/**
	 * Dirties tempType
	 * id can be either a class type or a variable
	 */
	@Override
	public void visit(Identifier id) {
		if (id.isClassName()) { // class type
			if (!env.containsClass(id.getTheValue())) {
				String s = "Name " + id.toString() + " cannot be resolved.";
				SemanticErrorObject seo = new SemanticErrorObject(
						id.getLineNumber(), id.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			tempType = env.getClassType(id.getTheValue());
		} else {	// variable
			if (!(env.containsVar(id.toString()))){
				String s = "Name " + id.toString() + " cannot be resolved.";
				SemanticErrorObject seo = new SemanticErrorObject(
						id.getLineNumber(), id.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			tempType = env.getVarType(id.toString());
			id.setType(tempType);
		}
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
				env.removeVar(id);	// precondition: stack only contains Variables
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
					env.removeVar(id);
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
	
	/**
	 * Visit all Decls to recursively typecheck
	 * @param Program p
	 */
	@Override
	public void visit(Program p) {
		/* 
		 * List of all declarations in the program
		 * where declarations include
		 * 	- GlobalDecl
		 *  - FunctionDecl
		 *  - ClassDecl
		 */
		List<Decl> decls = p.getAllDecls();
		for (Decl decl : decls) {
			decl.accept(this);
		}
	}

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
	 * TupleInit has the following form:
	 *  id:t,(id:t | _)* = functionCall
	 *  ex) x:int,y:int = f()
	 *  ex) x:Person,_,z:bool = g()
	 * @param TupleInit ti
	 */
	@Override
	public void visit(TupleInit ti) {
		// type of the right hand side 
		ti.getFunctionCall().accept(this);
		VType returnType = tempType;
		
		/* Special case of the form: _ = f() */
		if (ti.getIndex() == 0) {
			// returnType must be either a VarType or a ClassType
			if (returnType instanceof UnitType) {
				String id = ti.getFunctionCall().getIdentifier().toString();
				String s = id + " does not have a return value";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), 
						s);
				Main.handleSemanticError(seo);
			} else if (returnType instanceof TupleType) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), 
						s);
				Main.handleSemanticError(seo);	
			}
		} 
		
		/* General case */
		else {
			// build a TupleType object for the left hand side
			List<VarDecl> varDecls = ti.getVarDecls();
			List<VType> types = new ArrayList<VType>();
			for (int i = 0; i < varDecls.size(); i++) {
				VarDecl varDecl = varDecls.get(i);
				
				// build types
				if (varDecl == null) {	
					types.add(new UnitType());
				} else if (varDecl.getIndex() == 2) {
					Identifier classId = varDecl.getClassType();
					String className = classId.getTheValue();
					if (!env.containsClass(className)) {
						String s = "Type " + className + " cannot be resolved";
						SemanticErrorObject seo = new SemanticErrorObject(
								classId.getLineNumber(), classId.getColumnNumber(), s);
						Main.handleSemanticError(seo);
					}
					types.add(env.getClassType(className));
				} else {
					types.add(new VarType(varDecl));
				}
				
				// make sure that id is not in the env
				Identifier id = varDecl.getIdentifier();
				if (env.containsVar(id.getTheValue())) {
					String s = id + " is already declared";
					SemanticErrorObject seo = new SemanticErrorObject(
							id.getLineNumber(), id.getColumnNumber(), s);
					Main.handleSemanticError(seo);	
				}
			}
			
			// type of the left hand side
			TupleType tupleType = new TupleType(types);
			
			// check if types of both sides agree
			if (!returnType.equals(tupleType)) {
				String s = "Mismatched type";
				SemanticErrorObject seo = new SemanticErrorObject(
						ti.getFunctionCall_line(), ti.getFunctionCall_col(), s);
				Main.handleSemanticError(seo);	
			}
			
			// update env & stack
			for (int i = 0; i < varDecls.size(); i++) {
				VarDecl vd = varDecls.get(i);
				if (vd == null) {
					continue;
				}
				String id = vd.getIdentifier().getTheValue();
				VType type = types.get(i);
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
	 * Deprecated
	 */
	@Override
	public void visit(UseId ui) {
		return;
	}

	/**
	 * VarDecl has the following form:
	 * 	id:t
	 *  ex) x:int
	 *  ex) b:bool
	 *  
	 * Dirties tempType
	 */
	@Override
	public void visit(VarDecl vd) {
		/* 
		 * Typecheck the identifier by 
		 * making sure that the id is never declared before 
		 */
		Identifier id = vd.getIdentifier();
		if (env.containsVar(id.getTheValue())) {
			// Check if the identifier is declared before
			String s = vd.getIdentifier().toString() + " is already declared";
			SemanticErrorObject seo = new SemanticErrorObject(
					vd.getIdentifier().getLineNumber(), 
					vd.getIdentifier().getColumnNumber(),
					s);
			Main.handleSemanticError(seo);
		}
		
		/* Typecheck the type */
		int index = vd.getIndex();
		VType varType;
		if (index == 0) { // 0: MixedArrayType
			// check that all index expressions have been declared
			MixedBrackets mb= vd.getMixedArrayType().getMixedBrackets();
			if (vd.getMixedArrayType().getIndex() == 1) {
				for (Expr idx: mb.getContent()) {
					idx.accept(this);
					
					// check that the expr inside [] is of type int
					if (!(tempType instanceof VarType && 
							((VarType) tempType).isInt())) {
						String s = "Expected int, but found " + tempType.toString();
						SemanticErrorObject seo = new SemanticErrorObject(
								idx.getLineNumber(), idx.getColumnNumber(), s);
						Main.handleSemanticError(seo);
					}
				}
			}
			varType = new VarType(vd);
		} else if (index == 1) { // 1: PrimitiveArrayType
			vd.getPrimitiveType().accept(this);
			env.put(vd.getIdentifier().toString(), tempType);
			varType = tempType;
		} else { // 2: ClassType
			Identifier classId = vd.getClassType();
			String className = classId.getTheValue();
			if (!env.containsClass(className)) {
				String s = "Type " + className + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						classId.getLineNumber(), classId.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			varType = env.getClassType(className);
		}
		
		/* Update the env & stack */
		env.put(id.getTheValue(), varType);
		stack.push(id.getTheValue());
		
		/* Update global variables */
		stmtType = new UnitType();
		tempType = new UnitType();
	}
	
	/**
	 * VarInit has the following form:
	 *  id:t = e
	 *  ex) x:int = 5
	 *  ex) a:Animal = initLion("Simba")
	 */
	@Override
	public void visit(VarInit vi) {
		/* 
		 * Typecheck the identifier by 
		 * ensuring that the id is never declared before
		 */
		Identifier id = vi.getId();
		if (env.containsVar(id.getTheValue())){
			String s = vi.getId().toString() + " is already declared";
			SemanticErrorObject seo = new SemanticErrorObject(
					vi.getId().getLineNumber(),vi.getId().getColumnNumber(),s);
			Main.handleSemanticError(seo);
		}
		
		/* Typecheck the type */
		Type t = vi.getType();
		VType varType;
		if (t instanceof Identifier) {
			Identifier classId = (Identifier)t;
			String className = classId.getTheValue();
			
			// make sure the class type is in the env
			if (!env.containsClass(className)) {
				String s = "Name " + className + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						classId.getLineNumber(), classId.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			
			varType = env.getClassType(className);
		} else {
			varType = new VarType(t);
		}
		
		/* Typecheck the whole statement */
		assert(varType instanceof VarType || varType instanceof ClassType);
		VType tempLeftType = varType;
		vi.getExpr().accept(this);
		VType tempRightType = tempType;
		
		// make sure the left and right types are equal
		if (!(tempLeftType.equals(tempRightType))){
			String s = "Expected " + tempLeftType.toString() 
						+ ", but found " + tempRightType.toString();
			SemanticErrorObject seo = new SemanticErrorObject(
					vi.getExpr().getLineNumber(), 
					vi.getExpr().getColumnNumber(),
					s);
			Main.handleSemanticError(seo);
		}
		
		/* Update env & stack */
		String idString = vi.getId().toString();
		env.put(idString, varType);
		stack.push(idString);
		
		/* Update global variables */
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
		// decl can be either 1) GlobalDecl 2) FunctionDecl
		isInClass = true;
		for (Decl decl : decls) {
			decl.accept(this);
		}
		isInClass = false;
	}

	@Override
	public void visit(ClassDecl cd) {
		classEnv = env.getClassType(cd.getClassName().toString());
		cd.getClassBody().accept(this);
	}

	@Override
	public void visit(DotableExpr de) {
		DotableExpr.Type type = de.getType();
		switch(type) {
		case DOT:
			de.getDotableExpr().accept(this);
			VType childType = tempType;
			if (!childType.singleReturn()) {
				String s = "Cannot get a field on a function return that doesn't have exactly 1 return";
				SemanticErrorObject seo = new SemanticErrorObject(
											de.getLineNumber(), 
											de.getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			
			if (!(childType instanceof ClassType)) {
				String s = "Cannot call a method on a non-Class variable";
				SemanticErrorObject seo = new SemanticErrorObject(
											de.getLineNumber(), 
											de.getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			ClassType classView = (ClassType) childType;
			tempType = classView.getFieldType(de.getId().toString());
			break;
		case FUNCTION_CALL:
			de.getFunctionCall().accept(this);
			break;
		case IDENTIFIER:
			Identifier id = de.getId();
			id.accept(this);
			break;
		case NEW:
			// make sure that the className is a valid class name
			Identifier className = de.getId();
			if (!env.containsClass(className.toString())) {
				String s = "" + className.toString() + " is an unresolved Class";
				SemanticErrorObject seo = new SemanticErrorObject(
											de.getLineNumber(), 
											de.getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			tempType = env.getClassType(de.getId().toString());
			break;
		case PAREN:
			de.getDotableExpr().accept(this);
			break;
		case THIS:
			if (!(isInClass && isInFunctionDecl)) {
				String s = "Can only use the keyword \"this\" in a class method";
				SemanticErrorObject seo = new SemanticErrorObject(
											de.getLineNumber(), 
											de.getColumnNumber(),
											s
											);
				Main.handleSemanticError(seo);
			}
			tempType = classEnv;
			break;
		default:
			break;
		}
	}

	/**
	 * Call the accept/visit on the underlying decl/init
	 */
	@Override
	public void visit(GlobalDecl gd) {
		GlobalDecl.Type gdType = gd.getType();
		switch(gdType) {
		case SHORT_TUPLE_DECL:
			gd.getShortTupleDecl().accept(this);
			break;
		case TUPLE_INIT:
			gd.getTupleInit().accept(this);
			break;
		case VAR_DECL:
			gd.getVarDecl().accept(this);
			break;
		case VAR_INIT:
			gd.getVarInit().accept(this);
			break;
		}
	}

	@Override
	public void visit(Null n) {
		tempType = new NullType();
	}

	/**
	 * ShortTupleDecl has the following form:
	 * 	id,id,id*:type (at least two ids)
	 *  ex) x,y:int
	 *  ex) a,b,c:bool
	 */
	@Override
	public void visit(ShortTupleDecl std) {
		/* Typecheck the type */
		Type t = std.getType();
		VType type;
		if (t instanceof Identifier) {
			Identifier classId = (Identifier)t;
			String className = classId.getTheValue();
			if (!env.containsClass(className)) {
				String s = "Type " + classId.toString() + " cannot be resolved";
				SemanticErrorObject seo = new SemanticErrorObject(
						classId.getLineNumber(), classId.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			type = env.getClassType(className);
		} else {
			type = new VarType(t);
		}
		
		/* Typecheck the identifiers */
		List<Identifier> ids = std.getAllIdentifiers();
		for (Identifier id : ids) {
			// make sure the variable is never declared before
			if (env.containsVar(id.getTheValue())) {
				String s = id.toString() + " is already declared";
				SemanticErrorObject seo = new SemanticErrorObject(
						id.getLineNumber(), id.getColumnNumber(), s);
				Main.handleSemanticError(seo);
			}
			
			// put it in the env
			env.put(id.getTheValue(), type);
			stack.push(id.getTheValue());
		}
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

	/**
	 * 
	 * @param currentClass: name of the class to get super classes of
	 * @return list of superclasses in order of most immediate superclass to
	 * highest superclass
	 */
	private List<String> getSuperClasses(String currClassName, List<String> superclasses) {
		ClassType ct = env.getClassType(currClassName);
		String superName = ct.getSuperClassName();
		if (superName != null) {
			superclasses.add(superName);
			getSuperClasses(superName, superclasses);
		}
		return superclasses;
	}
	
}
