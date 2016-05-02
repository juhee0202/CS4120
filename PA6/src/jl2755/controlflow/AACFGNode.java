package jl2755.controlflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jl2755.assembly.Instruction;
import jl2755.assembly.Operand;
import jl2755.assembly.Register;

public class AACFGNode extends CFGNode {
	
	/** The Instruction that this CFGNode represents. */
	public Instruction underlyingInstruction;
	
	
	public AACFGNode(Instruction argInstruction) {
		super();
		underlyingInstruction = argInstruction;
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
	
	public void setPredecessors(List<CFGNode> argNodes) {
		predecessors = argNodes;
	}
	
	public void replaceSuccessor(AACFGNode old, AACFGNode node) {
		if (successor1 == old) {
			successor1 = null;
		} else if (successor2 == old) {
			successor2 = null;
		}
		addSuccessor(node);
	}

	public Instruction getUnderlyingInstruction() {
		return underlyingInstruction;
	}

	public void setUnderlyingInstruction(Instruction underlyingInstruction) {
		this.underlyingInstruction = underlyingInstruction;
	}
	
	@Override
	public String dotOutput(Set<CFGNode> visited) {
		if (visited.contains(this)) {
			return "";
		}
		visited.add(this);
		String s = "\t" + "\"" + underlyingInstruction + "\n" + this + "\"";
		s += " -> {";
		if (successor1 != null) {
			s += "\"" + ((AACFGNode) successor1).underlyingInstruction + "\n" + successor1 + "\"";
			if (successor2 != null) {
				s += ", \"" + ((AACFGNode) successor2).underlyingInstruction + "\n" + successor2 + "\"";
			}
		}
		s += "}\n";
		if (successor1 != null) {
			s += successor1.dotOutput(visited);
			if (successor2 != null) {
				s += successor2.dotOutput(visited);
			}
		}
		return s;
	}
}
