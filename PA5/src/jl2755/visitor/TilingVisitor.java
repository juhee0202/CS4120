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
import jl2755.assembly.Register.RegisterName;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	private static IRTreeEqualsVisitor cmpTreeVisitor = new IRTreeEqualsVisitor();
	private int registerCount = 0;

	private List<Tile> tileLibrary;
	
	private int stackCounter = 0;
	private HashMap<String, Integer> functionSpaceMap = new HashMap<String, Integer>();
	private String currentFunction;
	
	/** list of first 6 function call arg registers */
	private static final String[] ARG_REG_LIST = {
			"RDI", "RSI", "RDX", "RCX", "R8", "R9"
	};
	
	/** list of callee-saved registers (except rbp) */
	private static final String[] CALLEE_REG_LIST = {
			"RDI", "RSI", "RBX", "R12", "R13", "R14", "R15"
	};
	
	/** list of caller-saved registers */
	private static final String[] CALLER_REG_LIST = {
			"RAX", "RCX", "RDX", "R8", "R9", "R10", "R11"
	};
	// shuttle regs: rcx, rdx, r11
	
	
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
					"BinOp1",
					"null1",
					"BinOp2",
					"BinOp3",
					"null2",
					"Const2",
					"Const1"
					));
	private static final List<String> MEM_EFFECTIVE_IN = new ArrayList<String>(
			Arrays.asList(
					"null1",
					"BinOp1",
					"null2",
					"BinOp3",
					"Const2",
					"BinOp2",
					"Const1",
					"Mem"
					));
	
	public TilingVisitor() {
		tileLibrary = new ArrayList<Tile>();
		List<Instruction> emptyInstructions1 = new ArrayList<Instruction>();
		Tile tile1 = new Tile(MEM_IN,MEM_PRE,emptyInstructions1,0);
		
		
		List<Instruction> emptyInstructions2 = new ArrayList<Instruction>();
		Tile tile2 = new Tile(MEM_EFFECTIVE_IN,MEM_EFFECTIVE_PRE,emptyInstructions2,0);
		
		tileLibrary = new ArrayList<Tile>();
		tileLibrary.add(tile1);
		tileLibrary.add(tile2);
	}
	
	public String parseTiles(IRNode argNode) {
		tileMap = new HashMap<IRNode, Tile>();
		argNode.accept(this);
		Tile temp = tileMap.get(argNode);
		return temp.toString();
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
        	tileOp = Operation.IMULQ2;
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
		
		// src: reg, const, mem
		// dest: reg, mem
		/* 
		 * binop a, b
		 * 		mov a %freshTemp
		 * 		binop b %freshTemp
		 * */
		if (op == OpType.ADD ||
			op == OpType.SUB ||
			op == OpType.AND ||
			op == OpType.OR  ||
			op == OpType.XOR ||
			op == OpType.MUL) 
		{
			Register t = new Register("tileRegister" + registerCount++);
			Instruction movToFreshTemp = new Instruction(Operation.MOVQ, leftOperand, t);
			Instruction binopInstr = new Instruction(tileOp, rightOperand, t);
			instrList.add(movToFreshTemp);
			instrList.add(binopInstr);
			cost = 2;
			argDest = t;
		}
		/* 
		 * HMUL x, y
		 * 		movq %x,%rax     #Store x into %rax
		 * 		imulq %y           #multiplies %y to %rax
		 * 		#mulq stores high and low values into rax and rdx.
		 * 		movq %rax, %freshTemp
		 */
		else if (op == OpType.HMUL) {
			// TODO
			Register rax = new Register(RegisterName.RAX);
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
			
			Instruction multiply = new Instruction(Operation.IMULQ1, operand);
			instrList.add(multiply);
			cost++;
			
			// imulq stores high and low values into rax and rdx
			argDest = rax;
		}
		
		/* 
		 * assembly idivq r/m32:
		 * 		Signed divide RDX:RAX by r/m32, with result 
		 * 		stored in RAX = Quotient, RDX = Remainder.
		 *
		 * dividend / divisor
		 * 		mov 0, %rdx
		 * 		mov dividend, %rax
		 * 		mov divisor, register
		 * 		idiv register
		 * 
		 */
		else if (op == OpType.DIV || op == OpType.MOD) {
			Register rdx = new Register(RegisterName.RDX);
			Register rax = new Register(RegisterName.RAX);
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
			argDest = (op == OpType.DIV)? rax : rdx;
		}
		
		/* 
		 * cmp <src> <dest> assembly instruction
		 * imm reg X
		 * imm mem X
		 * imm imm X
		 * 
		 * reg reg 
		 * reg mem
		 * reg imm
		 * 
		 * mem reg
		 * mem mem X
		 * mem imm
		 * */
		else if (op == OpType.EQ ||
				 op == OpType.NEQ ||
				 op == OpType.LT ||
				 op == OpType.GT ||
				 op == OpType.LEQ || 
				 op == OpType.GEQ) 
		{
			Operand src = leftOperand;
			Operand dest = rightOperand;
			
			if (leftOperand instanceof Constant) {
				if (rightOperand instanceof Register || 
				    rightOperand instanceof Memory) {
					src = rightOperand;
					dest = leftOperand;
				}
				else if (rightOperand instanceof Constant) {
					Register t = new Register("tileRegister" + registerCount++);
					Instruction moveToRegister = new Instruction(Operation.MOVQ, rightOperand, t);
					instrList.add(moveToRegister);
					cost++;
					dest = t;
				}
			}
			else if (leftOperand instanceof Memory) {
				if (leftOperand instanceof Memory) {
					Register t = new Register("tileRegister" + registerCount++);
					Instruction moveToRegister = new Instruction(Operation.MOVQ, rightOperand, t);
					instrList.add(moveToRegister);
					cost++;
					dest = t;
				}
			}

			Instruction compare = new Instruction(Operation.CMPQ, src, dest);
			instrList.add(compare);
			cost++;
			
			Register result = new Register("tileRegister" + registerCount++);
			
			if (op == OpType.EQ) {
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(1), result);	    // dest = 1 if equal
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(0), result);      // dest = 0 if not equal
				instrList.add(cmove);
				instrList.add(cmovne);
				cost += 2;
			}
			else if (op == OpType.NEQ) {
				Instruction cmovne = new Instruction(Operation.CMOVNE, new Constant(1), result);		// dest = 1 if not equal
				Instruction cmove = new Instruction(Operation.CMOVE, new Constant(0), result);        // dest = 0 if equal
				instrList.add(cmovne);
				instrList.add(cmove);
				cost += 2;
			}
			else if (op == OpType.LT) {
				Instruction cmovl = new Instruction(Operation.CMOVL, new Constant(1), result);	    // dest = 1 if equal
				Instruction cmovege = new Instruction(Operation.CMOVGE, new Constant(0), result);     // dest = 0 if not equal
				instrList.add(cmovl);
				instrList.add(cmovege);
				cost += 2;
			}
			else if (op == OpType.GT) {
				Instruction cmovg = new Instruction(Operation.CMOVG, new Constant(1), result);	    // dest = 1 if equal
				Instruction cmovele = new Instruction(Operation.CMOVLE, new Constant(0), result);     // dest = 0 if not equal
				instrList.add(cmovg);
				instrList.add(cmovele);
				cost += 2;
			}
			else if (op == OpType.LEQ) {
				Instruction cmovle = new Instruction(Operation.CMOVLE, new Constant(1), result);	    // dest = 1 if <=
				Instruction cmovg = new Instruction(Operation.CMOVG, new Constant(0), result);     	// dest = 0 if >
				instrList.add(cmovle);
				instrList.add(cmovg);
				cost += 2;
			}
			else if (op == OpType.GEQ) {
				Instruction cmovge = new Instruction(Operation.CMOVGE, new Constant(1), result);	    // dest = 1 if equal
				Instruction cmovl = new Instruction(Operation.CMOVL, new Constant(0), result);     // dest = 0 if not equal
				instrList.add(cmovge);
				instrList.add(cmovl);
				cost += 2;
			}
			argDest = result;
		}

		// create tile and put into tileMap
		Tile tile = new Tile(instrList, cost, argDest);
		tileMap.put(bo, tile);
	}
	
	/**
	 * The following instructions are created: 
	 * Prologue
	 * 	- save caller-save registers (conservatively save all)
	 * 	- allocate stack space for ret3, ret4, ... retm
	 * 	- push argn, argn-1, ..., arg7 (or arg6 if m > 2)
	 * 	- move arg6(or arg5 if m > 2) ... arg1 to ARG_REGs
	 * Call (saves rip to rsp)
	 * 
	 * Epilogue is handled in MOV and SEQ(parent of EXP)
	 */
	@Override
	public void visit(IRCall call) {
		if (tileMap.containsKey(call)) {
			return;
		}
		
		/* Get info about the function call */
		List<IRExpr> args = call.args();
		int numArgs = args.size();
		int numReturns = call.getNumReturns();
		int numArgRegs = ARG_REG_LIST.length;
		
		/* Create an empty list of instructions */
		List<Instruction> instructions = new ArrayList<Instruction>();
		// this will be appended to instructions after visiting all arg exprs
		List<Instruction> tempInstructions = new ArrayList<Instruction>();
		
		/* Save caller-save registers */
		// TODO: only save used registers if possible
		for (int i = 0; i < CALLER_REG_LIST.length; i++) {
			Register reg = new Register(CALLER_REG_LIST[i]);
			// "pushq reg"
			Instruction instr = new Instruction(Operation.PUSHQ, reg);
			tempInstructions.add(instr);
		}
		
		/* Allocate stack space for ret3...retm if (m > 2) */
		if (numReturns > 2) {
			// set rdi to ret3's address in stack frame
			Register rdi = new Register(RegisterName.RDI);
			Register rsp = new Register(RegisterName.RSP);
			Memory ret3 = new Memory(new Constant(-8), rsp); 
			// "movq ret3 rdi"
			Instruction instr = new Instruction(Operation.MOVQ, ret3, rdi);
			tempInstructions.add(instr);
			
			// create (m-2) words space 
			Constant c = new Constant(8*(numReturns - 2));
			// "subq $(8*(m-2)) rsp"
			Instruction instr2 = new Instruction(Operation.SUBQ, c, rsp);
			tempInstructions.add(instr2);
			numArgRegs--;	// because rdi is used to store ret3
		}
		
		/* Push argn...arg7 (or arg6 if m > 2) */
		if (numArgs > numArgRegs) {
			for (int i = numArgs-1; i > numArgRegs; i--) {
				// tile the expression
				IRExpr expr = args.get(i); 
				expr.accept(this);
				Tile exprTile = tileMap.get(expr);
				List<Instruction> exprInstrs = exprTile.getInstructions(); 
				instructions.addAll(exprInstrs);
				Operand dest = exprTile.getDest();
				
				// "pushq dest"
				Instruction instr = new Instruction(Operation.PUSHQ, dest);
				tempInstructions.add(instr);
			}
			numArgs = numArgRegs;
		}
		
		/* Move arg6(or arg5 if m > 2) ... arg1 to ARG_REGs */
		int offset = numArgRegs == 5 ? 1 : 0; // numArgRegs is 5 if m > 2
		for (int i = numArgs-1; i >= 0; i--) {
			// tile the expression
			IRExpr expr = args.get(i);
			expr.accept(this);			
			Tile exprTile = tileMap.get(expr);
			List<Instruction> exprInstr = exprTile.getInstructions();
			instructions.addAll(exprInstr);
			Operand dest = exprTile.getDest();
			
			// "movq dest special_reg"
			Operand reg = new Register(ARG_REG_LIST[i + offset]);
			Instruction instr = new Instruction(Operation.MOVQ, dest, reg);
			tempInstructions.add(instr);
		}
		
		// tile f
		IRExpr target = call.target();
		target.accept(this);
		Tile targetTile = tileMap.get(target);
		List<Instruction> targetInstr = targetTile.getInstructions();
		instructions.addAll(targetInstr);
		
		// "callq targetDest"
		Label targetDest = (Label)targetTile.getDest();
		targetDest.setLabelName("FUNC("+targetDest.toString().substring(1)+")");
		Instruction callInstruction = new Instruction(Operation.CALLQ, targetDest);
		tempInstructions.add(callInstruction);
	
		// append tempInstructions to instructions
		instructions.addAll(tempInstructions);
		
		// calculate total cost
		int totalCost = 0;
		for (Instruction i : instructions) {
			totalCost += i.getCost();
		}
		
		// create a Tile for this node
		Operand dest = new Register(RegisterName.RAX);
		Tile tile = new Tile(instructions, totalCost, dest);
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
		
		Tile superTile = null;
		
		for (IRFuncDecl fd : cu.functions().values()) {
			fd.accept(this);
			if (superTile == null) {
				superTile = tileMap.get(fd);
			} else {
				superTile = Tile.mergeTiles(superTile, tileMap.get(fd));
			}
		}
		
		tileMap.put(cu, superTile);
		
//		superTile = null;
//		
//		// Register/Stack allocation
//		stackAllocation(cu);
//		
//		// Set parameters of all function decls
//		for (Entry<IRNode, Tile> entry : tileMap.entrySet()) {
//			if (entry.getKey() instanceof IRFuncDecl) {
//				IRFuncDecl fd = (IRFuncDecl) entry.getKey();
//				Tile fdTile = entry.getValue();
//				Instruction enter = fdTile.getInstructions().get(1);
//				// complete "enter 8*l, 0"
//				Constant space = new Constant(8*(functionSpaceMap.get(fd.assemblyLabel())));
//				enter.setSrc(space);
//				fdTile.getInstructions().set(1,enter);
//				tileMap.put(fd, fdTile);
//			}
//		}
//		
//		for (IRFuncDecl fd : cu.functions().values()) {
//			if (superTile == null) {
//				superTile = tileMap.get(fd);
//			} else {
//				superTile = Tile.mergeTiles(superTile, tileMap.get(fd));
//			}
//		}
		
		tileMap.put(cu, superTile);
		
		// TODO: REFACTOR TO PUT RIGHT TILE IN COMPUNIT AFTER EPILOGUE
		// AND PROLOGUE STUFFFFFFFFFFFFFFF
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
		Tile constTile = new Tile(new ArrayList<Instruction>(), 0, new Constant(con.value()));
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
		Label fnLabel = new Label(fd.assemblyLabel());
		instructions.add(new Instruction(Operation.LABEL, fnLabel));
		// Prologue
		// TODO: replace enter with push/mov/sub for optimization
		// "enter 8*l, 0" 8*l will be filled in later
		Instruction enter = new Instruction(Operation.ENTER, new Constant(0), new Constant(0));
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
			Instruction moveArgToParam = new Instruction(Operation.MOVQ, arg, 
														 param);
			instructions.add(moveArgToParam);
		}
		Register rbp = new Register(RegisterName.RBP);
		for (int i = numRegParams; i < numArgs; i++) {
			Memory arg = new Memory(new Constant(8*(2+i-numRegParams)), rbp);
			Register param = new Register(paramList.get(i));
			Instruction moveArgtoParam = new Instruction(Operation.MOVQ, arg, 
					 param);
			instructions.add(moveArgtoParam);
		}
		// Body 
		IRStmt body = fd.body();
		// remove duplicate move(%ARG, %arg) instructions
		if (body instanceof IRSeq) {
			for (int i = 0; i < numArgs; i++) {
				((IRSeq) body).stmts().remove(0);
			}	
		}
		body.accept(this);
		instructions.addAll(tileMap.get(body).getInstructions());
		// Epilogue
		// assume last instruction of body is ret
