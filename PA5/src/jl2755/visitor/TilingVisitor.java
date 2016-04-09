package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.*;
import jl2755.assembly.*;
import jl2755.assembly.Instruction.Operation;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, List<Tile>> tileMap;

	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();

	/** list of first 6 function call arg registers */
	private static final String[] ARG_REG_LIST = {
			"rdi", "rsi", "rdx", "rcx", "r8", "r9"
	};

	
	
	/** Lists of strings representing possible tiles. */
	// TODO: put all these in a json file and read the json file to populate patternMap

//	// BINOP
//	private static final List<String> BINOP = new ArrayList<String>(
//			Arrays.asList("BINOP"));

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
	}
	
	/**
	 * Assembly: 
	 * push en
	 * push en-1
	 * ...
	 * push e7
	 * mov e6, r9
	 * ...
	 * mov e1, rdi
	 * call f
	 */
	@Override
	public void visit(IRCall call) {
		if (tileMap.containsKey(call)) {
			return;
		}
		
		List<IRExpr> args = call.args();
		int numArgs = args.size();
		
		// for args7...
		List<Instruction> instructions = new ArrayList<Instruction>();
		if (numArgs > 6) {
			for (int i = numArgs-1; i > 6; i--) {
				// tile the expression
				IRExpr expr = args.get(i); 
				expr.accept(this);
				Tile tile = tileMap.get(expr);
				
				// assembly: "push ei"
				Instruction instr = new Instruction(Operation.PUSH, tile.getDest());
				instructions.add(instr);
			}
			
			numArgs = 6;
		}
		
		// for args1 ... args6
		for (int i = numArgs-1; i >= 0; i--) {
			IRExpr expr = args.get(6);
			expr.accept(this);
			Tile tile = tileMap.get(expr);
			
			Instruction instr = new Instruction(Operation.MOV, 
												tile.getDest(),
												new Register(ARG_REG_LIST[i]));
			instructions.add(instr);
		}
		
		// tile f
		IRExpr target = call.target();
		target.accept(this);
		
		// TODO: check
		Instruction callInstruction = new Instruction(Operation.CALL, tileMap.get(target).getDest());
		instructions.add(callInstruction);
		
		// create a corresponding tile
		Operand dest = new Register("rax");
		Tile tile = new Tile(instructions, instructions.size(), dest);
		tileMap.put(call, tile);
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

	/**
	 * Check if a Tile has been made for this IRConst node. If not,
	 * 
	 */
	@Override
	public void visit(IRConst con) {
		if (tileMap.containsKey(con)) {
			return;
		}
		Tile constTile = new Tile(null, 0, new Constant(con.value()));
		List<Tile> tempListOfTiles = new ArrayList<Tile>();
		tempListOfTiles.add(constTile);
		tileMap.put(con, tempListOfTiles);
	}

	@Override
	public void visit(IRESeq eseq) {
		// Should not be here I think
		System.out.println("Got to ESeq in TilingVisitor when you shouldn't have");
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
		if (tileMap.containsKey(l)) {
			return;
		}
		Instruction labelInst = new Instruction(Operation.LABEL,
				null, l.name());
		List<Instruction> tempListOfInstr = new ArrayList<Instruction>();
		Tile labelTile = new Tile(tempListOfInstr,0,l.name());
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
