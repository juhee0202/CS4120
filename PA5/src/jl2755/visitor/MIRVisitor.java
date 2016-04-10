package jl2755.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.OpType;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;
import jl2755.ast.*;
import jl2755.type.VarType;

public class MIRVisitor implements ASTVisitor{
	
	private IRNode tempNode;
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private int labelCount = 0;
	private int tempCount = 0;
	public IRNode program;
	
	@Override
	public void visit(ArrayElement ae) {
		int index = ae.getIndex();

		// 0: identifier with indexedBrackets
		if (index == 0) {
			ae.getIdentifier().accept(this);
			IRExpr identifierIR = (IRExpr) tempNode;
			tempNode = new IRMem(createIRExprForBrackets(identifierIR, ae.getIndexedBrackets()));
		}
		// 1: functionCall with indexedBrackets
		else if (index == 1) {
			ae.getFunctionCall().accept(this);
			tempNode = new IRMem(createIRExprForBrackets((IRExpr) tempNode, ae.getIndexedBrackets()));
		}
		// 2: arrayLiteral with IndexedBrackets
		else {
			ae.getArrayLiteral().accept(this);
			tempNode = new IRMem(createIRExprForBrackets((IRExpr) tempNode, ae.getIndexedBrackets()));
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
		IRTemp tempOfArray2 = new IRTemp(tempOfArray.name());
		IRMem memOfArray = new IRMem(tempOfArray2);
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
			IRTemp tempOfArray3 = new IRTemp(tempOfArray.name());
			IRBinOp offsetBinOp = new IRBinOp(OpType.ADD, offSet, tempOfArray3);
			IRMem memOfCell = new IRMem(offsetBinOp);
			IRMove moveElementIntoCell = new IRMove(memOfCell, (IRExpr) tempNode);
			stmtList.add(moveElementIntoCell);
		}
		
		// Make array point to one cell after (since cell -1 should be length)
		IRConst oneOffset = new IRConst(Configuration.WORD_SIZE);
		IRTemp tempOfArray4 = new IRTemp(tempOfArray.name());
		IRBinOp offSetAndMem = new IRBinOp(OpType.ADD, tempOfArray4, oneOffset);
		IRTemp tempOfArray5 = new IRTemp(tempOfArray.name());
		IRMove moveOffsetAndMem = new IRMove(tempOfArray5, offSetAndMem);
		stmtList.add(moveOffsetAndMem);
		IRSeq movingElementsIntoArray = new IRSeq(stmtList);
		// temp(a)
		IRTemp tempOfArray6 = new IRTemp(tempOfArray.name());
		IRESeq createArrayLit = new IRESeq(movingElementsIntoArray, tempOfArray6);
		tempNode = createArrayLit;
	}

	@Override
	public void visit(ArrayLiteral al) {
		al.getArrElemList().accept(this);
	}

