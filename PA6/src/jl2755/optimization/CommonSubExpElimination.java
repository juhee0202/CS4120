package jl2755.optimization;

import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.dataflow.AvailableExpressionAnalysis;

/**
 * Class that, after an available expressions analysis has been done,
 * uses common subexpressions by creating new IRMem into temps
 * and using that temp over, instead of recalculating expressions.
 */
public class CommonSubExpElimination extends Optimization{
	
	private IRFuncDecl irfc;
	
	
	public CommonSubExpElimination() {
	}
	
	@Override
	public boolean run(ControlFlowGraph cfg) {
		AvailableExpressionAnalysis analyzerObject = new AvailableExpressionAnalysis(cfg);
		analyzerObject.analyze();
		for (CFGNode node: cfg.getAllNodes()) {
			
		}
		
		
		return false;
	}
	
}
