package jl2755.controlflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.dataflow.IRExprOverrider;

/**
 * A class that is useful for, when an element is added, calculating all
 * the subtrees of the node and then adding all possible subtree components
 * into the list too. Specialized for IRExpr.
 */
public class SubTreeListMaker {

	private List<IRExprOverrider> backingList;
	private List<IRExpr> irexprVersion;
	
	public SubTreeListMaker() {
		backingList = new ArrayList<IRExprOverrider>();
		irexprVersion = new ArrayList<IRExpr>();
	}
	
	public void add(IRExpr argExpr, IRCFGNode ownerNode) {
		List<IRExpr> exprList = extractAllSubTrees(argExpr);
		irexprVersion.addAll(exprList);
		for (int i = 0; i < exprList.size(); i++) {
			backingList.add(new IRExprOverrider(exprList.get(i),ownerNode));
		}
	}
	
	public boolean contains(IRExpr argExpr) {
		IRExprOverrider overridedView = new IRExprOverrider(argExpr,null);
		return backingList.contains(overridedView);
	}
	
	public List<IRExprOverrider> getList() {
		return backingList;
	}
	
	private List<IRExpr> extractAllSubTrees(IRExpr argExpr) {
		List<IRExpr> tempList = new ArrayList<IRExpr>();
		if (argExpr instanceof IRBinOp) {
			IRBinOp binopView = (IRBinOp) argExpr;
			tempList.addAll(extractAllSubTrees(binopView.left()));
			tempList.addAll(extractAllSubTrees(binopView.right()));
			tempList.add(argExpr);
		}
		else if (argExpr instanceof IRCall) {
			IRCall callView = (IRCall) argExpr;
			for (int i = 0; i < callView.args().size(); i++) {
				tempList.addAll(extractAllSubTrees(callView.args().get(i)));
			}
		}
		else if (argExpr instanceof IRConst) {
			// Don't add IRConst since this doesn't cost an instruction anyway.
			// It would be more expensive to turn these things into a shuttle.
		}
		else if (argExpr instanceof IRMem) {
			IRMem memView = (IRMem) argExpr;
			tempList.add(argExpr);
			tempList.addAll(extractAllSubTrees((memView.expr())));
		}
		else if (argExpr instanceof IRTemp) {
			// Don't add IRTemp since this doesn't cost an instruction anyway.
			// It would be more expensive to turn these things into a shuttle.
		}
		else {
			assert(false);
			// Other cases should not be part of the IRTree, or visited at least.
		}
		return tempList;
	}
	
	public IRExprOverrider highestMatchingExpr(Set<IRExprOverrider> argOverriders) {
		
		
		return null;
	}
	
	public static int getValue(IRExpr argExpr) {
		if (argExpr instanceof IRBinOp) {
			return 1 + getValue(((IRBinOp) argExpr).left()) + getValue(((IRBinOp) argExpr).right());
		}
		else if (argExpr instanceof IRCall) {
			IRCall callView = (IRCall) argExpr;
			int currentValue = 0;
			for (int i = 0; i < callView.args().size(); i++) {
				currentValue += getValue(callView.args().get(i));
			}
			return 1 + currentValue;
		}
		else if (argExpr instanceof IRConst) {
			return 0;
		}
		else if (argExpr instanceof IRMem) {
			IRMem memView = (IRMem) argExpr;
			return getValue(memView.expr());
		}
		else if (argExpr instanceof IRName) {
			return 0;
		}
		else if (argExpr instanceof IRTemp) {
			return 0;
		}
		assert(false);
		// Should not fall through to here. In this particular function,
		// it's okay to reach IRConst or IRName, but IRESeq should have been
		// taken out long ago.
		return 0;
	}
	
	@Override
	public String toString() {
		return backingList.toString();
	}
}
