package jl2755.dataflow;

import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;

/**
 * Class that wraps an IRExpr object. This is needed to have a seperate
 * .equals method. We need the original object pointer .equals method
 * since we need to use HashMaps and stuff (that actually refer
 * to object ID's).
 */
public class IRExprOverrider {
	private IRExpr encapsulatedIRExpr;
	
	public IRExprOverrider(IRExpr argExpr) {
		encapsulatedIRExpr = argExpr;
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
	
	public boolean compareTwoIRTrees(IRExpr arg1, IRExpr arg2) {
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
}
