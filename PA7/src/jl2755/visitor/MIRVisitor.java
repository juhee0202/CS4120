package jl2755.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;
import jl2755.ast.*;
import jl2755.type.ClassType;
import jl2755.type.VarType;

public class MIRVisitor implements ASTVisitor{
	
	private IRNode tempNode;
	private int labelCount = 0;
	private int tempCount = 0;
	private static final IRConst WORD_SIZE = new IRConst(Configuration.WORD_SIZE);
	
	// While loop globals for break and continue
	private String currentWhileStart;
	private String currentWhileEnd;
	private Map<String, String> startToEnd = new HashMap<String, String>();
	
	// Tracking inside of a class declaration
	private boolean inClass = false;
	private ClassType classType;
	private IRTemp thisNode;
	
	// Global for Method Dispatch
	private Map<String, IRDispatchVector> classToDispatch =  new HashMap<String, IRDispatchVector>();
	
	// Global Variable name to its ABIName to be used for labels
	private Map<String, String> globalNameToABI = new HashMap<String, String>();
	
	// Global Environment
	private Environment env;
	
	public IRNode program;
	
	public MIRVisitor(Environment global) {
		env = global;
	}
	
	@Override
	public void visit(ArrayElement ae) {
		int index = ae.getIndex();
		
		// arrayLiteral[i]
		if (index == 2) {
			ae.getArrayLiteral().accept(this);
			tempNode = createIRExprForBrackets((IRExpr) tempNode, ae.getIndexedBrackets());
			
		// dotableExpr[i]
		} else if (index == 3) {
			ae.getDotableExpr().accept(this);
			tempNode = createIRExprForBrackets((IRExpr) tempNode, ae.getIndexedBrackets());
		}
	}

	@Override
	public void visit(ArrayElementList ael) {
		// {i,j,...,z}
		List<Expr> exprList = ael.getAllExprInArray();
		int length = exprList.size();
		// Allocate memory for array and length "field"
		IRName addrOfAllocFunc = new IRName("_I_alloc_i");
		IRCall theIRCall = new IRCall(addrOfAllocFunc, new IRConst((length + 1)*Configuration.WORD_SIZE));
		IRTemp tempOfArray = new IRTemp("t"+tempCount++);
		
		// Move allocated space pointer into tempOfArray
		IRMove moveCallIntoTemp = new IRMove(tempOfArray, theIRCall);
		
		// Move length into first cell
		IRMem memOfArray = new IRMem((IRTemp) tempOfArray.copy());
		IRMove moveLengthIntoFirst = new IRMove(memOfArray, new IRConst(length));
		
		// Initialize sequence of statements with moving allocated space into IRTemp and adding length
		List<IRStmt> stmtList = new ArrayList<IRStmt>();
		stmtList.add(moveCallIntoTemp);
		stmtList.add(moveLengthIntoFirst);
		
		// For each expression in the array literal, get the IRNode for it, and make a IRMove
		// node for the IRExpr into each cell
		for (int i = 0; i < exprList.size(); i++) {
			exprList.get(i).accept(this);
			IRConst offSet = new IRConst((i+1)*Configuration.WORD_SIZE);
			IRBinOp offsetBinOp = new IRBinOp(OpType.ADD, offSet, (IRTemp) tempOfArray.copy());
			IRMem memOfCell = new IRMem(offsetBinOp);
			IRMove moveElementIntoCell = new IRMove(memOfCell, (IRExpr) tempNode);
			stmtList.add(moveElementIntoCell);
		}
		
		// Make array point to one cell after (since cell -1 should be length)
		IRBinOp offSetAndMem = new IRBinOp(OpType.ADD, (IRTemp) tempOfArray.copy(), (IRConst) WORD_SIZE.copy());
		IRMove moveOffsetAndMem = new IRMove((IRTemp) tempOfArray.copy(), offSetAndMem);
		stmtList.add(moveOffsetAndMem);
		IRSeq movingElementsIntoArray = new IRSeq(stmtList);
		// temp(a)
		IRESeq createArrayLit = new IRESeq(movingElementsIntoArray, (IRTemp) tempOfArray.copy());
		tempNode = createArrayLit;
	}

	@Override
	public void visit(ArrayLiteral al) {
		al.getArrElemList().accept(this);
	}

	// ADD LOGIC FOR CLASSES
	@Override
	public void visit(AssignmentStmt as) {
		as.getDotableExpr().accept(this);
		IRExpr de = (IRExpr) tempNode;
		as.getExpr().accept(this);
		IRExpr e = (IRExpr) tempNode;
		tempNode = new IRMove(de,e);
		
//		int index = as.getIndex();
//		if (index == 0) {
//			// a = 3
//			as.getDotableExpr().accept(this);
//			IRExpr dotableExpr = (IRExpr) tempNode;
//			as.getExpr().accept(this);
//			IRExpr tempExpr = (IRExpr) tempNode;
//			tempNode = new IRMove(dotableExpr, tempExpr);
//		} else {
//			// a[i] = 3
//			as.getDotableExpr().accept(this);
//			IRTemp base = (IRTemp) tempNode;
//			IndexedBrackets ib = as.getIndexedBrackets();
//			IRExpr mem = createIRExprForBrackets(base, ib);
//			as.getExpr().accept(this);
//			IRExpr e = (IRExpr) tempNode;
//			tempNode = new IRMove(mem,e);
//		}
	}

