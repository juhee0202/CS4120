package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
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
	
	// MEM(BINOP(ADD, TEMP, BINOP(ADD, BINOP(MUL, TEMP, CONST), CONST)))
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
        	tileOp = Operation.IMUL;
            break;
        case HMUL:
        	// TODO
        	tileOp = Operation.HMUL;
            break;
        case DIV:
        	tileOp = Operation.IDIV;
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
		}
		/* create instruction 
		 * 		imul <reg32>,<reg32>
		 * 		imul <reg32>,<mem>
		 */
		else if (tileOp == Operation.IMUL) {
			if (leftOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction movConstReg = new Instruction(Operation.MOV, leftOperand, t);
				instrList.add(movConstReg);
				cost++;
			}
			if (rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction movConstReg = new Instruction(Operation.MOV, rightOperand, t);
				instrList.add(movConstReg);
				cost++;
			}
			Instruction multiply = new Instruction(Operation.IMUL, leftOperand, rightOperand);
			instrList.add(multiply);
			cost++;
			argDest = rightOperand;
		}
		else if (tileOp == Operation.HMUL) {
			// TODO
		}
		
		/* 
		 * dividend / divisor
		 * 		mov 0, %rdx
		 * 		mov dividend, %rax
		 * 		mov divisor, register
		 * 		idiv register
		 * 
		 * stores quotient in %rax, and remainder in %rdx
		 */
		else if (tileOp == Operation.IDIV || tileOp == Operation.MOD) {
			Register rdx = new Register("rdx");
			Register rax = new Register("rax");
			Operand divisor = null;
			
			Instruction moveZeroToRdx = new Instruction(Operation.MOV, new Constant(0), rdx);
			Instruction moveDividendToRax = new Instruction(Operation.MOV, leftOperand, rax);
			instrList.add(moveZeroToRdx);
			instrList.add(moveDividendToRax);
			cost += 2;
			
			if (rightOperand instanceof Register) {
				divisor = (Register) rightOperand;
			}
			else if (rightOperand instanceof Memory) {
				divisor = (Memory) rightOperand;
			}
			else if (rightOperand instanceof Constant) {
				divisor = new Register("tileRegister" + registerCount++);
				Instruction moveDivisorToReg = new Instruction(Operation.MOV, rightOperand, divisor);
				instrList.add(moveDivisorToReg);
				cost++;
			}

			Instruction divide = new Instruction(Operation.IDIV, divisor, null);
			instrList.add(divide);
			cost++;
			
			if (tileOp == Operation.IDIV) {
				argDest = rax;
			}
			else {
				argDest = rdx;
			}
		}
		
		else if (tileOp == Operation.EQ ||
				 tileOp == Operation.NEQ ||
				 tileOp == Operation.LT ||
				 tileOp == Operation.GT ||
				 tileOp == Operation.LEQ || 
				 tileOp == Operation.GEQ) 
		{
			Operand src = leftOperand;
			Operand dest = rightOperand;
			
			if (leftOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction moveToRegister = new Instruction(Operation.MOV, leftOperand, t);
				instrList.add(moveToRegister);
				cost++;
				src = t;
			}
			if (rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction moveToRegister = new Instruction(Operation.MOV, rightOperand, t);
				instrList.add(moveToRegister);
				cost++;
				dest = t;
			}
			Instruction compare = new Instruction(Operation.CMP, src, dest);
			instrList.add(compare);
			cost++;
			
			if (tileOp == Operation.EQ) {
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(1), dest);	    // dest = 1 if equal
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(0), dest);      // dest = 0 if not equal
				instrList.add(cmove);
				instrList.add(cmovne);
				cost += 2;
			}
			else if (tileOp == Operation.NEQ) {
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(1), dest);		// dest = 1 if not equal
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(0), dest);        // dest = 0 if equal
				instrList.add(cmovne);
				instrList.add(cmove);
				cost += 2;
			}
			else if (tileOp == Operation.LT) {
				Instruction cmovl = new Instruction(Operation.CMOVL, new Constant(1), dest);	    // dest = 1 if equal
				Instruction cmovege = new Instruction(Operation.CMOVGE, new Constant(0), dest);     // dest = 0 if not equal
				instrList.add(cmovl);
				instrList.add(cmovege);
				cost += 2;
			}
			else if (tileOp == Operation.LEQ) {
				Instruction cmovle = new Instruction(Operation.CMOVLE, new Constant(1), dest);	    // dest = 1 if <=
				Instruction cmovg = new Instruction(Operation.CMOVG, new Constant(0), dest);     	// dest = 0 if >
				instrList.add(cmovle);
				instrList.add(cmovg);
				cost += 2;
			}
			else if (tileOp == Operation.GEQ) {
				Instruction cmovge = new Instruction(Operation.CMOVGE, new Constant(1), dest);	    // dest = 1 if equal
				Instruction cmovl = new Instruction(Operation.CMOVL, new Constant(0), dest);     // dest = 0 if not equal
				instrList.add(cmovge);
				instrList.add(cmovl);
				cost += 2;
			}
			argDest = dest;
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
	 * mov rax, dest
	 * add $(8*(n-6)), rsp
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
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		// temp list to be appended to instructions after visiting all arg exprs
		List<Instruction> tempInstructions = new ArrayList<Instruction>();
		
		// for args7...
		if (numArgs > 6) {
			for (int i = numArgs-1; i > 6; i--) {
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
			numArgs = 6;
		}
		
		// for args1 ... args6
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
			Operand reg = new Register(ARG_REG_LIST[i]);
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
		// TODO Auto-generated method stub

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
		if (tileMap.containsKey(temp)) {
			return;
		}
		Tile tempTile = new Tile(null, 0, new Register(temp.name()));
		tileMap.put(temp, tempTile);
	}
}
