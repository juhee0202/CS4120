package optimization;

import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;

/**
 * Class that, after an available expressions analysis has been done,
 * uses common subexpressions by creating new IRMem into temps
 * and using that temp over, instead of recalculating expressions.
 */
public class CommonSubExpElimination {
	
	private IRCompUnit ircu;
	
	
	public CommonSubExpElimination(IRCompUnit argIrcu) {
		ircu = argIrcu;
	}
	
	public void run() {
		
	}
	
}
