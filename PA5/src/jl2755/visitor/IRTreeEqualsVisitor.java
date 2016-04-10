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
import jl2755.assembly.Constant;
import jl2755.assembly.Operand;
import jl2755.assembly.Register;

/**
 * Class that is used to compare two trees and see if one is a pattern
 * in another.
 */
public class IRTreeEqualsVisitor implements IRTreeVisitor{

	private boolean currentBool;
	
	private IRNode currentNode;
	
	private List<IRNode> allChildrenNode;
	
	private List<Operand> operandOfNodesInTile;
	
	public IRTreeEqualsVisitor(){
		allChildrenNode = new ArrayList<IRNode>();
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
	
	private void equalSubTrees(IRNode rootOfFakeInstance, IRNode rootOfRealInstance) {
		boolean temp = doesEquals(rootOfFakeInstance, rootOfRealInstance);
		
		currentBool = currentBool && temp;
		if (temp) {
			currentNode = rootOfRealInstance;
			if (rootOfFakeInstance != null) {
				rootOfFakeInstance.accept(this);

			}
		}
		if (rootOfFakeInstance == null) {
			allChildrenNode.add(rootOfRealInstance);
//			if (!(rootOfRealInstance == null)) {
//				allChildrenNode.add(rootOfRealInstance);
//			}
		}
	}
	
	public List<IRNode> getAllChildrenNode() {
		return allChildrenNode;
	}

	public List<Operand> getOperandOfNodesInTile() {
		return operandOfNodesInTile;
	}

	@Override
	public void visit(IRBinOp bo) {
		IRBinOp tempCurrent = (IRBinOp) currentNode;
		
		equalSubTrees(bo.left(), tempCurrent.left());
		equalSubTrees(bo.right(), tempCurrent.right());
	}

	@Override
	public void visit(IRCall call) {
		IRCall tempCurrent = (IRCall) currentNode;
		
		equalSubTrees(call.target(),tempCurrent.target());
		List<IRExpr> fakeExprs = call.args();
		List<IRExpr> trueExprs = tempCurrent.args();
		if (fakeExprs.size() != trueExprs.size()) {
			currentBool = false;
		}
		else {
			for (int i = 0; i < fakeExprs.size(); i++) {
				equalSubTrees(fakeExprs.get(i),trueExprs.get(i));
			}
		}
	}

	@Override
	public void visit(IRCJump cj) {
		IRCJump tempCurrent = (IRCJump) currentNode;
		
		equalSubTrees(cj.expr(),tempCurrent.expr());
	}

	@Override
	public void visit(IRCompUnit cu) {
		// Not needed
	}

	@Override
	public void visit(IRConst con) {
		operandOfNodesInTile.add(new Constant(con.value()));
	}

	@Override
	public void visit(IRESeq eseq) {
		// Not needed
	}

	@Override
	public void visit(IRExp exp) {
		IRExp tempCurrent = (IRExp) currentNode;
		
		equalSubTrees(exp.expr(),tempCurrent.expr());
	}

	@Override
	public void visit(IRFuncDecl fd) {
		IRFuncDecl tempCurrent = (IRFuncDecl) currentNode;
		
		equalSubTrees(fd.body(),tempCurrent.body());
	}

	@Override
	public void visit(IRJump j) {
		IRJump tempCurrent = (IRJump) currentNode;
		
		equalSubTrees(j.target(), tempCurrent.target());
	}

	@Override
	public void visit(IRLabel l) {
		// Not needed
	}

	@Override
	public void visit(IRMem mem) {
		IRMem tempCurrent = (IRMem) currentNode;
		
		equalSubTrees(mem.expr(), tempCurrent.expr());
	}

	@Override
	public void visit(IRMove mov) {
		IRMove tempCurrent = (IRMove) currentNode;
		
		equalSubTrees(mov.target(),tempCurrent.target());
		equalSubTrees(mov.expr(),tempCurrent.expr());
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
		operandOfNodesInTile.add(new Register(temp.name()));
	}
	
	private static boolean doesEquals(IRNode left, IRNode right) {
		if (left == null) {
			if (right == null) {
				return true;
			}
			return false;
		}
		if (left instanceof IRBinOp) {
			if (right instanceof IRBinOp) {
				return true;
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
