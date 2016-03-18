package jl2755.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.IRBinOp.OpType;
import jl2755.ast.*;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionArg fa) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * f(a1,...,an) --IR--> CALL(NAME(f), e1,...,en)
	 */
	
	// TODO: emit symbol names following convention 
	@Override
	public void visit(FunctionCall fc) {
		int index = fc.getIndex();
		if (index == 0) {									// f()
			String id = fc.getIdentifier().toString();
			IRName lf = new IRName(id);
			tempNode = new IRCall(lf);
		} else if (index == 1) {							// f(a1,...,an) 
			// get function label
			String id = fc.getIdentifier().toString();
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
			IRName lf = new IRName("length"); 
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
		String id = fd.getIdentifier().toString();
		IRLabel irLabel = new IRLabel(id.toString());
		
		// get statement sequence
		BlockStmt blockStmt = fd.getBlockStmt();
		// options
		// 1) recursively visit each stmt in block stmt here
		// 2) recursively visit block stmt and make it return IRSeq <-- this
		blockStmt.accept(this);
		IRSeq irSeq = (IRSeq) tempNode;
		List<IRStmt> stmts = irSeq.stmts();
		stmts.add(0,irLabel);
		tempNode = new IRSeq(stmts);
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
			tempNode = l.getBoolLit()? new IRConst(1) : new IRConst(0);
		}
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
		UnaryOp op = ue.getUnaryOp();
		ue.getExpr().accept(this);
		IRExpr expr = (IRExpr) tempNode;

		switch (op) {
		case INT_NEG: 
			IRExpr zero = new IRConst(0);
			tempNode = new IRBinOp(OpType.SUB, zero, expr);
			break;
		case LOG_NEG: 
			tempNode = new IRBinOp(OpType.NOT, expr, null);
			break;
		default:
			// TODO: error handling
		}
				
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