	@Override
	public void visit(AssignmentStmt as) {
		int index = as.getIndex();
		if (index == 0) {
			// x = 0
			as.getIdentifier().accept(this);
			IRExpr tempID = (IRExpr) tempNode;
			as.getExpr().accept(this);
			IRExpr tempExpr = (IRExpr) tempNode;
			tempNode = new IRMove(tempID, tempExpr);
		} else if (index == 1) {
			// a[i][j]...[z] = 0
			as.getIdentifier().accept(this);
			IRTemp base = (IRTemp) tempNode;
			IndexedBrackets ib = as.getIndexedBrackets();
			IRBinOp addr = (IRBinOp) createIRExprForBrackets(base,ib);
			IRMem mem = new IRMem(addr);
			as.getExpr().accept(this);
			IRExpr e = (IRExpr) tempNode;
			tempNode = new IRMove(mem,e);
		} else {
			// f()[i][j]...[z] = 0
			as.getFunctionCall().accept(this);
			IRCall fCall = (IRCall) tempNode;
			IndexedBrackets ib = as.getIndexedBrackets();
			IRMem base = new IRMem(fCall);
			IRBinOp addr = (IRBinOp) createIRExprForBrackets(base,ib);
			IRMem mem = new IRMem(addr);
			as.getExpr().accept(this);
			IRExpr e = (IRExpr) tempNode;
			tempNode = new IRMove(mem,e);
		}
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
			// master statement list
			List<IRStmt> stmtList = new ArrayList<IRStmt>();
			
			// Left expression
			// Array literal
			if (leftNode instanceof IRESeq) {
				IRESeq leftESeq = (IRESeq) leftNode;
				stmtList.add(leftESeq.stmt());
				leftNode = leftESeq.expr();
			}
			IRConst oneCell = new IRConst(Configuration.WORD_SIZE);
			IRBinOp leftLengthCell = new IRBinOp(OpType.SUB, leftNode, oneCell);
			
			// Right expression
			// Array literal
			if (rightNode instanceof IRESeq) {
				IRESeq rightESeq = (IRESeq) rightNode;
				stmtList.add(rightESeq.stmt());
				rightNode = rightESeq.expr();
			}
			IRConst oneCell2 = new IRConst(Configuration.WORD_SIZE);
			IRBinOp rightLengthCell = new IRBinOp(OpType.SUB, rightNode, oneCell2);
			
			IRBinOp newLength = new IRBinOp(OpType.ADD, new IRMem(leftLengthCell), new IRMem(rightLengthCell));	// fresh
			if (leftNode instanceof IRTemp) {
				leftNode = new IRTemp(((IRTemp) leftNode).name());
			}
			if (rightNode instanceof IRTemp) {
				rightNode = new IRTemp(((IRTemp) rightNode).name());
			}
			IRConst oneCell3 = new IRConst(Configuration.WORD_SIZE);
			IRBinOp leftLengthCell2 = new IRBinOp(OpType.SUB, leftNode, oneCell3);
			IRConst oneCell4 = new IRConst(Configuration.WORD_SIZE);
			IRBinOp rightLengthCell2 = new IRBinOp(OpType.SUB, rightNode, oneCell4);
			IRBinOp newLength2 = new IRBinOp(OpType.ADD, new IRMem(leftLengthCell2), new IRMem(rightLengthCell2));
			IRBinOp realNewLength = new IRBinOp(OpType.ADD, newLength2, new IRConst(1));
			IRBinOp realrealNewLength = new IRBinOp(OpType.MUL, realNewLength, new IRConst(Configuration.WORD_SIZE));	// length in bytes	// fresh
			
			/* leftNode/rightNode are either CALL/TEMP after this point */
			
			// allocate space for the concatenated array
			IRName nameOfAlloc = new IRName("_I_alloc_i");
			IRCall mallocCall = new IRCall(nameOfAlloc, realrealNewLength); // base address
			IRTemp tempOfArray = new IRTemp("t" + tempCount++);
			IRMove moveCallToArray = new IRMove(tempOfArray, mallocCall);
			stmtList.add(moveCallToArray);
			
			// move the length
			IRTemp tempOfArray2 = new IRTemp(tempOfArray.name());
			IRMove moveLength = new IRMove(new IRMem(tempOfArray2), newLength);
			stmtList.add(moveLength);
			
			// update base address to a[0]
			IRTemp tempOfArray3 = new IRTemp(tempOfArray.name());
			IRBinOp offset = new IRBinOp(OpType.ADD, tempOfArray3, new IRConst(Configuration.WORD_SIZE));
			IRTemp tempOfArray4 = new IRTemp(tempOfArray.name());
			IRMove moveBaseUp = new IRMove(tempOfArray4, offset); 
			stmtList.add(moveBaseUp);
			
			/* first while loop */
			// i:int = 0
			IRTemp firstLoopCounter = new IRTemp("t" + tempCount++);
			IRMove initializeFirstCounter = new IRMove(firstLoopCounter,new IRConst(0));
			IRLabel startOfLoop1 = new IRLabel("l"+labelCount++);
			IRTemp firstLoopCounter2 = new IRTemp(firstLoopCounter.name());
			// TODO: create fresh leftLengthCell
			if (leftNode instanceof IRTemp) {
				leftNode = new IRTemp(((IRTemp) leftNode).name());
			}
			IRBinOp leftLengthCell3 = new IRBinOp(OpType.SUB, leftNode, new IRConst(Configuration.WORD_SIZE));
			IRBinOp loopCondition1 = new IRBinOp(OpType.LT, firstLoopCounter2, new IRMem(leftLengthCell3));
			IRLabel trueLabel1 = new IRLabel("l"+labelCount++);
			IRLabel falseLabel1 = new IRLabel("l"+labelCount++);
			// while(i < length(e1))
			IRCJump firstWhileJump = new IRCJump(loopCondition1,trueLabel1.name(),falseLabel1.name());
			IRTemp firstLoopCounter3 = new IRTemp(firstLoopCounter.name());
			IRBinOp arrayOffset = new IRBinOp(OpType.MUL, firstLoopCounter3, new IRConst(Configuration.WORD_SIZE)); // [i]
			IRTemp tempOfArray5 = new IRTemp(tempOfArray.name());
			// newArray[i]
			IRBinOp newArrayAddr = new IRBinOp(OpType.ADD, tempOfArray5, arrayOffset);
			IRTemp firstLoopCounter4 = new IRTemp(firstLoopCounter.name());
			IRBinOp leftArrayOffset = new IRBinOp(OpType.MUL, firstLoopCounter4, new IRConst(Configuration.WORD_SIZE)); // [i]
			if (leftNode instanceof IRTemp) {
				leftNode = new IRTemp(((IRTemp) leftNode).name());
			}
			// leftArray[i]
			IRBinOp leftArrayAddr = new IRBinOp(OpType.ADD, leftNode, leftArrayOffset);	// fresh
			IRMem newArrayElem = new IRMem(newArrayAddr);
			IRMem leftArrayElem = new IRMem(leftArrayAddr);
			// newArray[i] = leftArray[i]
			IRMove assignElem = new IRMove(newArrayElem, leftArrayElem);
			IRTemp firstLoopCounter5 = new IRTemp(firstLoopCounter.name());
			IRBinOp incrementValue = new IRBinOp(OpType.ADD, firstLoopCounter5, new IRConst(1));
			IRTemp firstLoopCounter6 = new IRTemp(firstLoopCounter.name());
			// i++
			IRMove incrementCounter = new IRMove(firstLoopCounter6, incrementValue);
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
			IRTemp secondLoopCounter2 = new IRTemp(secondLoopCounter.name());
			if (rightNode instanceof IRTemp) {
				rightNode = new IRTemp(((IRTemp) rightNode).name());
			}
			IRConst oneCell5 = new IRConst(Configuration.WORD_SIZE);
			IRBinOp rightLengthCell3 = new IRBinOp(OpType.SUB, rightNode, oneCell5);
			IRBinOp loopCondition2 = new IRBinOp(OpType.LT, secondLoopCounter2, new IRMem(rightLengthCell3));	// fresh
			IRLabel trueLabel2 = new IRLabel("l"+labelCount++);
			IRLabel falseLabel2 = new IRLabel("l"+labelCount++);
			// while(i < length(e2))
			IRCJump secondWhileJump = new IRCJump(loopCondition2,trueLabel2.name(),falseLabel2.name());
			// TODO: duplicate BinOp?
			IRTemp secondLoopCounter3 = new IRTemp(secondLoopCounter.name());
			IRTemp arrayBaseOffset = new IRTemp("t" + tempCount++);
			if (leftNode instanceof IRTemp) {
				leftNode = new IRTemp(((IRTemp) leftNode).name());
			}
			IRConst oneCell6 = new IRConst(Configuration.WORD_SIZE);
			IRBinOp leftLengthCell4 = new IRBinOp(OpType.SUB, leftNode, oneCell6);
			IRMove assignArrayBaseOffset = new IRMove(arrayBaseOffset, new IRMem(leftLengthCell4)); // length(e1)
			IRTemp arrayBaseOffset2 = new IRTemp(arrayBaseOffset.name());
			arrayOffset = new IRBinOp(OpType.ADD, secondLoopCounter3, arrayBaseOffset2);	// i + length(e1)
			arrayOffset = new IRBinOp(OpType.MUL, arrayOffset, new IRConst(Configuration.WORD_SIZE));	// [i + length(e1)]		// fresh
			IRTemp tempOfArray6 = new IRTemp(tempOfArray.name());
			// newArray[i+length(e1)]
			newArrayAddr = new IRBinOp(OpType.ADD, tempOfArray6, arrayOffset);
			IRTemp secondLoopCounter4 = new IRTemp(secondLoopCounter.name());
			IRBinOp rightArrayOffset = new IRBinOp(OpType.MUL, secondLoopCounter4, new IRConst(Configuration.WORD_SIZE));
			if (rightNode instanceof IRTemp) {
				rightNode = new IRTemp(((IRTemp) rightNode).name());
			}
			// rightArray[i]
			IRBinOp rightArrayAddr = new IRBinOp(OpType.ADD, rightNode, rightArrayOffset);
			newArrayElem = new IRMem(newArrayAddr);
			IRMem rightArrayElem = new IRMem(rightArrayAddr);
			// newArray[i+length(e1)] = rightArray[i]
			assignElem = new IRMove(newArrayElem, rightArrayElem);
			IRTemp secondLoopCounter5 = new IRTemp(secondLoopCounter.name());
			incrementValue = new IRBinOp(OpType.ADD, secondLoopCounter5, new IRConst(1));
			IRTemp secondLoopCounter6 = new IRTemp(secondLoopCounter.name());
			// i++
			incrementCounter = new IRMove(secondLoopCounter6, incrementValue);
			jumpToStart = new IRJump(new IRName(startOfLoop2.name()));
			List<IRStmt> secondWhileLoop = Arrays.asList(
				initializeSecondCounter, assignArrayBaseOffset, 
				startOfLoop2, secondWhileJump, trueLabel2,
				assignElem, incrementCounter, jumpToStart, falseLabel2
			);
			
			stmtList.addAll(firstWhileLoop);
			stmtList.addAll(secondWhileLoop);

			IRTemp tempOfArray7 = new IRTemp(tempOfArray.name());

			tempNode = new IRESeq(new IRSeq(stmtList),tempOfArray7);	
			return;
		}
		
