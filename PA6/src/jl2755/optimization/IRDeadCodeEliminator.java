package jl2755.optimization;

import java.util.Set;

import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.IRCFGNode;
import jl2755.controlflow.OptimizationGraph;
import jl2755.dataflow.IRExprOverrider;
import jl2755.dataflow.SSALiveAnalyzer;

public class IRDeadCodeEliminator extends Optimization {

	public IRDeadCodeEliminator() {
	}
	
	@Override
	public boolean run(OptimizationGraph cfg) {
		
		ControlFlowGraph cfgView = (ControlFlowGraph) cfg;
		

		
		boolean hasChanged = false;
		
		// Delete all nodes that don't have
		// their defs in their outs
		outer:
		while (true) {
			SSALiveAnalyzer analyzer = new SSALiveAnalyzer((ControlFlowGraph) cfg);
			
			analyzer.analyze();
			inner:
			for (CFGNode node : cfgView.getAllNodes()) {
				Set<IRExprOverrider> outSet = analyzer.outMap.get(node);
				IRExprOverrider def = analyzer.defs.get(node);
				if (def == null) {
					continue inner;
				}
				
				IRCFGNode irView = (IRCFGNode) node;
				if (irView.underlyingIRStmt instanceof IRMove) {
					IRMove moveView = (IRMove) irView.underlyingIRStmt;
					if (moveView.target() instanceof IRTemp) {
						IRTemp tempView = (IRTemp) moveView.target();
						if (tempView.name().contains("_RET")) {
							continue inner;
						}
					}
					if (moveView.expr() instanceof IRTemp) {
						IRTemp tempView = (IRTemp) moveView.expr();
						if (tempView.name().contains("_ARG")) {
							continue inner;
						}
					}
				}
				
				if (!outSet.contains(def)) {
					// Delete this node
					cfgView.remove(node);
					hasChanged = true;
					continue outer;
					
				}
			}
			break outer;
		}
		
		
		return hasChanged;
	}
	

}
