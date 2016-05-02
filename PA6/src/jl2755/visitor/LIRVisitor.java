package jl2755.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.*;
import polyglot.util.Pair;

public class LIRVisitor implements IRTreeVisitor{
	
	private Pair<IRSeq,IRNode> tempSeq;
	private int globalTempCount = 0;
	private int globalLabelCount = 0;
	private Map<String, BasicBlock> labelToBasicBlock;
	public IRCompUnit program;
	
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
			// S(s1), MOV(TEMP(T), e1'), S(s2), BINOP(TEMP(T), e2')
			IRTemp holyTemp = new IRTemp("temp" + globalTempCount++);
			IRMove storeExpr = new IRMove(holyTemp, (IRExpr) e1);
			IRSeq combinedSeq = combineTwoStmt(s1, storeExpr);
			combinedSeq = combineTwoStmt(combinedSeq, s2);
			IRTemp holyTemp2 = new IRTemp(holyTemp.name());
			IRNode holyBinOp = new IRBinOp(bo.opType(), holyTemp2, (IRExpr) e2);
//			combinedSeq = combineTwoStmt(combinedSeq, (IRStmt) holyBinOp);
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
		holyCall.setNumReturns(call.getNumReturns());
		holyCall.setReturnBoolList(call.getReturnBoolList());
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
			// coalesce RETURNs
			coalesceReturns(stmtList);
			// remove consecutive JUMP l; LABEL l instructions
			removeConsecutiveJumpLabel(stmtList);
			// remove unused Labels
			removeUnusedLabels(stmtList);
			// TODO: FIX (primes.xi won't run)
			// remove consecutive LABEL l1; JUMP l2 by replacing JUMP l1 with JUMP l2
//			removeConsecutiveLabelJump(stmtList);
			

