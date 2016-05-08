import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.IRNode;
import jl2755.assembly.Tile;
import jl2755.visitor.IRTreeEqualsVisitor;
import jl2755.visitor.TilingVisitor;

public class dohMyGod {

	@Test
	public void test() {
		int[] array = {1, 2, 3};
		System.out.println(array[-1]);
		
		int a = 'H';
		System.out.println(a);
		int x = 5&10;
		System.out.println(x);
		
		long b = (long) Math.pow(2, 63);
		b = -b - 1;
		System.out.println(-b);
//		System.out.println(-(b));
		long c = b + 1;
//		System.out.println((long)Double.MAX_VALUE);
//		System.out.println(c);
		
		int z = 5;
		System.out.println(z++);
		System.out.println(z);
		System.out.println("\n");
		
		char ff = 92;
		String gg = ff + "n";
		System.out.println("Break");
		System.out.println(gg);
		System.out.println();
		System.out.println("unbreak");
		
		
		
		
		
	}
	
	@Test
	public void test2() {
		String arg = "Hello World!\\n";
		String newString = "";
		System.out.println(arg);
		for (int i = 0; i < arg.length(); i++) {
			if (!(arg.charAt(i) == '\\')) {
				newString += arg.charAt(i);
			}
			else {
				char nextChar = arg.charAt(i+1);
				if (nextChar == 'n') {
					newString += "\n";
				}
				else if (nextChar == '\'') {
					newString += "\'";
				}
				else if (nextChar == '\\') {
					newString += '\\';
				}
				else if (nextChar == '\"') {
					newString += '\"';
				}
				i++;
			}

		}
		System.out.println(newString);
	}
	
	@Test
	public void test3() {
		long a = (long) Math.pow(2, 63);
		System.out.println((-9223372036854775808l));
	}
	
	@Test
	public void test4() {
		List<String> jl2755 = new ArrayList<String>();
		jl2755.add("1");
		jl2755.add("2");
		jl2755.add("3");
		jl2755.add("4");
		
		List<String> testing = jl2755.subList(2, 3);
		
		System.out.println(testing.get(0));
		
		boolean bool = true;
		bool = bool && false;
		System.out.println(bool);
		System.out.println(bool & false);
	}
	
//	@Test
//	public void testEqualTreeVisitor() {
//		IRTreeEqualsVisitor temp = new IRTreeEqualsVisitor();
//		List<String> testPre = new ArrayList<String>(
//				Arrays.asList(
//						"Mem"
//						));
//		
//		List<String> testIn = new ArrayList<String>(
//				Arrays.asList(
//						"Mem"
//						));
//		
//		IRNode tree1 = Tile.makeTree(testIn, testPre);
//		
//		List<String> testPre2 = new ArrayList<String>(
//				Arrays.asList(
//						"Mem",
//						"BinOp1",
//						"null1",
//						"BinOp2",
//						"BinOp3",
//						"null2",
//						"Const2",
//						"Const1"
//						));
//		
//		List<String> testIn2 = new ArrayList<String>(
//				Arrays.asList(
//						"null1",
//						"BinOp1",
//						"null2",
//						"BinOp3",
//						"Const2",
//						"BinOp2",
//						"Const1",
//						"Mem"
//						));
//		
//		IRNode tree2 = Tile.makeTree(testIn2, testPre2);
//		System.out.println("Subtree result is " + temp.equalTrees(tree1, tree2));
//	}
	


}
