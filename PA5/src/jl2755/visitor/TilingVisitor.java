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
import jl2755.assembly.Tile.tileEnum;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	private IRTreeEqualsVisitor cmpTreeVisitor;
	private int registerCount = 0;

	private List<Tile> tileLibrary;
	
	private int stackCounter = 0;
	private HashMap<String, Integer> functionSpaceMap = new HashMap<String, Integer>();
	private String currentFunction;
	
	/** list of first 6 function call arg registers */
	private static final String[] ARG_REG_LIST = {
			"rdi", "rsi", "rdx", "rcx", "r8", "r9"
	};
	
	/** list of callee-saved registers (except rbp) */
	private static final String[] CALLEE_REG_LIST = {
			"rdi", "rsi", "rbx", "r12", "r13", "r14", "r15"
	};
	
	/** list of caller-saved registers */
	private static final String[] CALLER_REG_LIST = {
			"rax", "rcx", "rdx", "r8", "r9", "r10", "r11"
	};
	// shuttle regs: rcx, rdx, r11
	
	
	/** Lists of strings representing possible tiles. */
	// TODO: put all these in a json file and read the json file to populate patternMap

	// BINOP
	private final List<String> BINOP_PRE = new ArrayList<String>(
			Arrays.asList(
					"BinOp"
					));
	private final List<String> BINOP_IN = new ArrayList<String>(
			Arrays.asList(
					"BinOp"
					));
	
	// Mem
	private final List<String> MEM_PRE = new ArrayList<String>(
			Arrays.asList(
					"Mem"
					));
	private final List<String> MEM_IN = new ArrayList<String>(
			Arrays.asList(
					"Mem"
					));
	
	/** Case of just Add between register and constant of the form k(r1)*/
	private final List<String> MEMBASEANDOFFSET_PRE = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddRightConstantOffsetLeftRegisterBase1",
					"null",
					"Const"
					));
	
	private final List<String> MEMBASEANDOFFSET_IN = new ArrayList<String>(
			Arrays.asList(
					"null",
					"BinOpAddRightConstantOffsetLeftRegisterBase1",
					"Const",
					"Mem"
					));

	/** Case of Add between 2 Registers and a Constant of the form k(r1,r2) */
	private final List<String> MEMBASEANDREGISTEROFFSET_PRE1 = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddLeftConstantOffset1",
					"Const",
					"BinOpAddLeftRegisterOffsetRightRegisterBase2",
					"null1",
					"null2"
					));
	
	private final List<String> MEMBASEANDREGISTEROFFSET_IN1 = new ArrayList<String>(
			Arrays.asList(
					"Const",
					"BinOpAddLeftConstantOffset1",
					"null1",
					"BinOpAddLeftRegisterOffsetRightRegisterBase2",
					"null2",
					"Mem"
					));
	
	/** Same case as above but in different order */
	private final List<String> MEMBASEANDREGISTEROFFSET_PRE2 = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddLeftRegisterBase",
					"null1",
					"BinOpAddLeftConstantOffset1",
					"Const",
					"null2"));
	
	private final List<String> MEMBASEANDREGISTEROFFSET_IN2 = new ArrayList<String>(
			Arrays.asList(
					"null1",
					"BinOpAddLeftRegisterBase",
					"Const",
					"BinOpAddLeftConstantOffset1",
					"null2",
					"Mem"
					));
	
	/** Case of Add between a Register and a Mult between a Register and ConstantFactor
	 * of the form (r1,r2,i)
	 */
	private final List<String> MEMBASEANDREGISTERFACTOR_PRE = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddLeftRegisterBase",
					"null1",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"null2",
					"Const"
					));
	
	private final List<String> MEMBASEANDREGISTERFACTOR_IN = new ArrayList<String>(
			Arrays.asList(
					"null1",
					"BinOpAddLeftRegisterBase",
					"null2",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"Const",
					"Mem"
					));
	
	/** Case of Add between a Constant, Register, and a Mult between a Register
	 * and ConstantFactor of the form k(r1,r2,i)
	 */
	private final List<String> MEMEVERYTHING_PRE1 = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddLeftConstantOffset",
					"Const1",
					"BinOpAddLeftRegisterBase",
					"null1",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"null2",
					"Const2"
					));
	
	private final List<String> MEMEVERYTHING_IN1 = new ArrayList<String>(
			Arrays.asList(
					"Const1",
					"BinOpAddLeftConstantOffset",
					"null1",
					"BinOpAddLeftRegisterBase",
					"null2",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"Const2",
					"Mem"
					));
	
	/** Same case as above in different order */
	private final List<String> MEMEVERYTHING_PRE2 = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAddLeftRegisterBase",
					"null1",
					"BinOpAddLeftConstantOffset",
					"Const1",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"null2",
					"Const2"
					));
	
	private final List<String> MEMEVERYTHING_IN2 = new ArrayList<String>(
			Arrays.asList(
					"null1",
					"BinOpAddLeftRegisterBase",
					"Const1",
					"BinOpAddLeftConstantOffset",
					"null2",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"Const2",
					"Mem"
					));
	
	/** Same case as above in different order */
	private final List<String> MEMEVERYTHING_PRE3 = new ArrayList<String>(
			Arrays.asList(
					"Mem",
					"BinOpAdd",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"null1",
					"Const1",
					"BinOpAddLeftConstantOffsetRightRegisterBase",
					"Const2",
					"null2"
					));
	
	private final List<String> MEMEVERYTHING_IN3 = new ArrayList<String>(
			Arrays.asList(
					"null1",
					"BinOpMultLeftRegisterOffsetRightConstantFactor",
					"Const1",
					"BinOpAdd",
					"Const2",
					"BinOpAddLeftConstantOffsetRightRegisterBase",
					"null2",
					"Mem"
					));
	
	// MEM(BINOP(ADD, TEMP, BINOP(ADD, BINOP(MUL, TEMP, CONST), CONST)))
	// eg: mov mem(%ebx + (%ecx*w + k)), %eax
	
	
	public TilingVisitor() {
		cmpTreeVisitor = new IRTreeEqualsVisitor(this);
		
		tileLibrary = new ArrayList<Tile>();
		List<Instruction> emptyInstructions1 = new ArrayList<Instruction>();
		Tile tile1 = new Tile(MEM_IN,MEM_PRE,emptyInstructions1,0,tileEnum.BASEONLY);
		
		List<Instruction> emptyInstructions2 = new ArrayList<Instruction>();
		Tile tile2 = new Tile(MEMBASEANDOFFSET_PRE,MEMBASEANDOFFSET_IN,emptyInstructions2,0,
				tileEnum.BASEWITHCONSTANTOFFSET);
		
//		List<Instruction> emptyInstructions3 = new ArrayList<Instruction>();
//		Tile tile3 = new Tile(MEMBASEANDREGISTEROFFSET_PRE1,MEMBASEANDREGISTEROFFSET_IN1,
//				emptyInstructions3,0,tileEnum.BASEWITHBOTHOFFSET);
//		
//		List<Instruction> emptyInstructions4 = new ArrayList<Instruction>();
//		Tile tile4 = new Tile(MEMBASEANDREGISTEROFFSET_PRE2,MEMBASEANDREGISTEROFFSET_IN2,
//				emptyInstructions4,0,tileEnum.BASEWITHBOTHOFFSET);
//		
//		List<Instruction> emptyInstruction5 = new ArrayList<Instruction>();
//		Tile tile5 = new Tile(MEMBASEANDREGISTERFACTOR_PRE,MEMBASEANDREGISTERFACTOR_IN,
//				emptyInstruction5,0,tileEnum.BASEWITHREGISTEROFFSETANDFACTOR);
//		
//		List<Instruction> emptyInstruction6 = new ArrayList<Instruction>();
//		Tile tile6 = new Tile(MEMEVERYTHING_PRE1,MEMEVERYTHING_IN1,
//				emptyInstruction6,0,tileEnum.EVERYTHING);
//		
//		List<Instruction> emptyInstruction7 = new ArrayList<Instruction>();
//		Tile tile7 = new Tile(MEMEVERYTHING_PRE2,MEMEVERYTHING_IN2,
//				emptyInstruction7,0,tileEnum.EVERYTHING);
//		
//		List<Instruction> emptyInstruction8 = new ArrayList<Instruction>();
//		Tile tile8 = new Tile(MEMEVERYTHING_PRE3,MEMEVERYTHING_IN3,
//				emptyInstruction8,0,tileEnum.EVERYTHING);
		
		
		tileLibrary = new ArrayList<Tile>();
		tileLibrary.add(tile1);
		tileLibrary.add(tile2);
//		tileLibrary.add(tile3);
//		tileLibrary.add(tile4);
//		tileLibrary.add(tile5);
//		tileLibrary.add(tile6);
//		tileLibrary.add(tile7);
//		tileLibrary.add(tile8);
	}
	
	public String parseTiles(IRNode argNode) {
		tileMap = new HashMap<IRNode, Tile>();
		argNode.accept(this);
		Tile temp = tileMap.get(argNode);
		List<Instruction> instructions = temp.getInstructions();
		
		/* Create assembly code */
		String assemblyString = "\t.text\n";
		boolean isFirstFunction = true;
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			if (instr.getOp() == Operation.LABEL) {
				Label label = (Label) instr.getDest();
				String labelString = label.toString();
				// if it's a function label 
				if (labelString.charAt(0) == '_') {
					if (!isFirstFunction) {
						assemblyString += "\n";
					} else {
						isFirstFunction = false;
					}
					assemblyString += "\t.globl\t" + labelString + "\n";
					assemblyString += "\t.align\t4\n";
				}
				assemblyString += instr.toString() + ":\n";
			} else {
				assemblyString += "\t" + instr.toString() + "\n";
			}
		}
		
		return assemblyString;
	}
	
	public Tile getTileOfNode(IRNode argNode) {
		return tileMap.get(argNode);
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
		
		Tile leftTile = tileMap.get(left);
		Tile rightTile = tileMap.get(right);
		Operand leftOperand = leftTile.getDest();
		Operand rightOperand = rightTile.getDest();
		
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
			Instruction compare;
			if (dest instanceof Constant) {
				Register cons = new Register(RegisterName.R9);
				compare = new Instruction(Operation.MOVQ,dest,cons);
				instrList.add(compare);
				cost += compare.getCost();
				compare = new Instruction(Operation.CMPQ,src,cons);
			} else {
				compare = new Instruction(Operation.CMPQ,src,dest);
			}
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
		Tile currTile = new Tile(instrList, cost, argDest);
		Tile mergeChildren = Tile.mergeTiles(leftTile, rightTile);
		Tile tile = Tile.mergeTiles(mergeChildren, currTile);
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
		int num8ByteSpace = 0;
		
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
			num8ByteSpace++;
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
			num8ByteSpace += numReturns - 2;
		}
		
		/* Push argn...arg7 (or arg6 if m > 2) */
		if (numArgs > numArgRegs) {
			for (int i = numArgs-1; i >= numArgRegs; i--) {
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
				num8ByteSpace++;
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
		
		// make rsp 16byte aligned
		if (num8ByteSpace % 2 == 1) {
			Constant c = new Constant(8);
			Register rsp = new Register(RegisterName.RSP);
			Instruction extraSpace = new Instruction(Operation.SUBQ, c, rsp);
			tempInstructions.add(extraSpace);
			num8ByteSpace++;
		}
		call.setNum8ByteSpace(num8ByteSpace);
		
		// "callq targetDest"
		Label targetDest = (Label)targetTile.getDest();
		targetDest.setLabelName(targetDest.toString());
//		targetDest.setLabelName("FUNC("+targetDest.toString().substring(1)+")");
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
			
			instructions.addAll(leftTile.getInstructions());
			instructions.addAll(rightTile.getInstructions());
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
				if (tempDest instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempSrc,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				}
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
				if (tempDest instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempSrc,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				}
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
				if (tempDest instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempSrc,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDest);
				}
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
				if (tempSrc instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempSrc,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDest,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				}
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
				if (tempSrc instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempSrc,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDest,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				}
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
				if (tempSrc instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempSrc,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDest,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				}
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
				if (tempSrc instanceof Constant) {
					Register cons = new Register(RegisterName.R9);
					tempInst = new Instruction(Operation.MOVQ,tempSrc,cons);
					instructions.add(tempInst);
					cost += tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDest,cons);
				} else {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				}
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
		
		// Register/Stack allocation
		stackAllocation(cu);
			
		// Set parameters of all function decls
		Tile cuTile = tileMap.get(cu);
		List<Instruction> instructions = cuTile.getInstructions();
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			// if curr instr is a function label
			if (instr.getOp() == Operation.LABEL && 
				((Label)instr.getDest()).getLabelName().charAt(0) == '_') {
				String fnName = ((Label)instr.getDest()).getLabelName();
				Instruction enter = instructions.get(++i);
				// complete "enter 8*l, 0"
				Constant space = new Constant(8*(functionSpaceMap.get(fnName)));
				enter.setSrc(space);
			}
		}
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
		Label fnLabel = new Label(fd.name());
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
		if (body instanceof IRSeq) {
			List<IRStmt> bodyStmtList = ((IRSeq) body).stmts();
			
			// prologue
			// precondition: first n stmts are moving n arg stmts
			// remove duplicate move(%ARG, %arg) instructions
			// TODO fix
			for (int i = 0; i < numArgs; i++) {
				bodyStmtList.remove(0);
			}	
		}
		
		// tile function body
		body.accept(this);
		Tile bodyTile = tileMap.get(body);
		// replace _RETi registers with correct ones
		replaceReturnRegisters(bodyTile);		
		instructions.addAll(bodyTile.getInstructions());
		
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
		ArrayList<ArrayList<Operand>> operandOfEachChildren = new ArrayList<ArrayList<Operand>>();
		for (int i = 0; i < tileLibrary.size(); i++) {
			if (cmpTreeVisitor.equalTrees(tileLibrary.get(i).getRootOfSubtree(), 
					mem)) {
				matchingTiles.add(new Tile(mem,tileLibrary.get(i)));
//				System.out.println(mem);
//				System.out.println("Last added Tile " + matchingTiles.get(matchingTiles.size()-1).getDest());
				childrenOfEachTile.add((ArrayList<IRNode>) cmpTreeVisitor.getAllChildrenNode());
//				System.out.println(childrenOfEachTile);
				operandOfEachChildren.add((ArrayList<Operand>) cmpTreeVisitor.getOperandOfNodesInTile());
			}
		}
		
		// Fill in Tiles' Operands
		
