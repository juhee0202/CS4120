import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import jl2755.Main;
import jl2755.controlflow.CFGNode;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.controlflow.SSAFormConverter;
import jl2755.controlflow.SSAFormGraph;
import jl2755.optimization.CopyPropagator;
import jl2755.optimization.DeadCodeEliminator;
import jl2755.optimization.Optimization;
import jl2755.optimization.UnreachableCodeEliminator;

public class CFGTester {
	@Test
	public void test1() {
////		String fileName = "/Users/thomasaeyo/Desktop/CS4120_hw/vm-1/shared/xth/tests/pa4/primes.xi";
//		String fileName = "/Users/thomasaeyo/Desktop/CS4120/PA6/tests/pa6/practice.xi";
////		String fileName = "/Users/Juhee/Desktop/CS4120/PA6/tests/test.xi";
//		Main.main(new String[] {"-irrun", "-O", fileName, "-libpath", "runtime/include", "-target"});
//		HashMap<String, IRCompUnit> file2IR = Main.fileToIR;
//		IRCompUnit cu = file2IR.get(fileName);
//		for (IRFuncDecl fd : cu.functions().values()) {
//			ControlFlowGraph cfg = new ControlFlowGraph(fd);
//			
//			System.out.println("*** Original CFG ***");
//			cfg.print();
//			
//			SSAFormConverter converter = new SSAFormConverter(cfg);
//			SSAFormGraph ssaCfg = converter.convertToSSAForm();
//			
//			System.out.println("*** SSA Form ***");
//			ssaCfg.print();
//			
//			DeadCodeEliminator dc = new DeadCodeEliminator();
//			
//			dc.run(ssaCfg);
//			
//			System.out.println("*** SSA Form: Dead code Eliminated succesfully ***");
//			ssaCfg.print();			
//		}
	}
	
	@Test
	public void test2() {
//		String fileName = "/Users/thomasaeyo/Desktop/CS4120_hw/vm-1/shared/xth/tests/pa4/primes.xi";
		String fileName = "/Users/thomasaeyo/Desktop/CS4120/PA6/tests/pa6/practice.xi";
		Main.main(new String[] {"-irrun", "-O", fileName, "-libpath", "runtime/include", "-target"});
		HashMap<String, IRCompUnit> file2IR = Main.fileToIR;
		IRCompUnit cu = file2IR.get(fileName);
		for (IRFuncDecl fd : cu.functions().values()) {
			System.out.println("************" + fd.getABIName() + "************");
			System.out.println("********************");
			System.out.println("*** Original CFG ***");
			System.out.println("********************");
			ControlFlowGraph cfg = new ControlFlowGraph(fd);
			cfg.print();
			
			System.out.println("*****************");
			System.out.println("*** SSA Form ***");
			System.out.println("*****************");
			SSAFormConverter converter = new SSAFormConverter(cfg);
			SSAFormGraph ssaCfg = converter.convertToSSAForm();
			ssaCfg.print();
			
//			System.out.println("********************");
//			System.out.println("*** DominanceMap ***");
//			System.out.println("********************");
//			Map<CFGNode, Set<CFGNode>> map = converter.dominanceMap;
//			for (Entry<CFGNode, Set<CFGNode>> entry : map.entrySet()) {
//				CFGNode node = entry.getKey();
//				System.out.println(node);
//				Set<CFGNode> set = entry.getValue();
//				for (CFGNode n : set) {
//					System.out.println("\t" + n);
//				}
//				System.out.println("********************");
//			}

			List<Optimization> opts = new ArrayList<Optimization>();
			CopyPropagator copy = new CopyPropagator();
//			UnreachableCodeEliminator uce = new UnreachableCodeEliminator();
			opts.add(copy);
			
			System.out.println("*********************");
			System.out.println("*** Optimized CFG ***");
			System.out.println("*********************");
			boolean optimized = true;
			while (optimized) {
				optimized = false;
				for (int i = 0; i < opts.size(); i++) {
					optimized |= opts.get(i).run(ssaCfg);
				}
			}
			ControlFlowGraph newCfg = converter.convertBack();
			newCfg.print();	
		}
	}
}