			IRSeq seq = new IRSeq(stmtList);
			IRFuncDecl newFuncDecl = new IRFuncDecl(funcDecl.getName(), 
					funcDecl.getABIName(), seq);
			newFuncDecl.setNumArgs(funcDecl.getNumArgs());
			newFuncDecl.setNumReturns(funcDecl.getNumReturns());
			newFuncDecl.setParamList(funcDecl.getParamList());
			newFuncDecls.add(newFuncDecl);
			cu.functions().put(funcDecl.getABIName(), newFuncDecl);
		}
		
		// After visiting tree
		program = cu;
	}
	
	/**
	 * Removes consecutive LABEL l1; JUMP l2 by replacing 
	 * JUMP l1 with JUMP l2
	 * @param stmtList
	 */
	private void removeConsecutiveLabelJump(List<IRStmt> stmtList) {
		// labels to JUMP/CJUMPs that jump to it
		Map<String, List<IRStmt>> label2jumps = new HashMap<String, List<IRStmt>>();
		
		// 1st pass: fill label2jumps 
		for (int i = 0; i < stmtList.size(); i++) {
			IRStmt stmt = stmtList.get(i);
			if (stmt instanceof IRJump || stmt instanceof IRCJump) {
				String label;
				if (stmt instanceof IRJump) {
					IRJump jumpStmt = (IRJump)stmt;
					label = ((IRName)jumpStmt.target()).name();
				} else {
					IRCJump cjumpStmt = (IRCJump)stmt;
					label = cjumpStmt.trueLabel();
				} 
				List<IRStmt> list; 
				if (label2jumps.containsKey(label)) {
					list = label2jumps.get(label);
				} else {
					list = new ArrayList<IRStmt>();
				}
				list.add(stmt);
				label2jumps.put(label, list);
			}
		}
		
		// 2nd pass: iterate backward to remove/replace
		IRStmt prev = null;
		ListIterator<IRStmt> currIt = stmtList.listIterator(stmtList.size());
		while (currIt.hasPrevious()) {
			IRStmt curr = currIt.previous();
			if (curr instanceof IRLabel && prev instanceof IRJump) {
				IRLabel currLabel = (IRLabel)curr;
				String label = currLabel.name();
				IRJump prevJump = (IRJump)prev;
				String newLabel = ((IRName)prevJump.target()).name();				
				List<IRStmt> jumps = label2jumps.get(label);
				
				// replace old labels with new labels in IRJumps
				for (IRStmt jump : jumps) {
					int index = stmtList.indexOf(jump);
					IRStmt newStmt;
					if (jump instanceof IRJump) {
						newStmt = new IRJump(new IRName(newLabel));
					} else {
						newStmt = jump;
						((IRCJump)newStmt).setTrueLabel(newLabel);
					}
					stmtList.set(index, newStmt);
				}
				
				// remove 
				currIt.remove();
				currIt.next();
				currIt.remove();
				prev = null;
				continue;
			}
			prev = curr;
		}
	}

	/**
	 * Removes consecutive JUMP l1; Label l2 instructions
	 * @param stmtList
	 */
	private void removeConsecutiveJumpLabel(List<IRStmt>  stmtList) {
		// list containing indices of label stmts to be removed
		List<Integer> indexList = new ArrayList<Integer>();

		// look for consecutive jump label instructions
		IRStmt prev = null;
		for (int i = 0; i < stmtList.size(); i++) {
			IRStmt curr = stmtList.get(i);
			if (prev instanceof IRJump && curr instanceof IRLabel) {
				IRJump jumpPrev = (IRJump)prev;
				IRLabel labelCurr = (IRLabel)curr;
				String labelName = ((IRName)jumpPrev.target()).name();
				if (labelName.equals(labelCurr.name())) {
					indexList.add(i);
				}
			}
			prev = curr;
		}
		
		// remove consecutive jump label instructions
		for (int i = indexList.size()-1; i >= 0; i--) {
			int index = indexList.get(i);
			stmtList.remove(index);		// remove label
			stmtList.remove(index-1);	// remove jump
		}
	}

	/**
	 * Removes unused Labels
	 * @param stmtList
	 */
	private void removeUnusedLabels(List<IRStmt>  stmtList) {
		// Map of Label string to stmtList index
		Map<String, Integer> label2index = new HashMap<String, Integer>();
		// Set of used Labels
		Set<String> usedLabels = new HashSet<String>();
		
		// update label2index & unusedLabels
		for (int i = 0; i < stmtList.size(); i++) {
			IRStmt stmt = stmtList.get(i);
			if (stmt instanceof IRLabel) {
				label2index.put(((IRLabel)stmt).name(), i);
			} else if (stmt instanceof IRJump) {
				IRJump jumpStmt = (IRJump)stmt;
				String labelName = ((IRName)jumpStmt.target()).name();
				usedLabels.add(labelName);
			} else if (stmt instanceof IRCJump) {
				IRCJump cjumpStmt = (IRCJump)stmt;
				String trueLabel = cjumpStmt.trueLabel();
				usedLabels.add(trueLabel);
			}
		}
		
		// remove used labels in label2index
		Iterator<Entry<String, Integer>> it = label2index.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, Integer> entry = it.next();
	        String label = entry.getKey();
	        if (usedLabels.contains(label)) {
	        	it.remove();
	        }
	    }
		
		// remove unused labels in stmtList
		List<Integer> indices = new ArrayList<Integer>(label2index.values());
		Collections.sort(indices);
		for (int i = indices.size()-1; i >= 0; i--) {
			int index = indices.get(i);
			stmtList.remove(index);
		}
	}
	
	/**
	 * Coalesce Returns in the function's stmt list by
	 * 1) Insert Label l before the first Return
	 * 2) Turn next returns into Jump l 
	 * @param stmts
	 */
	private void coalesceReturns(List<IRStmt> stmts) {
		String returnLabel = "";  //"label" + globalLabelCount++;
		boolean found_first_return = false;
		int first_return_index = -1;
		
		IRStmt prev = null;
		for (int i = 0; i < stmts.size(); i++) {
			IRStmt curr = stmts.get(i);
			if (curr instanceof IRReturn) {
				if (found_first_return) { 
					IRJump jump = new IRJump(new IRName(returnLabel));
					stmts.set(i, jump);
				} else {
					found_first_return = true;
					if (prev instanceof IRLabel) {
						returnLabel = ((IRLabel)prev).name();
					} else {
						returnLabel = "label" + globalLabelCount++;
						first_return_index = i;
					}
				}
			}
			prev = curr;
		}
		
		// Insert a label if needed
		if (first_return_index != -1) {
			IRLabel label = new IRLabel(returnLabel);
			stmts.add(first_return_index, label);
		}
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
		
		// s1, e1 = src
		mov.expr().accept(this);
		IRSeq s1 = tempSeq.part1();
		IRExpr e1 = (IRExpr) tempSeq.part2();
		// s2, e2 = target
		mov.target().accept(this);
		IRSeq s2 = tempSeq.part1();
		IRExpr e2 = (IRExpr) tempSeq.part2();
		
		
		if (checkCommute(s1, e2)) {
			IRSeq combinedSeq = combineTwoStmt(s2, s1);
			IRMove holyMove = new IRMove(e2, e1);
			combinedSeq = combineTwoStmt(combinedSeq, holyMove);
			tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, null);
		}
		else {
			// S(dest), save expr in dest=IRMem(expr), S(src), MOV(IRMem(savedExpr), e')
			IRTemp holyTemp = new IRTemp("temp" + globalTempCount++);
			if (e2 instanceof IRMem) {
				IRExpr memExpr = ((IRMem) e2).expr();
				IRMove storeExpr = new IRMove(holyTemp, (IRExpr) memExpr);
				IRSeq combinedSeq = combineTwoStmt(s2, storeExpr);
				combinedSeq = combineTwoStmt(combinedSeq, s1);
				IRTemp holyTemp2 = new IRTemp(holyTemp.name());
				IRNode holyMove = new IRMove(new IRMem(holyTemp2), e1);
				combinedSeq = combineTwoStmt(combinedSeq, (IRStmt) holyMove);
				tempSeq = new Pair<IRSeq, IRNode>(combinedSeq, null);
			}

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
			List<IRStmt> newStmt = new ArrayList<IRStmt>();
			newStmt.addAll(((IRSeq)left).stmts());
			if (right instanceof IRSeq) {
				List<IRStmt> rightStmt = ((IRSeq) right).stmts();
				newStmt.addAll(rightStmt);
				return new IRSeq(newStmt);
			}
			else {
				newStmt.add(right);
				return new IRSeq(newStmt);
			}
		}
		else {
			if (right instanceof IRSeq) {
				List<IRStmt> newStmt = new ArrayList<IRStmt>();
				newStmt.addAll(((IRSeq) right).stmts());
				newStmt.add(0,left);
				return new IRSeq(newStmt);
			}
			else {
				List<IRStmt> newList = new ArrayList<IRStmt>();
				newList.add(left);
				newList.add(right);
				return new IRSeq(newList);
			}
		}
	}
	
	/* case 1: IRMove: checkCommute(src_stmt, dest_expr) in mov dest, src 
	 * case 2: IRBinOp: checkCommute(s2, e1)
	 */
	private static boolean checkCommute(IRStmt stmt, IRExpr expr) {
		if (expr instanceof IRConst || expr instanceof IRName) {
			return true;
		}
		
		// ex: MOV TEMP1, expr
		//     This always commutes since temp1 can be overwritten by any side expression
		if (expr instanceof IRTemp) {
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
			trace = lowerJumps(trace);
			traceList.add(trace);
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
					IRBinOp negateOld = new IRBinOp(OpType.XOR, new IRConst(1) ,old.expr());
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