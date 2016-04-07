package jl2755.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.OpType;
import polyglot.util.Pair;

public class LIRVisitor implements IRTreeVisitor{
	
	private Pair<IRSeq,IRNode> tempSeq;
	private int globalTempCount = 0;
	private int globalLabelCount = 0;
	private Map<String, BasicBlock> labelToBasicBlock;
	public IRNode program;
	private static final IRConst TRUE = new IRConst(1);
	
	public LIRVisitor() {
		labelToBasicBlock = new HashMap<String, BasicBlock>();
	}
	
	public void visit(IRBinOp bo) {
		bo.left().accept(this);
		IRSeq s1 = tempSeq.part1();
		IRExpr e1 = (IRExpr) tempSeq.part2();
		bo.right().accept(this);
		IRSeq s2 = tempSeq.part1();
		IRExpr e2 = (IRExpr) tempSeq.part2();
		
		if (checkCommute(s2, e1)) {
			IRSeq combinedSeq = combineTwoStmt(s1, s2);
			IRBinOp holyBinOp = new IRBinOp(bo.opType(), e1, e2);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, holyBinOp);
		}
		else {			
			IRTemp holyTemp = new IRTemp("temp" + globalTempCount++);
			IRMove storeExpr = new IRMove(holyTemp, (IRExpr) e1);
			IRSeq combinedSeq = combineTwoStmt(s1, storeExpr);
			combinedSeq = combineTwoStmt(combinedSeq, s2);
			IRTemp holyTemp2 = new IRTemp(holyTemp.name());
			IRNode holyBinOp = new IRBinOp(bo.opType(), holyTemp2, (IRExpr) e2);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, holyBinOp);
		}
	}
	
	public void visit(IRCall call) {
		// Index temps to keep track of registers
		call.target().accept(this);
		
		// Get side effects and pure expressions of target
		IRSeq holySideEffects = tempSeq.part1();
		IRExpr holyTarget = (IRExpr) tempSeq.part2();
		
		// Keep track of temps to put in CALL at the end
		List<IRExpr> holyTemps = new ArrayList<IRExpr>();

		// Loop over, add side effects and move to SEQ, keep TEMPs
		List<IRExpr> dirtyExprList = call.args();
		for (int i = 0; i < dirtyExprList.size(); i++) {
			dirtyExprList.get(i).accept(this);
			holySideEffects = combineTwoStmt(holySideEffects,tempSeq.part1());
			IRExpr holyExpr = (IRExpr) tempSeq.part2();
			IRTemp holyTemp = new IRTemp("temp" + globalTempCount++);
			holyTemps.add(holyTemp);
			IRMove holyArgMove = new IRMove(holyTemp, holyExpr);
			holySideEffects = combineTwoStmt(holySideEffects, holyArgMove);
		}
		
		// create fresh temp objects
		for (int i = 0; i < holyTemps.size(); i++) {
			IRTemp temp = new IRTemp(((IRTemp)holyTemps.get(i)).name());
			holyTemps.set(i, temp);
		}
		
		IRTemp returnRegister = new IRTemp("temp" + globalTempCount++);
		IRCall holyCall = new IRCall(holyTarget, holyTemps);
		IRMove lastHolyMove = new IRMove(returnRegister, holyCall);
		holySideEffects = combineTwoStmt(holySideEffects, lastHolyMove);
		IRTemp returnRegister2 = new IRTemp(returnRegister.name());
		tempSeq = new Pair<IRSeq, IRNode>(holySideEffects, returnRegister2);
	}
	
	public void visit(IRCJump cj) {
		cj.expr().accept(this);
		IRSeq holySeq = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRCJump holyJump = new IRCJump(holyExpr, cj.trueLabel(), cj.falseLabel());
		IRSeq combined = combineTwoStmt(holySeq, holyJump);
		tempSeq = new Pair<IRSeq, IRNode>(combined, null);
	}
	
	public void visit(IRCompUnit cu) {
		List<IRFuncDecl> allFuncDecls = new ArrayList<IRFuncDecl>(cu.functions().values());
		List<IRFuncDecl> newFuncDecls = new ArrayList<IRFuncDecl>();
		
		for (int i = 0; i < allFuncDecls.size(); i++) {
			IRFuncDecl funcDecl = allFuncDecls.get(i);
			funcDecl.accept(this);
			List<IRStmt> stmts = new ArrayList<IRStmt>();
			IRLabel labelle = new IRLabel("tempLabel"+i);
			stmts.add(labelle);
			stmts.addAll(tempSeq.part1().stmts());

			IRSeq body = new IRSeq(stmts);
			List<BasicBlock> basicBlockList = createBasicBlocks(body);
			// Construct trace list
			List< List<BasicBlock> > traceList = constructTraces(basicBlockList);
			// Flatten trace list
			List<IRStmt> stmtList = new ArrayList<IRStmt>();
			for (List<BasicBlock> trace : traceList) {
				for (BasicBlock block : trace) {
					stmtList.addAll(block.stmtList);
				}
			}
			assert(stmtList.remove(0) == labelle);
			IRSeq seq = new IRSeq(stmtList);
			IRFuncDecl newFuncDecl = new IRFuncDecl(funcDecl.name(),seq);
			newFuncDecls.add(newFuncDecl);
			cu.functions().put(funcDecl.name(), newFuncDecl);
		}
		
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
		IRStmt holyStmts = tempSeq.part1();
		
		IRExpr exprPart = eseq.expr();
		exprPart.accept(this);
		IRStmt holyStmtsOfExpr = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRSeq combinedSideEffects = combineTwoStmt(holyStmts,
				holyStmtsOfExpr);
		tempSeq = new Pair<IRSeq, IRNode>(combinedSideEffects, holyExpr);
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
		fd.body().accept(this);
		IRSeq functionBody = tempSeq.part1();
//		IRLabel functionLabel = new IRLabel(fd.label());
//		IRSeq function = combineTwoStmt(functionLabel, functionBody);
		tempSeq = new Pair<IRSeq, IRNode>(functionBody, null);
	}
	
	public void visit(IRJump j) {
		j.target().accept(this);
		IRSeq holySeq = tempSeq.part1();
		IRExpr holyExpr = (IRExpr) tempSeq.part2();
		IRJump holyJump = new IRJump(holyExpr);
		holySeq = combineTwoStmt(holySeq, holyJump);
		tempSeq = new Pair<IRSeq, IRNode>(holySeq, null);
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
		// assuming no commute (side effect dirties exp)
		mov.expr().accept(this);
		IRSeq s1 = tempSeq.part1();
		IRExpr e1 = (IRExpr) tempSeq.part2();
		mov.target().accept(this);
		IRSeq s2 = tempSeq.part1();
		IRExpr e2 = (IRExpr) tempSeq.part2();
		
		if (checkCommute(s2, e1)) {
			IRSeq combinedSeq = combineTwoStmt(s1, s2);
			IRMove holyMove = new IRMove(e2, e1);
			combinedSeq = combineTwoStmt(combinedSeq, holyMove);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, null);
		}
		else {
			IRTemp holyTemp = new IRTemp("temp" + globalTempCount++);
			IRMove storeExpr = new IRMove(holyTemp, (IRExpr) e1);
			IRSeq combinedSeq = combineTwoStmt(s1, storeExpr);
			combinedSeq = combineTwoStmt(combinedSeq, s2);
			IRNode holyMove = new IRMove(e2, holyTemp);
			combinedSeq = combineTwoStmt(combinedSeq, (IRStmt) holyMove);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, null);
		}
	}
	
	public void visit(IRName name) {
		List<IRStmt> emptyIRStmt = new ArrayList<IRStmt>();
		IRSeq emptySeq = new IRSeq(emptyIRStmt);
		tempSeq = new Pair<IRSeq, IRNode>(emptySeq, name);
	}
	
	public void visit(IRReturn ret) {
		List<IRStmt> justRet = new ArrayList<IRStmt>();
		justRet.add(ret);
		tempSeq = new Pair<IRSeq, IRNode>(new IRSeq(justRet), null);
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
	
	public static IRSeq combineTwoStmt(IRStmt left, IRStmt right) {
		if (left instanceof IRSeq) {
			List<IRStmt> leftStmt = ((IRSeq) left).stmts();
			if (right instanceof IRSeq) {
				List<IRStmt> rightStmt = ((IRSeq) right).stmts();
				leftStmt.addAll(rightStmt);
				return new IRSeq(leftStmt);
			}
			else {
				leftStmt.add(right);
				return new IRSeq(leftStmt);
			}
		}
		else {
			if (right instanceof IRSeq) {
				List<IRStmt> rightStmt = ((IRSeq) right).stmts();
				rightStmt.add(0,left);
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
		
		if (stmt == null) {
			return true;
		}
		
		IRSeq seqVersion = (IRSeq) stmt;
		if (seqVersion.stmts().isEmpty()) {
			return true;
		}
		
		// TODO: Do dis
		
		return false;
		
	}
	
	/**
	 * A basic block is a sequence of statements with the following qualities:
	 * -	the first statement is a LABEL
	 * -	the last statement is a JUMP or CJUMP
	 * -	There are no other LABELs, JUMPs, or CJUMPs
	 * @param Sequence of Statements
	 * @return a List of basic blocks (IRSeq)
	 */
	private List<BasicBlock> createBasicBlocks(IRSeq stmts) {	
//		System.out.println("*****Start*****");
//		for (IRStmt s : stmts.stmts()) {
//			System.out.println(s);
//		}
//		System.out.println("******End******");
		
		// return this at the end
		List<BasicBlock> basicBlockList = new ArrayList<BasicBlock>();
		
		// list of program statements
		List<IRStmt> stmtList = stmts.stmts();
		
		// Create a list of basic blocks
		// End the basicblock if the currStmt is JUMP/CJUMP/RETURN
		// Start a new basicblock if the currStmt is LABEL
		BasicBlock basicBlock = null;
		for (IRStmt stmt : stmtList) {
			if (stmt instanceof IRLabel) {
				// if the prev basic block didn't have JUMP/CJUMP/RETURN
				if (basicBlock != null) {
					IRJump jump = new IRJump(new IRName(((IRLabel) stmt).name()));
					basicBlock.addIRStmt(jump);
					basicBlock.index = 1;
					basicBlockList.add(basicBlock);
				}
				// creates a new basic block
				basicBlock = new BasicBlock(((IRLabel) stmt));
				labelToBasicBlock.put(((IRLabel)stmt).name(), basicBlock);
			} else if (stmt instanceof IRJump) {
				// make sure that a basic block starts with a label
				if (basicBlock == null) {
					IRLabel startLabel = new IRLabel("label" + globalLabelCount++);
					basicBlock = new BasicBlock(startLabel);
				}
				
				basicBlock.addIRStmt(stmt);
				basicBlock.index = 1;
				basicBlockList.add(basicBlock);
				basicBlock = null;
			} else if (stmt instanceof IRCJump) {
				// make sure that a basic block starts with a label
				if (basicBlock == null) {
					IRLabel startLabel = new IRLabel("label" + globalLabelCount++);
					basicBlock = new BasicBlock(startLabel);
				}
				basicBlock.addIRStmt(stmt);
				basicBlock.index = 2;
				basicBlockList.add(basicBlock);
				basicBlock = null;			
			} else if (stmt instanceof IRReturn) {
				// make sure that a basic block starts with a label
				if (basicBlock == null) {
					IRLabel startLabel = new IRLabel("label" + globalLabelCount++);
					basicBlock = new BasicBlock(startLabel);
				}
				basicBlock.addIRStmt(stmt);
				basicBlock.index = 3;
				basicBlockList.add(basicBlock);
				basicBlock = null;
			} else {
				// make sure that a basic block starts with a label
				if (basicBlock == null) {
					IRLabel startLabel = new IRLabel("label" + globalLabelCount++);
					basicBlock = new BasicBlock(startLabel);
				}
				basicBlock.addIRStmt(stmt);
			}
		}
		
		// add the last basicBlock
		if (basicBlock != null) {
			basicBlock.addIRStmt(new IRReturn());
			basicBlock.index = 3;
			basicBlockList.add(basicBlock);
			basicBlock = null;
		}
		
		// set nextBlock for each basicBlock
		for (BasicBlock bb : basicBlockList) {
			switch (bb.index) {
			case 1:	// JUMP
				IRJump jump = (IRJump) bb.getLastStmt();
				String name = ((IRName)jump.target()).name();
				BasicBlock nextBlock = labelToBasicBlock.get(name);
				bb.nextBlock1 = nextBlock;
				nextBlock.preds.add(bb);
				break;
			case 2:	// CJUMP
				IRCJump cjump = (IRCJump) bb.getLastStmt();
				String falseLabelName = cjump.falseLabel();
				BasicBlock nextBlock1 = labelToBasicBlock.get(falseLabelName);
				bb.nextBlock1 = nextBlock1;
				nextBlock1.preds.add(bb);
				String trueLabelName = cjump.trueLabel();
				BasicBlock nextBlock2 = labelToBasicBlock.get(trueLabelName);
				bb.nextBlock2 = nextBlock2;
				nextBlock2.preds.add(bb);
				break;
			}
		}
		
		return basicBlockList;
	}
	
	/** 	
	 * input: List of basic blocks
	 * output: list of reordered traces (trace: sequence of basic blocks)
	 * 
	 * 1. unmark all basic blocks
	 * 2. while there is an unmarked basic block:
	 * 	3. choose an unmarked block (preferably blocks w/out unmarked pred
	 * 	4. construct a trace from that block
	 * 	5. mark all blocks reached
	 * 	6. reorder blocks in the trace (separate method)
	 * 7. return the list of traces
	 * 
	*/
	private List<List<BasicBlock>> constructTraces(List<BasicBlock> basicBlockList) {
		// to be returned
		List< List<BasicBlock> > traceList = new ArrayList< List<BasicBlock> >();
		
		int numBlocks = basicBlockList.size();
		int numUnmarkedBlocks = numBlocks;
		while (numUnmarkedBlocks > 0) {
			List<BasicBlock> trace = new ArrayList<BasicBlock>();
			// choose an unmarked block
			// preferably blocks without unmarked pred
			BasicBlock currBlock = pickStartBlock(basicBlockList);
			trace.add(currBlock);
			currBlock.marked = true;
			numUnmarkedBlocks--;
			while (currBlock.hasUnmarkedSucc()) {
				BasicBlock nextBlock = (currBlock.nextBlock1.marked) ?
						currBlock.nextBlock2 : currBlock.nextBlock1;
				trace.add(nextBlock);
				nextBlock.marked = true;
				numUnmarkedBlocks--;
				currBlock = nextBlock;
			}
			traceList.add(lowerJumps(trace));
		}
//		for (List<BasicBlock> bbList : traceList) {
//			System.out.println("*****Start*****");
//			for (BasicBlock bb : bbList) {
//				for (IRStmt s : bb.stmtList) {
//					System.out.println(s);
//				}
//			}
//			System.out.println("******End******");
//		}
		return traceList;
	}
	
	private BasicBlock pickStartBlock(List<BasicBlock> bbList) {
		// choose an unmarked block
		// preferably blocks without unmarked pred
		int minUnmarked = Integer.MAX_VALUE;
		BasicBlock start = null;
		for (BasicBlock block: bbList) {
			if (block.marked) {
				continue;
			}
			int currUnmarked = 0;
			for (BasicBlock pred: block.preds) {
				if (!pred.marked) {
					currUnmarked++;
				}
			}
			if (currUnmarked < minUnmarked) {
				minUnmarked = currUnmarked;
				start = block;
			}
		}
		return start;
	}
	
	private List<BasicBlock> lowerJumps(List<BasicBlock> trace) {
		// Iterate through, find CJUMP
		// if followed by false, new CJUMP without false label
		// if followed by true, switch true and false and negate 
		//condition and add new JUMP
		// else reorder so false label is right after CJUMP block 
		//and new CJUMP without false label
		for (int i = 0; i < trace.size(); i++) {
			BasicBlock currBlock = trace.get(i);
			if (currBlock.index == 2 && i != trace.size()-1) {
				// currBlock is a CJUMP
				BasicBlock nextBlock = trace.get(i+1);
				IRCJump old = (IRCJump) currBlock.getLastStmt();
				IRCJump cjump = null;
				if (nextBlock.label == currBlock.nextBlock1.label) {
					// followed by false label
					cjump = new IRCJump(old.expr(),old.trueLabel());
				} else {
					// followed by true label
					IRBinOp negateOld = new IRBinOp(OpType.XOR,TRUE,old.expr());
					cjump = new IRCJump(negateOld,old.falseLabel());
				}
				currBlock.setLastStmt(cjump);
			} else if (currBlock.index == 1 && i != trace.size()-1) {
				// currBlock is a JUMP
				BasicBlock nextBlock = trace.get(i+1);
				if (nextBlock.label == currBlock.nextBlock1.label &&
					trace.contains(nextBlock)) {
					currBlock.removeLastStmt();
				}
			}
		}
		return trace;
	}
}
