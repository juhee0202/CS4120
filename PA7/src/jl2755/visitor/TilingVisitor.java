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
import jl2755.optimization.RegisterAllocator;

public class TilingVisitor implements IRTreeVisitor {

	private HashMap<IRNode, Tile> tileMap;
	private IRTreeEqualsVisitor cmpTreeVisitor;
	private int registerCount = 0;
	private int labelCount = 0;

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
			"rbx", "rbp", "r12", "r13", "r14", "r15"
	};
	
	/** list of caller-saved registers */
	// TODO: temporarily added rdi and rsi here but i should refactor.
	private static final String[] CALLER_REG_LIST = {
			"rax", "rcx", "rdx", "r8", "r9", "r10", "r11", "rdi", "rsi"
	};
	// shuttle regs: rcx, rdx, r11
	
	private boolean Oreg;
	private boolean Omc;
	
	public TilingVisitor(boolean reg, boolean mc) {
		tileMap = new HashMap<IRNode, Tile>();
		Oreg = reg;
		Omc = mc;
	}
	
	public String parseTiles(IRNode argNode) {
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

	// TODO : remove asserts
	public Tile computeTile(IRBinOp node) {
//		Map<IRBinOp, Tile> nodeToTile = IRBinOp.getNodeToTile();
		if (tileMap.containsKey(node)) {
			return tileMap.get(node);
		}
		Tile newTile = null;
		List<Instruction> insts = new ArrayList<Instruction>();
		int cost = 0;
		Register dest = null;
		IRExpr left = node.left();
		IRExpr right = node.right();
		IRExpr regBase;
		IRExpr regOff;
		Register base;
		Register off;
		Tile baseTile;
		Tile offTile;
		Constant constOff;
		Constant constFac;
		Memory mem;
		Instruction lea;
		switch (node.getIndex()) {
		case 3:
			IRBinOp child;
			if (left instanceof IRBinOp) {
				child = (IRBinOp) left;
				constOff = new Constant(((IRConst) right).value());
			} else {
				assert(right instanceof IRBinOp);
				child = (IRBinOp) right;
				constOff = new Constant(((IRConst) left).value());
			}
			regBase = child.left();
			regOff = child.right();
			regBase.accept(this);
			regOff.accept(this);
			baseTile = tileMap.get(regBase);
			offTile = tileMap.get(regOff);
			dest = new Register("tileRegister" + registerCount++);
			cost = baseTile.getCost() + offTile.getCost() + 1;
			
			// Tile child
			child.accept(this);
			Tile childTile = tileMap.get(child);
			if (childTile.getCost() + 2 < cost) {
				cost = childTile.getCost() + 2;
				Instruction mov = new Instruction(Operation.MOVQ, 
						childTile.getDest(), dest);
				Instruction add = new Instruction(Operation.ADDQ, constOff, dest);
				insts.add(mov);
				insts.add(add);
				newTile = new Tile(insts, 2, dest);
				newTile = Tile.mergeTiles(newTile, childTile);
			} else {
				base = (Register) baseTile.getDest();
				off = (Register) offTile.getDest();
				mem = new Memory(constOff, base, off);
				lea = new Instruction(Operation.LEAQ, mem, dest);
				insts.add(lea);
				newTile = new Tile(insts, 1, dest);
				newTile = Tile.mergeTiles(newTile, baseTile);
				newTile = Tile.mergeTiles(newTile, offTile);
			}
			break;
		case 4:
			IRBinOp child1;
			if (left instanceof IRBinOp) {
				child1 = (IRBinOp) left;
				regBase = right;
				
			} else {
				assert(right instanceof IRBinOp);
				child1 = (IRBinOp) right;
				regBase = left;
			}
			if (child1.left() instanceof IRConst) {
				constOff = new Constant(((IRConst) child1.left()).value());
				regOff = child1.right();
			} else {
				assert(child1.right() instanceof IRConst);
				constOff = new Constant(((IRConst) child1.right()).value());
				regOff = child1.left();
			}
			regBase.accept(this);
			regOff.accept(this);
			baseTile = tileMap.get(regBase);
			offTile = tileMap.get(regOff);
			dest = new Register("tileRegister" + registerCount++);
			cost = baseTile.getCost() + offTile.getCost() + 1;
			
			// Tile child
			child1.accept(this);
			Tile childTile1 = tileMap.get(child1);
			if (childTile1.getCost() + baseTile.getCost() + 2 < cost) {
				cost = childTile1.getCost() + baseTile.getCost() + 2;
				Instruction mov = new Instruction(Operation.MOVQ, 
						childTile1.getDest(), dest);
				Instruction add = new Instruction(Operation.ADDQ, 
						baseTile.getDest(), dest);
				insts.add(mov);
				insts.add(add);
				newTile = new Tile(insts, 2, dest);
				newTile = Tile.mergeTiles(newTile, baseTile);
				newTile = Tile.mergeTiles(newTile, childTile1);
			} else {
				base = (Register) baseTile.getDest();
				off = (Register) offTile.getDest();
				mem = new Memory(constOff, base, off);
				lea = new Instruction(Operation.LEAQ, mem, dest);
				insts.add(lea);
				newTile = new Tile(insts, 1, dest);
				newTile = Tile.mergeTiles(newTile, baseTile);
				newTile = Tile.mergeTiles(newTile, offTile);
			}
			break;
		case 5:
			IRBinOp child2;
			if (left instanceof IRBinOp) {
				child2 = (IRBinOp) left;
				constOff = new Constant(((IRConst) right).value());
			} else {
				assert(right instanceof IRBinOp);
				child2 = (IRBinOp) right;
				constOff = new Constant(((IRConst) left).value());
			}
			if (child2.left() instanceof IRConst) {
				constFac = new Constant(((IRConst) child2.left()).value());
				regOff = child2.right();
			} else {
				assert(child2.right() instanceof IRConst);
				constFac = new Constant(((IRConst) child2.right()).value());
				regOff = child2.left();
			}
			regOff.accept(this);
			offTile = tileMap.get(regOff);
			dest = new Register("tileRegister" + registerCount++);
			cost = offTile.getCost() + 1;
			
			// Tile child
			child2.accept(this);
			Tile childTile2 = tileMap.get(child2);
			if (childTile2.getCost() + 2 < cost) {
				cost = childTile2.getCost() + 2;
				Instruction mov = new Instruction(Operation.MOVQ, 
						childTile2.getDest(), dest);
				Instruction add = new Instruction(Operation.ADDQ, constOff, dest);
				insts.add(mov);
				insts.add(add);
				newTile = new Tile(insts, 2, dest);
				newTile = Tile.mergeTiles(newTile, childTile2);
			} else {
				off = (Register) offTile.getDest();
				mem = new Memory(constOff, off, constFac);
				lea = new Instruction(Operation.LEAQ, mem, dest);
				insts.add(lea);
				newTile = new Tile(insts, 1, dest);
				newTile = Tile.mergeTiles(newTile, offTile);
			}
			break;
		case 6:
			IRBinOp child3;
			if (left instanceof IRBinOp) {
				child3 = (IRBinOp) left;
				regBase = right;
				
			} else {
				assert(right instanceof IRBinOp);
				child3 = (IRBinOp) right;
				regBase = left;
			}
			if (child3.left() instanceof IRConst) {
				constFac = new Constant(((IRConst) child3.left()).value());
				regOff = child3.right();
			} else {
				assert(child3.right() instanceof IRConst);
				constFac = new Constant(((IRConst) child3.right()).value());
				regOff = child3.left();
			}
			regBase.accept(this);
			regOff.accept(this);
			baseTile = tileMap.get(regBase);
			offTile = tileMap.get(regOff);
			dest = new Register("tileRegister" + registerCount++);
			cost = baseTile.getCost() + offTile.getCost() + 1;
			
			// Tile child
			child3.accept(this);
			Tile childTile3 = tileMap.get(child3);
			if (childTile3.getCost() + baseTile.getCost() + 2 < cost) {
				cost = childTile3.getCost() + baseTile.getCost() + 2;
				Instruction mov = new Instruction(Operation.MOVQ, 
						childTile3.getDest(), dest);
				Instruction add = new Instruction(Operation.ADDQ, 
						baseTile.getDest(), dest);
				insts.add(mov);
				insts.add(add);
				newTile = new Tile(insts, 2, dest);
				newTile = Tile.mergeTiles(newTile, baseTile);
				newTile = Tile.mergeTiles(newTile, childTile3);
			} else {
				base = (Register) baseTile.getDest();
				off = (Register) offTile.getDest();
				mem = new Memory(base, off, constFac);
				lea = new Instruction(Operation.LEAQ, mem, dest);
				insts.add(lea);
				newTile = new Tile(insts, 1, dest);
				newTile = Tile.mergeTiles(newTile, baseTile);
				newTile = Tile.mergeTiles(newTile, offTile);
			}
			break;
		case 7:
			IRBinOp child4;
			if (left instanceof IRBinOp) {
				child4 = (IRBinOp) left;
				constOff = new Constant(((IRConst) right).value());
				
			} else {
				assert(right instanceof IRBinOp);
				child4 = (IRBinOp) right;
				constOff = new Constant(((IRConst) left).value());
			}
			IRBinOp grandChild4;
			if (child4.left() instanceof IRBinOp) {
				grandChild4 = (IRBinOp) child4.left();
				regBase = child4.right();
			} else {
				assert(child4.right() instanceof IRBinOp);
				grandChild4 = (IRBinOp) child4.left();
				regBase = child4.left();
			}
			if (grandChild4.left() instanceof IRConst) {
				constFac = new Constant(((IRConst) grandChild4.left()).value());
				regOff = grandChild4.right();
			} else {
				assert(grandChild4.right() instanceof IRConst);
				constFac = new Constant(((IRConst) grandChild4.right()).value());
				regOff = grandChild4.left();
			}
			regBase.accept(this);
			regOff.accept(this);
			
			
			break;
		case 8:
			
		case 9:
			IRBinOp leftBin = (IRBinOp) left;
			IRBinOp rightBin = (IRBinOp) right;
			
			IRExpr regBase6;
			IRExpr regOff6;
			Constant constOff6;
			Constant constFac6;
			if (leftBin.opType() == OpType.ADD) {
				assert(rightBin.opType() == OpType.MUL);
				if (leftBin.left() instanceof IRConst) {
					regBase6 = leftBin.right();
//					constOff6 = 
				} else {
					assert(leftBin.right() instanceof IRConst);
				}
			} else {
				assert(leftBin.opType() == OpType.MUL);
				assert(rightBin.opType() == OpType.ADD);
			}
		case -1:
		case 0:
		case 1:
		case 2:
		default:
			return null;
		}
		tileMap.put(node, newTile);
		return newTile;
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
			op == OpType.MUL ||
			op == OpType.XOR)
		{
			
			Register t = new Register("tileRegister" + registerCount++);
			Instruction movToFreshTemp = new Instruction(Operation.MOVQ, leftOperand, t);
			Instruction binopInstr = new Instruction(tileOp, rightOperand, t);
			instrList.add(movToFreshTemp);
			instrList.add(binopInstr);
			cost = 2;
			argDest = t;
			
//			Instruction lea = createLeaInstruction(bo);
		
		}
		/*
		 * Short circuit logical binary operations
		 */
		else if (op == OpType.AND) {
			Register result = new Register("tileRegister" + registerCount++);
			Instruction moveLeftToTemp = new Instruction(Operation.MOVQ, leftOperand, result);
			Instruction test1 = new Instruction(Operation.TESTQ, result, result);
			Label fLabel = new Label("tileLabel" + labelCount++);
			Instruction labelInst = new Instruction(Operation.LABEL, fLabel);
			Instruction jump = new Instruction(Operation.JZ, fLabel);
			Instruction andLeftAndRight = new Instruction(Operation.ANDQ, rightOperand, result);			
			instrList.add(moveLeftToTemp);
			instrList.add(test1);
			instrList.add(jump);
			instrList.add(andLeftAndRight);
			instrList.add(labelInst);
			cost = 5;
			argDest = result;
			
		} else if (op == OpType.OR) {
			Register result = new Register("tileRegister" + registerCount++);
			Instruction moveLeftToTemp = new Instruction(Operation.MOVQ, leftOperand, result);
			Instruction test1 = new Instruction(Operation.TESTQ, result, result);
			Label fLabel = new Label("tileLabel" + labelCount++);
			Instruction labelInst = new Instruction(Operation.LABEL, fLabel);
			Instruction jump = new Instruction(Operation.JNZ, fLabel);
			Instruction orLeftAndRight = new Instruction(Operation.ORQ, rightOperand, result);			
			instrList.add(moveLeftToTemp);
			instrList.add(test1);
			instrList.add(jump);
			instrList.add(orLeftAndRight);
			instrList.add(labelInst);
			cost = 5;
			argDest = result;
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
			argDest = new Register(RegisterName.RDX);
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
				Register cons = new Register("tileRegister" + registerCount++);
				Instruction move = new Instruction(Operation.MOVQ,dest,cons);
				instrList.add(move);
				cost += move.getCost();
				compare = new Instruction(Operation.CMPQ,cons,src);
			} else {
				compare = new Instruction(Operation.CMPQ,dest,src);
			}
			instrList.add(compare);
			cost++;
			
			Register result = new Register("tileRegister" + registerCount++);
			
			if (op == OpType.EQ) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmove = new Instruction(Operation.CMOVE, freshOne.getNewOperand(), result.getNewOperand());	    // dest = 1 if equal
				Instruction cmovne = new Instruction(Operation.CMOVNE, freshZero, result);      // dest = 0 if not equal
				instrList.add(cmove);
				instrList.add(cmovne);
				cost += 4;
			}
			else if (op == OpType.NEQ) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmovne = new Instruction(Operation.CMOVNE, freshOne, result);		// dest = 1 if not equal
				Instruction cmove = new Instruction(Operation.CMOVE, freshZero, result);        // dest = 0 if equal
				instrList.add(cmovne);
				instrList.add(cmove);
				cost += 4;
			}
			else if (op == OpType.LT) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmovl = new Instruction(Operation.CMOVL, freshOne, result);	    // dest = 1 if equal
				Instruction cmovege = new Instruction(Operation.CMOVGE, freshZero, result);     // dest = 0 if not equal
				instrList.add(cmovl);
				instrList.add(cmovege);
				cost += 4;
			}
			else if (op == OpType.GT) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmovg = new Instruction(Operation.CMOVG, freshOne, result);	    // dest = 1 if equal
				Instruction cmovele = new Instruction(Operation.CMOVLE, freshZero, result);     // dest = 0 if not equal
				instrList.add(cmovg);
				instrList.add(cmovele);
				cost += 4;
			}
			else if (op == OpType.LEQ) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmovle = new Instruction(Operation.CMOVLE, freshOne, result);	    // dest = 1 if <=
				Instruction cmovg = new Instruction(Operation.CMOVG, freshZero, result);     	// dest = 0 if >
				instrList.add(cmovle);
				instrList.add(cmovg);
				cost += 4;
			}
			else if (op == OpType.GEQ) {
				Register freshOne = new Register("tileRegister" + registerCount++);
				Register freshZero = new Register("tileRegister" + registerCount++);
				Instruction moveOne = new Instruction(Operation.MOVQ, new Constant(1), freshOne);
				Instruction moveZero = new Instruction(Operation.MOVQ, new Constant(0), freshZero);
				instrList.add(moveOne);
				instrList.add(moveZero);
				
				Instruction cmovge = new Instruction(Operation.CMOVGE, freshOne, result);	    // dest = 1 if equal
				Instruction cmovl = new Instruction(Operation.CMOVL, freshZero, result);     // dest = 0 if not equal
				instrList.add(cmovge);
				instrList.add(cmovl);
				cost += 4;
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
			Register temp = new Register(RegisterName.R11);
			
			// create (m-2) words space 
			Constant c = new Constant(8*(numReturns - 2));
			// "subq $(8*(m-2)) rsp"
			Instruction instr2 = new Instruction(Operation.SUBQ, c, rsp);
			tempInstructions.add(instr2);
			numArgRegs--;	// because rdi is used to store ret3
			num8ByteSpace += numReturns - 2;
			
			// "movq rsp rdi"
			Instruction makeTempForRsp = new Instruction(Operation.MOVQ, rsp, rdi);
			tempInstructions.add(makeTempForRsp);
			// "addq 8*(m-2)-8 rdi"
			Constant offsetFromRsp = new Constant(8*(numReturns-2) - 8);
			if (offsetFromRsp.getValue() != 0) {
				Instruction computeRet3MemAddr = new Instruction(Operation.ADDQ, offsetFromRsp, rdi);
				tempInstructions.add(computeRet3MemAddr);
			}
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
			call.setHasExtra8ByteSpace(true);
			num8ByteSpace++;
		}
		call.setNum8ByteSpace(num8ByteSpace);
		
		// "callq targetDest"
		Label targetDest = (Label)targetTile.getDest();
		targetDest.setLabelName(targetDest.toString());
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
			tempTile = Tile.mergeTiles(condTile, tempTile);
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
			tempTile = Tile.mergeTiles(condTile, tempTile);
		} else if (condition instanceof IRMem) {
			// Visit condition child
			condition.accept(this);
			Tile condTile = tileMap.get(condition);
			cost = condTile.getCost();
			
			// test mem,1
			tempSrc = new Constant(1);
			tempDest = condTile.getDest();
			tempInst = new Instruction(Operation.TESTQ,tempSrc,tempDest);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			// jnz l
			tempInst = new Instruction(Operation.JNZ,label);
			instructions.add(tempInst);
			cost += tempInst.getCost();
			
			tempTile = new Tile(instructions,cost);
			tempTile = Tile.mergeTiles(condTile, tempTile);
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
				// Tile AND/OR/XOR using IRBinOp
				condition.accept(this);
				Tile condTile = tileMap.get(expr);
				cost = condTile.getCost();
				
				// test result, result
				tempSrc = condTile.getDest();
				tempInst = new Instruction(Operation.TESTQ,tempSrc,tempSrc);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				// jnz l
				tempInst = new Instruction(Operation.JNZ,label);
				instructions.add(tempInst);
				cost += tempInst.getCost();
				
				tempTile = new Tile(instructions, cost);
				tempTile = Tile.mergeTiles(condTile, tempTile);
				break;
			case EQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDestRegister);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
				break;
			case NEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempSrc,tempDestRegister);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
				break;
			case LT:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDestRegister,tempSrc);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,cons,tempSrc);
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
				break;
			case LEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDestRegister,tempSrc);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,cons,tempSrc);
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
				break;
			case GT:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDestRegister,tempSrc);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,cons,tempSrc);
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
				break;
			case GEQ:
				// Visit left and right
				left.accept(this);
				right.accept(this);
				leftTile = tileMap.get(left);
				rightTile = tileMap.get(right);
				tempSrc = leftTile.getDest();
				tempDest = rightTile.getDest();
				
				// cmp e1,e2
				cost = 0;
				if (tempDest instanceof Memory && tempSrc instanceof Memory) {
					Register tempDestRegister = new Register("tileRegister" + registerCount++);
					Instruction moveMemory = new Instruction(Operation.MOVQ,tempDest, tempDestRegister);
					instructions.add(moveMemory);
					cost = moveMemory.getCost();
					tempInst = new Instruction(Operation.CMPQ,tempDestRegister,tempSrc);
				} else if (tempDest instanceof Constant && !(tempSrc instanceof Constant)) {
					tempInst = new Instruction(Operation.CMPQ,tempDest,tempSrc);
				} else if (tempDest instanceof Constant && tempSrc instanceof Constant) {
					Register cons = new Register("tileRegister" + registerCount++);
					tempInst = new Instruction(Operation.MOVQ,tempDest,cons);
					instructions.add(tempInst);
					cost = tempInst.getCost();
					tempInst = new Instruction(Operation.CMPQ,cons,tempSrc);
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
				tempTile = Tile.mergeTiles(leftTile, tempTile);
				tempTile = Tile.mergeTiles(rightTile, tempTile);
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
		
		for (IRFuncDecl fd : cu.functions().values()) {
			fd.accept(this);
		}		
		
		// Register/Stack allocation
//		if (Oreg) {
//			regAllocation(cu);
//		} else {
//			stackAllocation(cu);
//		}
		stackAllocation(cu);
		
		Tile superTile = null;
		
		for (IRFuncDecl fd : cu.functions().values()) {
			if (superTile == null) {
				superTile = tileMap.get(fd);
			} else {
				superTile = Tile.mergeTiles(superTile, tileMap.get(fd));
			}
		}		
		tileMap.put(cu, superTile);
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
		
		/* Label */
		Label fnLabel = new Label(fd.getABIName(), true);
		instructions.add(new Instruction(Operation.LABEL, fnLabel));
		
		/* Prologue */
		// TODO: replace enter with push/mov/sub for optimization
		// "enter 8*l, 0" 8*l will be filled in later
		Instruction enter = new Instruction(Operation.ENTER, new Constant(0), new Constant(0));
		instructions.add(enter);

		// save callee-saved registers
		int currFnNumSpace = 0;
		for (int i = 0; i < CALLEE_REG_LIST.length; i++) {
			Register rbp = new Register(RegisterName.RBP);
			Register calleeReg = new Register(CALLEE_REG_LIST[i]);
			Constant offset = new Constant(-8*(i+1));
			Memory mem = new Memory(offset, rbp);
			// "movq calleeReg k(rbp)"
			Instruction instr = new Instruction(Operation.MOVQ, calleeReg, mem);
			instructions.add(instr);
			currFnNumSpace++;
		}
		fd.setNumSavedCalleeRegs(currFnNumSpace);
		
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
			int off = numArgs%2 == 0 ? 3+i-numRegParams : 2+i-numRegParams;
			Memory arg = new Memory(new Constant(8*off), rbp);
			Register param = new Register(paramList.get(i));
			Instruction moveArgtoParam = new Instruction(Operation.MOVQ, arg, 
					 param);
			instructions.add(moveArgtoParam);
		}
		// Body 
		IRStmt body = fd.body();
		if (body instanceof IRSeq) {
			List<IRStmt> bodyStmtList = ((IRSeq) body).stmts();
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
		
		/* Epilogue */
		// restore callee saved registers
		List<Instruction> epilogue = new ArrayList<Instruction>();
		for (int i = 0; i < CALLEE_REG_LIST.length; i++) {
			Register calleeReg = new Register(CALLEE_REG_LIST[i]);
			// "movq k(%rbp), reg"
			Constant memOffset = new Constant(-8*(i+1));
			Memory mem = new Memory(memOffset, rbp);			
			Instruction instr = new Instruction(Operation.MOVQ, mem, calleeReg);
			epilogue.add(instr);
		}
		
		insertEpilogue(instructions, epilogue);
		
		// create a tile for this node
		Tile tile = new Tile(instructions);
		tileMap.put(fd, tile);
	}

	/**
	 * Insert epilogue instructions right before leave; ret instructions.
	 * There is only one leave; ret instructinos
	 * @param instructions
	 * @param epilogue
	 */
	private void insertEpilogue(List<Instruction> instructions, List<Instruction> epilogue) {
		int index = -1;
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			if (instr.getOp() == Operation.LEAVE) {
				index = i;
				break;
			}
		}
		
		instructions.addAll(index,epilogue);
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
		
		if (mem.expr() instanceof IRBinOp) {
			//OPTIMIZE TILING: addressing mode
			Memory memory = createEffectiveMemory(mem);
			if (memory != null) {
				// TODO: memory should never be null if we're done implementing createEffectiveMemory

				List<Instruction> emptyInstr = new ArrayList<Instruction>();
				Tile dummyTile = new Tile(emptyInstr, 0, memory);
				tileMap.put(mem, dummyTile);		//merged with children in IRMove
				return;
			}
		}
		
		mem.expr().accept(this);
		Tile childTile = tileMap.get(mem.expr());
		Operand childDest = childTile.getDest();
		assert(childDest != null && !(childDest instanceof Constant));
		
		List<Instruction> newInstructions = new ArrayList<Instruction>();
		Operand newDest = null;
		int cost = 0;
		if (childDest instanceof Memory || childDest instanceof Constant) {
			Register newReg = new Register("tileRegister" + registerCount++);
			Instruction movPart = new Instruction(Operation.MOVQ,childDest,newReg);
			newInstructions.add(movPart);
			newDest = new Memory(newReg);
			cost++;
		}
		else {
			newDest = new Memory((Register) childDest);
		}
		Tile newTile = new Tile(newInstructions,cost,newDest);
		Tile finalTile = Tile.mergeTiles(childTile, newTile);
		tileMap.put(mem, finalTile);
	}

	/** takes in a IRBinOp and returns*/
