package jl2755.assembly;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.*;

public class Tile {

	private IRNode rootOfSubtree;
	private int cost;
	private List<Instruction> instructions;
	private Operand dest;
	
	/**
	 * Should only be used in mergeTiles to make a new blank Tile
	 */
	private Tile() {
	}
	
	public Tile(List<String> inOrder, List<String> preOrder, List<Instruction> i,
			int argCost) {
		rootOfSubtree = makeTree(inOrder, preOrder);
		instructions = i;
		cost = argCost;
	}
	
	/** Returns a cloned version of argTile with root as a real IRNode */
	public Tile(IRNode rootOfRealTree, Tile argTile) {
		Tile tempTile = new Tile();
		tempTile.rootOfSubtree = rootOfRealTree;
		tempTile.cost = argTile.cost;
		tempTile.instructions = argTile.instructions;
	}
	
	public Tile(List<Operand> argOperands, Tile argTile) {
		
	}
	
	public Tile(List<Instruction> i, int argCost) {
		instructions = i;
		cost = argCost;
	}
	
	public Tile(List<Instruction> i, int argCost, Operand argDest) {
		instructions = i;
		cost = argCost;
		dest = argDest;
	}
	
	/**
	 * Static method that merges two Tiles. The leftTile should be the
	 * "parent" Tile of the rightTile. That is, the root of the subtree
	 * of this new Tile should be the root of leftTile. Therefore,
	 * the instructions in the right are performed first, and
	 * the dest in the right becomes the dest of the final Tile.
	 * 
	 * @param leftTile is the parent Tile
	 * @param rightTile is the child Tile
	 * @return a merged Tile
	 */
	public static Tile mergeTiles(Tile leftTile, Tile rightTile) {
		Tile tempTile = new Tile();
		List<Instruction> leftInstructions = leftTile.getInstructions();
		List<Instruction> rightInstructions = rightTile.getInstructions();
		List<Instruction> newInstructions = new ArrayList<Instruction>();
		3newInstructions.addAll(rightInstructions);
		newInstructions.addAll(leftInstructions);
		
		List<Instruction> copiedInstructions = new ArrayList<Instruction>();
		for (int i = 0; i < newInstructions.size(); i++) {
			copiedInstructions.add(newInstructions.get(i).getCopyInstruction());
		}
		
		tempTile.instructions = copiedInstructions;
		tempTile.rootOfSubtree = leftTile.rootOfSubtree;
		tempTile.dest = rightTile.dest;
		tempTile.cost = leftTile.cost + rightTile.cost;
		return tempTile;
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
	public static IRNode makeTree(List<String> inOrder, List<String> preOrder) {
		if (preOrder.size() == 0 || inOrder.size() == 0) {
			return null;
		}
		System.out.println("This is inOrder " + inOrder);
		System.out.println("This is preOrder " + preOrder);
		String headOfSubtree = preOrder.get(0);
		preOrder.remove(0);
		
		int indexInInOrder = inOrder.indexOf(headOfSubtree);
		
		List<String> leftSubtree = inOrder.subList(0, indexInInOrder);
		
		List<String> rightSubtree = new ArrayList<String>();
		
		if (!(indexInInOrder == inOrder.size() - 1)) {
			rightSubtree = inOrder.subList(indexInInOrder + 1, inOrder.size());
		}
		
		IRNode rootOfLeft = makeTree(leftSubtree, preOrder);
		IRNode rootOfRight = makeTree(rightSubtree, preOrder);
		
		IRNode root = stringToNode(headOfSubtree);
		if (root != null) {
			root.addLeft(rootOfLeft);
			root.addRight(rootOfRight);
		}
		
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
		if (nodeName.contains("null")) {
			return null;
		}
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
	
	public void fillInOperands(List<Operand> argOperands) {
		for (int i = 0; i < instructions.size(); i++) {
			instructions.get(i).fillInInstructions(argOperands);
		}
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