	@Override
	public void visit(BinaryExpr be) {
		BinaryOp op = be.getBinaryOp();
		be.getLeftExpr().accept(this);
		IRExpr leftNode = (IRExpr) tempNode;
		be.getRightExpr().accept(this);
		IRExpr rightNode = (IRExpr) tempNode;

		// Array Concatenation
		VarType tempType = (VarType) be.getType();
		if (tempType.isArray()) {
			List<IRStmt> masterStmtList = new ArrayList<IRStmt>();
			/* Store calls into temps */
			if (leftNode instanceof IRCall) {
				IRCall leftCall = (IRCall) leftNode;
				IRTemp tempLeftCall = new IRTemp("t" + tempCount++);
				IRMove moveLeftToTemp = new IRMove(tempLeftCall, leftCall);
				masterStmtList.add(moveLeftToTemp);
				leftNode = (IRExpr) tempLeftCall.copy();
			}
			if (rightNode instanceof IRCall) {
				IRCall rightCall = (IRCall) rightNode;
				IRTemp tempRightCall = new IRTemp("t" + tempCount++);
				IRMove moveRightToTemp = new IRMove(tempRightCall, rightCall);
				masterStmtList.add(moveRightToTemp);
				rightNode = (IRExpr) tempRightCall.copy();
			}
			
			// master statement list
			List<IRStmt> stmtList = new ArrayList<IRStmt>();
			
			// Left expression
			// Array literal
			if (leftNode instanceof IRESeq) {
				IRESeq leftESeq = (IRESeq) leftNode;
				stmtList.add(leftESeq.stmt());
				leftNode = leftESeq.expr();
			}
			
			// Right expression
			// Array literal
			if (rightNode instanceof IRESeq) {
				IRESeq rightESeq = (IRESeq) rightNode;
				stmtList.add(rightESeq.stmt());
				rightNode = rightESeq.expr();
			}
			IRBinOp leftLengthCell = new IRBinOp(OpType.SUB, (IRExpr) leftNode.copy(), (IRConst) WORD_SIZE.copy());
			IRBinOp rightLengthCell = new IRBinOp(OpType.SUB, (IRExpr) rightNode.copy(), (IRConst) WORD_SIZE.copy());
			
			IRMem leftMem = new IRMem(leftLengthCell);
			IRMem rightMem = new IRMem(rightLengthCell);
			IRBinOp newLength = new IRBinOp(OpType.ADD, leftMem, rightMem);
			IRTemp numEl = new IRTemp("t" + tempCount++);
			IRMove moveLengthToTemp = new IRMove(numEl, newLength);
			stmtList.add(moveLengthToTemp);
			
			IRBinOp realNewLength = new IRBinOp(OpType.ADD, (IRTemp) numEl.copy(), new IRConst(1));
			IRBinOp realrealNewLength = new IRBinOp(OpType.MUL, realNewLength, (IRConst) WORD_SIZE.copy());	// length in bytes
			
			/* leftNode/rightNode are either CALL/TEMP after this point */
			
			// allocate space for the concatenated array
			IRName nameOfAlloc = new IRName("_I_alloc_i");
			IRCall mallocCall = new IRCall(nameOfAlloc, realrealNewLength); // base address
			IRTemp tempOfArray = new IRTemp("t" + tempCount++);
			IRMove moveCallToArray = new IRMove(tempOfArray, mallocCall);
			stmtList.add(moveCallToArray);
			
			// move the length
			IRMove moveLength = new IRMove(new IRMem((IRTemp) tempOfArray.copy()), (IRTemp) numEl.copy());
			stmtList.add(moveLength);
			
			// update base address to a[0]
			IRBinOp offset = new IRBinOp(OpType.ADD, (IRTemp) tempOfArray.copy(), (IRConst) WORD_SIZE.copy());
			IRMove moveBaseUp = new IRMove((IRTemp) tempOfArray.copy(), offset); 
			stmtList.add(moveBaseUp);
			
			/* first while loop */
			// i:int = 0
			IRTemp firstLoopCounter = new IRTemp("t" + tempCount++);
			IRMove initializeFirstCounter = new IRMove(firstLoopCounter,new IRConst(0));
			IRLabel startOfLoop1 = new IRLabel("l"+labelCount++);

			IRBinOp loopCondition1 = new IRBinOp(OpType.LT, (IRTemp) firstLoopCounter.copy(), (IRMem) leftMem.copy());
			IRLabel trueLabel1 = new IRLabel("l"+labelCount++);
			IRLabel falseLabel1 = new IRLabel("l"+labelCount++);
			
			// while(i < length(e1))
			IRCJump firstWhileJump = new IRCJump(loopCondition1,trueLabel1.name(),falseLabel1.name());
			IRBinOp arrayOffset = new IRBinOp(OpType.MUL, (IRTemp) firstLoopCounter.copy(), (IRConst) WORD_SIZE.copy()); // [i]
			
			// newArray[i]
			IRBinOp newArrayAddr = new IRBinOp(OpType.ADD, (IRTemp) tempOfArray.copy(), arrayOffset);
			IRBinOp leftArrayOffset = new IRBinOp(OpType.MUL, (IRTemp) firstLoopCounter.copy(), (IRConst) WORD_SIZE.copy()); // [i]

			// leftArray[i]
			IRBinOp leftArrayAddr = new IRBinOp(OpType.ADD, (IRExpr) leftNode.copy(), leftArrayOffset);	// fresh
			IRMem newArrayElem = new IRMem(newArrayAddr);
			IRMem leftArrayElem = new IRMem(leftArrayAddr);
			
			// newArray[i] = leftArray[i]
			IRMove assignElem = new IRMove(newArrayElem, leftArrayElem);
			IRBinOp incrementValue = new IRBinOp(OpType.ADD, (IRTemp) firstLoopCounter.copy(), new IRConst(1));
			
			// i++
			IRMove incrementCounter = new IRMove((IRTemp) firstLoopCounter.copy(), incrementValue);
			IRJump jumpToStart = new IRJump(new IRName(startOfLoop1.name()));
			List<IRStmt> firstWhileLoop = Arrays.asList(
				initializeFirstCounter, startOfLoop1, firstWhileJump, trueLabel1,
				assignElem, incrementCounter, jumpToStart, falseLabel1
			);
			
			/* second while loop */
			// i = 0
			IRTemp secondLoopCounter = new IRTemp("t" + tempCount++);
			IRMove initializeSecondCounter = new IRMove(secondLoopCounter,new IRConst(0));
			IRLabel startOfLoop2 = new IRLabel("l"+labelCount++);

			IRBinOp loopCondition2 = new IRBinOp(OpType.LT, (IRTemp) secondLoopCounter.copy(), (IRMem) rightMem.copy());
			IRLabel trueLabel2 = new IRLabel("l"+labelCount++);
			IRLabel falseLabel2 = new IRLabel("l"+labelCount++);
			
			// while(i < length(e2))
			IRCJump secondWhileJump = new IRCJump(loopCondition2,trueLabel2.name(),falseLabel2.name());
			IRTemp arrayBaseOffset = new IRTemp("t" + tempCount++);

			IRMove assignArrayBaseOffset = new IRMove(arrayBaseOffset, (IRMem) leftMem.copy()); // length(e1)
			arrayOffset = new IRBinOp(OpType.ADD, (IRTemp) secondLoopCounter.copy(), (IRTemp) arrayBaseOffset.copy());	// i + length(e1)
			arrayOffset = new IRBinOp(OpType.MUL, arrayOffset, (IRConst) WORD_SIZE.copy());	// [i + length(e1)]		// fresh
			
			// newArray[i+length(e1)]
			newArrayAddr = new IRBinOp(OpType.ADD, (IRTemp) tempOfArray.copy(), arrayOffset);
			IRBinOp rightArrayOffset = new IRBinOp(OpType.MUL, (IRTemp) secondLoopCounter.copy(), (IRConst) WORD_SIZE.copy());

			// rightArray[i]
			IRBinOp rightArrayAddr = new IRBinOp(OpType.ADD, (IRExpr) rightNode.copy(), rightArrayOffset);
			newArrayElem = new IRMem(newArrayAddr);
			IRMem rightArrayElem = new IRMem(rightArrayAddr);
			
			// newArray[i+length(e1)] = rightArray[i]
			assignElem = new IRMove(newArrayElem, rightArrayElem);
			incrementValue = new IRBinOp(OpType.ADD, (IRTemp) secondLoopCounter.copy(), new IRConst(1));
			
			// i++
			incrementCounter = new IRMove((IRTemp) secondLoopCounter.copy(), incrementValue);
			jumpToStart = new IRJump(new IRName(startOfLoop2.name()));
			List<IRStmt> secondWhileLoop = Arrays.asList(
				initializeSecondCounter, assignArrayBaseOffset, 
				startOfLoop2, secondWhileJump, trueLabel2,
				assignElem, incrementCounter, jumpToStart, falseLabel2
			);
			
			stmtList.addAll(firstWhileLoop);
			stmtList.addAll(secondWhileLoop);

			if (masterStmtList.size() > 0) {
				masterStmtList.addAll(stmtList);
				tempNode = new IRESeq(new IRSeq(masterStmtList), (IRTemp) tempOfArray.copy());
			} else {
				tempNode = new IRESeq(new IRSeq(stmtList),(IRTemp) tempOfArray.copy());	
			}
			
			return;
		}
		
		OpType tempOp = null;
		
		switch(op){
		case PLUS: 
			tempOp = OpType.ADD; 	
			if (rightNode instanceof IRConst &&
					((IRConst) rightNode).value() > 2147483647) {
				IRTemp reg = new IRTemp("t" + tempCount++);
				IRMove moveConstToReg = new IRMove(reg, rightNode);
				IRBinOp result = new IRBinOp(tempOp, leftNode, (IRExpr) reg.copy());
				tempNode = new IRESeq(moveConstToReg, result);
				return;
			}
									break;
		case MINUS:
			tempOp = OpType.SUB; 	break;
		case TIMES:
			tempOp = OpType.MUL; 	break;
		case HIGH_MULT:
			tempOp = OpType.HMUL; 	break;
		case DIVIDE:
			tempOp = OpType.DIV;	break;
		case MODULO:
			tempOp = OpType.MOD;	break;
		case LT:
			tempOp = OpType.LT;		break;
		case LEQ:
			tempOp = OpType.LEQ;	break;
		case GT:
			tempOp = OpType.GT;		break;
		case GEQ:
			tempOp = OpType.GEQ;	break;
		case AND:
			tempOp = OpType.AND;
			List<IRStmt> stmts1 = new ArrayList<IRStmt>();
			IRTemp temp1 = new IRTemp("t" + tempCount++);
			IRMove moveFalseToTemp = new IRMove(temp1, new IRConst(0));
			stmts1.add(moveFalseToTemp);
			
			IRLabel tL = new IRLabel("l" + labelCount++);
			IRLabel fL = new IRLabel("l" + labelCount++);
			IRCJump jump = new IRCJump(leftNode, tL.name(), fL.name());
			stmts1.add(jump);
			stmts1.add(tL);
			
			IRMove moveRightToTemp = new IRMove((IRExpr) temp1.copy(), rightNode);
			stmts1.add(moveRightToTemp);
			stmts1.add(fL);
			
			IRSeq shortCircuit = new IRSeq(stmts1);
			tempNode = new IRESeq(shortCircuit, (IRExpr) temp1.copy());
			return;
		case OR:
			tempOp = OpType.OR;

			List<IRStmt> stmts2 = new ArrayList<IRStmt>();
			IRTemp temp2 = new IRTemp("t" + tempCount++);
			IRMove moveTrueToTemp = new IRMove(temp2, new IRConst(1));
			stmts2.add(moveTrueToTemp);
			
			IRLabel tL2 = new IRLabel("l" + labelCount++);
			IRLabel fL2 = new IRLabel("l" + labelCount++);
			IRCJump jump2 = new IRCJump(leftNode, tL2.name(), fL2.name());
			stmts2.add(jump2);
			stmts2.add(fL2);
			
			IRMove moveRightToTemp2 = new IRMove((IRExpr) temp2.copy(), rightNode);
			stmts2.add(moveRightToTemp2);
			stmts2.add(tL2);
			
			IRSeq shortCircuit2 = new IRSeq(stmts2);
			tempNode = new IRESeq(shortCircuit2, (IRExpr) temp2.copy());
			return;
		case EQUAL:
			tempOp = OpType.EQ;		break;
		case NOT_EQUAL:
			tempOp = OpType.NEQ;	break;
		}
		if (tempOp == OpType.SUB && rightNode instanceof IRConst) {
			long val = ((IRConst) rightNode).value();
			if (val != Long.MIN_VALUE) {
				rightNode = new IRConst(-val);
				tempOp = OpType.ADD;
			}
		}
		tempNode = new IRBinOp(tempOp, leftNode, rightNode);
	}

