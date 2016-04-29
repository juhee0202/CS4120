package jl2755.dataflow;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jl2755.controlflow.AACFGNode;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.assembly.Memory;
import jl2755.assembly.Operand;
import jl2755.assembly.Register;

/**
 * Class that perform the live variable analysis
 * on the control flow graph.
 *
 * @param <Register>
 */
public class LiveVariableAnalyzer extends Dataflow<Register> {
	
	/**
	 * A mapping to keep track of the in set for
	 * each AACFGNode.
	 */
	private HashMap<AACFGNode, Set<Register>> inMap;
	
	/**
	 * A mapping to keep track of the uses for each AACFGNode.
	 */
	private HashMap<AACFGNode, Set<Register>> uses;
	
	/**
	 * A mapping to keep track of the def for each AACFGNode.
	 */
	private HashMap<AACFGNode, Register> defs;
	
	
	/**
	 * Assigns the cfg field, and hardcodes
	 * the direction of analysis to be backward. 
	 * 
	 * @param argCFG
	 */
	public LiveVariableAnalyzer(ControlFlowGraph argCFG) {
		inMap = new HashMap<AACFGNode, Set<Register>>();
		uses = new HashMap<AACFGNode, Set<Register>>();
		defs = new HashMap<AACFGNode, Register>();
		cfg = argCFG;
		for (CFGNode cfgNode : argCFG.getAllNodes()) {
			uses.put((AACFGNode) cfgNode, new HashSet<Register>());
		}
		for (CFGNode cfgNode : argCFG.getAllNodes()) {
			computeUses((AACFGNode) cfgNode);
			computeDefs((AACFGNode) cfgNode);
		}
		directionOfAnalysis = Direction.BACKWARD;
	}
	
	/**
	 * This is the meet operator for the live variable
	 * analysis dataflow. Should be the union of all
	 * the Operands.
	 */
	@Override
	public Set<Register> meetOperator(Register... args) {
		Set<Register> results = new HashSet<Register>();
		for (int i = 0; i < args.length; i++) {
			results.add(args[i]);
		}
		return results;
	}

	/**
	 * This is the transfer function for the live variable
	 * analysis dataflow. Should be 
	 * in(n) = use(n) U (U_n'>n in(n') - def(n))
	 * 
	 * @return false iff the in before and after the 
	 * transferFunction are the same.
	 */
	@Override
	public boolean transferFunction(CFGNode arg) {
		CFGNode firstSuccessor = arg.getSuccessor1();
		CFGNode secondSuccessor = arg.getSuccessor2();
		
		assert(firstSuccessor == null ||firstSuccessor instanceof AACFGNode);
		assert(secondSuccessor == null || secondSuccessor instanceof AACFGNode);
		
		assert(arg instanceof AACFGNode);
		
		AACFGNode AAView = (AACFGNode) arg;
		
		Set<Register> tempSet = new HashSet<Register>();
		
		if (firstSuccessor != null) {
			tempSet.addAll(inMap.get(firstSuccessor));
		}
		if (secondSuccessor != null) {
			tempSet.addAll(inMap.get(secondSuccessor));
		}
		
		if (defs.get(AAView) != null) {
			tempSet.remove(defs.get(AAView));
		}
		
		tempSet.addAll(uses.get(AAView));
		
		Set<Register> originalInSet = inMap.get(arg);
		
		if (originalInSet.equals(tempSet)) {
			return false;
		}
		
		return true;
	}

	/**
	 * Initializes worklist to contain all nodes.
	 * Initializes the in of all nodes to be the empty set.
	 * In a while loop, it polls the worklist for a node,
	 * applies the transfer function, and if something changed,
	 * adds all the predecessors to the worklist.
	 */
	@Override
	public void analyze() {
		Set<CFGNode> allNodes = cfg.getAllNodes();
		Iterator<CFGNode> iteratorVersion = allNodes.iterator();
		while (iteratorVersion.hasNext()) {
			CFGNode temp = iteratorVersion.next();
			inMap.put((AACFGNode) temp, new HashSet<Register>());
		}
		WorklistQueue<CFGNode> ourQueue = new WorklistQueue<CFGNode>();
		ourQueue.addAll(allNodes);
		while (!ourQueue.isEmpty()) {
			CFGNode currentNode = ourQueue.poll();
			if (transferFunction(currentNode)) {
				ourQueue.addAll(currentNode.getPredecessors());
			}
		}
	}
	
