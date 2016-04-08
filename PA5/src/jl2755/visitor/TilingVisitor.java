package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRESeq;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRNode;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import edu.cornell.cs.cs4120.xic.ir.OpType;
import jl2755.assembly.*;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	
	/** Lists of strings representing possible tiles. */
	
	private static final List<String> JUMP_PRE = new ArrayList<String>(
			Arrays.asList(
					"Jump", 
					"Name"
					));
	private static final List<String> JUMP_IN = new ArrayList<String>(
			Arrays.asList(
					"Name", 
					"Jump"
					));
	
	
	

	@Override
	public void visit(IRBinOp bo) {
		// TODO Auto-generated method stub
		IRNode left = bo.left();
		IRNode right = bo.right();
		left.accept(this);
		right.accept(this);

		OpType op = bo.opType();
		BinOpTile binOp;
		switch(op) {
		case ADD: 
			binOp = new BinOpTile(tileMap.get(left).getRelevantOperand(),
					tileMap.get(right).getRelevantOperand(), op);
			tileMap.put(bo, binOp);
			break;
		case SUB:
			binOp = new BinOpTile(tileMap.get(left).getRelevantOperand(),
					tileMap.get(right).getRelevantOperand(), op);
			tileMap.put(bo, binOp);
			break;
		case MUL:
			binOp = new BinOpTile(tileMap.get(left).getRelevantOperand(),
					tileMap.get(right).getRelevantOperand(), op);
			tileMap.put(bo, binOp);
			break;
		case HMUL:
			binOp = new BinOpTile(tileMap.get(left).getRelevantOperand(),
					tileMap.get(right).getRelevantOperand(), op);
			tileMap.put(bo, binOp);
			break;
		case DIV:
			binOp = new BinOpTile(tileMap.get(left).getRelevantOperand(),
					tileMap.get(right).getRelevantOperand(), op);
			tileMap.put(bo, binOp);
			break;
		case MOD:
			// TODO
		case AND:
			// cmp
		case OR:
			// cmp
		case XOR:
			// cmp
		case LSHIFT:
			// TODO
		case RSHIFT:
			// TODO
		case ARSHIFT:
			// TODO
		case EQ:
			// cmp
		case NEQ:
			// cmp
		case LT:
			// cmp
		case GT:
			// cmp
		case LEQ:
			// cmp
		case GEQ:
			// cmp
		}
	}

	@Override
	public void visit(IRCall call) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRCJump cj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRCompUnit cu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRConst con) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRESeq eseq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRExp exp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRFuncDecl fd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRJump j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRLabel l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRMem mem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRMove mov) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRName name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRReturn ret) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRSeq seq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRTemp temp) {
		// TODO Auto-generated method stub

	}

}
