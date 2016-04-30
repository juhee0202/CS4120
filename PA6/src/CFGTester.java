import java.util.HashMap;

import org.junit.Test;

import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import jl2755.Main;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.SSAFormConverter;
import jl2755.controlflow.SSAFormGraph;

public class CFGTester {
	@Test
	public void test1() {
//		String fileName = "/Users/thomasaeyo/Desktop/CS4120_hw/vm-1/shared/xth/tests/pa4/primes.xi";
		String fileName = "/Users/thomasaeyo/Desktop/CS4120/PA6/tests/pa6/practice.xi";
		Main.main(new String[] {"-irrun", "-O", fileName, "-libpath", "runtime/include", "-target"});
		HashMap<String, IRCompUnit> file2IR = Main.fileToIR;
		IRCompUnit cu = file2IR.get(fileName);
		for (IRFuncDecl fd : cu.functions().values()) {
			ControlFlowGraph cfg = new ControlFlowGraph(fd);
			
			System.out.println("*** Original CFG ***");
			cfg.print();
			
			SSAFormConverter converter = new SSAFormConverter(cfg);
			SSAFormGraph ssaCfg = converter.convertToSSAForm();
			
			System.out.println("*** SSA Form ***");
			ssaCfg.print();
			
//			System.out.println("*** Convert back ***");
//			ControlFlowGraph newCfg = converter.convertBack();
//			newCfg.print();
		}
	}
}
