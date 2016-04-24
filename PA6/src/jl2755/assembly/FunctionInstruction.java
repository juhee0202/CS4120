package jl2755.assembly;

import java.util.ArrayList;
import java.util.List;

import jl2755.assembly.Register.RegisterName;

public class FunctionInstruction extends Instruction {
	private String fnName;
	private List<Instruction> prologue;
	private List<Instruction> body;
	private List<Instruction> epilogue;
	private int tempMemOffset;
	
	
	public FunctionInstruction(String argFnName) {
		fnName = argFnName;
		prologue = new ArrayList<Instruction>();
		body = new ArrayList<Instruction>();
		epilogue = new ArrayList<Instruction>();
		cost = 0;
		setTempMemOffset(0);
	}
	
	public void addToPrologue(Instruction instr) {
		prologue.add(instr);
		cost += instr.getCost();
	}
	
	public void addToBody(Instruction instr) {
		body.add(instr);
		cost += instr.getCost();
	}
	
	public void addToBody(Tile tile) {
		List<Instruction> instructions = tile.getInstructions();
		for (int i = 0; i < instructions.size(); i++) {
			addToBody(instructions.get(i));
		}
	}
	
	public void addToEpilogue(Instruction instr) {
		epilogue.add(instr);
		cost += instr.getCost();
	}
	
	public void updateEnter(int numTemps) {
		NormalInstruction enter = (NormalInstruction) prologue.get(0);
		Constant space = new Constant(8*(numTemps + tempMemOffset));
		enter.setSrc(space);
		prologue.set(0, enter);
	}
	
	public List<Instruction> getAllInstructions() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		Label fnLabel = new Label(fnName);
		Instruction label = new NormalInstruction(Operation.LABEL, fnLabel);
		instructions.add(label);
		instructions.addAll(prologue);
		instructions.addAll(body);
		instructions.addAll(epilogue);
		return instructions;
	}

	@Override
	public Instruction getCopyInstruction() {
		// shouldn't need this
		System.out.println("Don't need this");
		return null;
	}

	public String getFnName() {
		return fnName;
	}

	public void setFnName(String fnName) {
		this.fnName = fnName;
	}

	@Override
	public int getCost() {
		return cost;
	}
	
	public int getTempMemOffset() {
		return tempMemOffset;
	}

	public void setTempMemOffset(int tempMemOffset) {
		this.tempMemOffset = tempMemOffset;
	}

	/**
	 * Replaces _RETi registers with the right registers
	 * @param functionTile
	 */
	public void replaceReturnRegisters() {
		List<Instruction> instructions = body;
		for (int i = 0; i < instructions.size(); i++) {
			NormalInstruction instr = (NormalInstruction) instructions.get(i);
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
						shuttleInstruction = new NormalInstruction(Operation.MOVQ, returnOperand, r11);
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
						shuttleInstruction = new NormalInstruction(Operation.MOVQ, srcOperand, r11);
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
		body = instructions;
	}
}