	/**
	 * sets tempNode to IRSeq
	 */
	@Override
	public void visit(BlockStmt bs) {
		int index = bs.getIndex();
		if (index == 0) {
			// Nothing
			tempNode = null;
		} else if (index == 1) {
			// List of stmts
			bs.getStmtList().accept(this);	// tempNode is set in here
		} else if (index == 2) {
			// List of stmts and return stmt
			// visit stmt list
			bs.getStmtList().accept(this);	// tempNode is set in here
			IRSeq stmtSeq = (IRSeq) tempNode;
			List<IRStmt> irStmtList = stmtSeq.stmts();
			
			// visit return stmt
			bs.getReturnStmt().accept(this);
			IRSeq returnSeq = (IRSeq) tempNode;	
			irStmtList.addAll(returnSeq.stmts());	// merge stmt seq and return seq
			tempNode = new IRSeq(irStmtList);
		} else if (index == 3){
			// Return stmt
			bs.getReturnStmt().accept(this);	// tempNode is set in here
		}
	}

	@Override
	public void visit(FunctionArg fa) {
		return;
	}

	/**
	 * f(a1,...,an) --IR--> CALL(NAME(f), e1,...,en)
	 */
	@Override
	public void visit(FunctionCall fc) {
		int index = fc.getIndex();
		
		boolean inEnv = false;
		if (index != 2) {
			inEnv = env.containsFun(fc.getIdentifier().toString());
		}

		if (index == 0) {									// f()
			IRCall call;
			if (inEnv) { 	// global function call with no args
				String id = fc.getABIName();
				IRName lf = new IRName(id);
				call = new IRCall(lf);
			} else {		// method call without dot with no args
				// Use this as the first argument
				IRMem dispatch = new IRMem(thisNode);
				IRDispatchVector dv = classToDispatch.get(classType.getClassName());
				int i = -1;
				for (String method : dv.getMethods()) {
					i++;
					if (method.equals(fc.getABIName())) {
						break;
					}
				}
				assert(i++ != -1);
				IRConst offset = new IRConst(i*8);
				IRBinOp offsetFromDispatch = new IRBinOp(OpType.ADD, dispatch, offset);
				IRMem label = new IRMem(offsetFromDispatch);
				call = new IRCall(label, thisNode);
			}
			call.setNumReturns(fc.getNumReturns());
			tempNode = call;
		} else if (index == 1) {							// f(a1,...,an) 
			// get function args
			FunctionArg functionArg = fc.getFunctionArg();
			List<Expr> args = functionArg.getArgExprs();
			List<IRExpr> irArgs = new ArrayList<IRExpr>();	
			for (Expr arg : args) {
				arg.accept(this);
				irArgs.add((IRExpr) tempNode);
			}
			IRCall call;
			if (inEnv) {	// global function call with args
				// get function label
				String id = fc.getABIName();
				IRName lf = new IRName(id);
				call = new IRCall(lf, irArgs);
			} else {		// method call without dot with args
				// Use this as the first argument
				irArgs.add(0, thisNode);
				IRMem dispatch = new IRMem(thisNode);
				IRDispatchVector dv = classToDispatch.get(classType.getClassName());
				int i = -1;
				for (String method : dv.getMethods()) {
					i++;
					if (method.equals(fc.getABIName())) {
						break;
					}
				}
				assert(i++ != -1);
				IRConst offset = new IRConst(i*8);
				IRBinOp offsetFromDispatch = new IRBinOp(OpType.ADD, dispatch, offset);
				IRMem label = new IRMem(offsetFromDispatch);
				call = new IRCall(label, irArgs);
			}
			call.setNumReturns(fc.getNumReturns());
			tempNode = call;
		} else if (index == 2){											// length(e)
			fc.getExpr().accept(this);
			IRExpr array = (IRExpr) tempNode;
			IRBinOp lengthAddr = new IRBinOp(OpType.SUB, array, (IRConst) WORD_SIZE.copy());
			tempNode = new IRMem(lengthAddr);
		} else if (index == 3) {										// method call with dot with no args
			// get function label
			String id = fc.getABIName();
			IRName lf = new IRName(id);
			// Use this as the first argument
			fc.getDotableExpr().accept(this);
			IRCall call = new IRCall(lf, (IRTemp) tempNode);
			call.setNumReturns(fc.getNumReturns());
			tempNode = call;
		} else {														// method call with dot with args
			assert(index == 4);
			// get function label
			String id = fc.getABIName();
			IRName lf = new IRName(id);
			
			// get function args
			FunctionArg functionArg = fc.getFunctionArg();
			List<Expr> args = functionArg.getArgExprs();
			List<IRExpr> irArgs = new ArrayList<IRExpr>();	
			// Use this as the first argument
			fc.getDotableExpr().accept(this);
			irArgs.add((IRTemp) tempNode);
			for (Expr arg : args) {
				arg.accept(this);
				irArgs.add((IRExpr) tempNode);
			}
			
			IRCall call = new IRCall(lf, irArgs);
			call.setNumReturns(fc.getNumReturns());
			tempNode = call;
		}
	}
	
