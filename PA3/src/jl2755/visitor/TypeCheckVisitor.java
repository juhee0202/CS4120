package jl2755.visitor;

import java.util.ArrayList;
import java.util.HashMap;
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
	private Stack<String> stack;	// "_": special marker
	private VType tempType;
	
	public TypeCheckVisitor(Program p){
		env = new HashMap<String, VType>();
		stack = new Stack<String>();
		// TODO add length function to the env
	}
	
	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(ArrayElement ae) {
		int index = ae.getIndex();
		if (index == 0){
			if (!(env.containsKey(ae.getIdentifier().toString()))){
				// TODO: ERROR HANDLING
			}
			VType tempScopeType = env.get(ae.getIdentifier().toString());
			if (!(tempScopeType instanceof VarType)){
				// TODO: ERROR HANDLING
			}
			VarType varTypeView = (VarType) tempScopeType;
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (numberOfBrackets > varTypeView.getNumBrackets()){
				// TODO: ERROR HANDLING
			}
			tempType = new VarType(varTypeView.getIsBool(), varTypeView.getNumBrackets() - numberOfBrackets);
		}
		else if (index == 1){
			ae.getFunctionCall().accept(this);
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (!(tempType instanceof VarType)){
				// TODO: ERROR HANDLING
			}
			VarType arrayTypeAfterVisit = (VarType) tempType;
			if (numberOfBrackets > arrayTypeAfterVisit.getNumBrackets()){
				// TODO: ERROR HANDLING
			}
			boolean oldIsBool = arrayTypeAfterVisit.getIsBool();
			int oldNumBrackets = arrayTypeAfterVisit.getNumBrackets();
			tempType = new VarType(oldIsBool, oldNumBrackets - numberOfBrackets);
		}
		else if (index == 2){
			ae.getArrayLiteral().accept(this);
			if (!(tempType instanceof VarType)){
				// TODO: ERROR HANDLING
			}
			VarType arrayTypeAfterVisit = (VarType) tempType;
			int numberOfBrackets = ae.getIndexedBrackets().getNumBrackets();
			if (numberOfBrackets > arrayTypeAfterVisit.getNumBrackets()){
				// TODO: ERROR HANDLING
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
				// TODO: ERROR HANDLING
			}
			tempTypesOfExprs.add((VarType)tempType);
		}
		for (int i = 0; i < tempTypesOfExprs.size() - 1; i++){
			if (!(tempTypesOfExprs.get(i).equals(tempTypesOfExprs.get(i+1)))){
				// TODO: ERROR HANDLING on i+1
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
			// TODO: ERROR HANDLING
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
			}
			
			VType idType = env.get(id);
			
			//check that identifier is a var type
			if (!(idType instanceof VarType)) {
				// TODO error handling
			}
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			// Check types
			if (!idType.equals(exprType)) {
				// TODO: error handling
			}
		
		//ex: arr[2] = 3;
		} else if (index == 1) {
			
			// check if identifier is in env
			String id = as.getIdentifier().toString();
			if (!env.containsKey(id)) {
				// TODO error handling
			}
			
			VarType idType = (VarType)env.get(id);
			VType elementType = new VarType(idType,as.getIndexedBrackets());
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				// TODO: error handling
			}
		
		//ex: f(3)[0] = "herro"
		} else {
			as.getFunctionCall().accept(this);
			VType functionCallType = tempType;
			if (!(functionCallType instanceof VarType)) {
				//TODO error handling
			}
			
			VarType funcCallType = (VarType) functionCallType;
			VType elementType = new VarType (funcCallType, as.getIndexedBrackets());
			
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			if (!elementType.equals(exprType)) {
				// TODO error handling
			}
		}
	}

	@Override
	public void visit(BinaryExpr be) {
		// TODO Auto-generated method stub
		Expr left = be.getLeftExpr();
		Expr right = be.getRightExpr();
		left.accept(this);
		VarType leftType = (VarType) tempType;
		right.accept(this);
		VarType rightType = (VarType) tempType;

		// if +, !=, == (i) both are int (ii) both are arrays with same element type
		BinaryOp op = be.getBinaryOp();
		if (op.toString().equals("+") ||
			op.toString().equals("!=") ||
			op.toString().equals("==")) {
			
			if (leftType.isInt()) {
				if (!rightType.isInt()) {
					//TODO error handling
				}
			}
			else if (leftType.isArray()) {
				if (!rightType.isArray()) {
					//TODO error handling
				}
			}
			else {
				//TODO error handling
			}
		}
	}
		
	@Override
	public void visit(BlockStmt bs) {
		// Start of scope
		stack.add("_");

		// Check stmt list
		(bs.getStmtList()).accept(this);
		
		// Check return stmt
		if (bs.getIndex() == 2) {
			(bs.getReturnStmt()).accept(this);
		}
		
		// Pop out of scope
		String id = stack.pop();
		while (!id.equals("_")) {
			env.remove(id);
			id = stack.pop();
		}
	}
	
	@Override
	public void visit(EmptyArrayType et) {
		// TODO Auto-generated method stub
		
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
			VarType arrayType = (VarType)tempType;
			if (!arrayType.isArray()) {
				String s = "Argument to the length method should be an array type";
				SemanticErrorObject seo = new SemanticErrorObject(
											fc.getExpr_col(), 
											fc.getExpr_line(),
											s
											);
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
										fc.getIdentifier_col(),
										fc.getIdentifier_line(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		FunType funType = (FunType)env.get(id);
		VType paramType = funType.getParamTypes();
		VType args;
		
		/* Case: id() */
		if (index == 0) {
			args = new UnitType();
			if (!args.equals(paramType)) {
				String s = "Expected " + paramType.toString() + ", but found unit";
				SemanticErrorObject seo = new SemanticErrorObject(
											fc.getIdentifier_col(),
											fc.getIdentifier_line(),
											s
											);
				// TODO revisit for the correct col, line number
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
											fc.getFunctionArg_col(),
											fc.getFunctionArg_line(),
											s
											);
				Main.handleSemanticError(seo);
			}
		}
		
		tempType = funType.getReturnTypes();
	}

	/**
	 * 1) check if the function identifier is in env
	 * 2) if not, add the function decl to the env.
	 * 3) typecheck the statement
	 * 		- create a temporary function scope
	 * 		- restore after typechecking function body
	 * @param FunctionDecl fd
	 */
	@Override
	public void visit(FunctionDecl fd) {
		/* Check if the function identifier is not in env */
		if (env.containsKey(fd.getIdentifier().toString())) {
			String s = "Name " + fd.getIdentifier().toString() + " cannot be resolved";
			SemanticErrorObject seo = new SemanticErrorObject(
										fd.getIdentifier_col(),
										fd.getIdentifier_line(),
										s
										);
			Main.handleSemanticError(seo);
		}
		
		/* Put function declaration in env */
		FunType funType = new FunType(fd);
		env.put(fd.getIdentifier().toString(), funType);
		
		/* Update the function scope env */
		Map<String, Type> paramToType = fd.getParamsWithTypes();
		// Stores parent scope's bindings in case the same id is used in FunDecl
		Map<String, VType> tempMap = new HashMap<String, VType>();	
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			String id = entry.getKey();
			VType type = new VarType(entry.getValue());
			if (env.containsKey(id)) {
				tempMap.put(id, env.get(id));
			}
			else {
				env.put(id, type);
			}
		}
		
		/* Typecheck function body */
		fd.getBlockStmt().accept(this);
		
		// TODO check if the return value is correct
		
		/* Restore the parent scope env */
		for (Entry<String, Type> entry : paramToType.entrySet()) {
			String id = entry.getKey();
			env.remove(id);
		}
		for (Entry<String, VType> entry : tempMap.entrySet()) {
			String id = entry.getKey();
			VType type = entry.getValue();
			env.put(id, type);
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
	public void visit(IfStmt is) {
		is.getExpr().accept(this);
		VType exprType = tempType;
		Type b = new PrimitiveType(1);
		VType bType = new VarType(b);
		
		// Check type of conditional
		if (!exprType.equals(bType)) {
			// TODO: error handling
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

	@Override
	public void visit(MixedArrayType mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PrimitiveType pt) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(Program p) {
		// Check use
		if (p.getIndex() == 1) {
			// something
		}
		
		// Check functions
		(p.getFunctionDeclList()).accept(this);
	}

	@Override
	public void visit(ReturnList rl) {
		(rl.getExpr()).accept(this);
		if (rl.getIndex() == 1) {
			(rl.getReturnList()).accept(this);
		}
		
	}

	@Override
	public void visit(ReturnStmt rs) {
		if (rs.getIndex() == 1) {
			(rs.getReturnList()).accept(this);
		}
	}

	@Override
	public void visit(ReturnType rt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Stmt s) {
		(s.getNakedStmt()).accept(this);
	}

	@Override
	public void visit(StmtList sl) {
		// Start new scope
		stack.push("_");
		
		// Check stmt
		(sl.getStmt()).accept(this);
		
		// Pop out of scope
		String id = stack.pop();
		while (id != "_") {
			env.remove(id);
			id = stack.pop();
		}
		
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
												ti.getFunctionCall_col(),
												ti.getFunctionCall_line(),
												s
												);
					Main.handleSemanticError(seo);
				} else {
					String s = "Mismatched number of values";
					SemanticErrorObject seo = new SemanticErrorObject(
												ti.getFunctionCall_col(),
												ti.getFunctionCall_line(),
												s
												);
					Main.handleSemanticError(seo);	
				}
			}
		}
		/* Case: _, tdl = f() */
		// TODO need refactoring
		else if (index == 1) {
			if (!(returnType instanceof TupleType)) {
				// TODO col,line numbering might be off
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
											ti.getFunctionCall_col(),
											ti.getFunctionCall_line(),
											s
											);
				Main.handleSemanticError(seo);	
			}
			returnType = (TupleType)returnType;
			TupleType tupleType = new TupleType(ti);
			if (!returnType.equals(tupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
											ti.getFunctionCall_col(),
											ti.getFunctionCall_line(),
											s
											);
				Main.handleSemanticError(seo);	
			}
		}
		/* Case: vd, tdl = f() */
		// TODO need refactoring
		else {
			if (!(returnType instanceof TupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
											ti.getFunctionCall_col(),
											ti.getFunctionCall_line(),
											s
											);
				Main.handleSemanticError(seo);	
			}
			returnType = (TupleType)returnType;
			TupleType tupleType = new TupleType(ti);
			tupleType.prependToTypes(new VarType(ti.getVarDecl()));
			if (!returnType.equals(tupleType)) {
				String s = "Mismatched number of values";
				SemanticErrorObject seo = new SemanticErrorObject(
											ti.getFunctionCall_col(),
											ti.getFunctionCall_line(),
											s
											);
				Main.handleSemanticError(seo);	
			}
		}
	}

	@Override
	public void visit(TypeList tl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(UnaryExpr ue) {
		// TODO Auto-generated method stub
		ue.getExpr().accept(this);
		
		if (!(tempType instanceof VarType)) {
			//TODO error handling
		}
		
		VarType exprType = (VarType) tempType;
		
		UnaryOp op = ue.getUnaryOp();
		if (op.toString().equals("!")) {
			if (!exprType.isBool()) {
				//TODO error handling
			}
		}
		else if (op.toString().equals("-")) {
			if (!exprType.isInt()) {
				//TODO error handling
			}
		}
	}

	@Override
	public void visit(UseId ui) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(VarDecl vd) {
		// Check if predeclared
		if (env.containsKey(vd.getIdentifier())){
			// TODO: ERROR HaNdLiNG
		}
		else{
			String id = vd.getIdentifier().toString();
			env.put(id, new VarType(vd));
			tempType = env.get(id);
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
