package jl2755.dataflow;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.IRCFGNode;

/**
 * Class that wraps an IRExpr object. This is needed to have a seperate
 * .equals method. We need the original object pointer .equals method
 * since we need to use HashMaps and stuff (that actually refer
 * to object ID's).
 */
public class IRExprOverrider {
	/**
	 * The IRExpr that this instance represents.
	 */
	public IRExpr encapsulatedIRExpr;
	
	/**
	 * All the nodes where this encapsulatedIRExpr has originated from.
	 */
	public List<IRCFGNode> nodesOfOrigin = new ArrayList<IRCFGNode>();
	
	/**
	 * Boolean that represents if encapsulatedIRExpr has been hoisted for
	 * all the nodesOfOrigin.
	 */
	public boolean hasBeenHoisted = false;
	
	/**
	 * Should only be used for merging two IRExprOverrider instances.
	 */
	private IRExprOverrider() {
	}
	
	/**
	 * Used for wrapping children in replaceSubTreeWithMove
	 *	
	 * @param argExpr
	 */
	private IRExprOverrider(IRExpr argExpr) {
		encapsulatedIRExpr = argExpr;
	}
	
	public IRExprOverrider(IRExpr argExpr, IRCFGNode argNode) {
		encapsulatedIRExpr = argExpr;
		nodesOfOrigin.add(argNode);
	}
	
	public IRExprOverrider(IRExprOverrider arg1, IRExprOverrider arg2) {
		assert(arg1.equals(arg2));
		IRExprOverrider temp = new IRExprOverrider();
		temp.encapsulatedIRExpr = arg1.encapsulatedIRExpr;
		temp.nodesOfOrigin.addAll(arg1.nodesOfOrigin);
		temp.nodesOfOrigin.addAll(arg2.nodesOfOrigin);
		assert(this.equals(arg1));
		assert(this.equals(arg2));
	}
	