	/**
	 * f(x1:t1,...,xn:tn):t' {s} --IR--> SEQ(LABEL(f), S[s])
	 */
	@Override
	public void visit(FunctionDecl fd) {
		// get function label
		String label = fd.getABIName();
		String name = fd.getIdentifier().toString();
		
		IRSeq seq = new IRSeq(new ArrayList<IRStmt>());
		List<String> holyParamList = fd.getParams();
		int start = 0;
		if (inClass) {
			// THIS is implicitly the first argument
			thisNode = new IRTemp(Configuration.ABSTRACT_ARG_PREFIX + 0);
			start = 1;
		}
		for (int i = start; i < holyParamList.size(); i++) {
			IRTemp paramTemp = new IRTemp(holyParamList.get(i));
			IRTemp argTemp = new IRTemp(Configuration.ABSTRACT_ARG_PREFIX + i);
			IRMove tempMove = new IRMove(paramTemp, argTemp);
			seq = LIRVisitor.combineTwoStmt(seq, tempMove);
		}
		
		// get statement sequence
		BlockStmt blockStmt = fd.getBlockStmt();
		blockStmt.accept(this);
		if (tempNode != null) {
			IRSeq irSeq = (IRSeq) tempNode;
			seq = LIRVisitor.combineTwoStmt(seq, irSeq);
		} else {
			seq = LIRVisitor.combineTwoStmt(seq, new IRReturn());
		}
		
		// create IRFuncDecl
		IRFuncDecl irFuncDecl = new IRFuncDecl(name, label, seq);
		irFuncDecl.setParamList(fd.getParams());
		irFuncDecl.setNumArgs(fd.getNumParams());
		irFuncDecl.setNumReturns(fd.getNumReturns());
		
		tempNode = irFuncDecl;
	}

	@Override
	public void visit(Identifier id) {
		String name = id.toString();
		if (env.containsVar(name)) {
			// id is a global variable, return a mem pointing to the global variable
			IRName irName = new IRName(globalNameToABI.get(name));
			tempNode = new IRMem(irName);
		} else {
			// Variable must be a field or local
			if (id.isField()) {
				String object = thisNode.name();
				IRDispatchVector dv = classToDispatch.get(object);
				List<String> fields = dv.getFields();
				// Find the field index (looking from back to front to get closest)
				VarType varType = classType.getFieldType(name);
				int i;
				for (i = fields.size()-1; i >= 0; i--) {
					VarType fieldType = classType.getFieldType(fields.get(i));
					if (fieldType.equals(varType)) {
						break;
					}
				}
				// Offset from object pointer to get field
				IRConst offset = new IRConst((i+1)*8);
				IRBinOp offsetFromObject = new IRBinOp(OpType.ADD, thisNode, offset);
				tempNode = new IRMem(offsetFromObject);
			} else {
				tempNode = new IRTemp(name);
			}
			
		}
	}

	@Override
	public void visit(IfStmt is) {
		IRLabel trueLabel = new IRLabel("l"+labelCount++);
		IRLabel falseLabel = new IRLabel("l"+labelCount++);
		IRStmt ifJump = controlFlow(is.getExpr(),trueLabel,falseLabel);
		if (is.getIndex() == 0) {
			// Only if
			is.getStmt1().accept(this);
			IRStmt stmt = (IRStmt) tempNode;
			tempNode = new IRSeq(ifJump,trueLabel,stmt,falseLabel);
		} else {
			// If, else
			String end = "l"+labelCount++;
			IRLabel endLabel = new IRLabel(end);
			IRJump endJump = new IRJump(new IRName(end));
			is.getStmt1().accept(this);
			IRStmt stmt1 = (IRStmt) tempNode;
			is.getStmt2().accept(this);
			IRStmt stmt2 = (IRStmt) tempNode;
			tempNode = new IRSeq(ifJump,trueLabel,stmt1,
					endJump,falseLabel,stmt2,endLabel);
		}
	}

	/**
	 * Should not be visited
	 */
	@Override
	public void visit(IndexedBrackets ib) {
		return;
	}

	@Override
	public void visit(Literal l) {
		int index = l.getIndex();
		/*
		 * 0 for int literal,
		 * 1 for string literal,
		 * 2 for character literal,
		 * 3 for boolean literal
		 */
		switch (index) {
		case 0:
			try {
				tempNode = new IRConst(Long.parseLong(l.getIntLit()));
			} catch (NumberFormatException e) {
				tempNode = new IRBinOp(OpType.ADD, new IRConst(Long.MAX_VALUE), new IRConst(1));
			}
			break;
		case 1:
			String stringLit = l.getStringLit();
			IRName addrOfAllocFunc = new IRName("_I_alloc_i");
			int length = stringLit.length();
			IRCall theIRCall = new IRCall(addrOfAllocFunc, new IRConst((length+1)*Configuration.WORD_SIZE));
			IRTemp tempOfArray = new IRTemp("t" + tempCount++);
			
			// Move allocated space pointer into tempOfArray
			IRMove moveCallIntoTemp = new IRMove(tempOfArray, theIRCall);
			IRMove moveLength = new IRMove(new IRMem((IRTemp) tempOfArray.copy()), new IRConst(length));
			
			// Initialize sequence of statements with moving allocated space into IRTemp and adding length
			List<IRStmt> stmtList = new ArrayList<IRStmt>();
			stmtList.add(moveCallIntoTemp);
			stmtList.add(moveLength);
			
			// For each char in stringLit, get the IRNode for it, and make a IRMove
			// node for the IRExpr into each cell
			for (int i = 0; i < length; i++) {
				IRConst character = new IRConst((long)stringLit.charAt(i));
				IRConst offSet = new IRConst((i+1)*Configuration.WORD_SIZE);
				IRBinOp offsetBinOp = new IRBinOp(OpType.ADD, offSet, (IRTemp) tempOfArray.copy());
				IRMem memOfCell = new IRMem(offsetBinOp);
				IRMove moveElementIntoCell = new IRMove(memOfCell, character);
				stmtList.add(moveElementIntoCell);
			}
			
			// Make array point to one cell after (since cell -1 should be length)
			IRBinOp offSetAndMem = new IRBinOp(OpType.ADD, (IRTemp) tempOfArray.copy(), (IRConst) WORD_SIZE.copy());
			IRMove moveOffsetAndMem = new IRMove((IRTemp) tempOfArray.copy(), offSetAndMem);
			stmtList.add(moveOffsetAndMem);
			IRSeq movingElementsIntoArray = new IRSeq(stmtList);
			// temp(a)
			IRESeq createStringLit = new IRESeq(movingElementsIntoArray, (IRTemp) tempOfArray.copy());
			tempNode = createStringLit;
			break;
			
		case 2:
			int character=-1;
			try {
				character = l.getCharLit().charAt(0);
			} catch (Exception e) {
				System.out.println("Expected a character stored in string");
			}
			tempNode = new IRConst(character);
			break;
		case 3:
			tempNode = l.getBoolLit()? new IRConst(1) : new IRConst(0);
			break;
		}
	}
	
