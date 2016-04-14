package jl2755.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRESeq;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRNode;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.assembly.ChildType;
import jl2755.assembly.Constant;
import jl2755.assembly.Operand;
import jl2755.assembly.Register;
import jl2755.assembly.Tile;

/**
 * Class that is used to compare two trees and see if one is a pattern
 * in another.
 */
public class IRTreeEqualsVisitor implements IRTreeVisitor{

	private boolean currentBool;
	
	private IRNode currentNode;
	
	private List<IRNode> allChildrenNode;
	
	private List<Operand> operandOfNodesInTile;
	
	private TilingVisitor tilingVisitor;
	
	private ChildType currentChildTypeOfFake;
	
	private enum TreeType {
		MEM;
	}
	
	public IRTreeEqualsVisitor(TilingVisitor argVisitor){
		allChildrenNode = new ArrayList<IRNode>();
		tilingVisitor = argVisitor;
	}
	
	/**
	 * Checks if the pattern tree is a subtree rooted in
	 * the IRTree
	 * 
	 * @param rootOfFakeInstance is the pattern tree
	 * @param rootOfRealInstance is the IRTree we are looking for a pattern in
	 * @return true iff the pattern tree is a subtree rooted in the IRTree
	 */
	public boolean equalTrees(IRNode rootOfFakeInstance, IRNode rootOfRealInstance) {
		currentBool = true;
		allChildrenNode = new ArrayList<IRNode>();
		operandOfNodesInTile = new ArrayList<Operand>();
		equalSubTrees(rootOfFakeInstance, rootOfRealInstance);
		return currentBool;
	}
	
	private boolean equalSubTrees(IRNode rootOfFakeInstance, IRNode rootOfRealInstance) {
		boolean temp = doesEquals(rootOfFakeInstance, rootOfRealInstance);

//		System.out.println("This is temp " + temp);
		
		currentBool = currentBool && temp;
		
//		System.out.println("This is currentBool right after " + currentBool);

		if (temp) {
			currentNode = rootOfRealInstance;
			if (rootOfFakeInstance != null) {
				rootOfFakeInstance.accept(this);
			} else {
				allChildrenNode.add(rootOfRealInstance);
//				if (currentChildTypeOfFake == null) {
					rootOfRealInstance.accept(tilingVisitor);
//					System.out.println(rootOfRealInstance);
//					System.out.println(tilingVisitor.getTileOfNode(rootOfRealInstance).getDest());
					System.out.println("GETTING HERE");
					operandOfNodesInTile.add(tilingVisitor.getTileOfNode(rootOfRealInstance).getDest());
//				}
			}
		}
		return temp;
	}
	
	public List<IRNode> getAllChildrenNode() {
		System.out.println("ALL CHILDREN " + allChildrenNode);
		return allChildrenNode;
	}

	public List<Operand> getOperandOfNodesInTile() {
		System.out.println("IRTREE " + operandOfNodesInTile);
		return operandOfNodesInTile;
	}
	
	private static void setTypeOfOperand(Operand argOperand, ChildType argTypeEnum) {
		if (argTypeEnum == ChildType.CONSTANTFACTOR) {
			((Constant)argOperand).setConstFactor();
		} else if (argTypeEnum == ChildType.CONSTANTOFFSET) {
			((Constant)argOperand).setConstOffset();
		} else if (argTypeEnum == ChildType.REGISTERBASE) {
			((Register)argOperand).setRegBase();
		} else if (argTypeEnum == ChildType.REGISTERFACTOR) {
			((Register)argOperand).setRegFactorOffset();
		}
	}

