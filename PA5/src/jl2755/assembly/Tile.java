package jl2755.assembly;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.*;

public class Tile {

	private IRNode rootOfSubtree;
	private int cost;
	private List<Instruction> instructions;
	private Operand dest;
	
	
	public Tile(List<String> inOrder, List<String> preOrder, List<Instruction> i,
			int argCost) {
		rootOfSubtree = makeTree(inOrder, preOrder);
		instructions = i;
		cost = argCost;
	}
	
	public Tile(List<Operand> argOperands, Tile argTile) {
		
	}
	
	public Tile(List<Instruction> i, int argCost) {
		instructions = i;
		cost = argCost;
	}
	
	/**
	 * Strings should be name of IRNode instance plus a number to make
	 * each node unique
	 * 
	 * @param inOrder is the in order traversal of such a tree
	 * @param preOrder is the pre order traversal of such a tree
	 * @return the root of the tree that the in order and pre order
	 * traversals describes
	 */
	private static IRNode makeTree(List<String> inOrder, List<String> preOrder) {
		if (preOrder.size() == 0) {
			return null;
		}
		String headOfSubtree = preOrder.get(0);
		preOrder.remove(0);
		
		int indexInInOrder = inOrder.indexOf(headOfSubtree);
		
		List<String> leftSubtree = inOrder.subList(0, indexInInOrder);
		
		List<String> rightSubtree = new ArrayList<String>();
		
		if (!(indexInInOrder == inOrder.size() - 1)) {
			rightSubtree = inOrder.subList(indexInInOrder + 1, inOrder.size() - 1);
		}
		
		IRNode rootOfLeft = makeTree(leftSubtree, preOrder);
		IRNode rootOfRight = makeTree(rightSubtree, preOrder);
		
		IRNode root = stringToNode(headOfSubtree);
		root.addLeft(rootOfLeft);
		root.addRight(rootOfRight);
		
		return root;
	}
	
	/**
	 * NAMING CONVENTION:
	 * IRMem = Mem;
	 * IRCall = Call;
	 * 
	 * 
	 * @param nodeName
	 * @return
	 */
	private static IRNode stringToNode(String nodeName) {
		if (nodeName.contains("BinOp")) {
			return new IRBinOp(null,null,null);
		}
		if (nodeName.contains("Call")) {
			IRExpr[] hue = null;
			return new IRCall(null, hue);
		}
		if (nodeName.contains("CJump")) {
			return new IRCJump(null,null);
		}
		if (nodeName.contains("Const")) {
			return new IRConst(0);
		}
		if (nodeName.contains("Exp")) {
			return new IRExp(null);
		}
		if (nodeName.contains("Jump")) {
			return new IRJump(null);
		}
		if (nodeName.contains("Label")) {
			return new IRLabel(null);
		}
		if (nodeName.contains("Mem")) {
			return new IRMem(null);
		}
		if (nodeName.contains("Move")) {
			return new IRMove(null,null);
		}
		if (nodeName.contains("Name")) {
			return new IRName(null);
		}
		if (nodeName.contains("Return")) {
			return new IRReturn();
		}
		if (nodeName.contains("Seq")) {
			return new IRSeq();
		}
		if (nodeName.contains("Temp")) {
			return new IRTemp(null);
		}
		System.out.println("You wrote a weird string for node name");
		assert(false);
		return null;
	}

	public IRNode getRootOfSubtree() {
		return rootOfSubtree;
	}

	public void setRootOfSubtree(IRNode rootOfSubtree) {
		this.rootOfSubtree = rootOfSubtree;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public Operand getDest() {
		return dest;
	}

	public void setDest(Operand dest) {
		this.dest = dest;
	}
}