	@Override
	public void visit(Program p) {
		// Create dispatch vectors for all class types
		Set<IRDispatchVector> dispatchVectors = new HashSet<IRDispatchVector>();
		for (ClassType ct : env.getClassTypes()) {
			List<String> methodTable = ct.getDispatchMethods(env);
			List<String> fieldTable = ct.getDispatchFields(env);
			IRDispatchVector dispatchVector = new IRDispatchVector(ct.getClassName(),
					methodTable, fieldTable);
			classToDispatch.put(ct.getClassName(), dispatchVector);
			dispatchVectors.add(dispatchVector);
		}

		Map<String, IRFuncDecl> functions = new HashMap<String, IRFuncDecl>();
		Set<IRGlobalVariable> globalVariables = new HashSet<IRGlobalVariable>();
		
		// Visit all declarations: class, function, global
		List<Decl> decls = p.getAllDecls();
		for (Decl d: decls) {
			d.accept(this);
			if (d instanceof FunctionDecl) {
				functions.put(((FunctionDecl) d).getABIName(), (IRFuncDecl) tempNode);
			} 
			else if (d instanceof GlobalDecl) {
				if (tempNode instanceof IRGVList) {
					IRGVList varView = (IRGVList) tempNode;
					globalVariables.addAll(varView.getGlobalVariables());
				}
				else if (tempNode instanceof IRGlobalVariable) {
					globalVariables.add((IRGlobalVariable) tempNode);
				}
				else {
					assert(false);
				}
			}
			else if (d instanceof ClassDecl) {
				IRFuncDeclList classBodyFuncDeclList = (IRFuncDeclList) tempNode;
				List<IRFuncDecl> funcDecls = classBodyFuncDeclList.getDecls();
				for (IRFuncDecl irfd : funcDecls) {
					functions.put(irfd.getABIName(), irfd);
				}
			}
			else {
				// Should have fallen through to another case previously
				assert(false);
			}
		}
		
		/***********WHAT WE USED TO HAVE******************/
//		List<FunctionDecl> funcs = p.getFunctionDecls();
//		for (FunctionDecl fd: funcs) {
//			fd.accept(this);
//			functions.put(fd.getABIName(), (IRFuncDecl) tempNode);
//		}
		program = new IRCompUnit("Program", functions, dispatchVectors, globalVariables);
	}

	@Override
	public void visit(ReturnStmt rs) {
		int index = rs.getIndex();
		if (index == 0) {
			// No return
			tempNode = new IRSeq(new IRReturn());
		} else {
			// At least 1 return
			List<Expr> exprList = rs.getListOfExpr();
			List<IRStmt> stmtList = new ArrayList<IRStmt>(); 
			
			// add all return values in _RET temp
			for (int i = 0; i < exprList.size(); i++) {
				exprList.get(i).accept(this);
				IRTemp ret = new IRTemp(Configuration.ABSTRACT_RET_PREFIX+i);
				IRMove irMove = new IRMove(ret, (IRExpr) tempNode);
				stmtList.add(irMove);
			}
			stmtList.add(new IRReturn());
			tempNode = new IRSeq(stmtList);
		}
	}

	@Override
	public void visit(Stmt s) {
		NakedStmt ns = s.getNakedStmt();
		ns.accept(this);
		if (ns instanceof FunctionCall) {
			tempNode = new IRExp((IRExpr) tempNode);
		}
	}

	@Override
	public void visit(StmtList sl) {
		List<Stmt> allStmts = sl.getAllStmt();
		List<IRStmt> allIRStmts = new ArrayList<IRStmt>();
		for (int i = 0; i < allStmts.size(); i++) {
			allStmts.get(i).accept(this);
			if (tempNode != null) {
				allIRStmts.add((IRStmt) tempNode);
			}
		}
		tempNode = new IRSeq(allIRStmts);
	}
	
	/**
	 * Dirties tempNode to IRCall or IRSeq
	 */
	@Override
	public void visit(TupleInit ti) {
		ti.getFunctionCall().accept(this);
		IRCall fCall = (IRCall) tempNode;
		boolean[] returnBoolList = fCall.getReturnBoolList();
		if (ti.getIndex() == 0) {
			// _ = f()
			assert(returnBoolList.length == 1);
			returnBoolList[0] = false;
			fCall.setReturnBoolList(returnBoolList);
			tempNode = new IRExp(fCall);
		} else {	
			// vd, tupleDeclList = f()
			IRTemp temp;
			IRExpr result;
			IRMove move;
			List<IRStmt> stmts = new ArrayList<IRStmt>();
			List<VarDecl> vdlist = ti.getVarDecls();
			boolean allUnderscore = true;
			for (int i = 0; i < vdlist.size(); i++) {
				VarDecl vd = vdlist.get(i);
				if (vd != null) {
					// Assign result of function call
					vd.getIdentifier().accept(this);
					temp = (IRTemp) tempNode;
					result = new IRTemp(Configuration.ABSTRACT_RET_PREFIX+i);
					move = new IRMove(temp,result);
					stmts.add(move);
					allUnderscore = false;
				} else { // _
					returnBoolList[i] = false;
				}
			}
			
			fCall.setReturnBoolList(returnBoolList);
			
			if (allUnderscore) {
				tempNode = new IRExp(fCall);
			} else {
				IRExp throwAway = new IRExp(fCall);
				stmts.add(0,throwAway);
				tempNode = new IRSeq(stmts);
			}
		}
	}

	@Override
	public void visit(UnaryExpr ue) {
		UnaryOp op = ue.getUnaryOp();
		ue.getExpr().accept(this);
		IRExpr expr = (IRExpr) tempNode;

		switch (op) {
		case INT_NEG: 
			tempNode = new IRBinOp(OpType.SUB, new IRConst(0), expr);
			break;
		case LOG_NEG: 
			tempNode = new IRBinOp(OpType.XOR, expr, new IRConst(1));
			break;
		}
	}

	@Override
	public void visit(UseId ui) {
		return;
	}

	@Override
	public void visit(VarDecl vd) {
		int vdIndex = vd.getIndex();
		
		// array declaration
		if (vdIndex == 0) {
			MixedArrayType mat = vd.getMixedArrayType();
			int index = mat.getIndex();
			// with a specified length
			if (index == 1 || index == 3) {		// x:t[e1]..[en][]*
				vd.getIdentifier().accept(this);
				IRTemp tempOfArray = (IRTemp) tempNode;
				
				List<Expr> exprList = mat.getMixedBrackets().getContent();
				// (stmt, expr) 
				// stmt: allocating,moving,assigning value stuff
				// expr: base address of the array
				// create a new arraylist for createArray
				List<IRStmt> preamble = new ArrayList<IRStmt>();
				List<IRTemp> sizes = new ArrayList<IRTemp>();
				for (int i = 0; i < exprList.size(); i++) {
					exprList.get(i).accept(this);
					IRTemp exprTemp = new IRTemp("t" + tempCount++);
					IRMove moveExprToTemp = new IRMove(exprTemp,(IRExpr) tempNode);
					sizes.add(exprTemp);
					preamble.add(moveExprToTemp);
				}
				IRESeq arrayGenesis = (IRESeq) createArray(0,sizes);
				List<IRStmt> preambleAndArray = new ArrayList<IRStmt>();
				preambleAndArray.addAll(preamble);
				preambleAndArray.addAll(((IRSeq) arrayGenesis.stmt()).stmts());
				IRSeq stmts = new IRSeq(preambleAndArray);
				arrayGenesis = new IRESeq(stmts,arrayGenesis.expr());
				IRMove moveAddrToTemp = new IRMove(tempOfArray,arrayGenesis.expr());
				IRSeq stmt = (IRSeq) arrayGenesis.stmt();
				List<IRStmt> newList = stmt.stmts();
				newList.add(moveAddrToTemp);
				tempNode = new IRSeq(newList);
			} else {
				IRTemp temp = new IRTemp("t" + tempCount++);
				IRMove moveDefault = new IRMove(temp, new IRConst(0));
				tempNode = moveDefault;
			}
		} else {
			// Primitive Type or Object Type
			IRTemp temp = new IRTemp("t" + tempCount++);
			IRMove moveDefault = new IRMove(temp, new IRConst(0));
			tempNode = moveDefault;
		}
		
		return;
	}
	
