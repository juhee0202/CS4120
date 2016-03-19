package edu.cornell.cs.cs4120.xic.ir.visit;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.InternalCompilerError;
import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;
import polyglot.util.Pair;

public class LIRVisitor extends IRVisitor implements IRTreeVisitor{
	
	private Pair<IRSeq,IRNode> tempSeq;
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
		// assuming no commute (side effect dirties exp)
		bo.left().accept(this);
		IRSeq s1 = tempSeq.part1();
		IRExpr e1 = (IRExpr) tempSeq.part2();
		bo.right().accept(this);
		IRSeq s2 = tempSeq.part1();
		IRExpr e2 = (IRExpr) tempSeq.part2();
		
		// TODO: Fill in arguments
		if (checkCommute(s2, e1)) {
			IRSeq combinedSeq = combineTwoStmt(s1, s2);
			IRBinOp holyBinOp = new IRBinOp(bo.opType(), e1, e2);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, holyBinOp);
		}
		else {			
			IRTemp holyTemp = new IRTemp("t");
			IRMove storeExpr = new IRMove(holyTemp, (IRExpr) e1);
			IRSeq combinedSeq = combineTwoStmt(s1, storeExpr);
			combinedSeq = combineTwoStmt(combinedSeq, s2);
			IRNode holyBinOp = new IRBinOp(bo.opType(), holyTemp, (IRExpr) e2);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, holyBinOp);
		}
	}
	
	public void visit(IRCall call) {
		// Index temps to keep track of registers
		int tempIndex = 0;
		call.target().accept(this);
		
		// Get side effects and pure expressions of target
		IRSeq holySideEffects = tempSeq.part1();
		IRExpr holyTarget = (IRExpr) tempSeq.part2();
		IRTemp targetTemp = new IRTemp("temp" + tempIndex++);
		
		// Keep track of temps to put in CALL at the end
		List<IRExpr> holyTemps = new ArrayList<IRExpr>();
		holyTemps.add(targetTemp);
		IRMove holyTargetMove = new IRMove(targetTemp, holyTarget);
		
		// Adds target's statement and move to SEQ
		holySideEffects = combineTwoStmt(holySideEffects, holyTargetMove);
		
		// Loop over, add side effects and move to SEQ, keep TEMPs
		List<IRExpr> dirtyExprList = call.args();
		for (int i = 0; i < dirtyExprList.size(); i++) {
			dirtyExprList.get(i).accept(this);
			holySideEffects = combineTwoStmt(holySideEffects,tempSeq.part1());
			IRExpr holyExpr = (IRExpr) tempSeq.part2();
			IRTemp holyTemp = new IRTemp("temp" + tempIndex++);
			holyTemps.add(holyTemp);
			IRMove holyArgMove = new IRMove(holyTemp, holyExpr);
			holySideEffects = combineTwoStmt(holySideEffects, holyArgMove);
		}
		
		IRTemp returnRegister = new IRTemp(Configuration.ABSTRACT_RET_PREFIX + "0");
		IRCall holyCall = new IRCall(holyTarget, holyTemps);
		IRMove lastHolyMove = new IRMove(returnRegister, holyCall);
		holySideEffects = combineTwoStmt(holySideEffects, lastHolyMove);
		tempSeq = new Pair<IRSeq, IRNode>(holySideEffects, returnRegister);
	}
	
	public void visit(IRCJump cj) {
		cj.expr().accept(this);
		IRSeq holySeq = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRNode holyJump = new IRCJump(holyExpr, cj.trueLabel(), cj.falseLabel());
		tempSeq = new Pair<IRSeq, IRNode>(holySeq, holyJump);
	}
	
	public void visit(IRCompUnit cu) {
		
		// After visiting tree
		program = cu;
	}
	
	public void visit(IRConst con) {
		List<IRStmt> emptyIRStmt = new ArrayList<IRStmt>();
		IRSeq emptySeq = new IRSeq(emptyIRStmt);
		tempSeq = new Pair<IRSeq, IRNode>(emptySeq, con);
	}
	
	public void visit(IRESeq eseq) {
		IRStmt stmtPart = eseq.stmt();
		stmtPart.accept(this);
		IRStmt sideEffectOfStmt = tempSeq.part1();
		IRExpr exprPart = eseq.expr();
		exprPart.accept(this);
		IRStmt sideEffectOfExpr = tempSeq.part1();
		IRExpr purifiedExpr = (IRExpr) tempSeq.part2();
		IRSeq combinedSideEffects = combineTwoStmt(sideEffectOfStmt,
				sideEffectOfExpr);
		tempSeq = new Pair<IRSeq, IRNode>(combinedSideEffects, purifiedExpr);
	}
	
	public void visit(IRExp exp) {
		exp.expr().accept(this);
		IRSeq holySeq = tempSeq.part1();
		List<IRStmt> holyStmts = holySeq.stmts();
		IRMove lastMoveWithCall = (IRMove) holyStmts.get(holyStmts.size() - 1);
		IRCall lastCall = (IRCall) lastMoveWithCall.expr();
		IRExp holyExp = new IRExp(lastCall);
		holyStmts.set(holyStmts.size() - 1, holyExp);
		IRSeq safeHolySeq = new IRSeq(holyStmts);
		tempSeq = new Pair<IRSeq, IRNode>(safeHolySeq, null);
	}
	
	public void visit(IRFuncDecl fd) {
		
	}
	
	public void visit(IRJump j) {
		j.target().accept(this);
		IRSeq holySeq = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRNode holyJump = new IRJump(holyExpr);
		tempSeq = new Pair<IRSeq, IRNode>(holySeq, holyJump);
	}
	
	public void visit(IRLabel l) {
		tempSeq = new Pair<IRSeq, IRNode>(new IRSeq(l), null);
	}
	
	public void visit(IRMem mem) {
		mem.expr().accept(this);
		IRSeq holyStmt = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRExpr memOfHolyExpr = new IRMem(holyExpr);
		tempSeq = new Pair<IRSeq, IRNode>(holyStmt, memOfHolyExpr);
	}
	
	public void visit(IRMove mov) {
		
	}
	
	public void visit(IRName name) {
		List<IRStmt> emptyIRStmt = new ArrayList<IRStmt>();
		IRSeq emptySeq = new IRSeq(emptyIRStmt);
		tempSeq = new Pair<IRSeq, IRNode>(emptySeq, name);
	}
	
	public void visit(IRReturn ret) {
		
	}
	
	public void visit(IRSeq seq) {
		List<IRStmt> stmtList = seq.stmts();
		IRSeq combinedSeq = new IRSeq(new ArrayList<IRStmt>());
		
		for (int i = 0; i < stmtList.size(); i++) {
			stmtList.get(i).accept(this);
			IRSeq holySeq = tempSeq.part1();
			combinedSeq = combineTwoStmt(combinedSeq, holySeq);
		}
		tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, null);
	}
	
	public void visit(IRTemp temp) {
		List<IRStmt> emptyIRStmt = new ArrayList<IRStmt>();
		IRSeq emptySeq = new IRSeq(emptyIRStmt);
		tempSeq = new Pair<IRSeq, IRNode>(emptySeq, temp);
	}
	
	private static IRSeq combineTwoStmt(IRStmt left, IRStmt right) {
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
	
	private static boolean checkCommute(IRStmt stmt, IRExpr expr) {
		if (expr instanceof IRConst || expr instanceof IRName) {
			return true;
		}
		
		// TODO: Do dis
		
		return false;
	}
}
