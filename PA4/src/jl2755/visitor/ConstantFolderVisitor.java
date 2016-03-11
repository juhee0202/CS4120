package jl2755.visitor;

import java.util.List;

import jl2755.ast.ArrayElement;
import jl2755.ast.ArrayElementList;
import jl2755.ast.ArrayLiteral;
import jl2755.ast.AssignmentStmt;
import jl2755.ast.BinaryExpr;
import jl2755.ast.BlockStmt;
import jl2755.ast.Expr;
import jl2755.ast.FunctionArg;
import jl2755.ast.FunctionCall;
import jl2755.ast.FunctionDecl;
import jl2755.ast.FunctionDeclList;
import jl2755.ast.Identifier;
import jl2755.ast.IfStmt;
import jl2755.ast.IndexedBrackets;
import jl2755.ast.Literal;
import jl2755.ast.Program;
import jl2755.ast.ReturnStmt;
import jl2755.ast.Stmt;
import jl2755.ast.StmtList;
import jl2755.ast.TupleInit;
import jl2755.ast.UnaryExpr;
import jl2755.ast.UseId;
import jl2755.ast.VarDecl;
import jl2755.ast.VarInit;
import jl2755.ast.WhileStmt;

/**
 * This visitor's goal is to sniff out and hunt expressions
 * that may be literals. If a unary or binary expression contains
 * only literals, then it can be reduced to one constant.
 */
public class ConstantFolderVisitor implements Visitor{

	@Override
	public void visit(ArrayElement ae) {
		// ArrayElements always have IndexedBrackets that
		// might contain expressions
		ae.getIndexedBrackets().accept(this);
	}

	@Override
	public void visit(ArrayElementList ael) {
		// ArrayElementList has expressions in
		// its list that may be foldable
		List<Expr> listOfExprs = ael.getAllExprInArray();
		for (int i = 0; i < listOfExprs.size(); i++) {
			listOfExprs.get(i).accept(this);
		}
	}

	@Override
	public void visit(ArrayLiteral al) {
		// Simply visit the child ArrayElementList
		al.getArrElemList().accept(this);
	}

		@Override
		public void visit(AssignmentStmt as) {
			as.getExpr().accept(this);
			if (as.getIndex() != 0) {
				as.getIndexedBrackets().accept(this);
			}
		}
	
		@Override
		public void visit(BinaryExpr be) {
			
		}
	
		@Override
		public void visit(BlockStmt bs) {
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
	public void visit(Identifier id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IfStmt is) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(IndexedBrackets ib) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Literal l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Program p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ReturnStmt rs) {
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
	public void visit(TupleInit ti) {
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

	@Override
	public void visit(VarDecl vd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarInit vi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(WhileStmt ws) {
		// TODO Auto-generated method stub
		
	}

}