	/**
	 * Dirties tempNode to IRMove
	 */
	@Override
	public void visit(VarInit vi) {
		Identifier id = vi.getId();
		
		id.accept(this);
		IRExpr dest = (IRExpr) tempNode;
		
		vi.getExpr().accept(this);
		IRExpr e = (IRExpr) tempNode;
		
		tempNode = new IRMove(dest,e);
	}

	@Override
	public void visit(WhileStmt ws) {
		String start = "l"+labelCount++;
		IRLabel startOfLoop = new IRLabel(start);
		IRLabel trueLabel = new IRLabel("l"+labelCount++);
		IRLabel falseLabel = new IRLabel("l"+labelCount++);
		IRStmt cJumpNode = controlFlow(ws.getExpr(),trueLabel,falseLabel);
		
		// Update currentWhile as you enter and leave a while loop
		String oldWhileStart = currentWhileStart;
		String oldWhileEnd = currentWhileEnd;
		currentWhileStart = start;
		currentWhileEnd = falseLabel.name();
		startToEnd.put(currentWhileStart, currentWhileEnd);
		ws.getStmt().accept(this);
		currentWhileStart = oldWhileStart;
		currentWhileEnd = oldWhileEnd;
		
		IRStmt loopStmts = (IRStmt) tempNode;
		IRJump jumpToStart = new IRJump(new IRName(start));
		tempNode = new IRSeq(startOfLoop, cJumpNode, trueLabel, 
				loopStmts, jumpToStart, falseLabel);
	}

	/**
	 * IndexedBrackets should represent the recursive case, where
	 * there are indices to be put into a IRMem and IRBinOp node.
	 * 
	 * @param ib is the rest of the indexed brackets to parse
	 * @param ire is the base address
	 * @return an IRExpr that points to the memory location pointed
	 * from IRExpr and IndexedBrackets
	 */
	
	private IRExpr createIRExprForBrackets(IRExpr base, IndexedBrackets ib) {
		List<IRStmt> stmtList = new ArrayList<IRStmt>();
		// a[i0][i1]...[in]
		// mem(mem(a + 8*i0) + 8*i1) +... 
		List<Expr> exprList = ib.getAllExprInIndexedBrackets();
		IRExpr arrayElem = base;
		IRTemp baseTemp = new IRTemp("t" + tempCount++);
		IRMove moveBaseToTemp = new IRMove(baseTemp, arrayElem);
		stmtList.add(moveBaseToTemp);
		
		for (int i = 0; i < exprList.size(); i++) {
			exprList.get(i).accept(this);
			IRExpr exprInBrackets = (IRExpr)tempNode;
			IRTemp tempForExprInBrackets = new IRTemp("t" + tempCount++);
			// move expr in brackets to a temp register
			IRMove moveToTemp = new IRMove(tempForExprInBrackets, exprInBrackets);
			stmtList.add(moveToTemp);
			// create array index out of bounds check stmts
			IRStmt boundsCheck = createIRStmtForArrayBoundCheck((IRTemp) baseTemp.copy(), 
					(IRExpr)tempForExprInBrackets.copy());
			stmtList.add(boundsCheck);
			IRBinOp offset = new IRBinOp(OpType.MUL, (IRConst) WORD_SIZE.copy(), (IRExpr)tempForExprInBrackets.copy());
			IRBinOp arrayElemAddr = new IRBinOp(OpType.ADD, (IRExpr) baseTemp.copy(), offset);
			arrayElem = new IRMem(arrayElemAddr);
			baseTemp = new IRTemp("t" + tempCount++);
			IRMove moveToNewBaseTemp = new IRMove(baseTemp, arrayElem);
			stmtList.add(moveToNewBaseTemp);
		}
		
		IRSeq stmtSeq = new IRSeq(stmtList);
		return new IRESeq(stmtSeq, (IRExpr) arrayElem.copy());	
	}
	
	/**
	 * Creates an IRStmt that performs array index out of bound check
	 * 
	 * a[i]
	 * @param baseAddr(a) is the base address of the array
	 * @param arrayIndex(i) is the index of the array in which you are trying to access 
	 * @return IRStmt that performs array index out of bound check
	 */
	private IRStmt createIRStmtForArrayBoundCheck(IRTemp baseAddr, IRExpr arrayIndex) {
		// master stmt list
		List<IRStmt> stmtList = new ArrayList<IRStmt>();
		
		/* Get array length */
		IRTemp length = new IRTemp("t" + tempCount++);
		IRBinOp arrayLengthAddr = new IRBinOp(OpType.SUB, baseAddr, (IRConst) WORD_SIZE.copy());
		IRMem arrayLength = new IRMem(arrayLengthAddr);
		IRMove moveLength = new IRMove(length, arrayLength);
		stmtList.add(moveLength);

		/* Check bounds */
		// i < 0
		IRLabel label1 = new IRLabel("l"+labelCount++);
		IRLabel label2 = new IRLabel("l"+labelCount++);
		IRConst zero = new IRConst(0);
		IRBinOp lessThanZero = new IRBinOp(OpType.LT, arrayIndex, zero);
		IRCJump cond1 = new IRCJump(lessThanZero, label2.name(), label1.name()); 
		stmtList.add(cond1);
		stmtList.add(label1);
		// i < length
		IRLabel label3 = new IRLabel("l"+labelCount++);
		arrayIndex = (IRExpr)arrayIndex.copy();
		IRBinOp lessThanLength = new IRBinOp(OpType.LT, arrayIndex, (IRTemp) length.copy());
		IRCJump cond2 = new IRCJump(lessThanLength, label3.name(), label2.name());
		stmtList.add(cond2);
		stmtList.add(label2);
		
		/* Call _I_outOfBounds_p */
		IRName nameOfOob = new IRName("_I_outOfBounds_p");
		IRCall oobCall = new IRCall(nameOfOob);
		IRExp oobExp = new IRExp(oobCall);
		stmtList.add(oobExp);
		IRReturn ret = new IRReturn();
		stmtList.add(ret);
		stmtList.add(label3);
		
		return new IRSeq(stmtList);
	}
	
