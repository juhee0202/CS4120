package jl2755.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	private int stackCounter = 0;
	private HashMap<String, Integer> functionSpaceMap;
	
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
			tileOp = Operation.ADDQ;
            break;
        case SUB:
        	tileOp = Operation.SUBQ;
            break;
        case MUL:
        	tileOp = Operation.IMULQ;
            break;
        case DIV:
        	tileOp = Operation.IDIVQ;
            break;
        case MOD:
            break;
        case AND:
        	tileOp = Operation.ANDQ;
            break;
        case OR:
        	tileOp = Operation.ORQ;
            break;
        case XOR:
        	tileOp = Operation.XORQ;
            break;
        default:
        	// TODO
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
		
		if (op == OpType.ADD ||
			op == OpType.SUB ||
			op == OpType.AND ||
			op == OpType.OR  ||
			op == OpType.XOR) 
		{
			if (leftOperand instanceof Constant) {
				/* Change <binop> CONST, CONST to
				 * 		MOV CONST, REG
				 * 		<binop> CONST, REG */
				if (rightOperand instanceof Constant) {
					Register t = new Register("tileRegister" + registerCount++);
					Instruction moveInstruction = new Instruction(Operation.MOVQ, rightOperand, t);
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
					Instruction moveInstruction = new Instruction(Operation.MOVQ, rightOperand, t);
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
		else if (op == OpType.MUL) {
			if (leftOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction movConstReg = new Instruction(Operation.MOVQ, leftOperand, t);
				instrList.add(movConstReg);
				cost++;
			}
			if (rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction movConstReg = new Instruction(Operation.MOVQ, rightOperand, t);
				instrList.add(movConstReg);
				cost++;
			}
			Instruction multiply = new Instruction(Operation.IMULQ, leftOperand, rightOperand);
			instrList.add(multiply);
			cost++;
			argDest = rightOperand;
		}
		/* 
		 * HMUL x, y
		 * 		movq %rx,%rax     #Store x into %rax
		 * 		mulq %y           #multiplies %y to %rax
		 * 		#mulq stores high and low values into rax and rdx.
		 * 		movq %rax,(%r8)   #Move low into &lower
		 * 		movq %rdx,(%r9)   #Move high answer into &higher
		 */
		else if (op == OpType.HMUL) {
			// TODO
			Register rax = new Register("rax");
			Operand operand = rightOperand;

			Instruction movToRax = new Instruction(Operation.MOVQ, leftOperand, rax);
			instrList.add(movToRax);
			cost++;
			
			if (rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction movConstReg = new Instruction(Operation.MOVQ, rightOperand, t);
				instrList.add(movConstReg);
				cost++;
				operand = t;
			}
			
			Instruction multiply = new Instruction(Operation.IMULQ, null, operand);
			instrList.add(multiply);
			cost++;
			
			// mulq stores high and low values into rax and rdx
			argDest = rax;
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
		else if (op == OpType.DIV || op == OpType.MOD) {
			Register rdx = new Register("rdx");
			Register rax = new Register("rax");
			Operand divisor = null;
			
			Instruction moveZeroToRdx = new Instruction(Operation.MOVQ, new Constant(0), rdx);
			Instruction moveDividendToRax = new Instruction(Operation.MOVQ, leftOperand, rax);
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
				Instruction moveDivisorToReg = new Instruction(Operation.MOVQ, rightOperand, divisor);
				instrList.add(moveDivisorToReg);
				cost++;
			}

			Instruction divide = new Instruction(Operation.IDIVQ, null , divisor);
			instrList.add(divide);
			cost++;
			
			if (op == OpType.DIV) {
				argDest = rax;
			}
			else {
				argDest = rdx;
			}
		}
		
		else if (op == OpType.EQ ||
				 op == OpType.NEQ ||
				 op == OpType.LT ||
				 op == OpType.GT ||
				 op == OpType.LEQ || 
				 op == OpType.GEQ) 
		{
			Operand src = leftOperand;
			Operand dest = rightOperand;
			
			if (leftOperand instanceof Constant && rightOperand instanceof Constant) {
				Register t = new Register("tileRegister" + registerCount++);
				Instruction moveToRegister = new Instruction(Operation.MOVQ, rightOperand, t);
				instrList.add(moveToRegister);
				cost++;
				dest = t;
			}
			Instruction compare = new Instruction(Operation.CMPQ, src, dest);
			instrList.add(compare);
			cost++;
			
			if (op == OpType.EQ) {
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(1), dest);	    // dest = 1 if equal
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(0), dest);      // dest = 0 if not equal
				instrList.add(cmove);
				instrList.add(cmovne);
				cost += 2;
			}
			else if (op == OpType.NEQ) {
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(1), dest);		// dest = 1 if not equal
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(0), dest);        // dest = 0 if equal
				instrList.add(cmovne);
				instrList.add(cmove);
				cost += 2;
			}
			else if (op == OpType.LT) {
				Instruction cmovl = new Instruction(Operation.CMOVL, new Constant(1), dest);	    // dest = 1 if equal
				Instruction cmovege = new Instruction(Operation.CMOVGE, new Constant(0), dest);     // dest = 0 if not equal
				instrList.add(cmovl);
				instrList.add(cmovege);
				cost += 2;
			}
			else if (op == OpType.LEQ) {
				Instruction cmovle = new Instruction(Operation.CMOVLE, new Constant(1), dest);	    // dest = 1 if <=
				Instruction cmovg = new Instruction(Operation.CMOVG, new Constant(0), dest);     	// dest = 0 if >
				instrList.add(cmovle);
				instrList.add(cmovg);
				cost += 2;
			}
			else if (op == OpType.GEQ) {
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
				Instruction instr = new Instruction(Operation.PUSHQ, dest);
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
			Instruction instr = new Instruction(Operation.MOVQ, dest, reg);
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
		Instruction callInstruction = new Instruction(Operation.CALLQ, targetDest);
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
			tempInst = new Instruction(Operation.TESTQ,tempSrc,tempSrc);
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
			tempInst = new Instruction(Operation.TESTQ,tempSrc,tempSrc);
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
			tempInst = new Instruction(Operation.TESTQ,tempSrc,tempDest);
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
			
			// Visit left and right
			left.accept(this);
			right.accept(this);
			leftTile = tileMap.get(left);
			rightTile = tileMap.get(right);
			cost = leftTile.getCost() + rightTile.getCost();
			tempSrc = leftTile.getDest();
			tempDest = rightTile.getDest();
			switch (op) {
			case AND:
				// test e1,e2 = AND
				tempInst = new Instruction(Operation.TESTQ,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jnz l
				tempInst = new Instruction(Operation.JNZ,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case OR:
				// Move left to temp
				Register temp = new Register("tileRegister" + registerCount++);
				tempInst = new Instruction(Operation.MOVQ,tempSrc,temp);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// or e1,e2
				tempInst = new Instruction(Operation.ORQ,tempDest,temp);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jnz l
				tempInst = new Instruction(Operation.JNZ,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case XOR:
				// cmp e1,e2
				tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jnz l
				tempInst = new Instruction(Operation.JNZ,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case EQ:
				// cmp e1,e2
				tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// je l
				tempInst = new Instruction(Operation.JE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case NEQ:
				// cmp e1,e2
				tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jne l
				tempInst = new Instruction(Operation.JNE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case LT:
				// cmp e2,e1
				tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jl l
				tempInst = new Instruction(Operation.JL,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case LEQ:
				// cmp e2,e1
				tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jle l
				tempInst = new Instruction(Operation.JLE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case GT:
				// cmp e2,e1
				tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jg l
				tempInst = new Instruction(Operation.JG,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			case GEQ:
				// cmp e2,e1
				tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jge l
				tempInst = new Instruction(Operation.JGE,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions,cost);
				break;
			default:
				System.out.println("Invalid operator for conditional!");
				break;
			}
		}
		tileMap.put(cj,tempTile);
	}

	@Override
	public void visit(IRCompUnit cu) {
		// Visit all function decls
		for (Entry<String, IRFuncDecl> fd : cu.functions().entrySet()) {
			((IRFuncDecl) fd).accept(this);
		}
		
		// Register/Stack allocation
		stackAllocation(cu);
		
		// Set parameters of all function decls
		// TODO
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
		ArrayList<ArrayList<IRNode>> childrenOfEachTile = new ArrayList<ArrayList<IRNode>>();
		for (int i = 0; i < tileLibrary.size(); i++) {
			if (cmpTreeVisitor.equalTrees(tileLibrary.get(i).getRootOfSubtree(), 
					mem)) {
				matchingTiles.add(new Tile(mem,tileLibrary.get(i)));
				childrenOfEachTile.add((ArrayList<IRNode>) cmpTreeVisitor.getAllChildrenNode());
			}
		}
		
		ArrayList<ArrayList<Operand>> operandOfEachChildren = new ArrayList<ArrayList<Operand>>();
		
		// Iterate through, call accept for each child, and 
		// populate the Operand list.
		for (int i = 0; i < childrenOfEachTile.size(); i++) {
			for (int j = 0; j < childrenOfEachTile.get(i).size(); j++) {
				IRNode currentNode = childrenOfEachTile.get(i).get(j);
				currentNode.accept(this);
				Tile currentTile = tileMap.get(currentNode);
				operandOfEachChildren.get(i).add(currentTile.getDest());
			}
		}
		
		// Fill in Tiles' Operands
		for (int i = 0; i < matchingTiles.size(); i++) {
			matchingTiles.get(i).fillInOperands(operandOfEachChildren.get(i));
		}
		
		// Second pass to merge Tiles. Do not do this in the first pass, otherwise
		// filling in Operands of instructions will get messed up
		for (int i = 0; i < childrenOfEachTile.size(); i++) {
			for (int j = 0; j < childrenOfEachTile.get(i).size(); j++) {
				Tile currentTile = tileMap.get(childrenOfEachTile.get(i).get(j));
				matchingTiles.set(i,Tile.mergeTiles(matchingTiles.get(i),currentTile));
			}
		}
		
		// Third pass through matchingTiles to get a minimum cost Tile
		int minimumCost = Integer.MAX_VALUE;
		int indexOfSmallest = -1;
		for (int i = 0; i < matchingTiles.size(); i++) {
			if (matchingTiles.get(i).getCost() < minimumCost) {
				minimumCost = matchingTiles.get(i).getCost();
				indexOfSmallest = i;
			}
		}
		tileMap.put(mem, matchingTiles.get(indexOfSmallest));
	}

	@Override
	public void visit(IRMove mov) {
		if (tileMap.containsKey(mov)) {
			return;
		}
		mov.expr().accept(this);
		mov.target().accept(this);
		Tile sourceTile = tileMap.get(mov.expr());
		Tile targetTile = tileMap.get(mov.target());
		Operand sourceOperand = sourceTile.getDest();
		Operand targetOperand = targetTile.getDest();
		List<Instruction> addingInstr = new ArrayList<Instruction>();
		addingInstr.addAll(sourceTile.getInstructions());
		addingInstr.addAll(targetTile.getInstructions());
		Instruction movInstruction = new Instruction(Operation.MOVQ,
				sourceOperand, targetOperand);
		addingInstr.add(movInstruction);
		Tile finalTile = new Tile(addingInstr, 1 + sourceTile.getCost() + targetTile.getCost(),
				targetOperand);
		finalTile = Tile.mergeTiles(finalTile, sourceTile);
		finalTile = Tile.mergeTiles(finalTile, targetTile);
		tileMap.put(mov, finalTile);
	}

	/**
	 * maps name to a label operand
	 */
	@Override
	public void visit(IRName name) {
		if (tileMap.containsKey(name)) {
			return;
		}
		
		Label label = new Label(name.name());
		Tile tile = new Tile(null, 0, label);
		tileMap.put(name, tile);
	}

	/**
	 * "ret"
	 * @param ret
	 */
	@Override
	public void visit(IRReturn ret) {
		if (tileMap.containsKey(ret)) {
			return;
		}
		
		Instruction instr = new Instruction(Operation.RET);
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(instr);
		Tile tile = new Tile(instructions, 1);
		tileMap.put(ret, tile);
	}

	@Override
	public void visit(IRSeq seq) {
		// Visit all children
		for (IRStmt s : seq.stmts()) {
			s.accept(this);
		}
	}
	
	@Override
	public void visit(IRTemp temp) {
		if (tileMap.containsKey(temp)) {
			return;
		}
		Tile tempTile = new Tile(null, 0, new Register(temp.name()));
		tileMap.put(temp, tempTile);
	}
	
	/**
	 * Handles converting all temps to stack allocations
	 * 
	 * @param headNode	the IR node representing the xi program
	 */
	private void stackAllocation(IRCompUnit headNode) {
		Tile masterTile = tileMap.get(headNode);
		List<Instruction> everyInstruction = masterTile.getInstructions();
		Map<String, Integer> registerToStackOffsetMap = new HashMap<String, Integer>();
		
		// Call addNecessaryInstruction
		
	}
	
	/**
	 * Inserts the necessary instructions to shuttle values to and from stack
	 * 
	 * @param instructions	the list of instructions for our program
	 * @param regToStack	the map of register names to the relative stack offset
	 * @return				list of instructions with correct insertions
	 */
	private List<Instruction> addNecessaryInstruction(
			List<Instruction> instructions, Map<String, Integer> regToStack) {
		int size = instructions.size();
		if (size == 0) {
			return new ArrayList<Instruction>();
		}
		Instruction currentInstruction = instructions.get(0);
		Operand src = currentInstruction.getSrc();
		Operand dest = currentInstruction.getDest();
		
		List<Instruction> added = new ArrayList<Instruction>();
		if (dest == null) {
			Operation op = currentInstruction.getOp();
			if (op == Operation.LABEL && op.name().substring(0,5) == "FUNC ") {
				functionSpaceMap.put(op.name(),stackCounter);
				stackCounter = 0;
			}
			// Label, ret
			added.add(currentInstruction);
			added.addAll(addNecessaryInstruction(
					instructions.subList(1,size),regToStack));
			return added;
		} 
		Register rcx = new Register("rcx");
		Register rdx = new Register("rdx");
		Register r11 = new Register("r11");
		Register rsp = new Register("rsp");
		
		if (src == null || src instanceof Constant) {
			// push, pop, call, jumps, div?
			if (dest instanceof Register) {
				String reg = ((Register) dest).getName();
				if (regToStack.containsKey(reg)) {
					int addr = regToStack.get(reg);
					Memory mem = new Memory(new Constant(addr),rsp);
					Instruction movToReg = new Instruction(Operation.MOVQ,mem,rcx);
					added.add(movToReg);
				}
				currentInstruction.setDest(rcx);
				added.add(currentInstruction);
				
				if (src != null) {
					int addr1 = -8*++stackCounter;
					Memory mem1 = new Memory(new Constant(addr1),rsp);
					Instruction movToMem = new Instruction(Operation.MOVQ,rcx,mem1);
					added.add(movToMem);
					
					// Add dest to hashmap
					regToStack.put(reg,addr1);
				}
			} else if (dest instanceof Memory) {
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				if (regOff != null) {
					// two register operands for memory
					int addr1 = regToStack.get(regBase.getName());
					int addr2 = regToStack.get(regOff.getName());
					Memory mem1 = new Memory(new Constant(addr1),rsp);
					Memory mem2 = new Memory(new Constant(addr2),rsp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					
					added.add(movToReg1);
					added.add(movToReg2);
				} else {
					int addr = regToStack.get(regBase.getName());
					Memory mem = new Memory(new Constant(addr),rsp);
					Instruction movToReg = new Instruction(Operation.MOVQ,mem,rcx);
					newMem = new Memory(cons,rcx);
					
					added.add(movToReg);
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);
			} else {
				added.add(currentInstruction);
			}
		} else {
			// src is not nul
			if (dest instanceof Memory) {
				// src must be register
				int addr1 = regToStack.get(((Register) src).getName());
				Memory mem1 = new Memory(new Constant(addr1),rsp);
				Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
				currentInstruction.setSrc(r11);
				added.add(movToReg1);
				
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				if (regOff != null) {
					// two register operands for memory
					int addr2 = regToStack.get(regBase.getName());
					int addr3 = regToStack.get(regOff.getName());
					Memory mem2 = new Memory(new Constant(addr2),rsp);
					Memory mem3 = new Memory(new Constant(addr3),rsp);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
					Instruction movToReg3 = new Instruction(Operation.MOVQ,mem3,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					
					added.add(movToReg2);
					added.add(movToReg3);
					
				} else {
					int addr2 = regToStack.get(regBase.getName());
					Memory mem2 = new Memory(new Constant(addr2),rsp);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
					newMem = new Memory(cons,rcx);
					
					added.add(movToReg2);
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);			
			} else {
				// dest is register, src is memory
				String reg = ((Register) dest).getName();
				if (regToStack.containsKey(reg)) {
					int addr1 = regToStack.get(reg);
					Memory mem1 = new Memory(new Constant(addr1),rsp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
					added.add(movToReg1);
				}
				currentInstruction.setDest(r11);
				
				// Add dest to hashmap
				int addrNew = -8*++stackCounter;
				Memory memNew = new Memory(new Constant(addrNew),rsp);
				regToStack.put(reg,addrNew);
				Instruction movToMem1 = new Instruction(Operation.MOVQ,r11,memNew);
				
				Memory memOp = (Memory) src;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				if (regOff != null) {
					// two register operands for memory
					int addr2 = regToStack.get(regBase.getName());
					int addr3 = regToStack.get(regOff.getName());
					Memory mem2 = new Memory(new Constant(addr2),rsp);
					Memory mem3 = new Memory(new Constant(addr3),rsp);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
					Instruction movToReg3 = new Instruction(Operation.MOVQ,mem3,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					
					added.add(movToReg2);
					added.add(movToReg3);
					
				} else {
					int addr2 = regToStack.get(regBase.getName());
					Memory mem2 = new Memory(new Constant(addr2),rsp);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
					newMem = new Memory(cons,rcx);
					
					added.add(movToReg2);
				}
				currentInstruction.setSrc(newMem);
				added.add(currentInstruction);
				added.add(movToMem1);
			}
		}
		added.addAll(addNecessaryInstruction(
				instructions.subList(1,size),regToStack));
		return added;
	}
	
}
