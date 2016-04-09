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
//	= new Tile(matchdPattern, parameters);
	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();

	
	/** Lists of strings representing possible tiles. */
		
	
	private static final List<String> BINOP1_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Temp1",
					"Temp2"
			));
	private static final List<String> BINOP1_IN = new ArrayList<String>(
			Arrays.asList(
					"Temp1",
					"Binop",
					"Temp2"
			));
	private static final List<String> BINOP2_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Mem",
					"Temp"
			));
	private static final List<String> BINOP2_IN = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"Binop",
					"Temp"
			));
	private static final List<String> BINOP3_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Temp",
					"Mem"
			));
	private static final List<String> BINOP3_IN = new ArrayList<String>(
			Arrays.asList(
					"Temp",
					"Binop",
					"Mem"
			));
	private static final List<String> BINOP4_PRE = new ArrayList<String>(
			Arrays.asList(
					"Binop",
					"Const",
					"Temp"
			));
	private static final List<String> BINOP4_IN = new ArrayList<String>(
			Arrays.asList(
					"Const",
					"Binop",
					"Temp"
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
	private static final List<String> CJUMP_BinOp_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"BinOp"
					));
	private static final List<String> CJUMP_BinOp_IN = new ArrayList<String>(
			Arrays.asList(
					"BinOp",
					"CJump"
					));
	private static final List<String> CJUMP_Temp_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"Temp"
					));
	private static final List<String> CJUMP_Temp_IN = new ArrayList<String>(
			Arrays.asList(
					"Temp",
					"CJump"
					));
	private static final List<String> CJUMP_Mem_PRE = new ArrayList<String>(
			Arrays.asList(
					"CJump", 
					"Mem"
					));
	private static final List<String> CJUMP_Mem_IN = new ArrayList<String>(
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
