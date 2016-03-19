package edu.cornell.cs.cs4120.xic.ir.visit;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;
import edu.cornell.cs.cs4120.xic.ir.*;
import polyglot.util.Pair;

public class LIRVisitor extends IRVisitor implements IRTreeVisitor{
	
	private Pair<IRStmt,IRExpr> tempSeq;
	public IRNode program;
	
	public LIRVisitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LIRVisitor enter(IRNode parent, IRNode n) {
        return this;
    }
	
	@Override
	protected IRNode leave(IRNode parent, IRNode n, IRNode n_,
            IRVisitor v_) {
		n_.lower();
		
		
		return n_;
    }
	
	public void visit(IRBinOp bo) {
	}
	
	public void visit(IRCall call) {
		
	}
	
	public void visit(IRCJump cj) {
		
	}
	
	public void visit(IRCompUnit cu) {
		
		// After visiting tree
		program = cu;
	}
	
	public void visit(IRConst con) {
		
	}
	
	public void visit(IRESeq eseq) {
		IRStmt stmtPart = eseq.stmt();
		stmtPart.accept(this);
		IRExpr exprPart = eseq.expr();
		exprPart.accept(this);
		IRStmt sideEffectOfExpr = tempSeq.part1();
		
	}
	
	public void visit(IRExp exp) {
		
	}
	
	public void visit(IRFuncDecl fd) {
		
	}
	
	public void visit(IRJump j) {
		
	}
	
	public void visit(IRLabel l) {
		
	}
	
	public void visit(IRMem mem) {
		
	}
	
	public void visit(IRMove mov) {
		
	}
	
	public void visit(IRName name) {
		
	}
	
	public void visit(IRReturn ret) {
		
	}
	
	public void visit(IRSeq seq) {
		
	}
	
	public void visit(IRTemp temp) {
		
	}
	
	private IRSeq combineTwoStmt(IRStmt left, IRStmt right) {
		if (left instanceof IRSeq) {
			List<IRStmt> leftStmt = ((IRSeq) left).stmts();
			if (right instanceof IRSeq) {
				List<IRStmt> rightStmt = ((IRSeq) right).stmts();
				rightStmt.addAll(leftStmt);
				return new IRSeq(rightStmt);
			}
			else {
				leftStmt.add(right);
				return new IRSeq(leftStmt);
			}
		}
		else {
			if (right instanceof IRSeq) {
				List<IRStmt> rightStmt = ((IRSeq) right).stmts();
				rightStmt.add(left);
				return new IRSeq(rightStmt);
			}
			else {
				List<IRStmt> newList = new ArrayList<IRStmt>();
				newList.add(left);
				newList.add(right);
				return new IRSeq(newList);
			}
		}
	}
	
}