	@Override
	public void visit(IRBinOp bo) {
//		System.out.println(currentBool);

		IRBinOp tempCurrent = (IRBinOp) currentNode;
		
		currentChildTypeOfFake = bo.getLeftChildEnumType();
		boolean normalResult = equalSubTrees(bo.left(), tempCurrent.left());
		
//		System.out.println("1 " + currentChildTypeOfFake);

		currentChildTypeOfFake = bo.getRightChildEnumType();
		normalResult = normalResult && equalSubTrees(bo.right(), tempCurrent.right());
		
//		System.out.println("2 " + currentChildTypeOfFake);
		
		
		if (normalResult) {
			if (bo.getLeftChildEnumType() != null) {
				tempCurrent.left().accept(tilingVisitor);
				Tile tempTile = tilingVisitor.getTileOfNode(tempCurrent.left());
				Operand destOfLeftTile = tempTile.getDest();
				setTypeOfOperand(destOfLeftTile,bo.getLeftChildEnumType());
				operandOfNodesInTile.add(destOfLeftTile);
//				System.out.println("Got Here 1 " + destOfLeftTile);
			}
			else {
				currentNode = tempCurrent.left();
				bo.left().accept(this);
			}
			if (bo.getRightChildEnumType() != null) {
				tempCurrent.right().accept(tilingVisitor);
				Tile tempTile = tilingVisitor.getTileOfNode(tempCurrent.right());
				Operand destOfRightTile = tempTile.getDest();
				setTypeOfOperand(destOfRightTile,bo.getRightChildEnumType());
				operandOfNodesInTile.add(destOfRightTile);
//				System.out.println("Got Here 2 " + destOfRightTile);

			}
			else {
				currentNode = tempCurrent.right();
				bo.right().accept(this);
			}
			currentBool = currentBool && normalResult;
			currentNode = tempCurrent;
			return;
		}
		
		currentChildTypeOfFake = bo.getLeftChildEnumType();
		boolean flippedResult = equalSubTrees(bo.left(),tempCurrent.right());
		
		currentChildTypeOfFake = bo.getRightChildEnumType();
		flippedResult = flippedResult && equalSubTrees(bo.right(),tempCurrent.left());
		
//		System.out.println(flippedResult);
		
		if (flippedResult) {
			if (bo.getLeftChildEnumType() != null) {
				tempCurrent.right().accept(tilingVisitor);
				Tile tempTile = tilingVisitor.getTileOfNode(tempCurrent.right());
				Operand destOfRightTile = tempTile.getDest();
				setTypeOfOperand(destOfRightTile,bo.getLeftChildEnumType());
				operandOfNodesInTile.add(destOfRightTile);
//				System.out.println("Got Here 3 " + destOfRightTile);
			}
			else {
				currentNode = tempCurrent.right();
				bo.left().accept(this);
			}
			if (bo.getRightChildEnumType() != null) {
				tempCurrent.left().accept(tilingVisitor);
				Tile tempTile = tilingVisitor.getTileOfNode(tempCurrent.left());
				Operand destOfLeftTile = tempTile.getDest();
				setTypeOfOperand(destOfLeftTile,bo.getRightChildEnumType());
				operandOfNodesInTile.add(destOfLeftTile);
//				System.out.println("Got Here 4 " + destOfLeftTile);
			}
			else {
				currentNode = tempCurrent.left();
				bo.right().accept(this);
			}
			currentBool = currentBool && flippedResult;
//			System.out.println(tempCurrent);
			currentNode = tempCurrent;
			return;
		}
		currentNode = tempCurrent;
		currentBool = false;
//		System.out.println("ever get here");
	}

	@Override
	public void visit(IRCall call) {
		IRCall tempCurrent = (IRCall) currentNode;
		boolean tempBool = currentBool;
		currentBool = equalSubTrees(call.target(),tempCurrent.target()) && tempBool;
		List<IRExpr> fakeExprs = call.args();
		List<IRExpr> trueExprs = tempCurrent.args();
		if (fakeExprs.size() != trueExprs.size()) {
			currentBool = false;
		}
		else {
			for (int i = 0; i < fakeExprs.size(); i++) {
				boolean tempBool2 = currentBool;
				currentBool = equalSubTrees(fakeExprs.get(i),trueExprs.get(i)) && tempBool2;
			}
		}
	}

	@Override
	public void visit(IRCJump cj) {
		IRCJump tempCurrent = (IRCJump) currentNode;
		boolean tempBool = currentBool;
		currentBool = equalSubTrees(cj.expr(),tempCurrent.expr()) && tempBool;
	}

	@Override
	public void visit(IRCompUnit cu) {
		// Not needed
	}

	@Override
	public void visit(IRConst con) {
		
	}

	@Override
	public void visit(IRESeq eseq) {
		// Not needed
	}