		OpType tempOp = null;
		
		switch(op){
		case PLUS: 
			tempOp = OpType.ADD; 	break;
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
			tempOp = OpType.AND;	break;
		case OR:
			tempOp = OpType.OR;		break;
		case EQUAL:
			tempOp = OpType.EQ;		break;
		case NOT_EQUAL:
			tempOp = OpType.NEQ;	break;
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
	// TODO: emit symbol names following convention 
	@Override
	public void visit(FunctionCall fc) {
		int index = fc.getIndex();
		if (index == 0) {									// f()
			String id = fc.getABIName();
			IRName lf = new IRName(id);
			tempNode = new IRCall(lf);
			((IRCall) tempNode).setNumReturns(fc.getNumReturns());
		} else if (index == 1) {							// f(a1,...,an) 
			// get function label
			String id = fc.getABIName();
			IRName lf = new IRName(id);
			
			// get function args
			FunctionArg functionArg = fc.getFunctionArg();
			List<Expr> args = functionArg.getArgExprs();
			List<IRExpr> irArgs = new ArrayList<IRExpr>();	
			for (Expr arg : args) {
				arg.accept(this);
				irArgs.add((IRExpr) tempNode);
			}
			tempNode = new IRCall(lf, irArgs);
			((IRCall) tempNode).setNumReturns(fc.getNumReturns());
		} else {											// length(e)
			fc.getExpr().accept(this);
			IRExpr array = (IRExpr) tempNode;
			IRConst offset = new IRConst(Configuration.WORD_SIZE);
			IRBinOp lengthAddr = new IRBinOp(OpType.SUB, array, offset);
			tempNode = new IRMem(lengthAddr);
		}
	}
	
	/**
	 * f(x1:t1,...,xn:tn):t' {s} --IR--> SEQ(LABEL(f), S[s])
	 */
	@Override
	public void visit(FunctionDecl fd) {
		 // get function label
		String label = fd.getABIName();
		IRLabel irLabel = new IRLabel(label);
		
		IRSeq seq = new IRSeq(new ArrayList<IRStmt>());
		List<String> holyParamList = fd.getParams();
		for (int i = 0; i < holyParamList.size(); i++) {
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
			List<IRStmt> stmts = irSeq.stmts();
			// TODO: refactor HUMMUS
			seq = LIRVisitor.combineTwoStmt(seq, new IRSeq(stmts));
		} else {
			seq = LIRVisitor.combineTwoStmt(seq, new IRSeq(irLabel, new IRReturn()));
		}
		
		// create IRFuncDecl
		tempNode = new IRFuncDecl(label, seq);
		((IRFuncDecl) tempNode).setParamList(fd.getParams());
		((IRFuncDecl) tempNode).setNumArgs(fd.getNumParams());
		((IRFuncDecl) tempNode).setNumReturns(fd.getNumReturns());
	}

	@Override
	public void visit(Identifier id) {
		tempNode = new IRTemp(id.toString());
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
			String t = "t" + tempCount++;
			IRTemp tempOfArray = new IRTemp(t);
			
			// Move allocated space pointer into tempOfArray
			IRMove moveCallIntoTemp = new IRMove(tempOfArray, theIRCall);
			IRTemp tempOfArray2 = new IRTemp(t);
			IRMove moveLength = new IRMove(new IRMem(tempOfArray2), new IRConst(length));
			
			// Initialize sequence of statements with moving allocated space into IRTemp and adding length
			List<IRStmt> stmtList = new ArrayList<IRStmt>();
			stmtList.add(moveCallIntoTemp);
			stmtList.add(moveLength);
			
			// For each char in stringLit, get the IRNode for it, and make a IRMove
			// node for the IRExpr into each cell
			for (int i = 0; i < length; i++) {
				IRConst character = new IRConst((long)stringLit.charAt(i));
				IRConst offSet = new IRConst((i+1)*Configuration.WORD_SIZE);
				IRTemp tempOfArray3 = new IRTemp(t);
				IRBinOp offsetBinOp = new IRBinOp(OpType.ADD, offSet, tempOfArray3);
				IRMem memOfCell = new IRMem(offsetBinOp);
				IRMove moveElementIntoCell = new IRMove(memOfCell, character);
				stmtList.add(moveElementIntoCell);
			}
			
			// Make array point to one cell after (since cell -1 should be length)
			IRConst oneOffset = new IRConst(Configuration.WORD_SIZE);
			IRTemp tempOfArray4 = new IRTemp(t);
			IRBinOp offSetAndMem = new IRBinOp(OpType.ADD, tempOfArray4, oneOffset);
			IRTemp tempOfArray5 = new IRTemp(t);
			IRMove moveOffsetAndMem = new IRMove(tempOfArray5, offSetAndMem);
			stmtList.add(moveOffsetAndMem);
			IRSeq movingElementsIntoArray = new IRSeq(stmtList);
			// temp(a)
			IRTemp tempOfArray6 = new IRTemp(t);
			IRESeq createStringLit = new IRESeq(movingElementsIntoArray, tempOfArray6);
			tempNode = createStringLit;
			break;
			
		case 2:
			int character=-1;
			try {
				character = l.getCharLit().charAt(0);
			} catch (Exception e) {
				// TODO
				System.out.println("Expected a character stored in string");
			}
			tempNode = new IRConst(character);
			break;
		case 3:
			tempNode = l.getBoolLit()? new IRConst(TRUE) : new IRConst(FALSE);
			break;
		}
	}

