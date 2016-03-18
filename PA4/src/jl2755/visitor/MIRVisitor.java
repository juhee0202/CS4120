package jl2755.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.IRBinOp.OpType;
import jl2755.ast.*;
import edu.cornell.cs.cs4120.xic.ir.interpret.Configuration;

public class MIRVisitor implements Visitor{
	
	private IRNode tempNode;
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private int labelCount;
	
	public MIRVisitor(Program p) {
		labelCount = 0;
		// TODO: Constructor
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
		default:
			// TODO: error handling
		}
		tempNode = new IRBinOp(tempOp, leftNode, rightNode);
	}

	/**
	 * sets tempNode to IRSeq
	 */
	@Override
	public void visit(BlockStmt bs) {
		int index = bs.getIndex();
		if (index == 1) {
			bs.getStmtList().accept(this);	// tempNode is set in here
		} else if (index == 2) {
			// visit stmt list
			bs.getStmtList().accept(this);	// tempNode is set in here
			IRSeq stmtSeq = (IRSeq) tempNode;
			List<IRStmt> irStmtList = stmtSeq.stmts();
			
			// visit return stmt
			bs.getReturnStmt().accept(this);
			if (tempNode != null) {	// there is a return value
				IRSeq returnSeq = (IRSeq) tempNode;
				List<IRStmt> returnStmtList = returnSeq.stmts();	
				irStmtList.addAll(returnStmtList);	// merge stmt seq and return seq
			}
			tempNode = new IRSeq(irStmtList);
		} else if (index == 3){
			bs.getReturnStmt().accept(this);	// tempNode is set in here
		}
	}

	@Override
	public void visit(FunctionArg fa) {
		// TODO Mebbe delete this shiet
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
			IRName lf = new IRName("_Ilength_iai"); 	// TODO confirm function length's ABIName
			fc.getExpr().accept(this);
			IRExpr arg = (IRExpr) tempNode;
			tempNode = new IRCall(lf, arg);
		}
	}
	
	/**
	 * f(x1:t1,...,xn:tn):t' {s} --IR--> SEQ(LABEL(f), S[s])
	 */
	// TODO: function name convention
	@Override
	public void visit(FunctionDecl fd) {
		 // get function label
		String label = fd.getABIName();
		IRLabel irLabel = new IRLabel(label);
		
		// get statement sequence
		BlockStmt blockStmt = fd.getBlockStmt();
		blockStmt.accept(this);
		IRSeq irSeq = (IRSeq) tempNode;
		List<IRStmt> stmts = irSeq.stmts();
		stmts.add(0,irLabel);
		tempNode = new IRSeq(stmts);
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
			is.getStmt1().accept(this);
			IRStmt stmt = (IRStmt) tempNode;
			tempNode = new IRSeq(ifJump,trueLabel,stmt,falseLabel);
		} else {
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
		// Should not be visited
	}

	@Override
	public void visit(Literal l) {
		// TODO Auto-generated method stub

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
		case 2:
			int character=-1;
			try {
				character = l.getCharLit().charAt(0);
			} catch (Exception e) {
				// TODO
				System.out.println("Expected a character stored in string");
			}
			tempNode = new IRConst(character);
		case 3:
			tempNode = l.getBoolLit()? new IRConst(TRUE) : new IRConst(FALSE);
		}

		// Ask about how to represent booleans

	}

	@Override
	public void visit(Program p) {
		List<FunctionDecl> funcs = p.getFunctionDecls();
		List<IRStmt> list = new ArrayList<IRStmt>();
		for (FunctionDecl fd: funcs) {
			fd.accept(this);
			list.add((IRStmt) tempNode);
		}
		tempNode = new IRSeq(list);
	}

	@Override
	public void visit(ReturnStmt rs) {
		int index = rs.getIndex();
		if (index == 0) {
			tempNode = null;
		} else {
			List<Expr> exprList = rs.getListOfExpr();
			List<IRStmt> stmtList = new ArrayList<IRStmt>(); 
			
			// add all return values in _RET temp
			for (int i = 0; i < exprList.size(); i++) {
				exprList.get(i).accept(this);
				IRTemp ret = new IRTemp(Configuration.ABSTRACT_RET_PREFIX+i);
				IRMove irMove = new IRMove(ret, (IRExpr) tempNode);
				stmtList.add(irMove);
			}
			
			tempNode = new IRSeq(stmtList);
		}
	}

	@Override
	public void visit(Stmt s) {
		s.getNakedStmt().accept(this);
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
		if (ti.getIndex() != 0) {
			// vd, tupleDeclList = f()
			int count = 0;
			IRTemp temp;
			IRExpr result;
			IRMove move;
			List<IRStmt> stmts = new ArrayList<IRStmt>();
			List<VarDecl> vdlist = ti.getVarDecls();
			for (VarDecl vd : vdlist) {
				if (vd != null) {
					// Assign result of function call
					temp = new IRTemp(vd.getIdentifier().toString());
					result = new IRTemp(Configuration.ABSTRACT_RET_PREFIX+count);
					move = new IRMove(temp,result);
					stmts.add(move);
				}
				count++;
			}
			tempNode = new IRSeq(stmts);
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
		default:
			// TODO: error handling
		}
	}

	@Override
	public void visit(UseId ui) {
		// Din dew nuffin
	}

	@Override
	public void visit(VarDecl vd) {
		// Should do nothing
	}
	
	/**
	 * Dirties tempNode to IRMove
	 */
	@Override
	public void visit(VarInit vi) {
		VarDecl vd = vi.getVarDecl();
		Identifier id = vd.getIdentifier();
		int index = vd.getIndex();
		if (index == 0) {
			// x: t[] = e
			// move(temp(x),mem(e))
			vi.getExpr().accept(this);
			IRExpr array = (IRExpr) tempNode;
			IRExpr temp = new IRTemp(id.toString());
			tempNode = new IRMove(temp,array);
		} else if (index == 1) {
			// x: int = e or x: bool = e
			// move(temp(x),E(e))
			id.accept(this);
			IRExpr temp = (IRExpr) tempNode;
			vi.getExpr().accept(this);
			IRExpr e = (IRExpr) tempNode;
			tempNode = new IRMove(temp,e);
		}
		
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
		// TODO
		return null;
	}
	
	/**
	 * Creates and returns an IRStmt that corresponds to the control flow given.
	 * 
	 * May dirty tempNode to IRExpr
	 * 
	 * @param e	the expression to evaluate, must be boolean
	 * @param t the IR label to jump to if e evaluates to true
	 * @param f te IR label to jump to if e evaluates to falseh
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
		} else if (e instanceof UnaryExpr) {
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
}
