package jl2755.controlflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jl2755.assembly.Instruction;
import jl2755.assembly.Operand;
import jl2755.assembly.Register;

public class AACFGNode extends CFGNode {
	/**
	 * The Instruction that this CFGNode represents.
	 */
	private Instruction underlyingInstruction;
	/**
	 * The use (if applicable) of this AACFGNode.
	 */
	private Register use1;
	/**
	 * The second use of this AACFGNode.
	 */
	private Register use2;
	/**
	 * The third (there can be at most 3) use of
	 * this AACFGNode. There can be 3 because one of the
	 * src or dest can have 2 Registers in it as a Memory operand.
	 */
	private Register use3;
	/**
	 * The one and only (if applicable) def
	 * of this AACFGNode.
	 */
	private Register def;
	
	public AACFGNode(Instruction argInstruction) {
		super();
		underlyingInstruction = argInstruction;
		
	}
	
	private void computeUses() {
		// TODO: Complete
		Operand source = underlyingInstruction.getSrc();
		Operand target = underlyingInstruction.getDest();
		Set<Register> sourceUsed = new HashSet<Register>();
		Set<Register> targetUsed = new HashSet<Register>();
		if (source != null) {
			sourceUsed.addAll(source.getRegistersUsed());
		}
		if (target != null) {
			targetUsed.addAll(target.getRegistersUsed());
		}
		Set<Register> bothCombined = new HashSet<Register>();
		bothCombined.addAll(sourceUsed);
		bothCombined.addAll(targetUsed);
//		sourceUsed.addAll(targetUsed);
//		assert(sourceUsed.size() <= 3);
//		Iterator<Register> iteratorOfUses = sourceUsed.iterator();
//		while (iteratorOfUses.hasNext()) {
//			addUse(iteratorOfUses.next());
//		}
		switch(underlyingInstruction.getOp()) {
			case ADDQ :
				addUse(bothCombined);
				break;
			case ANDQ :
				addUse(bothCombined);
				break;
			case CALLQ :
				break;
			case CMOVE :
				addUse(sourceUsed);
				break;
			case CMOVG :
				addUse(sourceUsed);
				break;
			case CMOVGE :
				addUse(sourceUsed);
				break;
			case CMOVL :
				addUse(sourceUsed);
				break;
			case CMOVLE :
				addUse(sourceUsed);
				break;
			case CMOVNE :
				addUse(sourceUsed);
				break;
			case CMPQ :
				addUse(bothCombined);
				break;
			case ENTER :
				break;
			case IDIVQ :
				addUse(bothCombined);
				break;
			case IMULQ1 :
				addUse(bothCombined);
				break;
			case IMULQ2 :
				addUse(bothCombined);
				break;
			case JE :
				break;
			case JG :
				break;
			case JGE :
				break;
			case JL :
				break;
			case JLE :
				break;
			case JMP :
				break;
			case JNE :
				break;
			case JNZ :
				break;
			case JZ :
				break;
			case LABEL : 
				break;
			case MOVQ :
				addUse(sourceUsed);
				break;
			case ORQ :
				addUse(bothCombined);
				break;
			case POPQ :
				break;
			case PUSHQ :
				break;
			case RET :
				break;
			case SUBQ :
				addUse(bothCombined);
				break;
			case TESTQ :
				break;
			case XORQ :
				addUse(bothCombined);
				break;
			default :
				break;
			
		}
	}
	
	private void computeDefs() {
		Operand source = underlyingInstruction.getSrc();
		Operand target = underlyingInstruction.getDest();
		Set<Register> sourceUsed = new HashSet<Register>();
		Set<Register> targetUsed = new HashSet<Register>();
		if (source != null) {
			sourceUsed.addAll(source.getRegistersUsed());
		}
		if (target != null) {
			targetUsed.addAll(target.getRegistersUsed());
		}
		switch (underlyingInstruction.getOp()) {
			case ADDQ :
				addDef(targetUsed);
				break;
			case ANDQ :
				addDef(targetUsed);
				break;
			case CALLQ :
				break;
			case CMOVE :
				addDef(targetUsed);
				break;
			case CMOVG :
				addDef(targetUsed);
				break;
			case CMOVGE :
				addDef(targetUsed);
				break;
			case CMOVL :
				addDef(targetUsed);
				break;
			case CMOVLE :
				addDef(targetUsed);
				break;
			case CMOVNE :
				addDef(targetUsed);
				break;
			case CMPQ :
				break;
			case ENTER :
				break;
			case IDIVQ :
				addDef(targetUsed);
				break;
			case IMULQ1 :
				addDef(targetUsed);
				break;
			case IMULQ2 :
				addDef(targetUsed);
				break;
			case JE :
				break;
			case JG :
				break;
			case JGE :
				break;
			case JL :
				break;
			case JLE :
				break;
			case JMP :
				break;
			case JNE :
				break;
			case JNZ :
				break;
			case JZ :
				break;
			case LABEL :
				break;
			case LEAVE :
				break;
			case MOVQ :
				addDef(targetUsed);
				break;
			case ORQ :
				addDef(targetUsed);
				break;
			case POPQ :
				break;
			case PUSHQ :
				break;
			case RET :
				break;
			case SUBQ :
				addDef(targetUsed);
				break;
			case TESTQ :
				break;
			case XORQ :
				addDef(targetUsed);
				break;
			default :
				break;
		
		}
	}
	
	private void addUse(Set<Register> argRegister) {
		Iterator<Register> iteratorView = argRegister.iterator();
		assert(argRegister.size() <= 3);
		while (iteratorView.hasNext()) {
			if (use1 != null) {
				use1 = iteratorView.next();
			}
			else if (use2 != null) {
				use2 = iteratorView.next();
			}
			else if (use3 != null) {
				use3 = iteratorView.next();
			}
		}
	}
	
	private void addDef(Set<Register> argRegister) {
		Iterator<Register> iteratorView = argRegister.iterator();
		assert(argRegister.size() == 1);
		while (iteratorView.hasNext()) {
			def = iteratorView.next();
		}
	}
	
	private Set<Register> getUses() {
		Set<Register> theSet = new HashSet<Register>();
		if (use1 != null) {
			theSet.add(use1);
		}
		if (use2 != null) {
			theSet.add(use2);
		}
		if (use3 != null) {
			theSet.add(use3);
		}
		return theSet;
	}
	
	public void addSuccessor(AACFGNode argNode) {
		proposeToSuccessor(argNode);
		if (successor1 == null) {
			successor1 = argNode;
		}
		else if (successor2 == null) {
			successor2 = argNode;
		}
		else {
			assert(false);
			System.out.println("Already added 2 successors");
		}
	}
}