	public HashMap<AACFGNode,Set<Register>> getInMap() {
		return inMap;
	}
	
	private void computeUses(AACFGNode argNode) {
		// TODO: Complete
		Operand source = argNode.underlyingInstruction.getSrc();
		Operand target = argNode.underlyingInstruction.getDest();
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
		switch(argNode.underlyingInstruction.getOp()) {
			case ADDQ :
				addUse(argNode,bothCombined);
				break;
			case ANDQ :
				addUse(argNode,bothCombined);
				break;
			case CALLQ :
				break;
			case CMOVE :
				addUse(argNode,sourceUsed);
				break;
			case CMOVG :
				addUse(argNode,sourceUsed);
				break;
			case CMOVGE :
				addUse(argNode,sourceUsed);
				break;
			case CMOVL :
				addUse(argNode,sourceUsed);
				break;
			case CMOVLE :
				addUse(argNode,sourceUsed);
				break;
			case CMOVNE :
				addUse(argNode,sourceUsed);
				break;
			case CMPQ :
				addUse(argNode,bothCombined);
				break;
			case ENTER :
				break;
			case IDIVQ :
				addUse(argNode,bothCombined);
				break;
			case IMULQ1 :
				addUse(argNode,bothCombined);
				break;
			case IMULQ2 :
				addUse(argNode,bothCombined);
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
				addUse(argNode,sourceUsed);
				break;
			case ORQ :
				addUse(argNode,bothCombined);
				break;
			case POPQ :
				break;
			case PUSHQ :
				break;
			case RET :
				break;
			case SUBQ :
				addUse(argNode,bothCombined);
				break;
			case TESTQ :
				break;
			case XORQ :
				addUse(argNode,bothCombined);
				break;
			default :
				break;
			
		}
	}
	
	private void computeDefs(AACFGNode argNode) {
		Operand target = argNode.underlyingInstruction.getDest();
		// If the target is a Memory operand or a constant, we can't do anything
		// about it since no variable gets defined
		if (!(target instanceof Register)) {
			return;
		}
		Register registerView = (Register) target;
		switch (argNode.underlyingInstruction.getOp()) {
			case ADDQ :
				addDef(argNode,registerView);
				break;
			case ANDQ :
				addDef(argNode,registerView);
				break;
			case CALLQ :
				break;
			case CMOVE :
				addDef(argNode,registerView);
				break;
			case CMOVG :
				addDef(argNode,registerView);
				break;
			case CMOVGE :
				addDef(argNode,registerView);
				break;
			case CMOVL :
				addDef(argNode,registerView);
				break;
			case CMOVLE :
				addDef(argNode,registerView);
				break;
			case CMOVNE :
				addDef(argNode,registerView);
				break;
			case CMPQ :
				break;
			case ENTER :
				break;
			case IDIVQ :
				addDef(argNode,registerView);
				break;
			case IMULQ1 :
				addDef(argNode,registerView);
				break;
			case IMULQ2 :
				addDef(argNode,registerView);
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
				addDef(argNode,registerView);
				break;
			case ORQ :
				addDef(argNode,registerView);
				break;
			case POPQ :
				break;
			case PUSHQ :
				break;
			case RET :
				break;
			case SUBQ :
				addDef(argNode,registerView);
				break;
			case TESTQ :
				break;
			case XORQ :
				addDef(argNode,registerView);
				break;
			default :
				break;
		
		}
	}
	
	private void addUse(AACFGNode argNode, Set<Register> argRegisters) {
		Set<Register> currentUses = uses.get(argNode);
		currentUses.addAll(argRegisters);
		assert(currentUses.size() <= 3);
	}
	
	private void addDef(AACFGNode argNode, Register argRegister) {
		if (defs.containsKey(argNode)) {
			// Something weird was done with calculating defs twice for a node
			assert(false);
		}
		else {
			defs.put(argNode, argRegister);
		}
	}
}