//		System.out.println(matchingTiles.size());
//		Thread.dumpStack();
		
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
			assert(numReturns <= 1);
			
			Tile epilogueTile = createEndCallTile((IRCall)mov.expr());
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
				boolean[] returnBoolList = call.getReturnBoolList();
				int numReturns = call.getNumReturns();
				int numSkips = 0;
				
				Tile masterTile = new Tile(new ArrayList<Instruction>(), 0);
				
				// move return values to the variable registers
				// temp Instruction list to be added to the masterTile later
				List<Instruction> tempInstructions = new ArrayList<Instruction>();
				for (int j = 0; j < numReturns; j++) {
					// skip _
					if (!returnBoolList[j]) {
						numSkips++;
						continue;
					}
					IRMove move = (IRMove) stmtList.get(i+j+1-numSkips);
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
				
				Tile epilogueTile = createEndCallTile(call);
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
//				if (op == Operation.LABEL && label.toString().contains("FUNC(")) {
				if (op == Operation.LABEL && label.toString().charAt(0) == '_') {
					functionSpaceMap.put(currentFunction,stackCounter);
					currentFunction = label.toString();
					stackCounter = 0;
				}
				added.add(currentInstruction);
			}
		} else {
			// src is not null and is not constant
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
						System.out.println(currentInstruction);
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
					assert(regBase != null);
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
					if (((Register) src).getType() != RegisterName.TEMP) {
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
	
	private Tile createEndCallTile(IRCall call) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		int numArgs = call.args().size();

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
		Constant offset = new Constant(8*call.getNum8ByteSpace());
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
	
	/**
	 * Replaces _RETi registers with the right registers
	 * @param functionTile
	 */
	private void replaceReturnRegisters(Tile functionTile) {
		List<Instruction> instructions = functionTile.getInstructions();
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			// TODO we can probably optimize this
			// currently i'm looking for any instruction that has 
			// _RETi registers as dest

			Operand dest = instr.getDest();
			Operand src = instr.getSrc();
			
			// src is %_RETi
			if (src instanceof Register && ((Register) src).getName().contains("_RET")) {
				Register reg = (Register) src;
				String regName = reg.getName();
				int regNum = Integer.parseInt(regName.substring(4, regName.length()));
				Operand returnOperand;
				Instruction shuttleInstruction = null;
				if (regNum == 0) {
					returnOperand = new Register(RegisterName.RAX);
				} else if (regNum == 1) {
					returnOperand = new Register(RegisterName.RDX);
				} else {
					Register rdi = new Register(RegisterName.RDI);
					Constant offset = new Constant(8*(regNum-2));
					returnOperand = new Memory(offset, rdi);
					
					Operand destOperand = instr.getSrc();
					if (destOperand instanceof Memory) {
						Register r11 = new Register(RegisterName.R11);
						shuttleInstruction = new Instruction(Operation.MOVQ, returnOperand, r11);
						returnOperand = r11;
					}
				}
				instr.setSrc(returnOperand);
				// replace the old instruction
				instructions.set(i, instr);
				// insert a shuttle instruction if needed
				if (shuttleInstruction != null) {
					instructions.add(i++, shuttleInstruction);
				}
			}
			
			// dest is %_RETi
			if (dest instanceof Register && ((Register) dest).getName().contains("_RET")) {
				Register reg = (Register) dest;
				String regName = reg.getName();
				int regNum = Integer.parseInt(regName.substring(4, regName.length()));
				Operand returnOperand;
				Instruction shuttleInstruction = null;
				if (regNum == 0) {
					returnOperand = new Register(RegisterName.RAX);
				} else if (regNum == 1) {
					returnOperand = new Register(RegisterName.RDX);
				} else {
					Register rdi = new Register(RegisterName.RDI);
					Constant offset = new Constant(8*(regNum-2));
					returnOperand = new Memory(offset, rdi);
					
					Operand srcOperand = instr.getSrc();
					if (srcOperand instanceof Memory) {
						Register r11 = new Register(RegisterName.R11);
						shuttleInstruction = new Instruction(Operation.MOVQ, srcOperand, r11);
						instr.setSrc(r11);
					}
				}
				instr.setDest(returnOperand);
				// replace the old instruction
				instructions.set(i, instr);
				// insert a shuttle instruction if needed
				if (shuttleInstruction != null) {
					instructions.add(i++, shuttleInstruction);
				}
			}
		}
		functionTile.setInstructions(instructions);
	}
	
}
