package jl2755.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.IRBinOp.OpType;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;
import jl2755.ast.*;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;

public class MIRVisitor implements ASTVisitor{
	
	private IRNode tempNode;
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private int labelCount = 0;
	private int tempCount = 0;
	public IRNode program;
	
	@Override
	public void visit(ArrayElement ae) {
		// a[i][j]..[z]
		ae.getIdentifier().accept(this);
		IRExpr identifierIR = (IRExpr) tempNode;
		tempNode = createIRExprForBrackets(identifierIR, ae.getIndexedBrackets());
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
		IRMem memOfArray = new IRMem(tempOfArray);
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
			IRBinOp offsetBinOp = new IRBinOp(OpType.ADD, offSet, tempOfArray);
			IRMem memOfCell = new IRMem(offsetBinOp);
			IRMove moveElementIntoCell = new IRMove(memOfCell, (IRExpr) tempNode);
			stmtList.add(moveElementIntoCell);
		}
		
		// Make array point to one cell after (since cell -1 should be length)
		IRConst oneOffset = new IRConst(Configuration.WORD_SIZE);
		IRBinOp offSetAndMem = new IRBinOp(OpType.ADD, tempOfArray, oneOffset);
		IRMove moveOffsetAndMem = new IRMove(tempOfArray, offSetAndMem);
		stmtList.add(moveOffsetAndMem);
		IRSeq movingElementsIntoArray = new IRSeq(stmtList);
		// temp(a)
		IRESeq createArrayLit = new IRESeq(movingElementsIntoArray, tempOfArray);
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
			tempNode = new IRExp(new IRCall(lf));
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
		
		// get statement sequence
		IRSeq seq;
		BlockStmt blockStmt = fd.getBlockStmt();
		blockStmt.accept(this);
		if (tempNode != null) {
			IRSeq irSeq = (IRSeq) tempNode;
			List<IRStmt> stmts = irSeq.stmts();
			// TODO: refactor HUMMUS
			seq = new IRSeq(stmts);
		} else {
			seq = new IRSeq(irLabel, new IRReturn());
		}
		
		// create IRFuncDecl
		tempNode = new IRFuncDecl(label, seq);	
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
			tempNode = new IRConst(Long.parseLong(l.getIntLit()));
			break;
		case 1:
			String stringLit = l.getStringLit();
			IRExpr addr = new IRName("_I_alloc_i");
			IRCall alloc = new IRCall(addr, new IRConst(stringLit.length()+1));
			String t = "wat";
			IRTemp reg = new IRTemp(t);
			IRMove memAddr = new IRMove(reg, alloc);
			
			// fill up the allocated memory with stringLit
			for (int i=-1; i <stringLit.length(); i++) {
				IRConst val;
				if (i == -1) {
					val = new IRConst(stringLit.length());
				}
				else {
					val = new IRConst(stringLit.charAt(i));
				}
				IRMove move = new IRMove(new IRMem(new IRBinOp(OpType.ADD, addr, new IRConst(i*8))), val);
			}
			tempNode = reg;
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
					temp = new IRTemp(vd.getIdentifier().toString());
					result = new IRTemp(Configuration.ABSTRACT_ARG_PREFIX+i);
					move = new IRMove(temp,result);
					stmts.add(move);
					allUnderscore = false;
				}
			}
			if (allUnderscore) {
				tempNode = new IRExp((IRExpr) tempNode);
			} else {
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
		return;
	}
	
	/**
	 * Dirties tempNode to IRMove
	 */
	@Override
	public void visit(VarInit vi) {
		VarDecl vd = vi.getVarDecl();
		Identifier id = vd.getIdentifier();
		
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
		IRBinOp byteMult = new IRBinOp(IRBinOp.OpType.MUL, byteOffset, (IRExpr) tempNode);
		IRBinOp offsetAddition = new IRBinOp(IRBinOp.OpType.ADD, ire, byteMult);
		if (ib.getIndex() == 0) {
			return offsetAddition;
		}
		return createIRExprForBrackets(offsetAddition, ib.getIndexedBrackets());
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
	 * @return (IRCompUnit) tempNode
	 */
	public IRNode getRoot() {
		return tempNode;
	}
}
