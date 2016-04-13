package jl2755.assembly;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.*;
import jl2755.assembly.Instruction.Operation;

public class Tile {

	private IRNode rootOfSubtree;
	private int cost;
	private List<Instruction> instructions;
	private Operand dest;
	private tileEnum theEnum = tileEnum.NOTHING;
	
	public enum tileEnum {
		NOTHING,
		/**(0)*/
		BASEONLY,
		/**0(1)*/
		BASEWITHCONSTANTOFFSET,
		/**0(1,2)*/
		BASEWITHBOTHOFFSET,
		/**(0,1,2)*/
		BASEWITHREGISTEROFFSETANDFACTOR,
		/**0(1,2,3)*/
		EVERYTHING;
		
//		public static Operand enumToEmptyDest(tileEnum argEnum) {
//			switch (argEnum){
//			case NOTHING: return null;
//			case BASEONLY: return new Memory(null);
//			case BASEWITHCONSTANTOFFSET: return 
//			
//			default: System.out.println("Something wrong in enumToEmptyDest method"); break;
//			}
//		}
	}
	
	public Tile(List<Instruction> i) {
		instructions = i;
	}
	
	/**
	 * Should only be used in mergeTiles to make a new blank Tile
	 */
	private Tile() {
	}
	
	public Tile(List<String> preOrder, List<String> inOrder, List<Instruction> i,
			int argCost) {
		rootOfSubtree = makeTree(inOrder, preOrder);
		instructions = i;
		cost = argCost;
	}
	
	public Tile(List<String> preOrder, List<String> inOrder, List<Instruction> i,
			int argCost, tileEnum argEnum) {
		rootOfSubtree = makeTree(inOrder, preOrder);
		instructions = i;
		cost = argCost;
		theEnum = argEnum;
	}
	
	/** Returns a cloned version of argTile with root as a real IRNode */
	public Tile(IRNode rootOfRealTree, Tile argTile) {
		Tile tempTile = new Tile();
		tempTile.rootOfSubtree = rootOfRealTree;
		tempTile.cost = argTile.cost;
		List<Instruction> tempInstructions = argTile.instructions;
		List<Instruction> newInstructions = new ArrayList<Instruction>();
		for (int i = 0; i < tempInstructions.size(); i++) {
			newInstructions.add(tempInstructions.get(i).getCopyInstruction());
		}
		instructions = newInstructions;
		theEnum = argTile.theEnum;
	}
	
//	public Tile(List<Operand> argOperands, Tile argTile) {
//		
//	}
	
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
	 * the dest in the parent becomes the dest of the final Tile.
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
		newInstructions.addAll(leftInstructions);
		newInstructions.addAll(rightInstructions);
		
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
		IRNode theMakingNode = null;
		if (nodeName.contains("null")) {
			theMakingNode = null;
		}
		else if (nodeName.contains("BinOpAdd")) {
			theMakingNode = new IRBinOp(OpType.ADD,null,null);
		}
		else if (nodeName.contains("BinOpMult")) {
			theMakingNode = new IRBinOp(OpType.MUL,null,null);
		}
		else if (nodeName.contains("Call")) {
			IRExpr[] hue = null;
			theMakingNode = new IRCall(null, hue);
		}
		else if (nodeName.contains("CJump")) {
			theMakingNode = new IRCJump(null,null);
		}
		else if (nodeName.contains("Const")) {
			theMakingNode = new IRConst(0);
		}
		else if (nodeName.contains("Exp")) {
			theMakingNode = new IRExp(null);
		}
		else if (nodeName.contains("Jump")) {
			theMakingNode = new IRJump(null);
		}
		else if (nodeName.contains("Label")) {
			theMakingNode = new IRLabel(null);
		}
		else if (nodeName.contains("Mem")) {
			theMakingNode = new IRMem(null);
		}
		else if (nodeName.contains("Move")) {
			theMakingNode = new IRMove(null,null);
		}
		else if (nodeName.contains("Name")) {
			theMakingNode = new IRName(null);
		}
		else if (nodeName.contains("Return")) {
			theMakingNode = new IRReturn();
		}
		else if (nodeName.contains("Seq")) {
			theMakingNode = new IRSeq();
		}
		else if (nodeName.contains("Temp")) {
			theMakingNode = new IRTemp(null);
		}
//		System.out.println("You wrote a weird string for node name");
//		assert(false);
		
