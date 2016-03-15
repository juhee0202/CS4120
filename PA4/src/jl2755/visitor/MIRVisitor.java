package jl2755.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.*;
import edu.cornell.cs.cs4120.xic.ir.IRBinOp.OpType;
import jl2755.ast.*;

public class MIRVisitor implements Visitor{
	
	private IRNode tempNode;
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	
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
		case PLUS: tempOp = OpType.ADD; break;
		// TODO: Pls.
		}
		tempNode = new IRBinOp(tempOp, leftNode, rightNode);
	}

	/**
	 * sets tempNode to IRSeq
	 */
	@Override
	public void visit(BlockStmt bs) {
		// TODO Thomaz
		
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
		// TODO Recursively visit and make SEQ
	}

	@Override
	public void visit(Identifier id) {
		tempNode = new IRTemp(id.toString());
	}

	@Override
	public void visit(IfStmt is) {
		// TODO Auto-generated method stub
		
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
		// Ask about how to represent booleans
	}

	@Override
	public void visit(Program p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ReturnStmt rs) {
		// TODO Auto-generated method stub
		// Thomas: "I got it"
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
	 * Dirties tempNode to IRExp or IRSeq
	 */
	@Override
	public void visit(TupleInit ti) {
		// TODO Auto-generated method stub
		// Jeff: "I got it"
		ti.getFunctionCall().accept(this);
		if (ti.getIndex() == 0) {
			tempNode = new IRExp((IRExpr) tempNode);
		} else {
			// vd, tupleDeclList = f()
			// THIS IMPLEMENTATION ASSUMES ALL RETURN VALUES ARE KEPT ON THE STACK
			// TODO: finalize implementation
			IRExpr stack = ((IRCall) tempNode).args().get(0);
			IRExp exp = new IRExp((IRExpr) tempNode);
			int count = 0;
			IRTemp temp;
			IRExpr result;
			IRBinOp addr;
			IRMove move;
			List<IRStmt> stmts = new ArrayList<IRStmt>();
			stmts.add(exp);
			List<VarDecl> vdlist = ti.getVarDecls();
			for (VarDecl vd : vdlist) {
				if (vd != null) {
					// Assign result of function call
					temp = new IRTemp(vd.getIdentifier().toString());
					addr = new IRBinOp(OpType.ADD,stack,new IRConst(8*count));
					result = new IRMem(addr);
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
		// TODO Auto-generated method stub
		// NOT with null right, MINUS with 0
	}

	@Override
	public void visit(UseId ui) {
		// TODO Auto-generated method stub
		// Din dew nuffin
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
