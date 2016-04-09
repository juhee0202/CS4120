package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.*;
import jl2755.assembly.*;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
//	= new Tile(matchdPattern, parameters);
	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();

	
	/** Lists of strings representing possible tiles. */
	// TODO: put all these in a json file and read the json file to populate patternMap

	private static final List<String> BINOP1_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop"
					));
	private static final List<String> BINOP1_IN = new ArrayList<String>(
			Arrays.asList(
					"Binop"
					));
	private static final List<String> BINOP2_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Mem"
					));
	private static final List<String> BINOP2_IN = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"Binop"
					));
	private static final List<String> BINOP3_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Mem"
					));
	private static final List<String> BINOP3_IN = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Mem"
					));
	private static final List<String> BINOP4_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Const"
					));
	private static final List<String> BINOP4_IN = new ArrayList<String>(
			Arrays.asList(
					"Const",
					"Binop"
					));
	private static final List<String> BINOP5_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Const",
					"Mem"
					));
	private static final List<String> BINOP5_IN = new ArrayList<String>(
			Arrays.asList(
					"Const",
					"Binop",
					"Mem"
					));

	// CJUMP
	private static final List<String> CJUMP_BINOP_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"BinOp"
					));
	private static final List<String> CJUMP_BINOP_IN = new ArrayList<String>(
			Arrays.asList(
					"BinOp",
					"CJump"
					));
	private static final List<String> CJUMP_TEMP_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"Temp"
					));
	private static final List<String> CJUMP_TEMP_IN = new ArrayList<String>(
			Arrays.asList(
					"Temp",
					"CJump"
					));
	private static final List<String> CJUMP_MEM_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"Mem"
					));
	private static final List<String> CJUMP_MEM_IN = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"CJump"
					));
	
	// JUMP
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
	
	// Const
	private static final List<String> CONST_PRE = new ArrayList<String>(
			Arrays.asList(
					"Const"
					));
	private static final List<String> CONST_IN = new ArrayList<String>(
			Arrays.asList(
					"Const"
					));
	
	// Label
	private static final List<String> LABEL_PRE = new ArrayList<String>(
			Arrays.asList(
					"Label"
					));
	private static final List<String> LABEL_IN = new ArrayList<String>(
			Arrays.asList(
					"Label"
					));
	
	// Mem
	private static final List<String> MEM_PRE = new ArrayList<String>(
			Arrays.asList(
					"Mem"
					));
	private static final List<String> MEM_IN = new ArrayList<String>(
			Arrays.asList(
					"Mem"
					));
	
	// MEM(BINOP(ADD, CONST, BINOP(ADD, BINOP(MUL, TEMP, CONST), CONST)))
	// eg: mov mem(%ebx + (%ecx*w + k)), %eax
	private static final List<String> MEM_EFFECTIVE_PRE = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"Binop",
					"Binop",
					"Binop",
					"Const",
					"Const"
					));
	private static final List<String> MEM_EFFECTIVE_IN = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Binop",
					"Const",
					"Binop",
					"Const",
					"Mem"
					));


	// Move
	private static final List<String> MOVE_PRE = new ArrayList<String>(
			Arrays.asList(
					"Move"
					));
	private static final List<String> MOVE_IN = new ArrayList<String>(
			Arrays.asList(
					"Move"
					));
	
	// Name
	private static final List<String> NAME_PRE = new ArrayList<String>(
			Arrays.asList(
					"Name"
					));
	private static final List<String> NAME_IN = new ArrayList<String>(
			Arrays.asList(
					"Name"
					));
	
	// Temp
	private static final List<String> TEMP_PRE = new ArrayList<String>(
			Arrays.asList(
					"Temp"
					));
	private static final List<String> TEMP_IN = new ArrayList<String>(
			Arrays.asList(
					"Temp"
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
		
	}

	@Override
	public void visit(IRCall call) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRCJump cj) {
		// TODO Auto-generated method stub
		IRExpr condition = cj.expr();
		List<Tile> tiling = new ArrayList<Tile>();
		Tile tempTile;
		if (condition instanceof IRConst) {
			// boolean literal
			tempTile = new Tile()
		} else if (condition instanceof IRTemp) {
			// temp
			
		} else if (condition instanceof IRMem) {
			// mem
			
		} else {
			// expression
			IRBinOp expr = (IRBinOp) condition;
			OpType op = expr.opType();
			switch (op) {
			case AND:
				
			case OR:
			case XOR:
			case EQ:
			case NEQ:
			case LT:
			case LEQ:
			case GT:
			case GEQ:
			}
		}
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
