package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.*;
import jl2755.assembly.*;
import jl2755.assembly.Instruction.Operation;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();
	private int registerCount = 0;

	private List<Tile> tileLibrary;
	
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
	
	public TilingVisitor() {
		tileLibrary = new ArrayList<Tile>();
		
	}

	/**
	 * <binop> src, dest
	 *  */
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
        	// TODO
        	tileOp = Operation.MUL;
            break;
        case HMUL:
        	// TODO
        	tileOp = Operation.HMUL;
            break;
        case DIV:
        	// TODO
        	tileOp = Operation.DIV;
            break;
        case MOD:
        	// TODO
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
        	// TODO
        	tileOp = Operation.EQ;
            break;
        case NEQ:
        	// TODO
        	tileOp = Operation.NEQ;
            break;
        case LT:
        	// TODO
        	tileOp = Operation.LT;
            break;
        case GT:
        	// TODO
        	tileOp = Operation.GT;
            break;
        case LEQ:
        	// TODO
        	tileOp = Operation.LEQ;
            break;
        case GEQ:
        	// TODO
        	tileOp = Operation.GEQ;
            break;
		}
		
		// Visit left & right children to get current node's operands
		IRNode left = bo.left();
		IRNode right = bo.right();
		left.accept(this);
		right.accept(this);
		
		Operand leftOperand = tileMap.get(left).getDest();
		Operand rightOperand = tileMap.get(right).getDest();
		
		// declare fields to create new Tile
		List<Instruction> instrList = new ArrayList<Instruction>();
		int cost = 0;
		Operand argDest = null;
		
		if (tileOp == Operation.ADD ||
			tileOp == Operation.SUB ||
			tileOp == Operation.AND ||
			tileOp == Operation.OR  ||
			tileOp == Operation.XOR) 
		{
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
		else if (tileOp == Operation.LSHIFT ||
				 tileOp == Operation.RSHIFT || 
				 tileOp == Operation.ARSHIFT) 
		{
			if (leftOperand instanceof Constant) {
				if (rightOperand instanceof Register || rightOperand instanceof Memory) {
					Instruction shiftConstReg = new Instruction(tileOp, leftOperand, rightOperand);
					instrList.add(shiftConstReg);
					cost = 1;
					argDest = rightOperand;
				}
				else if (rightOperand instanceof Constant) {
					Register t = new Register("tileRegister" + registerCount++);
					Instruction moveInstruction = new Instruction(Operation.MOV, rightOperand, t);
					Instruction shiftConstReg = new Instruction(tileOp, leftOperand, t);
					instrList.add(moveInstruction);
					instrList.add(shiftConstReg);
					cost = 2;
					argDest = t;
				}
			}
			else {
				System.out.println("TilingVisitor: LEFT OPERAND FOR SHIFT SHOULD HAVE BEEN A CONSTANT");
			}
		}
		// TODO CONTINUE HERE TOMORROW FOR DIFF KINDS OF TILEOP OMFGSDJLKFJSDKLFJDS KILL ME NOW
	}
	
	/**
	 * Assembly: 
	 * sub $(8*(m-2)), rsp			// m = number of return values
	 * push en
	 * push en-1
	 * ...
	 * push e7
	 * mov e6, r9
	 * ...
	 * mov e1, rdi
	 * (push rax) <- do we need this?
	 * call f
	 * mov rax, dest
	 * add $(8*( (n-6) + (m-2) ), rsp		// n = number of args
	 * 
	 * TODO
	 * modify visit(IRMove) to handle MOVE(expr,CALL(...))
	 */
	@Override
	public void visit(IRCall call) {
		if (tileMap.containsKey(call)) {
			return;
		}
		
		List<IRExpr> args = call.args();
		int numArgs = args.size();
		int numReturns = call.getNumReturns();
		int numArgRegs = 6;
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		// temp list to be appended to instructions after visiting all arg exprs
		List<Instruction> tempInstructions = new ArrayList<Instruction>();
		
		// for ret3...
		if (numReturns > 2) {
			// set rdi to ret3's address
			Register rdi = new Register("rdi");
			Register rsp = new Register("rsp");
			Memory ret3 = new Memory(new Constant(-8), rsp); 
			Instruction instr = new Instruction(Operation.MOVQ, ret3, rdi);
			tempInstructions.add(instr);
			
			// create (m-2) words space 
			Constant c = new Constant(numReturns - 2);
			Instruction instr2 = new Instruction(Operation.SUBQ, c, rsp);
			tempInstructions.add(instr2);
			numArgRegs--;
		}
		
		// for args7/6...
		if (numArgs > numArgRegs) {
			for (int i = numArgs-1; i > numArgRegs; i--) {
				// tile the expression
				IRExpr expr = args.get(i); 
				expr.accept(this);
				Tile exprTile = tileMap.get(expr);
				List<Instruction> exprInstrs = exprTile.getInstructions();
				// check if the tile is a mem-tile (no instruction) 
				if (exprInstrs != null) {
					instructions.addAll(exprInstrs);
				}
				Operand dest = exprTile.getDest();
				
				// "push dest"
				Instruction instr = new Instruction(Operation.PUSH, dest);
				tempInstructions.add(instr);
			}
			numArgs = numArgRegs;
		}
		
		int offset = numArgRegs == 5 ? 1 : 0; // numArgRegs is 5 if 3< returns
		// for args1 ... args6/5
		for (int i = numArgs-1; i >= 0; i--) {
			// tile the expression
			IRExpr expr = args.get(i);
			expr.accept(this);			
			Tile exprTile = tileMap.get(expr);
			List<Instruction> exprInstr = exprTile.getInstructions();
			if (exprInstr != null) {
				instructions.addAll(exprInstr);
			}
			Operand dest = exprTile.getDest();
			
			// ex) "mov e3 rdi"
			Operand reg = new Register(ARG_REG_LIST[i + offset]);
			Instruction instr = new Instruction(Operation.MOV, dest, reg);
			tempInstructions.add(instr);
		}
		
		// tile f
		IRExpr target = call.target();
		target.accept(this);
		Tile targetTile = tileMap.get(target);
		List<Instruction> targetInstr = targetTile.getInstructions();
		if (targetInstr != null) {
			instructions.addAll(targetInstr);
		}
		Operand targetDest = targetTile.getDest();
		Instruction callInstruction = new Instruction(Operation.CALL, targetDest);
		tempInstructions.add(callInstruction);
	
		// append tempInstructions to instructions
		instructions.addAll(tempInstructions);
		
		// create a Tile for this node
		Operand dest = new Register("rax");
		Tile tile = new Tile(instructions, instructions.size(), dest);
		tileMap.put(call, tile);
	}

	@Override
	public void visit(IRCJump cj) {
		IRExpr condition = cj.expr();
		Tile tempTile = null;
		List<Instruction> instructions = new ArrayList<Instruction>();
		Instruction tempInst;
		Operand tempSrc;
		Operand tempDest;
		Operand label = new Label(cj.trueLabel());
		int cost;

		if (condition instanceof IRConst) {		
			// Visit condition child
			condition.accept(this);
			Tile condTile = tileMap.get(condition);
			cost = condTile.getCost();
			
			// test c,c
			tempSrc = condTile.getDest();
			tempInst = new Instruction(Operation.TEST,tempSrc,tempSrc);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			// jnz l
			tempInst = new Instruction(Operation.JNZ,label);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			tempTile = new Tile(instructions,cost);
		} else if (condition instanceof IRTemp) {		
			// Visit condition child
			condition.accept(this);
			Tile condTile = tileMap.get(condition);
			cost = condTile.getCost();
			
			// test r,r
			tempSrc = condTile.getDest();
			tempInst = new Instruction(Operation.TEST,tempSrc,tempSrc);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			// jnz l
			tempInst = new Instruction(Operation.JNZ,label);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			tempTile = new Tile(instructions,cost);
		} else if (condition instanceof IRMem) {
			// Visit condition child
			condition.accept(this);
			Tile condTile = tileMap.get(condition);
			cost = condTile.getCost();
			
			// test mem,1
			tempSrc = condTile.getDest();
			tempDest = new Constant(1);
			tempInst = new Instruction(Operation.TEST,tempSrc,tempDest);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			// jnz l
			tempInst = new Instruction(Operation.JNZ,label);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			tempTile = new Tile(instructions,cost);
		} else {
			
			IRBinOp expr = (IRBinOp) condition;
			IRExpr left = expr.left();
			IRExpr right = expr.right();
			Tile leftTile;
			Tile rightTile;
			OpType op = expr.opType();
			switch (op) {
			case AND:
			case OR:
			case XOR:
				// Visit condition child
				condition.accept(this);
				Tile condTile = tileMap.get(condition);
				cost = condTile.getCost();
				
				// test r,r
				tempSrc = condTile.getDest();
				tempInst = new Instruction(Operation.TEST,tempSrc,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jnz l
				tempInst = new Instruction(Operation.JNZ,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case EQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// je l
				tempInst = new Instruction(Operation.JE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case NEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jne l
				tempInst = new Instruction(Operation.JNE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
				
			// TODO: Check AT&T syntax for cmp for 4 below!!!
			case LT:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jl l
				tempInst = new Instruction(Operation.JL,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case LEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jle l
				tempInst = new Instruction(Operation.JLE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case GT:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jg l
				tempInst = new Instruction(Operation.JG,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case GEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				cost = leftTile.getCost() + rightTile.getCost();
				
				// cmp e1,e2
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				tempInst = new Instruction(Operation.CMP,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jge l
				tempInst = new Instruction(Operation.JGE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			}
		}
		tileMap.put(cj,tempTile);
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
		tileMap.put(con, constTile);
	}

	@Override
	public void visit(IRESeq eseq) {
		// Should not be here I think
		System.out.println("Got to ESeq in TilingVisitor when you shouldn't have");
		return;
	}

	// assumption: EXP is only used to wrap CALL
	@Override
	public void visit(IRExp exp) {
		IRCall call = (IRCall) exp.expr();
		call.accept(this);
	}

	@Override
	public void visit(IRFuncDecl fd) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		// Label
		Label fnLabel = new Label(fd.label());
		instructions.add(new Instruction(Operation.LABEL, fnLabel));
		// Prologue
		// "enter 8*l, 0" 8*l will be filled in later
		Instruction enter = new Instruction(Operation.PUSHQ, null, new Constant(0));
		instructions.add(enter);
		// move args to param regs
		List<String> paramList = fd.getParamList();
		int numArgs = fd.getNumArgs();
		int numReturns = fd.getNumReturns();
		int offset = numReturns > 2 ? 1 : 0;	
		int numRegParams = Math.min(numArgs, 6-offset);
		for (int i = 0; i < numRegParams; i++) {
			Register arg = new Register(ARG_REG_LIST[i + offset]);
			Register param = new Register(paramList.get(i));
			Instruction moveArgtoParam = new Instruction(Operation.MOVQ, arg, 
														 param);
			instructions.add(moveArgtoParam);
		}
		Register rbp = new Register("rbp");
		for (int i = numRegParams; i < numArgs; i++) {
			Memory arg = new Memory(new Constant(8*(2+i-numRegParams)), rbp);
			Register param = new Register(paramList.get(i));
			Instruction moveArgtoParam = new Instruction(Operation.MOVQ, arg, 
					 param);
			instructions.add(moveArgtoParam);
		}
		// TODO: is ret3 register rdi value preserved? 
		// Body
		IRStmt body = fd.body();
		body.accept(this);
		instructions.addAll(tileMap.get(body).getInstructions());
		// Epilogue
		// assume last instruction of body is ret
		Instruction leave = new Instruction(Operation.LEAVE);
		instructions.add(instructions.size()-1, leave);
		
		// create a tile for this node
		Tile tile = new Tile(instructions);
		tileMap.put(fd, tile);
	}

	@Override
	public void visit(IRJump j) {
		// jmp l
		Operand label = new Label(((IRTemp) j.target()).name());
		Instruction jmp = new Instruction(Operation.JMP,label);
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(jmp);
		tileMap.put(j,new Tile(instructions,jmp.getCost()));
	}

	@Override
	public void visit(IRLabel l) {
		if (tileMap.containsKey(l)) {
			return;
		}
		Label theName = new Label(l.name());
		Instruction labelInst = new Instruction(Operation.LABEL,
				null, theName);
		List<Instruction> tempListOfInstr = new ArrayList<Instruction>();
		tempListOfInstr.add(labelInst);
		Tile labelTile = new Tile(tempListOfInstr,0,theName);
		tileMap.put(l, labelTile);
	}

	@Override
	public void visit(IRMem mem) {
		if (tileMap.containsKey(mem)) {
			return;
		}
		List<Tile> matchingTiles = new ArrayList<Tile>();
		for (int i = 0; i < tileLibrary.size(); i++) {
			if (cmpTreeVisitor.equalTrees(tileLibrary.get(i).getRootOfSubtree(), 
					mem)) {
				matchingTiles.add(tileLibrary.get(i));
			}
		}
		// TODO Must get all children of each matching tiles set
	}

	@Override
	public void visit(IRMove mov) {
		mov.expr().accept(this);
		mov.target().accept(this);
		Tile sourceTile = tileMap.get(mov.expr());
		Tile targetTile = tileMap.get(mov.target());
		Operand sourceOperand = sourceTile.getDest();
		Operand targetOperand = targetTile.getDest();
		List<Instruction> addingInstr = new ArrayList<Instruction>();
		addingInstr.addAll(sourceTile.getInstructions());
		addingInstr.addAll(targetTile.getInstructions());
		Instruction movInstruction = new Instruction(Operation.MOV,
				sourceOperand, targetOperand);
		addingInstr.add(movInstruction);
		Tile finalTile = new Tile(addingInstr, 1 + sourceTile.getCost() + targetTile.getCost(),
				targetOperand);
		tileMap.put(mov, finalTile);
	}

	@Override
	public void visit(IRName name) {
		
	}

	/**
	 * "ret"
	 * @param ret
	 */
	@Override
	public void visit(IRReturn ret) {
		Instruction instr = new Instruction(Operation.RET);
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(instr);
		Tile tile = new Tile(instructions, 1);
		tileMap.put(ret, tile);
	}

	@Override
	public void visit(IRSeq seq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IRTemp temp) {
		if (tileMap.containsKey(temp)) {
			return;
		}
		Tile tempTile = new Tile(null, 0, new Register(temp.name()));
		tileMap.put(temp, tempTile);
	}
	
	private void stackAllocation(IRNode headNode) {
		Tile masterTile = tileMap.get(headNode);
		List<Instruction> everyInstruction = masterTile.getInstructions();
		Map<String, Integer> registerToStackOffsetMap = new HashMap<String, Integer>();
		
		
	}
	
	private List<Instruction> addNecessaryInstruction(
			List<Instruction> argInstructions,
			Map<String, Integer> argRegisterToStackOffsetMap) {
		if (argInstructions.size() == 0) {
			return new ArrayList<Instruction>();
		}
		Instruction currentInstruction = argInstructions.get(0);
		
		
		return null;
	}
}
