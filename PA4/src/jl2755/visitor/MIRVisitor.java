package jl2755.visitor;

import edu.cornell.cs.cs4120.xic.ir.*;
import jl2755.ast.ArrayElement;
import jl2755.ast.ArrayElementList;
import jl2755.ast.ArrayLiteral;
import jl2755.ast.AssignmentStmt;
import jl2755.ast.BinaryExpr;
import jl2755.ast.BlockStmt;
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

public class MIRVisitor implements Visitor{
	
	private IRNode tempNode;

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

	@Override
	public void visit(AssignmentStmt as) {
		// TODO: Finish index 1 and 2
		int index = as.getIndex();
		if (index == 0) {
			as.getIdentifier().accept(this);
			IRExpr tempID = (IRExpr) tempNode;
			as.getExpr().accept(this);
			IRExpr tempExpr = (IRExpr) tempNode;
			tempNode = new IRMove(tempID, tempExpr);
		} else if (index == 1) {
			IRName arrayIDName = new IRName(as.getIdentifier().toString());
			
		} else {
			
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
		// Unnecessary
		if (vd.getIndex() == 0) {
			// x: t[]
			// Allocate memory for array
		}
	}

	/**
	 * Dirties tempNode to IRMove
	 */
	@Override
	public void visit(VarInit vi) {
		VarDecl vd = vi.getVarDecl();
		int index = vd.getIndex();
		if (index == 0) {
			// x: t[] = e
			
		} else if (index == 1) {
			// x: int = e or x: bool = e
			vd.getIdentifier().accept(this);
			IRExpr temp = (IRExpr) tempNode;
			vi.getExpr().accept(this);
			IRExpr e = (IRExpr) tempNode;
			tempNode = new IRMove(temp,e);
		}
		
	}

	@Override
	public void visit(WhileStmt ws) {
		// TODO: Optimize this naive way
		IRLabel startOfLoop = new IRLabel("Head");
		ws.getExpr().accept(this);
		IRExpr conditionalExpr = (IRExpr) tempNode;
		IRLabel trueLabel = new IRLabel("True");
		IRLabel falseLabel = new IRLabel("False");
		IRCJump cJumpNode = new IRCJump(conditionalExpr, "True", "False");
		ws.getStmt().accept(this);
		IRStmt loopStmts = (IRStmt) tempNode;
		IRJump jumpToStart = new IRJump(new IRName("Head"));
		tempNode = new IRSeq(startOfLoop, cJumpNode, trueLabel, loopStmts,
				jumpToStart, falseLabel);
	}

	/**
	 * IndexedBrackets should represent the recursive case, where
	 * there are indices to be put into a IRMem and IRBinOp node.
	 * 
	 * @param ib is the rest of the indexed brackets to parse
	 * @return an IRExpr that points to the memory location pointed
	 * from IRExpr and IndexedBrackets
	 */
	private IRExpr createIRExprForBrackets(IRExpr ire, IndexedBrackets ib) {
		// TODO
		return null;
	}
	
}