//		Instruction leave = new Instruction(Operation.LEAVE);
//		instructions.add(instructions.size()-1, leave);
		
		// create a tile for this node
		Tile tile = new Tile(instructions);
		tileMap.put(fd, tile);
	}

	@Override
	public void visit(IRJump j) {
		// jmp l
		Operand label = new Label(((IRName) j.target()).name());
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
		// Initialize list of matching tiles
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
			operandOfEachChildren.add(new ArrayList<Operand>());
			for (int j = 0; j < childrenOfEachTile.get(i).size(); j++) {
				IRNode currentNode = childrenOfEachTile.get(i).get(j);
				currentNode.accept(this);
				Tile currentTile = tileMap.get(currentNode);
				operandOfEachChildren.get(i).add(currentTile.getDest());
			}
		}
		
		// Fill in Tiles' Operands
		
		// TODO: Fill in Tiles correctly (Mem doesn't fill Instructions
		// with Operands, only the dest Operand). Create more methods.
		
		for (int i = 0; i < matchingTiles.size(); i++) {
			matchingTiles.get(i).fillInOperands(operandOfEachChildren.get(i));
		}
		
		// Second pass to merge Tiles. Do not do this in the first pass, otherwise
		// filling in Operands of instructions will get messed up
		for (int i = 0; i < childrenOfEachTile.size(); i++) {
			for (int j = 0; j < childrenOfEachTile.get(i).size(); j++) {
				Tile currentTile = tileMap.get(childrenOfEachTile.get(i).get(j));
				matchingTiles.set(i,Tile.mergeTiles(currentTile,matchingTiles.get(i)));
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
		
		boolean redundant = sourceOperand.toString().equals(targetOperand.toString());		
		
		// If both children are Memory Operands
		if (sourceTile.getDest() instanceof Memory && targetTile.getDest() instanceof Memory) {
			List<Instruction> newInstructions = new ArrayList<Instruction>();
			Tile finalTile;
			if (!redundant) {
				newInstructions.add(new Instruction(Operation.MOVQ,sourceOperand,new Register(RegisterName.RCX)));
				newInstructions.add(new Instruction(Operation.MOVQ,new Register(RegisterName.RCX),targetOperand));
				finalTile = new Tile(newInstructions,2,targetOperand);
			} else {
				finalTile = new Tile(newInstructions,0,targetOperand);
			}
			
			finalTile = Tile.mergeTiles(targetTile, finalTile);
			finalTile = Tile.mergeTiles(sourceTile, finalTile);
			tileMap.put(mov, finalTile);
			return;
		}
		
		List<Instruction> addingInstr = new ArrayList<Instruction>();
		Instruction movInstruction = new Instruction(Operation.MOVQ,
				sourceOperand, targetOperand);
		Tile finalTile;
		if (!redundant) {
			addingInstr.add(movInstruction);	
			finalTile = new Tile(addingInstr, 1, targetOperand);
		} else {
			finalTile = new Tile(addingInstr, 0, targetOperand);
		}
		finalTile = Tile.mergeTiles(targetTile, finalTile);
		finalTile = Tile.mergeTiles(sourceTile, finalTile);
		
		// If source was a Call, put an epilogue after the mov instruction
		// the function should have a single return
		if (mov.expr() instanceof IRCall) {			
			// sanity check 
			// delete later
			IRCall callVersion = (IRCall) mov.expr();
			int numReturns  = callVersion.getNumReturns();
			assert(numReturns == 1);
			
			Tile epilogueTile = createEpilogueTile((IRCall)mov.expr());
			finalTile = Tile.mergeTiles(finalTile, epilogueTile); 
		}
		
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
		Tile tile = new Tile(new ArrayList<Instruction>(), 0, label);
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
		Instruction leaveInstr = new Instruction(Operation.LEAVE);
		Instruction retInstr = new Instruction(Operation.RET);
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(leaveInstr);
		instructions.add(retInstr);
		Tile tile = new Tile(instructions, 1);
		tileMap.put(ret, tile);
	}

	@Override
	public void visit(IRSeq seq) {
		List<IRStmt> stmtList = seq.stmts();
		Tile superTile = null;
		for (int i = 0; i < stmtList.size(); i++) {
			IRStmt stmt = stmtList.get(i);
			if (stmt instanceof IRExp) {
				// EXP(CALL(...)) [MOV(dest, REG)]
				IRCall call = (IRCall) ((IRExp) stmt).expr();
				int numReturns = call.getNumReturns();
				Tile masterTile = new Tile(new ArrayList<Instruction>(), 0);
				
				// move return values to the variable registers
				// temp Instruction list to be added to the masterTile later
				List<Instruction> tempInstructions = new ArrayList<Instruction>();
				for (int j = 0; j < numReturns; j++) {
					IRMove move = (IRMove) stmtList.get(i+j+1);
					IRExpr target = move.target();
					target.accept(this);
					Tile exprTile = tileMap.get(target);
					masterTile = Tile.mergeTiles(masterTile, exprTile);
					Operand dest = exprTile.getDest();
					Instruction instr;
					if (j == 0) {
						Register rax = new Register(RegisterName.RAX);
						instr = new Instruction(Operation.MOVQ, rax, dest);
					} else if (j == 1) {
						Register rdx = new Register(RegisterName.RDX);
						instr = new Instruction(Operation.MOVQ, rdx, dest);
					} else {
						Register rdi = new Register(RegisterName.RDI);
						Constant offset = new Constant(8*(j-2));
						Memory mem = new Memory(offset, rdi);
						if (dest instanceof Memory) {
							Register r11 = new Register(RegisterName.R11);
							Instruction shuttle = new Instruction(Operation.MOVQ, mem, r11);
							tempInstructions.add(shuttle);
							instr = new Instruction(Operation.MOVQ, r11, dest);
						} else {
							instr = new Instruction(Operation.MOVQ, mem, dest);
						}
					}
					tempInstructions.add(instr);
				}
				
				int totalCost = 0;
				for (Instruction instr : tempInstructions) {
					totalCost += instr.getCost();
				}
				
				call.accept(this);
				Tile callTile = tileMap.get(call);
				masterTile = Tile.mergeTiles(masterTile, callTile);
				
				Tile moveTile = new Tile(tempInstructions, totalCost);
				masterTile = Tile.mergeTiles(masterTile, moveTile);
				
				Tile epilogueTile = createEpilogueTile(call);
				masterTile = Tile.mergeTiles(masterTile, epilogueTile);
				
				// masterTile = tile for variables being assigned
				//				+ tile for call instruction
				//				+ tile for move instructions
				//				+ tile for epilogue instruction
				tileMap.put(stmt, masterTile);
				if (superTile == null) {
					superTile = masterTile;
				} else {
					superTile = Tile.mergeTiles(superTile, masterTile);
				}
				i += numReturns;
			} else {
				stmt.accept(this);
				if (superTile == null) {
					superTile = tileMap.get(stmt);
				} else {
					superTile = Tile.mergeTiles(superTile, tileMap.get(stmt));
				}
			}
		}
		tileMap.put(seq, superTile);
	}
	
	@Override
	public void visit(IRTemp temp) {
		if (tileMap.containsKey(temp)) {
			return;
		}
		Register tempRegister = new Register(temp.name());
		Tile tempTile = new Tile(new ArrayList<Instruction>(), 0, tempRegister);
		tempTile.setDest(tempRegister);
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
		masterTile.setInstructions(addNecessaryInstruction(
				everyInstruction,registerToStackOffsetMap));
		functionSpaceMap.put(currentFunction,stackCounter);
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
			// ret, leave
			added.add(currentInstruction);
			added.addAll(addNecessaryInstruction(
					instructions.subList(1,size),regToStack));
			return added;
		} 
		Register rcx = new Register(RegisterName.RCX);
		Register rdx = new Register(RegisterName.RDX);
		Register r11 = new Register(RegisterName.R11);
		Register rbp = new Register(RegisterName.RBP);
		
		if (src == null || src instanceof Constant) {
			// push, pop, call, jumps, div?
			if (dest instanceof Register) {
				// Dest uses a built-in register
				if (((Register) dest).getType() != RegisterName.TEMP) {
					added.add(currentInstruction);
					added.addAll(addNecessaryInstruction(
							instructions.subList(1,size),regToStack));
					return added;
				}
				
				String reg = ((Register) dest).getName();
				Memory mem;
				if (regToStack.containsKey(reg)) {
					int addr = regToStack.get(reg);
					mem = new Memory(new Constant(addr),rbp);
					Instruction movToReg = new Instruction(Operation.MOVQ,mem,rcx);
					added.add(movToReg);
				} else {
					int addr = -8*++stackCounter;
					mem = new Memory(new Constant(addr),rbp);
					regToStack.put(reg,addr);
				}
				currentInstruction.setDest(rcx);
				added.add(currentInstruction);
				
				if (src != null) {
					// TODO: check this condition??
					Instruction movToMem = new Instruction(Operation.MOVQ,rcx,mem);
					added.add(movToMem);
				}
			} else if (dest instanceof Memory) {
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				
				int addr1 = regToStack.get(regBase.getName());
				Memory mem1 = new Memory(new Constant(addr1),rbp);
				Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
				if (regOff != null && regOff.getType() == RegisterName.TEMP) {
					// two register operands for memory
					int addr2 = regToStack.get(regOff.getName());
					Memory mem2 = new Memory(new Constant(addr2),rbp);
					Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					added.add(movToReg2);
				} else if (regBase.getType() == RegisterName.TEMP) {
					if (regOff == null) {
						newMem = new Memory(cons,rcx);
					} else {
						newMem = new Memory(cons,rcx,regOff,memOp.getConstantFactor());
					}
					added.add(movToReg1);
				} else {
					// both registers are built-in
					newMem = memOp;
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);
			} else {
				// dest is constant or label
				Operation op = currentInstruction.getOp();
				Operand label = currentInstruction.getDest();
				if (op == Operation.LABEL && label.toString().contains("FUNC(")) {
					functionSpaceMap.put(currentFunction,stackCounter);
					currentFunction = label.toString();
					stackCounter = 0;
				}
				added.add(currentInstruction);
			}
		} else {
			// src is not null
			if (dest instanceof Memory && src instanceof Register) {
				// src must be register that must be in stack
				if (((Register) src).getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(((Register) src).getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
					currentInstruction.setSrc(r11);
					added.add(movToReg1);
				}
				
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				
				int addr2 = regToStack.get(regBase.getName());
				Memory mem2 = new Memory(new Constant(addr2),rbp);
				Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
				if (regOff != null && regOff.getType() == RegisterName.TEMP) {
					// two register operands for memory
					int addr3 = regToStack.get(regOff.getName());
					Memory mem3 = new Memory(new Constant(addr3),rbp);
					Instruction movToReg3 = new Instruction(Operation.MOVQ,mem3,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					added.add(movToReg3);
				} else if (regBase.getType() == RegisterName.TEMP) {
					if (regOff == null) {
						newMem = new Memory(cons,rcx);
					} else {
						newMem = new Memory(cons,rcx,regOff,memOp.getConstantFactor());
					}
					added.add(movToReg2);
				} else {
					// both registers are built-in
					newMem = memOp;
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);			
			} else if (dest instanceof Register && src instanceof Register) {
				// dest and src are registers
				String regD = ((Register) dest).getName();
				String regS = ((Register) src).getName();
				Memory memD;
				Instruction movToMemD = null;
				if (((Register) dest).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(regD)) {
						int addrD = regToStack.get(regD);
						memD = new Memory(new Constant(addrD),rbp);
						Instruction movToRegD = new Instruction(Operation.MOVQ,memD,rcx);
						added.add(movToRegD);
					} else {
						// Need to create a new memory address
						int addrD = -8*++stackCounter;
						memD = new Memory(new Constant(addrD),rbp);
						regToStack.put(regD,addrD);
					}
					currentInstruction.setDest(rcx);
					movToMemD = new Instruction(Operation.MOVQ,rcx,memD);
				}
				if (((Register) src).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(regS)) {
						int addrS = regToStack.get(regS);
						Memory memS = new Memory(new Constant(addrS),rbp);
						Instruction movToRegS = new Instruction(Operation.MOVQ,memS,rdx);
						added.add(movToRegS);
					} else {
						System.out.println(regS);
						System.out.println("Access a register that hasn't been set!");
						assert(false);
					}
					currentInstruction.setSrc(rdx);
				}
				added.add(currentInstruction);
				if (movToMemD != null) {
					added.add(movToMemD);
				}
			} else if (dest instanceof Register && src instanceof Memory){	
				// dest is register, src is memory
				String reg = ((Register) dest).getName();
				Memory mem1;
				Instruction movToMem1 = null;
				if (((Register) dest).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(reg)) {
						int addr1 = regToStack.get(reg);
						mem1 = new Memory(new Constant(addr1),rbp);
						Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
						added.add(movToReg1);
					} else {
						int addr1 = -8*++stackCounter;
						mem1 = new Memory(new Constant(addr1),rbp);
						regToStack.put(reg,addr1);
					}
					currentInstruction.setDest(r11);
					movToMem1 = new Instruction(Operation.MOVQ,r11,mem1);
				}
				
				Memory memOp = (Memory) src;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Memory newMem;
				
				Instruction movToReg2 = null;
				if (regBase.getType() == RegisterName.TEMP) {
					int addr2 = regToStack.get(regBase.getName());
					Memory mem2 = new Memory(new Constant(addr2),rbp);
					movToReg2 = new Instruction(Operation.MOVQ,mem2,rcx);
				}
				
				if (regOff != null && regOff.getType() == RegisterName.TEMP) {
					// two register operands for memory
					int addr3 = regToStack.get(regOff.getName());
					Memory mem3 = new Memory(new Constant(addr3),rbp);
					Instruction movToReg3 = new Instruction(Operation.MOVQ,mem3,rdx);
					newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
					added.add(movToReg3);
				} else if (regBase.getType() == RegisterName.TEMP) {
					if (regOff == null) {
						newMem = new Memory(cons,rcx);
					} else {
						newMem = new Memory(cons,rcx,regOff,memOp.getConstantFactor());
					}
					added.add(movToReg2);
				} else {
					// both registers are built-in
					newMem = memOp;
				}
				currentInstruction.setSrc(newMem);
				added.add(currentInstruction);
				if (movToMem1 != null) {
					added.add(movToMem1);
				}
			} else {
				// dest is constant, src is not
				Instruction movToReg1;
				if (src instanceof Memory) {
					Memory memOp = (Memory) src;
					Register regBase = memOp.getRegisterBase();
					Register regOff = memOp.getRegisterOffset();
					Constant cons = memOp.getConstantOffset();
					Memory newMem;
					
					int addr1 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					movToReg1 = new Instruction(Operation.MOVQ,mem1,rcx);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,rdx);
						newMem = new Memory(cons,rcx,rdx,memOp.getConstantFactor());
						added.add(movToReg2);
					} else if (regBase.getType() == RegisterName.TEMP) {
						if (regOff == null) {
							newMem = new Memory(cons,rcx);
						} else {
							newMem = new Memory(cons,rcx,regOff,memOp.getConstantFactor());
						}
						added.add(movToReg1);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
					currentInstruction.setSrc(newMem);
				} else {
					// src uses a built-in register
					if (((Register) dest).getType() != RegisterName.TEMP) {
						added.add(currentInstruction);
						added.addAll(addNecessaryInstruction(
								instructions.subList(1,size),regToStack));
						return added;
					}
					// src uses a temp register
					int addr1 = regToStack.get(((Register) src).getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					movToReg1 = new Instruction(Operation.MOVQ,mem1,r11);
					currentInstruction.setSrc(r11);
					added.add(movToReg1);
				}
				added.add(currentInstruction);
			}
		}
		added.addAll(addNecessaryInstruction(
				instructions.subList(1,size),regToStack));
		return added;
	}
	
	private Tile createEpilogueTile(IRCall call) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		int numArgs = call.args().size();
		int numReturns  = call.getNumReturns();

		// restore caller-save registers 
		int argOffset = Math.max(0, numArgs-6);
		int numCallerReg = CALLER_REG_LIST.length;
		Register rsp = new Register(RegisterName.RSP);
		for (int i = 0; i < numCallerReg; i++) {
			Constant offset = new Constant(8*(argOffset + numCallerReg - 1 - i));
			Memory mem = new Memory(offset, rsp);
			// "movq offset(rsp) caller_saved_reg"
			Instruction instr = new Instruction(Operation.MOVQ, mem, new Register(CALLER_REG_LIST[i]));
			instructions.add(instr);
		}
		
		// back to original rsp
		Constant offset = new Constant(8*(argOffset + numCallerReg));
		// "addq offset rsp" 
		Instruction instr = new Instruction(Operation.ADDQ, offset, rsp);
		instructions.add(instr);
		
		// calculate total cost
		int totalCost = 0;
		for (Instruction i : instructions) {
			totalCost = i.getCost();
		}
		
		return new Tile(instructions, totalCost);
	}
}