	/**
	 * Creates and returns an IRStmt that corresponds to the control flow given.
	 * 
	 * May dirty tempNode to IRExpr
	 * 
	 * @param e	the expression to evaluate, must be boolean
	 * @param t the IR label to jump to if e evaluates to true
	 * @param f the IR label to jump to if e evaluates to false
	 * @return	an IR statement that gives the execution of the control flow
	 */
	private IRStmt controlFlow(Expr e, IRLabel t, IRLabel f) {
		if (e instanceof Literal) {
			// true or false
			Literal e1 = (Literal) e;
			if (e1.getBoolLit()) {
				return new IRJump(new IRName(t.name()));
			} else {
				return new IRJump(new IRName(f.name()));
			}
		} else if (e instanceof UnaryExpr) {			// not reachable
			// !e
			UnaryExpr e1 = (UnaryExpr) e;
			Expr e2 = e1.getExpr();
			return controlFlow(e2, f, t);
		} else if (e instanceof BinaryExpr) {
			// e1 == e2, e1 != e2, e1 & e2, e1 | e2, e1 < e2, e1 > e2
			BinaryExpr e1 = (BinaryExpr) e;
			BinaryOp op = e1.getBinaryOp();
			if (op == BinaryOp.AND) {
				IRLabel trueLabel = new IRLabel("l"+labelCount++);
				IRStmt c1 = controlFlow(e1.getLeftExpr(),trueLabel,f);
				IRStmt c2 = controlFlow(e1.getRightExpr(),t,f);
				return new IRSeq(c1,trueLabel,c2);
			} else if (op == BinaryOp.OR) {
				IRLabel falseLabel = new IRLabel("l"+labelCount++);
				IRStmt c1 = controlFlow(e1.getLeftExpr(),t,falseLabel);
				IRStmt c2 = controlFlow(e1.getRightExpr(),t,f);
				return new IRSeq(c1,falseLabel,c2);
			} else {
				e.accept(this);
				IRExpr e2 = (IRExpr) tempNode;
				return new IRCJump(e2,t.name(),f.name());
			}
		} else {
			// x, a[i], f() 
			e.accept(this);
			IRExpr e2 = (IRExpr) tempNode;
			return new IRCJump(e2,t.name(),f.name());
		}		
	}
	
	/**
	 * Helper function to create an array
	 * 
	 * @param sizes		list of the contents inside the brackets that represents the array dim
	 * @param index		the current bracket of varDecl that we're currently allocating memory for
 	 * 		ex: x: int[5][6][] -> 
 	 * 			first call would be createArray(exprList, 0) 
 	 * 			second call would be createArray(exprList, 1)
 	 * 			third: reaches base case because exprList.size == 2 and index == 2
	 */
	private IRExpr createArray(int index, List<IRTemp> sizes) {		
		// Base case
		if (index == sizes.size()) {
			IRMove move0IntoTemp = new IRMove(new IRTemp("t" + tempCount++), new IRConst(0));
			IRSeq tempSeq = new IRSeq(move0IntoTemp);
			IRESeq tempESeq = new IRESeq(tempSeq,new IRConst(0));
			return tempESeq;
		}
		List<IRStmt> stmts = new ArrayList<IRStmt>();
		
		// Make call to malloc for exprList.get(index)
		IRName nameOfAlloc = new IRName("_I_alloc_i");
		IRTemp lengthTemp = sizes.get(index);
		IRBinOp sizeOfArray = new IRBinOp(OpType.ADD, (IRTemp) lengthTemp.copy(), new IRConst(1));	//add 1 to store length of array
		sizeOfArray = new IRBinOp(OpType.MUL, sizeOfArray, (IRConst) WORD_SIZE.copy());
	
		IRCall callToAlloc = new IRCall(nameOfAlloc,sizeOfArray);
		IRTemp freshArray = new IRTemp("t"+tempCount++);
		IRMove moveAddrToTemp = new IRMove(freshArray,callToAlloc);
		stmts.add(moveAddrToTemp);
		
		// insert length
		IRMove moveLength = new IRMove(new IRMem((IRTemp) freshArray.copy()), (IRTemp) lengthTemp.copy());
		stmts.add(moveLength);
		
		// move base address up
		IRBinOp arrayBase = new IRBinOp(OpType.ADD,(IRTemp) freshArray.copy(),(IRConst) WORD_SIZE.copy());
		IRMove moveBaseUp = new IRMove((IRTemp) freshArray.copy(),arrayBase);
		stmts.add(moveBaseUp);
		
		// while loop
		IRTemp loopCounter = new IRTemp("t" + tempCount++);
		IRMove initializeCounter = new IRMove(loopCounter, new IRConst(0));
	
		IRLabel startOfLoop = new IRLabel("l" + labelCount++);
		
		IRBinOp loopCondition = new IRBinOp(OpType.LT, (IRTemp) loopCounter.copy(), (IRTemp) lengthTemp.copy());
		IRLabel trueLabel = new IRLabel("l" + labelCount++);
		IRLabel falseLabel = new IRLabel("l" + labelCount++);
		IRCJump whileJump = new IRCJump(loopCondition, trueLabel.name(), falseLabel.name());
		IRBinOp baseOffset = new IRBinOp(OpType.MUL, (IRTemp) loopCounter.copy(), (IRConst) WORD_SIZE.copy());
		IRBinOp currAddr = new IRBinOp(OpType.ADD, (IRTemp) freshArray.copy(), baseOffset);
		IRMove assignValue = new IRMove(new IRMem(currAddr), createArray(index+1,sizes));
		IRBinOp incrementedValue = new IRBinOp(OpType.ADD, (IRTemp) loopCounter.copy(), new IRConst(1));
		IRMove incrementCounter = new IRMove((IRTemp) loopCounter.copy(), incrementedValue);
		IRJump jumpToStart = new IRJump(new IRName(startOfLoop.name()));
		List<IRStmt> whileLoop = Arrays.asList(initializeCounter, startOfLoop, whileJump,
				trueLabel, assignValue, incrementCounter, jumpToStart, falseLabel);
		stmts.addAll(whileLoop);
		return new IRESeq(new IRSeq(stmts), (IRTemp) freshArray.copy());
	}

	@Override
	public void visit(Break br) {
		String label;
		if (br.hasLabel()) {
			label = startToEnd.get(br.getLabel());
		} else {
			label = currentWhileEnd;
		}
		tempNode = new IRJump(new IRName(label));
	}

	@Override
	public void visit(ClassBody cb) {
		// Don't need to visit fields
		
		// Visit method declarations
		
		IRFuncDeclList tempListNode = new IRFuncDeclList();
		
		for (FunctionDecl fd : cb.getMethods()) {
			fd.accept(this);
			tempListNode.addDecl((IRFuncDecl) tempNode);
		}
		tempNode = tempListNode;
	}

	@Override
	public void visit(ClassDecl cd) {
		inClass = true;
		classType = env.getClassType(cd.getClassName().toString());
		cd.getClassBody().accept(this);
		inClass = false;
		classType = null;
	}

	@Override
	public void visit(Continue cn) {
		String label;
		if (cn.hasLabel()) {
			label = cn.getLabel();
		} else {
			label = currentWhileStart;
		}
		tempNode = new IRJump(new IRName(label));
	}

