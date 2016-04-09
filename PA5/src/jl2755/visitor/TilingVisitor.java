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
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
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
import jl2755.assembly.Instruction.Operation;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();
	private int registerCount = 0;

	/** list of first 6 function call arg registers */
	private static final String[] ARG_REG_LIST = {
			"rdi", "rsi", "rdx", "rcx", "r8", "r9"
	};

	
	
	/** Lists of strings representing possible tiles. */
	// TODO: put all these in a json file and read the json file to populate patternMap

	// BINOP
	private static final List<String> BINOP_PRE = new ArrayList<String>(
			Arrays.asList(
					"BinOp"
					));
	private static final List<String> BINOP_IN = new ArrayList<String>(
			Arrays.asList(
					"BinOp"
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

		OpType op = bo.opType();
		Operation tileOp = null;
		switch(op) {
		case ADD:
			tileOp = Operation.ADD;
            break;
        case SUB:
        	tileOp = Operation.SUB;
            break;
        case MUL:
        	tileOp = Operation.MUL;
            break;
        case HMUL:
        	tileOp = Operation.HMUL;
            break;
        case DIV:
        	tileOp = Operation.DIV;
            break;
        case MOD:
        	tileOp = Operation.MOD;
            break;
        case AND:
        	tileOp = Operation.AND;
            break;
        case OR:
        	tileOp = Operation.OR;
            break;
        case XOR:
        	tileOp = Operation.XOR;
            break;
        case LSHIFT:
        	tileOp = Operation.LSHIFT;
            break;
        case RSHIFT:
        	tileOp = Operation.RSHIFT;
            break;
        case ARSHIFT:
        	tileOp = Operation.ARSHIFT;
            break;
        case EQ:
        	tileOp = Operation.EQ;
            break;
        case NEQ:
        	tileOp = Operation.NEQ;
            break;
        case LT:
        	tileOp = Operation.LT;
            break;
        case GT:
        	tileOp = Operation.GT;
            break;
        case LEQ:
        	tileOp = Operation.LEQ;
            break;
        case GEQ:
        	tileOp = Operation.GEQ;
            break;
		}
				
		IRNode left = bo.left();
		IRNode right = bo.right();
		left.accept(this);
		right.accept(this);
		
		Operand leftOperand = tileMap.get(left).getDest();
		Operand rightOperand = tileMap.get(right).getDest();
		
		List<Instruction> instrList = new ArrayList<Instruction>();
		int cost = 0;
		Operand argDest = null;
		
		if (leftOperand instanceof Constant) {
			/* Change <binop> CONST, CONST to
			 * 		MOV CONST, REG
			 * 		<binop> CONST, REG */
			if (rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction moveInstruction = new Instruction(Operation.MOV, rightOperand, t);
				Instruction binopConstReg = new Instruction(tileOp, leftOperand, t);
				instrList.add(moveInstruction);
				instrList.add(binopConstReg);
				cost = 2;
				argDest = t;
			}
			else {
				Instruction binopConstRegOrMem = new Instruction(tileOp, leftOperand, rightOperand);
				instrList.add(binopConstRegOrMem);
				cost = 1;
				argDest = rightOperand;
			}
		}
		else if (leftOperand instanceof Memory) {
			if (rightOperand instanceof Register) {
				Instruction binopMemReg = new Instruction(tileOp, leftOperand, rightOperand);
				instrList.add(binopMemReg);
				cost = 1;
				argDest = rightOperand;
			}
			else if (rightOperand instanceof Constant) {
				Instruction binopConstMem = new Instruction(tileOp, rightOperand, leftOperand);
				instrList.add(binopConstMem);
				cost = 1;
				argDest = leftOperand;
			}
			/* 
			 * Change <binop> MEM1, MEM2 to
			 * 		MOV MEM2 REG
			 * 		<binop> MEM1, REG
			 */
			else if (rightOperand instanceof Memory) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction moveInstruction = new Instruction(Operation.MOV, rightOperand, t);
				Instruction binopMemReg = new Instruction(tileOp, leftOperand, t);
				instrList.add(moveInstruction);
				instrList.add(binopMemReg);
				cost = 2;
				argDest = t;
			}
		}
		else if (leftOperand instanceof Register) {
			if (rightOperand instanceof Memory || rightOperand instanceof Register) {
				Instruction binopRegRegOrMem = new Instruction(
						tileOp, 
						leftOperand, 
						rightOperand
						);
				instrList.add(binopRegRegOrMem);
				cost = 1;
				argDest = rightOperand;
			}
			else if (rightOperand instanceof Constant) {
				Instruction binopConstReg = new Instruction(tileOp, rightOperand, leftOperand);
				instrList.add(binopConstReg);
				cost = 1;
				argDest = leftOperand;
			}
		}
		
		// create tile and put into tileMap
		Tile tile = new Tile(instrList, cost, argDest);
		tileMap.put(bo, tile);
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
				null, new Label(l.name()));
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