	@Override
	public void visit(IRExp exp) {
		IRExp tempCurrent = (IRExp) currentNode;
		boolean tempBool = currentBool;
		currentBool = equalSubTrees(exp.expr(),tempCurrent.expr()) && tempBool;
	}

	@Override
	public void visit(IRFuncDecl fd) {
		IRFuncDecl tempCurrent = (IRFuncDecl) currentNode;
		boolean tempBool = currentBool;
		currentBool = equalSubTrees(fd.body(),tempCurrent.body()) && tempBool;
	}

	@Override
	public void visit(IRJump j) {
		IRJump tempCurrent = (IRJump) currentNode;
		boolean tempBool = currentBool;
		currentBool = equalSubTrees(j.target(), tempCurrent.target()) && tempBool;
	}

	@Override
	public void visit(IRLabel l) {
		// Not needed
	}

	@Override
	public void visit(IRMem mem) {
		IRMem tempCurrent = (IRMem) currentNode;
//		System.out.println("BEFORE: " + currentBool);
		currentBool = equalSubTrees(mem.expr(), tempCurrent.expr()) && currentBool;

//		System.out.println("AFTER: " + currentBool);

//		System.out.println(currentBool);
	}

	@Override
	public void visit(IRMove mov) {
		IRMove tempCurrent = (IRMove) currentNode;
		
		boolean tempBool1 = currentBool;
		currentBool = equalSubTrees(mov.target(),tempCurrent.target()) && tempBool1;
		boolean tempBool2 = currentBool;
		currentBool = equalSubTrees(mov.expr(),tempCurrent.expr()) && tempBool2;
	}

	@Override
	public void visit(IRName name) {
		// Not needed
	}

	@Override
	public void visit(IRReturn ret) {
		// Not needed
	}

	@Override
	public void visit(IRSeq seq) {
		// Not needed
	}

	@Override
	public void visit(IRTemp temp) {
//		operandOfNodesInTile.add(new Register(temp.name()));
	}
	
	private static boolean doesEquals(IRNode left, IRNode right) {
		if (left == null) {
			return true;
		}
		if (left instanceof IRBinOp) {
			if (right instanceof IRBinOp) {
				IRBinOp leftBinOp = (IRBinOp)  left;
				IRBinOp rightBinOp = (IRBinOp) right;
				if (leftBinOp.opType() == rightBinOp.opType()) {
					return true;
				}
			}
			return false;
		}
		if (left instanceof IRCall) {
			if (right instanceof IRCall) {
				return true;
			}
			return false;
		}
		if (left instanceof IRCJump) {
			if (right instanceof IRCJump) {
				return true;
			}
			return false;
		}
		if (left instanceof IRCompUnit) {
			if (right instanceof IRCompUnit) {
				return true;
			}
			return false;
		}
		if (left instanceof IRConst) {
			if (right instanceof IRConst) {
				return true;
			}
			return false;
		}
		if (left instanceof IRESeq) {
			if (right instanceof IRESeq) {
				return true;
			}
			return false;
		}
		if (left instanceof IRExp) {
			if (right instanceof IRExp) {
				return true;
			}
			return false;
		}
		if (left instanceof IRFuncDecl) {
			if (right instanceof IRFuncDecl) {
				return true;
			}
			return false;
		}
		if (left instanceof IRJump) {
			if (right instanceof IRJump) {
				return true;
			}
			return false;
		}
		if (left instanceof IRLabel) {
			if (right instanceof IRLabel) {
				return true;
			}
			return false;
		}
		if (left instanceof IRMem) {
			if (right instanceof IRMem) {
				return true;
			}
			return false;
		}
		if (left instanceof IRMove) {
			if (right instanceof IRMove) {
				return true;
			}
			return false;
		}
		if (left instanceof IRName) {
			if (right instanceof IRName) {
				return true;
			}
			return false;
		}
		if (left instanceof IRReturn) {
			if (right instanceof IRReturn) {
				return true;
			}
			return false;
		}
		if (left instanceof IRSeq) {
			if (right instanceof IRSeq) {
				return true;
			}
			return false;
		}
		if (left instanceof IRTemp) {
			if (right instanceof IRTemp) {
				return true;
			}
			return false;
		}
		System.out.println("Something wrong happened in IRTreeEqualsVisitor equals");
		assert(false);
		return false;
	}

}