	@Override
	public void visit(Program p) {
		Map<String, IRFuncDecl> functions = new HashMap<String, IRFuncDecl>();
		List<FunctionDecl> funcs = p.getFunctionDecls();
		for (FunctionDecl fd: funcs) {
			fd.accept(this);
			functions.put(fd.getABIName(), (IRFuncDecl) tempNode);
		}
		program = new IRCompUnit("Program", functions);
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
			allIRStmts.add((IRStmt) tempNode);
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
		if (ti.getIndex() == 0) {
			// _ = f()
			tempNode = new IRExp((IRExpr) tempNode);
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
				}
			}
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
			IRExpr zero = new IRConst(0);
			tempNode = new IRBinOp(OpType.SUB, zero, expr);
			break;
		case LOG_NEG: 
			tempNode = new IRBinOp(OpType.XOR, expr, new IRConst(TRUE));
			break;
		}
	}

	@Override
	public void visit(UseId ui) {
		return;
	}

	@Override
	public void visit(VarDecl vd) {
		// array declaration
		if (vd.getIndex() == 0) {
			MixedArrayType mat = vd.getMixedArrayType();
			int index = mat.getIndex();
			// with a specified length
			if (index == 1) {		// x:t[e1]..[en][]*
				vd.getIdentifier().accept(this);
				IRTemp tempOfArray = (IRTemp) tempNode;
				
				List<Expr> exprList = mat.getMixedBrackets().getContent();
				// (stmt, expr) 
				// stmt: allocating,moving,assigning value stuff
				// expr: base address of the array
				IRESeq arrayGenesis = (IRESeq) createArray(exprList,0);
				IRMove moveAddrToTemp = new IRMove(tempOfArray,arrayGenesis.expr());
				IRSeq stmt = (IRSeq) arrayGenesis.stmt();
				List<IRStmt> newList = stmt.stmts();
				newList.add(moveAddrToTemp);
				tempNode = new IRSeq(newList);
			}
		}
		
		return;
	}
	
	/**
	 * Dirties tempNode to IRMove
	 */
	@Override
	public void visit(VarInit vi) {
//		VarDecl vd = vi.getVarDecl();
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
		ws.getStmt().accept(this);
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
	 * @return an IRExpr that points to the memory location pointed
	 * from IRExpr and IndexedBrackets
	 */
	
	private IRExpr createIRExprForBrackets(IRExpr ire, IndexedBrackets ib) {
		Expr exprInBracket = ib.getExpression();
		exprInBracket.accept(this);
		IRConst byteOffset = new IRConst(Configuration.WORD_SIZE);
		IRBinOp byteMult = new IRBinOp(OpType.MUL, byteOffset, (IRExpr) tempNode);
		IRBinOp arrayAddr = new IRBinOp(OpType.ADD, ire, byteMult);
		if (ib.getIndex() == 0) {
			return arrayAddr;
		}
		return createIRExprForBrackets(new IRMem(arrayAddr), ib.getIndexedBrackets());
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
	 */
	private IRExpr createArray(List<Expr> exprList, int index) {		
		// Base case
		if (index == exprList.size()) {
			return new IRConst(97);
			// TODO: change to this
			// return new IRConst(0);
		}
		List<IRStmt> stmts = new ArrayList<IRStmt>();
		
		// Make call to malloc for exprList.get(index)
		IRName nameOfAlloc = new IRName("_I_alloc_i");
		exprList.get(index).accept(this);
		IRExpr length = (IRExpr) tempNode;
		IRBinOp sizeOfArray = new IRBinOp(OpType.ADD,length,new IRConst(1));
		sizeOfArray = new IRBinOp(OpType.MUL, sizeOfArray, new IRConst(Configuration.WORD_SIZE));
		IRCall callToAlloc = new IRCall(nameOfAlloc,sizeOfArray);
		IRTemp freshArray = new IRTemp("t"+tempCount++);
		IRMove moveAddrToTemp = new IRMove(freshArray,callToAlloc);
		stmts.add(moveAddrToTemp);
		// insert length
		IRTemp freshArray2 = new IRTemp(freshArray.name());
		exprList.get(index).accept(this);
		IRExpr length2 = (IRExpr) tempNode;
		IRMove moveLength = new IRMove(new IRMem(freshArray2),length2);
		stmts.add(moveLength);
		// move base address up
		IRTemp freshArray3 = new IRTemp(freshArray.name());
		IRConst wordSize = new IRConst(Configuration.WORD_SIZE);
		IRBinOp arrayBase = new IRBinOp(OpType.ADD,freshArray3,wordSize);
		IRTemp freshArray4 = new IRTemp(freshArray.name());
		IRMove moveBaseUp = new IRMove(freshArray4,arrayBase);
		stmts.add(moveBaseUp);
		
		// while loop
		IRTemp loopCounter = new IRTemp("t"+tempCount++);
		IRMove initializeCounter = new IRMove(loopCounter,new IRConst(0));
		IRLabel startOfLoop = new IRLabel("l"+labelCount++);
		IRTemp loopCounter2 = new IRTemp(loopCounter.name());
		exprList.get(index).accept(this);
		IRExpr length3 = (IRExpr) tempNode;
		IRBinOp loopCondition = new IRBinOp(OpType.LT,loopCounter2,length3);
		IRLabel trueLabel = new IRLabel("l"+labelCount++);
		IRLabel falseLabel = new IRLabel("l"+labelCount++);
		IRCJump whileJump = new IRCJump(loopCondition,trueLabel.name(),falseLabel.name());
		IRTemp freshArray5 = new IRTemp(freshArray.name());
		IRTemp loopCounter3 = new IRTemp(loopCounter.name());
		IRConst wordSize2 = new IRConst(Configuration.WORD_SIZE);
		IRBinOp baseOffset = new IRBinOp(OpType.MUL,loopCounter3,wordSize2);
		IRBinOp currAddr = new IRBinOp(OpType.ADD,freshArray5,baseOffset);
		IRMove assignValue = new IRMove(new IRMem(currAddr),createArray(exprList,index+1));
		IRTemp loopCounter4 = new IRTemp(loopCounter.name());
		IRBinOp incrementedValue = new IRBinOp(OpType.ADD, loopCounter4, new IRConst(1));
		IRTemp loopCounter5 = new IRTemp(loopCounter.name());
		IRMove incrementCounter = new IRMove(loopCounter5,incrementedValue);
		IRJump jumpToStart = new IRJump(new IRName(startOfLoop.name()));
		List<IRStmt> whileLoop = Arrays.asList(initializeCounter,startOfLoop,whileJump,
				trueLabel,assignValue,incrementCounter,jumpToStart,falseLabel);
		stmts.addAll(whileLoop);
		IRTemp freshArray6 = new IRTemp(freshArray.name());
		return new IRESeq(new IRSeq(stmts),freshArray6);
	}
}