	public IRExpr getIRExpr() {
		return encapsulatedIRExpr;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof IRExprOverrider)) {
			return false;
		}
		IRExprOverrider irexprView = (IRExprOverrider) o;
		IRExpr otherIRExpr = irexprView.getIRExpr();
		return compareTwoIRTrees(encapsulatedIRExpr, otherIRExpr);
	}
	
	/**
	 * Checks if two trees are identical (not object pointers wise).
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static boolean compareTwoIRTrees(IRExpr arg1, IRExpr arg2) {
		if (arg1 instanceof IRBinOp) {
			if (arg2 instanceof IRBinOp) {
				IRBinOp binopView1 = (IRBinOp) arg1;
				IRBinOp binopView2 = (IRBinOp) arg2;
				if (binopView1.opType() == binopView2.opType()) {
					return compareTwoIRTrees(binopView1.left(),binopView2.left()) &&
							compareTwoIRTrees(binopView1.right(),binopView2.right());
				}
				else {
					return false;
				}
			}
			return false;
		}
		if (arg1 instanceof IRCall) {
			if (arg2 instanceof IRCall) {
				IRCall callView1 = (IRCall) arg1;
				IRCall callView2 = (IRCall) arg2;
				List<IRExpr> callArgs1 = callView1.args();
				List<IRExpr> callArgs2 = callView2.args();
				if (callArgs1.size() != callArgs2.size()) {
					return false;
				}
				for (int i = 0; i < callArgs1.size(); i++) {
					if (!compareTwoIRTrees(callArgs1.get(i),callArgs2.get(i))) {
						return false;
					}
				}
				IRExpr target1 = callView1.target();
				IRExpr target2 = callView2.target();
				return compareTwoIRTrees(target1, target2);
			}
			return false;
		}
		if (arg1 instanceof IRConst) {
			if (arg2 instanceof IRConst) {
				IRConst constView1 = (IRConst) arg1;
				IRConst constView2 = (IRConst) arg2;
				return constView1.value() == constView2.value();
			}
			return false;
		}
		if (arg1 instanceof IRMem) {
			if (arg2 instanceof IRMem) {
				IRMem memView1 = (IRMem) arg1;
				IRMem memView2 = (IRMem) arg2;
				return compareTwoIRTrees(memView1.expr(),memView2.expr());
			}
			return false;
		}
		if (arg1 instanceof IRName) {
			if (arg2 instanceof IRName) {
				IRName nameView1 = (IRName) arg1;
				IRName nameView2 = (IRName) arg2;
				return nameView1.name().equals(nameView2.name());
			}
			return false;
		}
		if (arg1 instanceof IRTemp) {
			if (arg2 instanceof IRTemp) {
				IRTemp tempView1 = (IRTemp) arg1;
				IRTemp tempView2 = (IRTemp) arg2;
				return tempView1.name().equals(tempView2.name());
			}
			return false;
		}
		// Should have fallen into another case.
		assert(false);
		return false;
	}
	
	/**
	 * Returns true if and only if encapsulateIRExpr has arg in it.
	 * 
	 * @param arg
	 * @return
	 */
	public boolean doesContain(IRExprOverrider arg) {
		IRExpr temp = arg.encapsulatedIRExpr;
		assert(temp instanceof IRTemp);
		if (compareTwoIRTrees(temp,encapsulatedIRExpr)) {
			return true;
		}
		if (encapsulatedIRExpr instanceof IRBinOp) {
			IRBinOp binopView = (IRBinOp) encapsulatedIRExpr;
			return compareTwoIRTrees(temp,binopView.left()) ||
					compareTwoIRTrees(temp,binopView.right());
		}
		else if (encapsulatedIRExpr instanceof IRCall) {
			IRCall callView = (IRCall) encapsulatedIRExpr;
			for (int i = 0; i < callView.args().size(); i++) {
				if (compareTwoIRTrees(temp,callView.args().get(i))) {
					return true;
				}
			}
			return false;
		}
		else if (encapsulatedIRExpr instanceof IRConst) {
			// Const can never be a tree with a temp somewhere in its subtree
			return false;
		}
		else if (encapsulatedIRExpr instanceof IRMem) {
			IRMem memView = (IRMem) encapsulatedIRExpr;
			return compareTwoIRTrees(memView,temp);
		}
		else if (encapsulatedIRExpr instanceof IRTemp) {
			return compareTwoIRTrees(encapsulatedIRExpr,temp);
		}
		
		// Should never fall through to the cases of ESeq or Name
		assert(false);
		return false;
	}
	
	/**
	 * Looks for the subTree within this IRExpr, and makes a new IRMove statement
	 * out of it, to be executed before this instruction. Then, it replaces the
	 * sub tree with a temp of target of the previous IRMove statement. We know that
	 * since encapsulatedIRExpr must be more complex than just an IRConst or IRTemp,
	 * that we are always guaranteed to be able to have, in the tree, a "parent" node
	 * of the subtree we are looking to replace. Therefore, we can always replace
	 * the pointer of the subtree with a pointer to a temp.
	 * 
	 * @param globalCounter
	 * @param subTree
	 */
	public static void replaceExprTreeWithMove(int globalCounter, 
			IRExprOverrider ownerTree, IRExprOverrider subTree) {
		assert(!(subTree.encapsulatedIRExpr instanceof IRTemp));
		
		if (ownerTree.encapsulatedIRExpr instanceof IRBinOp) {
			IRBinOp binOpView = (IRBinOp) ownerTree.encapsulatedIRExpr;
			if (compareTwoIRTrees(subTree.encapsulatedIRExpr,binOpView.left())) {
				IRTemp tempNode = new IRTemp("csetemp" + globalCounter);
				binOpView.addLeft(tempNode);
			}
			if (compareTwoIRTrees(subTree.encapsulatedIRExpr,binOpView.right())) {
				IRTemp tempNode = new IRTemp("csetemp" + globalCounter);
				binOpView.addRight(tempNode);
			}
			else {
				IRExprOverrider leftOne = new IRExprOverrider(binOpView.left());
				replaceExprTreeWithMove(globalCounter, ownerTree, leftOne);
				IRExprOverrider rightOne = new IRExprOverrider(binOpView.right());
				replaceExprTreeWithMove(globalCounter, ownerTree, rightOne);
			}
			return;
		}
		else if (ownerTree.encapsulatedIRExpr instanceof IRCall) {
			IRCall callView = (IRCall) ownerTree.encapsulatedIRExpr;
			for (int i = 0; i < callView.args().size(); i++) {
				if (compareTwoIRTrees(subTree.encapsulatedIRExpr,callView.args().get(i))) {
					IRTemp tempNode = new IRTemp("csetemp" + globalCounter);
					callView.args().set(i, tempNode);
				}
				else {
					IRExprOverrider argOne = new IRExprOverrider(callView.args().get(i));
					replaceExprTreeWithMove(globalCounter, ownerTree, subTree);
				}
			}
			return;
		}
		else if (ownerTree.encapsulatedIRExpr instanceof IRMem) {
			IRMem memView = (IRMem) ownerTree.encapsulatedIRExpr;
			if (compareTwoIRTrees(subTree.encapsulatedIRExpr,memView)) {
				IRTemp tempNode = new IRTemp("csetemp" + globalCounter);
				memView.addLeft(tempNode);
			}
			else {
				IRExprOverrider argOne = new IRExprOverrider(memView.expr());
				replaceExprTreeWithMove(globalCounter, ownerTree, subTree);
			}
			return;
		}
		
		assert(false);
		// The subtree that got matched shouldn't be of IRConst, IRESeq,
		// IRName or IRTemp. This is because IRESeq and IRName shouldn't even be in
		// relevant in this analysis, and IRTemp and IRConst shouldn't even be considered
		// in expr(n) back in available expressions analysis.
	}
	
	public static void replaceExprInStmt(int globalCounter, IRStmt argStmt, IRExprOverrider argExpr) {
		if (argStmt instanceof IRCJump) {
			IRCJump cjumpView = (IRCJump) argStmt;
			replaceExprTreeWithMove(globalCounter, new IRExprOverrider(cjumpView.expr()), argExpr);
		}
		else if (argStmt instanceof IRExp) {
			IRExp expView = (IRExp) argStmt;
			assert(expView.expr() instanceof IRCall);
			replaceExprTreeWithMove(globalCounter, new IRExprOverrider(expView.expr()), argExpr);
		}
		else if (argStmt instanceof IRMove) {
			IRMove moveView = (IRMove) argStmt;
			replaceExprTreeWithMove(globalCounter, new IRExprOverrider(moveView.expr()), argExpr);
			replaceExprTreeWithMove(globalCounter, new IRExprOverrider(moveView.target()), argExpr);
			
		}
		// Nothing else should be in this statement if there is an available expression for it.
		// It cannot be IRJump since that doesn't have any meaningful expressions in it.
		// It cannot be IRLabel since labels don't use any other expressions.
		// It cannot be IRReturn since returns don't have any expressions in it.
		// It cannot be IRSeq since there cannot be nested IRSeq's, and we never have IRSeq nodes in the CFG.
	}
}