		if (nodeName.contains("LeftConstantOffset")) {
			theMakingNode.setLeftChildEnumType(ChildType.CONSTANTOFFSET);
		}
		if (nodeName.contains("RightConstantOffset")) {
			theMakingNode.setRightChildEnumType(ChildType.CONSTANTOFFSET);
		}
		if (nodeName.contains("LeftRegisterBase")) {
			theMakingNode.setLeftChildEnumType(ChildType.REGISTERBASE);
		}
		if (nodeName.contains("RightRegisterBase")) {
			theMakingNode.setRightChildEnumType(ChildType.REGISTERBASE);
		}
		if (nodeName.contains("LeftConstantFactor")) {
			theMakingNode.setLeftChildEnumType(ChildType.CONSTANTFACTOR);
		}
		if (nodeName.contains("RightConstantFactor")) {
			theMakingNode.setRightChildEnumType(ChildType.CONSTANTFACTOR);
		}
		if (nodeName.contains("LeftRegisterOffset")) {
			theMakingNode.setLeftChildEnumType(ChildType.REGISTERFACTOR);
		}
		if (nodeName.contains("RightRegisterOffset")) {
			theMakingNode.setRightChildEnumType(ChildType.REGISTERFACTOR);
		}
		
		return theMakingNode;
	}
	
	public void fillInOperands(List<Operand> argOperands) {
		
		// Parse through and replace all Mem Operands with Mov to registers
		
		String[] shuttleStuff = {"rcx", "rdx", "r11"};
		List<Instruction> neededMovs = new ArrayList<Instruction>();
		for (int i = 0; i < argOperands.size(); i++) {
			if (argOperands.get(i) instanceof Memory) {
				neededMovs.add(new Instruction(Operation.MOVQ,argOperands.get(i), new Register(shuttleStuff[i])));
				argOperands.set(i,new Register(shuttleStuff[i]));
				cost++;
			}
		}
		
		
		if (theEnum == tileEnum.BASEONLY) {
			Register registerView = (Register) argOperands.get(0);
			dest = new Memory(registerView);
		}
		if (theEnum == tileEnum.BASEWITHCONSTANTOFFSET) {
			Register theBase = null;
			Constant theOffset = null;
			assert(argOperands.size() == 2);
			for (int i = 0; i < argOperands.size(); i++) {
				if (argOperands.get(i).isRegBase()) {
					theBase = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isConstOffset()) {
					theOffset = (Constant) argOperands.get(i);
				}
			}
			System.out.println("" + theBase + " " + theOffset);
			dest = new Memory(theOffset,theBase);
		}
		if (theEnum == tileEnum.BASEWITHBOTHOFFSET) {
			Register theBase = null;
			Register registerOffset = null;
			Constant constantOffset = null;
			assert(argOperands.size() == 3);
			for (int i = 0; i < argOperands.size(); i++) {
				if (argOperands.get(i).isRegBase()) {
					theBase = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isRegFactorOffset()) {
					registerOffset = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isConstOffset()) {
					constantOffset = (Constant) argOperands.get(i);
				}
			}
			dest = new Memory(constantOffset,theBase,registerOffset);
		}
		if (theEnum == tileEnum.BASEWITHREGISTEROFFSETANDFACTOR) {
			Register theBase = null;
			Register registerOffset = null;
			Constant constantFactor = null;
			assert(argOperands.size() == 3);
			for (int i = 0; i < argOperands.size(); i++) {
				if (argOperands.get(i).isRegBase()) {
					theBase = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isRegFactorOffset()) {
					registerOffset = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isConstFactor()) {
					constantFactor = (Constant) argOperands.get(i);
				}
			}
			dest = new Memory(theBase,registerOffset,constantFactor);
		}
		if (theEnum == tileEnum.EVERYTHING) {
			Register theBase = null;
			Register registerOffset = null;
			Constant constantFactor = null;
			Constant constantOffset = null;
			assert(argOperands.size() == 4);
			for (int i = 0; i < argOperands.size(); i++) {
				if (argOperands.get(i).isRegBase()) {
					theBase = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isRegFactorOffset()) {
					registerOffset = (Register) argOperands.get(i);
				}
				if (argOperands.get(i).isConstFactor()) {
					constantFactor = (Constant) argOperands.get(i);
				}
				if (argOperands.get(i).isConstOffset()) {
					constantOffset = (Constant) argOperands.get(i);
				}
			}
			dest = new Memory(constantOffset, theBase, registerOffset, constantFactor);
		}
	}
	
	// TODO: Uncomment
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instr = instructions.get(i);
			if (instr.getOp() == Operation.LABEL) {
				s += instr.toString() + ":\n";
			} else {
				s += "\t" + instr.toString() + "\n";
			}
		}
		s+=dest;
		s+=theEnum;
		return s;
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