//	private Tile createLeaTile(IRBinOp binopExpr) {
//		// TODO
//		Memory resultMem = null;
//		
//		if (binopExpr.opType() == OpType.ADD || binopExpr.opType() == OpType.SUB) {
//
//			// Case 4: constantOffset(%base, %offset, constantFactor)
//			if (binopExpr.left() instanceof IRTemp && binopExpr.right() instanceof IRBinOp) {
//				
//			}
//			
//			else if ()
//			
//			// Case 3: (%base, %registerOffset, constantFactor) 
//			else if ((binopExpr.opType() == OpType.ADD) &&
//					(binopExpr.left() instanceof IRTemp) && 
//					(binopExpr.right() instanceof IRBinOp)) 
//			{
//				IRTemp baseTemp = (IRTemp) binopExpr.left();
//				IRBinOp rightExpr = (IRBinOp) binopExpr.right();
//				if (rightExpr.opType() == OpType.MUL) {
//					IRTemp registerOffset = null;
//					IRConst constantFactor = null;
//
//					if (rightExpr.left() instanceof IRConst && rightExpr.right() instanceof IRTemp) {
//						registerOffset = (IRTemp) rightExpr.right();
//						constantFactor = (IRConst) rightExpr.left();
//					}
//					else if (rightExpr.left() instanceof IRTemp && rightExpr.right() instanceof IRConst) {
//						registerOffset = (IRTemp) rightExpr.left();
//						constantFactor = (IRConst) rightExpr.right();
//					}
//					else {
//						return null;
//					}
//
//					resultMem = new Memory(new Constant(0),
//							new Register(baseTemp.name()), 
//							new Register(registerOffset.name()), 
//							new Constant(constantFactor.value()));
//				}
//				
//			}
//
//			// Case 2: constantOffset(%registerOffset, constantFactor)
//			else if (binopExpr.opType() == OpType.ADD && 
//					binopExpr.left() instanceof IRConst &&
//					binopExpr.right() instanceof IRBinOp) 
//			{
//				List<Tile> tilesToMerge = new ArrayList<Tile>();
//				List<Instruction> instrList = new ArrayList<Instruction>();
//				int cost = 0;
//
//				binopExpr.left().accept(this);
//				Tile constantOffsetTile = tileMap.get(binopExpr.left());
//				tilesToMerge.add(constantOffsetTile);
//
//				Constant constantOffset = (Constant) constantOffsetTile.getDest();
//
//				IRBinOp binop = (IRBinOp) binopExpr.right();
//				if (binop.opType() == OpType.MUL) {
//					Register registerOffset = null;
//					Constant constantFactor = null;
//					Tile registerOffsetTile = null;
//					Tile constantFactorTile = null;
//
//					binop.left().accept(this);						
//					binop.right().accept(this);
//
//					if (binop.right() instanceof IRConst) {
//						registerOffsetTile = tileMap.get(binop.left());
//						constantFactorTile = tileMap.get(binop.right());
//					}
//					else if (binop.left() instanceof IRConst) {
//						registerOffsetTile = tileMap.get(binop.right());
//						constantFactorTile = tileMap.get(binop.left());
//					}
//					else {
//						return null;
//					}
//
//					constantFactor = (Constant) constantFactorTile.getDest();
//
//					if (!(registerOffsetTile.getDest() instanceof Register)) {
//						//move to a register
//						Register freshTemp = new Register("tilingRegister" + registerCount++);
//						Instruction movToRegister = new Instruction(Operation.MOVQ, 
//								registerOffsetTile.getDest().getNewOperand(), 
//								freshTemp.getNewOperand());
//						instrList.add(movToRegister);
//						cost++;
//
//						registerOffset = freshTemp;
//					}
//					else {
//						registerOffset = (Register) registerOffsetTile.getDest();
//					}
//
//					tilesToMerge.add(registerOffsetTile);
//					tilesToMerge.add(constantFactorTile);
//
//					resultMem = new Memory(constantOffset, registerOffset, constantFactor);
//				}
//				else {
//					return null;
//				}
//
//				Tile currTile = new Tile(instrList, cost, resultMem);
//				tilesToMerge.add(currTile);
//
//				Tile mergedTile = tilesToMerge.get(0);
//				for (int i = 1; i < tilesToMerge.size(); i++) {
//					mergedTile = Tile.mergeTiles(mergedTile, tilesToMerge.get(i));
//				}
//
//				tileMap.put(mem, mergedTile);
//			}
//
//			// Case 1: constantOffset(%base)
//			else if (binopExpr.left() instanceof IRConst && binopExpr.right() instanceof IRTemp) {				
//				IRConst leftConst = (IRConst) binopExpr.left();
//				IRTemp rightTemp = (IRTemp) binopExpr.right();
//
//				//visit children
//				leftConst.accept(this);
//				rightTemp.accept(this);
//				Tile leftTile = tileMap.get(leftConst);
//				Tile rightTile = tileMap.get(rightTemp);
//				Constant leftOperand = (Constant) leftTile.getDest();
//				Register rightOperand = (Register) rightTile.getDest();
//
//				if (binopExpr.opType() == OpType.SUB) {
//					leftOperand.setValue(leftOperand.getValue()*-1);
//				}
//				resultMem = new Memory(leftOperand, rightOperand);
//
//				List<Instruction> emptyInstr = new ArrayList<Instruction>();
//				Tile currTile = new Tile(emptyInstr, 0, resultMem);
//
//				Tile childrenTiles = Tile.mergeTiles(leftTile, rightTile);
//				currTile = Tile.mergeTiles(childrenTiles, currTile);
//
//				tileMap.put(mem, currTile);
//			}
//
//			// Case 1: constantOffset(%base)
//			else if (binopExpr.left() instanceof IRTemp && binopExpr.right() instanceof IRConst) {
//				IRTemp leftTemp = (IRTemp) binopExpr.left();
//				IRConst rightConst = (IRConst) binopExpr.right();
//
//				//visit children
//				leftTemp.accept(this);
//				rightConst.accept(this);
//				Tile leftTile = tileMap.get(leftTemp);
//				Tile rightTile = tileMap.get(rightConst);
//				Register leftOperand = (Register) leftTile.getDest();
//				Constant rightOperand = (Constant) rightTile.getDest();
//
//				if (binopExpr.opType() == OpType.SUB) {
//					rightOperand.setValue(rightOperand.getValue()*-1);
//				}
//				resultMem = new Memory(rightOperand, leftOperand);
//
//				List<Instruction> emptyInstr = new ArrayList<Instruction>();
//				Tile currTile = new Tile(emptyInstr, 0, resultMem);
//
//				Tile childrenTiles = Tile.mergeTiles(leftTile, rightTile);
//				currTile = Tile.mergeTiles(childrenTiles, currTile);
//
//				tileMap.put(mem, currTile);
//			}
//		}
//		return resultMem;
//	}
	
	
	/* Returns a Memory Operand with the most effective addressing mode */
	private Memory createEffectiveMemory(IRMem mem) {
		Memory resultMem = null;
		
		IRBinOp binopExpr = (IRBinOp) mem.expr();
		if (binopExpr.opType() == OpType.ADD || binopExpr.opType() == OpType.SUB) {
			
			// Case 1: constantOffset(%base)
			if (binopExpr.left() instanceof IRConst && binopExpr.right() instanceof IRTemp) {				
				IRConst leftConst = (IRConst) binopExpr.left();
				IRTemp rightTemp = (IRTemp) binopExpr.right();
				
				//visit children
				leftConst.accept(this);
				rightTemp.accept(this);
				Tile leftTile = tileMap.get(leftConst);
				Tile rightTile = tileMap.get(rightTemp);
				Constant leftOperand = (Constant) leftTile.getDest();
				Register rightOperand = (Register) rightTile.getDest();
								
				if (binopExpr.opType() == OpType.SUB) {
					leftOperand.setValue(leftOperand.getValue()*-1);
				}
				resultMem = new Memory(leftOperand, rightOperand);
				
				List<Instruction> emptyInstr = new ArrayList<Instruction>();
				Tile currTile = new Tile(emptyInstr, 0, resultMem);
				
				Tile childrenTiles = Tile.mergeTiles(leftTile, rightTile);
				currTile = Tile.mergeTiles(childrenTiles, currTile);
								
				tileMap.put(mem, currTile);
			}
			
			// Case 1: constantOffset(%base)
			else if (binopExpr.left() instanceof IRTemp && binopExpr.right() instanceof IRConst) {
				IRTemp leftTemp = (IRTemp) binopExpr.left();
				IRConst rightConst = (IRConst) binopExpr.right();
				
				//visit children
				leftTemp.accept(this);
				rightConst.accept(this);
				Tile leftTile = tileMap.get(leftTemp);
				Tile rightTile = tileMap.get(rightConst);
				Register leftOperand = (Register) leftTile.getDest();
				Constant rightOperand = (Constant) rightTile.getDest();
								
				if (binopExpr.opType() == OpType.SUB) {
					rightOperand.setValue(rightOperand.getValue()*-1);
				}
				resultMem = new Memory(rightOperand, leftOperand);
				
				List<Instruction> emptyInstr = new ArrayList<Instruction>();
				Tile currTile = new Tile(emptyInstr, 0, resultMem);
				
				Tile childrenTiles = Tile.mergeTiles(leftTile, rightTile);
				currTile = Tile.mergeTiles(childrenTiles, currTile);
								
				tileMap.put(mem, currTile);
			}
			
			// Case 2: constantOffset(%registerOffset, constantFactor)
			else if (binopExpr.opType() == OpType.ADD && 
					 binopExpr.left() instanceof IRConst &&
					 binopExpr.right() instanceof IRBinOp) 
			{
				List<Tile> tilesToMerge = new ArrayList<Tile>();
				List<Instruction> instrList = new ArrayList<Instruction>();
				int cost = 0;
				
				binopExpr.left().accept(this);
				Tile constantOffsetTile = tileMap.get(binopExpr.left());
				tilesToMerge.add(constantOffsetTile);
				
				Constant constantOffset = (Constant) constantOffsetTile.getDest();
				
				IRBinOp binop = (IRBinOp) binopExpr.right();
				if (binop.opType() == OpType.MUL) {
					Register registerOffset = null;
					Constant constantFactor = null;
					Tile registerOffsetTile = null;
					Tile constantFactorTile = null;
					
					binop.left().accept(this);						
					binop.right().accept(this);
					
					if (binop.right() instanceof IRConst) {
						registerOffsetTile = tileMap.get(binop.left());
						constantFactorTile = tileMap.get(binop.right());
					}
					else if (binop.left() instanceof IRConst) {
						registerOffsetTile = tileMap.get(binop.right());
						constantFactorTile = tileMap.get(binop.left());
					}
					else {
						return null;
					}

					constantFactor = (Constant) constantFactorTile.getDest();
					
					if (!(registerOffsetTile.getDest() instanceof Register)) {
						//move to a register
						Register freshTemp = new Register("tilingRegister" + registerCount++);
						Instruction movToRegister = new Instruction(Operation.MOVQ, 
								registerOffsetTile.getDest().getNewOperand(), 
								freshTemp.getNewOperand());
						instrList.add(movToRegister);
						cost++;
						
						registerOffset = freshTemp;
					}
					else {
						registerOffset = (Register) registerOffsetTile.getDest();
					}
					
					tilesToMerge.add(registerOffsetTile);
					tilesToMerge.add(constantFactorTile);
					
					resultMem = new Memory(constantOffset, registerOffset, constantFactor);
				}
				else {
					return null;
				}

				Tile currTile = new Tile(instrList, cost, resultMem);
				tilesToMerge.add(currTile);

				Tile mergedTile = tilesToMerge.get(0);
				for (int i = 1; i < tilesToMerge.size(); i++) {
					mergedTile = Tile.mergeTiles(mergedTile, tilesToMerge.get(i));
				}

				tileMap.put(mem, mergedTile);
			}
			
			// Case 3: (%base, %registerOffset, constantFactor) 
			else if ((binopExpr.opType() == OpType.ADD) &&
				(binopExpr.left() instanceof IRTemp) && 
				(binopExpr.right() instanceof IRBinOp)) 
			{
				IRTemp baseTemp = (IRTemp) binopExpr.left();
				IRBinOp rightExpr = (IRBinOp) binopExpr.right();
				if (rightExpr.opType() == OpType.MUL) {
					IRTemp registerOffset = null;
					IRConst constantFactor = null;
					
					if (rightExpr.left() instanceof IRConst && rightExpr.right() instanceof IRTemp) {
						registerOffset = (IRTemp) rightExpr.right();
						constantFactor = (IRConst) rightExpr.left();
					}
					else if (rightExpr.left() instanceof IRTemp && rightExpr.right() instanceof IRConst) {
						registerOffset = (IRTemp) rightExpr.left();
						constantFactor = (IRConst) rightExpr.right();
					}
					else {
						return null;
					}

					resultMem = new Memory(new Constant(0),
							new Register(baseTemp.name()), 
							new Register(registerOffset.name()), 
							new Constant(constantFactor.value()));
				}
			}
		}
		return resultMem;
	}

	@Override
	public void visit(IRMove mov) {
		if (tileMap.containsKey(mov)) {
			return;
		}
		
		mov.expr().accept(this);
		mov.target().accept(this);
		Tile sourceTile = tileMap.get(mov.expr());		// sourceTile may contain the optimized memory operand
		Tile targetTile = tileMap.get(mov.target());	// targetTile may contain the optimized memory operand
		Operand sourceOperand = sourceTile.getDest();
		Operand targetOperand = targetTile.getDest();
		
		/* OPTIMIZATON: addressing modes */
		if ((!(sourceOperand instanceof Memory) && targetOperand instanceof Memory) ||
			(!(targetOperand instanceof Memory) && sourceOperand instanceof Memory)) 
		{
			List<Instruction> instrList = new ArrayList<Instruction>();
			int cost = 0;
			Operand argDest = null;
			Instruction movConstToMem = new Instruction(Operation.MOVQ, sourceOperand, targetOperand);
			instrList.add(movConstToMem);
			cost++;
			argDest = targetOperand;
			
			Tile finalTile = new Tile(instrList, cost, argDest);
			finalTile = Tile.mergeTiles(targetTile, finalTile);
			finalTile = Tile.mergeTiles(sourceTile, finalTile);
			tileMap.put(mov, finalTile);
			return;
		}
		
		
		boolean redundant = sourceOperand.toString().equals(targetOperand.toString());		
		
		// If both children are Memory Operands
		if (sourceTile.getDest() instanceof Memory && targetTile.getDest() instanceof Memory) {
			List<Instruction> newInstructions = new ArrayList<Instruction>();
			Tile finalTile;
			if (!redundant) {
				Register temp = new Register("tileRegister" + registerCount++);
				newInstructions.add(new Instruction(Operation.MOVQ,sourceOperand,temp));
				newInstructions.add(new Instruction(Operation.MOVQ,temp,targetOperand));
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
							Register reg = new Register("tileRegister" + registerCount++);
							Instruction shuttle = new Instruction(Operation.MOVQ, mem, reg);
							tempInstructions.add(shuttle);
							instr = new Instruction(Operation.MOVQ, reg, dest);
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
				i += numReturns - numSkips;
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
	 * Handles allocating registers to all temps
	 * 
	 * @param headNode	the IR node representing the xi program
	 */
	private void regAllocation(IRCompUnit headNode) {
		// Create a register allocator
		RegisterAllocator rAlloc = new RegisterAllocator(Omc);
		
		// Call registerAllocation on each function
		for (IRFuncDecl fd : headNode.functions().values()) {
			Tile functionTile = tileMap.get(fd);
			List<Instruction> functionInstructions = functionTile.getInstructions();
			List<Instruction> newInsts = rAlloc.registerAllocation(functionInstructions, fd);
			functionTile.setInstructions(newInsts);
			
			// complete "enter 8*l, 0"
			Instruction enter = newInsts.get(1);
			int counter = rAlloc.getStackCounter();
			int numSpace = counter % 2 == 1 ? counter + 1 : counter;
			Constant space = new Constant(8*numSpace);
			enter.setSrc(space);
		}
	}
	
	/**
	 * Handles converting all temps to stack allocations
	 * 
	 * @param headNode	the IR node representing the xi program
	 */
	private void stackAllocation(IRCompUnit headNode) {
		// Call addNecessaryInstruction on each function
		for (IRFuncDecl fd : headNode.functions().values()) {
			Tile functionTile = tileMap.get(fd);
			List<Instruction> functionInstructions = functionTile.getInstructions();
			Map<String, Integer> registerToStackOffsetMap = new HashMap<String, Integer>();
			List<Instruction> newInsts = addNecessaryInstruction(
					functionInstructions, registerToStackOffsetMap, fd.getNumSavedCalleeRegs());
			functionTile.setInstructions(newInsts);
			
			// complete "enter 8*l, 0"
			Instruction enter = newInsts.get(1);
			int numSpace = stackCounter % 2 == 1 ? stackCounter + 1 : stackCounter;
			Constant space = new Constant(8*numSpace);
			enter.setSrc(space);
		}
	}
	
	/**
	 * Inserts the necessary instructions to shuttle values to and from stack
	 * 
	 * @param instructions	the list of instructions for our program
	 * @param regToStack	the map of register names to the relative stack offset
	 * @return				list of instructions with correct insertions
	 */
	public List<Instruction> addNecessaryInstruction(
			List<Instruction> instructions, Map<String, Integer> regToStack,
			int num) {
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
					instructions.subList(1,size),regToStack,num));
			return added;
		} 
		Register r12 = new Register(RegisterName.R12);
		Register r13 = new Register(RegisterName.R13);
		Register r14 = new Register(RegisterName.R14);
		Register rbp = new Register(RegisterName.RBP);
		
		if (src == null || src instanceof Constant) {
			// push, pop, call, jumps, div?
			if (dest instanceof Register) {
				// Dest uses a built-in register
				if (((Register) dest).getType() != RegisterName.TEMP) {
					added.add(currentInstruction);
					added.addAll(addNecessaryInstruction(
							instructions.subList(1,size),regToStack,num));
					return added;
				}
				
				String reg = ((Register) dest).getName();
				Memory mem;
				if (regToStack.containsKey(reg)) {
					int addr = regToStack.get(reg);
					mem = new Memory(new Constant(addr),rbp);
					if (currentInstruction.getOp() != Operation.MOVQ) {
						Instruction movToReg = new Instruction(Operation.MOVQ,mem,r14);
						added.add(movToReg);
					}
				} else {
					int addr = -8*++stackCounter;
					mem = new Memory(new Constant(addr),rbp);
					regToStack.put(reg,addr);
				}
				currentInstruction.setDest(r14);
				added.add(currentInstruction);
				
				if (src != null) {
					Instruction movToMem = new Instruction(Operation.MOVQ,r14,mem);
					added.add(movToMem);
				}
			} else if (dest instanceof Memory) {
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r12);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,r12,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
				}
				currentInstruction.setDest(newMem);
				added.add(currentInstruction);
			} else {
				// dest is constant or label
				Operation op = currentInstruction.getOp();
				Operand label = currentInstruction.getDest();
				if (op == Operation.LABEL && ((Label) label).isFuncLabel()) {
					functionSpaceMap.put(currentFunction,stackCounter);
					currentFunction = label.toString();
					stackCounter = num;
					regToStack.clear();
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
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r14);
					currentInstruction.setSrc(r14);
					added.add(movToReg1);
				}
				
				Memory memOp = (Memory) dest;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr2 = regToStack.get(regBase.getName());
					Memory mem1 = new Memory(new Constant(addr2),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r12);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr3 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr3),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,r12,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr3 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr3),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
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
						if (currentInstruction.getOp() != Operation.MOVQ) {
							Instruction movToRegD = new Instruction(Operation.MOVQ,memD,r12);
							added.add(movToRegD);
						}
					} else {
						// Need to create a new memory address
						int addrD = -8*++stackCounter;
						memD = new Memory(new Constant(addrD),rbp);
						regToStack.put(regD,addrD);
					}
					currentInstruction.setDest(r12);
					movToMemD = new Instruction(Operation.MOVQ,r12,memD);
				}
				if (((Register) src).getType() == RegisterName.TEMP) {
					if (regToStack.containsKey(regS)) {
						int addrS = regToStack.get(regS);
						Memory memS = new Memory(new Constant(addrS),rbp);
						Instruction movToRegS = new Instruction(Operation.MOVQ,memS,r14);
						added.add(movToRegS);
					} else {
						System.out.println(currentInstruction);
						System.out.println(regS);
						System.out.println(regToStack);
						System.out.println("Access a register that hasn't been set!");
						assert(false);
					}
					currentInstruction.setSrc(r14);
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
						if (currentInstruction.getOp() != Operation.MOVQ) {
							Instruction movToReg1 = new Instruction(Operation.MOVQ,mem1,r14);
							added.add(movToReg1);
						}
					} else {
						int addr1 = -8*++stackCounter;
						mem1 = new Memory(new Constant(addr1),rbp);
						regToStack.put(reg,addr1);
					}
					currentInstruction.setDest(r14);
					movToMem1 = new Instruction(Operation.MOVQ,r14,mem1);
				}
				
				Memory memOp = (Memory) src;
				Register regBase = memOp.getRegisterBase();
				Register regOff = memOp.getRegisterOffset();
				Constant cons = memOp.getConstantOffset();
				Constant fact = memOp.getConstantFactor();
				Memory newMem;
				
				if (regBase.getType() == RegisterName.TEMP) {
					int addr1 = regToStack.get(regBase.getName());
					Memory mem2 = new Memory(new Constant(addr1),rbp);
					Instruction movToReg1 = new Instruction(Operation.MOVQ,mem2,r12);
					added.add(movToReg1);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem3 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem3,r13);
						newMem = new Memory(cons,r12,r13,fact);
						added.add(movToReg2);
					} else {
						newMem = new Memory(cons,r12,regOff,fact);
					}
				} else {
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,memOp.getConstantFactor());
						added.add(movToReg2);
					} else {
						// both registers are built-in
						newMem = memOp;
					}
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
					movToReg1 = new Instruction(Operation.MOVQ,mem1,r12);
					if (regOff != null && regOff.getType() == RegisterName.TEMP) {
						// two register operands for memory
						int addr2 = regToStack.get(regOff.getName());
						Memory mem2 = new Memory(new Constant(addr2),rbp);
						Instruction movToReg2 = new Instruction(Operation.MOVQ,mem2,r13);
						newMem = new Memory(cons,r12,r13,memOp.getConstantFactor());
						added.add(movToReg2);
					} else if (regBase.getType() == RegisterName.TEMP) {
						if (regOff == null) {
							newMem = new Memory(cons,r12);
						} else {
							newMem = new Memory(cons,r12,regOff,memOp.getConstantFactor());
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
								instructions.subList(1,size),regToStack,num));
						return added;
					}
					// src uses a temp register
					int addr1 = regToStack.get(((Register) src).getName());
					Memory mem1 = new Memory(new Constant(addr1),rbp);
					movToReg1 = new Instruction(Operation.MOVQ,mem1,r14);
					currentInstruction.setSrc(r14);
					added.add(movToReg1);
				}
				added.add(currentInstruction);
			}
		}
		added.addAll(addNecessaryInstruction(
				instructions.subList(1,size),regToStack,num));
		return added;
	}
	
	private Tile createEndCallTile(IRCall call) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		int numArgs = call.args().size();

		// restore caller-save registers 
		int argOffset = Math.max(0, numArgs-6);
		int extraSpaceOffset = call.hasExtra8ByteSpace() ? 1 : 0;
		int retOffset = call.getNumReturns() > 2 ? 1 : 0;
		int numCallerReg = CALLER_REG_LIST.length;
//		int totalOffset = argOffset + extraSpaceOffset + retOffset;
		Register rsp = new Register(RegisterName.RSP);
		if (call.hasExtra8ByteSpace()) {
			Instruction extraPop = new Instruction(Operation.ADDQ, new Constant(8), rsp);
			instructions.add(extraPop);
		}
		for (int i = numCallerReg-1; i >= 0; i--) {
//			Constant offset = new Constant(8*(numCallerReg + totalOffset - 1 - i));
//			Memory mem = new Memory(offset, rsp);
//			// "movq offset(rsp) caller_saved_reg"
//			Instruction instr = new Instruction(Operation.MOVQ, mem, new Register(CALLER_REG_LIST[i]));
//			instructions.add(instr);
			Register reg = new Register(CALLER_REG_LIST[i]);
			// "pushq reg"
			Instruction instr = new Instruction(Operation.POPQ, reg);
			instructions.add(instr);
		}
		
		// back to original rsp
//		Constant offset = new Constant(8*call.getNum8ByteSpace());
//		// "addq offset rsp" 
//		Instruction instr = new Instruction(Operation.ADDQ, offset, rsp);
//		instructions.add(instr);
		
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