	@Override
	public void visit(DotableExpr de) {
		switch (de.getType()) {
		case DOT:
			de.getDotableExpr().accept(this);
			thisNode = (IRTemp) tempNode;
			de.getId().accept(this);
			break;
		case FUNCTION_CALL:
			de.getFunctionCall().accept(this);
			break;
		case IDENTIFIER:
			de.getId().accept(this);
			break;
		case NEW:
			List<IRStmt> list = new ArrayList<IRStmt>();
			
			// Allocate space for the object
			IRName nameOfAlloc = new IRName("_I_alloc_i");
			String id = de.getId().toString();
			IRDispatchVector dv = classToDispatch.get(id);
			List<String> fields = dv.getFields();
			long size = fields.size() + 1;
			IRConst sizeOfObject = new IRConst(size*8);
			IRCall callToAlloc = new IRCall(nameOfAlloc, sizeOfObject);
			
			// Move result to temp
			IRTemp object = new IRTemp("t"+tempCount++);
			IRMove moveResultToObject = new IRMove(object, callToAlloc);
			list.add(moveResultToObject);
			
			// Initialize pointers
				// 0: points to dispatch vector
				// primitives & uninitialized arrays & objects: 0/null
				// initialized arrays: points to array with null values
			IRName dvName = new IRName("_I_vt_" + id);
			IRMove moveDVToObject = new IRMove(new IRMem(object), dvName);
			list.add(moveDVToObject);
			ClassType type = env.getClassType(id);
			for (int i = 0; i < fields.size(); i++) {
				VarType vtype = type.getFieldType(fields.get(i));
				if (vtype.isPrimitive() || vtype.isObject()) {
					// Do nothing if alloc initializes to 0
					continue;
				} else if (vtype.isArray()) {
					List<Expr> exprList = vtype.getExprs();
					if (exprList.size() == 0) {
						// Do nothing if alloc initializes to 0
						continue;
					}
					// Initialize array with length
					List<IRTemp> sizes = new ArrayList<IRTemp>();
					for (Expr e : exprList) {
						e.accept(this);
						IRTemp exprTemp = new IRTemp("t" + tempCount++);
						IRMove moveExprToTemp = new IRMove(exprTemp,(IRExpr) tempNode);
						sizes.add(exprTemp);
						list.add(moveExprToTemp);
					}
					IRESeq arrayGenesis = (IRESeq) createArray(0, sizes);
					list.addAll(((IRSeq) arrayGenesis.stmt()).stmts());
					
					// Move array pointer into object table at field offset
					IRExpr array = arrayGenesis.expr();
					IRBinOp offset = new IRBinOp(OpType.ADD, object, new IRConst((i+1)*8));
					IRMem fieldAddr = new IRMem(offset);
					IRMove moveArrayToFieldAddr = new IRMove(fieldAddr, array);
					list.add(moveArrayToFieldAddr);
				} else {
					System.out.println("VarType has a weird type");
					assert(false);
				}
			}
			IRSeq seq = new IRSeq(list);
			IRESeq creatingObject = new IRESeq(seq, object);
			tempNode = creatingObject;
			break;
		case ARRAY:
			de.getArrayElement().accept(this);
			break;
		case THIS:
			tempNode = thisNode;
			break;
		}
	}

	@Override
	public void visit(GlobalDecl gd) {
		switch (gd.getType()) {
		case SHORT_TUPLE_DECL:
			ShortTupleDecl std = gd.getShortTupleDecl();
			List<IRGlobalVariable> list = new ArrayList<IRGlobalVariable>();
			Type type = std.getType();
			for (Identifier i : std.getAllIdentifiers()) {
				String name = i.toString();
				VarType vType = env.getVarType(name);
				IRGlobalVariable gv;
				String ABIName;
				if (type instanceof MixedArrayType) {
					List<Expr> exprList = ((MixedArrayType) type).getAllExprs();
					List<IRTemp> sizes = new ArrayList<IRTemp>();
					List<IRStmt> stmts = new ArrayList<IRStmt>();
					for (int ii = 0; ii < exprList.size(); ii++) {
						exprList.get(ii).accept(this);
						IRTemp exprTemp = new IRTemp("t" + tempCount++);
						IRMove moveExprToTemp = new IRMove(exprTemp,(IRExpr) tempNode);
						sizes.add(exprTemp);
						stmts.add(moveExprToTemp);
					}
					IRESeq array = (IRESeq) createArray(0, sizes);
					stmts.addAll(0, ((IRSeq) array.stmt()).stmts());
					ABIName = translateVarTypeToABI(vType, name);
					gv = new IRGlobalVariable(name, ABIName, 
											((IRConst) array.expr()).value(),
											new IRSeq(stmts));
				} else {
					ABIName = translateVarTypeToABI(vType, name);
					gv = new IRGlobalVariable(name, ABIName, 0);
				}
				globalNameToABI.put(name, ABIName);
				list.add(gv);
			}
			tempNode = new IRGVList(list);
			return;
		case SIMPLE_VAR_INIT:
			SimpleVarInit svi = gd.getSimpleVarInit();
			String name = svi.getIdentifier().toString();
			VarType vType = env.getVarType(name);
			svi.getLiteral().accept(this);
			long value;
			if (tempNode instanceof IRBinOp) {
				value = Long.MIN_VALUE;
			} else {
				assert(tempNode instanceof IRConst);
				value = ((IRConst) tempNode).value();
			}
			String ABIName = translateVarTypeToABI(vType, name);
			tempNode = new IRGlobalVariable(name, ABIName, value);
			globalNameToABI.put(name, ABIName);
			return;
		case VAR_DECL:
			VarDecl vd = gd.getVarDecl();
			int index = vd.getIndex();
			String id = vd.getIdentifier().toString();
			VarType type1 = env.getVarType(id);
			String ABIName1;
			if (index == 0) {
				// vd is mixed array type (x[5][])
				List<Expr> exprList = vd.getMixedArrayType().getAllExprs();
				List<IRTemp> sizes = new ArrayList<IRTemp>();
				List<IRStmt> stmts = new ArrayList<IRStmt>();
				for (int ii = 0; ii < exprList.size(); ii++) {
					exprList.get(ii).accept(this);
					IRTemp exprTemp = new IRTemp("t" + tempCount++);
					IRMove moveExprToTemp = new IRMove(exprTemp,(IRExpr) tempNode);
					sizes.add(exprTemp);
					stmts.add(moveExprToTemp);
				}
				IRESeq array = (IRESeq) createArray(0, sizes);
				stmts.addAll(0, ((IRSeq) array.stmt()).stmts());
				ABIName1 = translateVarTypeToABI(type1, id);
				tempNode = new IRGlobalVariable(id, ABIName1, 
											((IRConst) array.expr()).value());
			} else {
				// vd is primitive or class type
				ABIName1 = translateVarTypeToABI(type1, id);
				tempNode = new IRGlobalVariable(id, ABIName1, 0);
			}
			globalNameToABI.put(id, ABIName1);
			return;
		}		
	}
	
	public String translateVarTypeToABI(VarType t, String globalName) {
		String ABIString = "_I_g_" + globalName + "_";
		if (t.isArray()) {							// array
			int numBrackets = t.getNumBrackets();
			for (int i = 0; i < numBrackets; i++) {
				ABIString += "a";
			}
			ABIString += t.getIsBool() ? "b" : "i";
		} else if (t.getIsInt()) {						// int
			ABIString += "i";
		} else if (t.getIsBool())  {					// bool
			ABIString += "b";
		} else {
			String className = t.getElementType();
			int len =  className.length();
			ABIString += "o" + len + className;
		}
		return ABIString;
	}

	@Override
	public void visit(ShortTupleDecl std) {
		Type type = std.getType();
		IRExpr src = null;
		if (type instanceof PrimitiveType) {
			src = new IRConst(0);
		} else if (type instanceof Identifier) {
			src = new IRConst(0);	// null = 0?
		}
		for (Identifier id : std.getAllIdentifiers()) {	
			id.accept(this);
			IRExpr dest = (IRExpr) tempNode;
			tempNode = new IRMove(dest, src);
		}
	}

	// Probably don't need
	@Override
	public void visit(FieldDecl fieldDecl) {
//		switch (fieldDecl.getType()) {
//		case SHORT_TUPLE_DECL:
//			fieldDecl.getShortTupleDecl().accept(this);
//			break;
//		case VAR_DECL:
//			fieldDecl.getVarDecl().accept(this);
//			break;
//		}
	}
	
	@Override
	public void visit(Null n) {
		tempNode = new IRConst(0);
	}

	// Probably don't need
	@Override
	public void visit(SimpleVarInit svi) {
//		Identifier id = svi.getIdentifier();
//		
//		id.accept(this);
//		IRExpr dest = (IRExpr) tempNode;
//		
//		svi.getLiteral().accept(this);
//		IRExpr e = (IRExpr) tempNode;
//		
//		tempNode = new IRMove(dest,e);
	}
}