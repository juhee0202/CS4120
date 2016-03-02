package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import jl2755.ast.*;
import jl2755.type.VType;
import jl2755.type.VarType;

public class TypeCheckVisitor implements Visitor {

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
		tempType;
		int index = as.getIndex();
		if (index == 0) {
			as.getIdentifier().accept(this);
			VType identifierType = tempType;
			as.getExpr().accept(this);
			List<VType> exprTypeList = tempType;
			if (tempType.size() > 1) {
				// TODO: error handling
				// tempType may be a TupleType which is a list of Types
			}
			if (!identifierType.equals(exprTypeList.get(0))) {
				// TODO: error handling
			}
		} else if (index == 1) {
			// a[0] = 3;
			String identifierName = as.getIdentifier().toString();
			as.getExpr().accept(this);
			List<VType> exprTypeList = tempType;
			if (tempType.size() > 1) {
				// TODO: error handling
			}
			if (!arrElemType.equals(exprTypeList.get(0))) {
				// TODO: error handling
				// Unpackaged ArrayType into AssignmentStmt. That's
				// there's an error.
			}
		}
	}

	@Override
	public void visit(BinaryExpr be) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BlockStmt bs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Constant c) {
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

	@Override
	public void visit(Identifier i) {
		if (!env.containsKey(i.getTheValue())) {
			// TODO: error handling
		}
	}

	@Override
	public void visit(IfStmt is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Literal l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MixedArrayType mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NakedStmt ns) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OpExpr oe) {
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	

}
