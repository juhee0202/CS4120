package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import jl2755.ast.*;
import jl2755.type.VType;
import jl2755.type.VarType;

public class TypeCheckVisitor implements Visitor {

	/** HashMap of all declared variables */
	private HashMap<String, VType> env;
	private Stack<String> stack;
	private VType tempType;	        // cleared at the beginning of each
										// visit methods that uses it
	
	public TypeCheckVisitor(Program p){
		env = new HashMap<String, VType>();
		stack = new Stack<String>();
		
	}
	
	@Override
	public void visit(ArrayElement ae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayElementList ael) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayLiteral al) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(AssignmentStmt as) {
		int index = as.getIndex();
		if (index == 0) {

			// Identifier visit checks if its in env
			
			as.getIdentifier().accept(this);
			VType idType = tempType;	// can also do env.get(id);
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			// Check types
			if (!idType.equals(exprType)) {
				// TODO: error handling
			}
			
		} else if (index == 1) {
			// a[0] = 3;
			
			as.getIdentifier().accept(this);
			VType idType = tempType;
			as.getExpr().accept(this);
			VType exprType = tempType;
			
			// Check types (HOW TO DO THIS: ARRAY VS. ARRAY ELEMENT)
			if (!idType.equals(exprType)) {
				// TODO: error handling
				// Unpackaged ArrayType into AssignmentStmt. That's
				// there's an error.
			}
			
		} else {
			// function call
			// DOESN'T THIS KIND OF ASSIGNMENT STMT HAVE NO EFFECT?? ALLOWED??
		}
	}

	@Override
	public void visit(BinaryExpr be) {
		// TODO Auto-generated method stub
		Expr left = be.getLeftExpr();
		Expr right = be.getRightExpr();
		left.accept(this);
		VType leftType = tempType;
		right.accept(this);
		VType rightType = tempType;

		if (!leftType.equals(rightType)) {
			//TODO error handling
		}		
	}

	@Override
	public void visit(BlockStmt bs) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(EmptyArrayType et) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionArg fa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionCall fc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionDecl fd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionDeclList fdl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionParam fp) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Dirties tempType
	 */
	@Override
	public void visit(Identifier i) {
		if (!env.containsKey(i.getTheValue())) {
			// TODO: error handling
		}
		tempType = env.get(i.getTheValue());
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
		// Check if-then stmt
		(is.getStmt1()).accept(this);
		
		// Check else stmt
		if (is.getIndex() == 1) {
			(is.getStmt2()).accept(this);
		}
	}

	@Override
	public void visit(Literal l) {
		// TODO Auto-generated method stub
		int index = l.index;
		switch (index) {
			//int
			case 0: tempType = new VarType(new PrimitiveType(0));
			//string
			case 1: tempType = new VarType();
			//char
			case 2: tempType = new VarType(new PrimitiveType(0));
			//boolean
			case 3: tempType = new VarType(new PrimitiveType(1));
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(PureContentArrayType pca) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(ReturnList rl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ReturnStmt rs) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TupleDeclList tdl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TupleInit ti) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TypeList tl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(UnaryExpr ue) {
		// TODO Auto-generated method stub
		
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
			env.put(vd.getIdentifier().toString(), new VarType(vd));
			tempType = env.get(vd.getIdentifier().toString());
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
		// Check stmt
		(ws.getStmt()).accept(this);
		
	}
	

}
