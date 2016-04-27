package jl2755.dataflow;

import java.util.Set;

import jl2755.controlflow.CFGNode;
import jl2755.controlflow.CSECFGNode;

public class CommonSubExp extends Dataflow<CSECFGNode> {

	@Override
	public Set<CSECFGNode> meetOperator(CSECFGNode... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean transferFunction(CFGNode arg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void analyze() {
		// TODO Auto-generated method stub
		
	}

}
