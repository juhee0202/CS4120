import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;
import jl2755.assembly.Constant;
import jl2755.assembly.Instruction;
import jl2755.assembly.Instruction.Operation;
import jl2755.assembly.Label;
import jl2755.assembly.Register;
import jl2755.ast.BlockStmt;
import jl2755.ast.FunctionDecl;
import jl2755.ast.FunctionParam;
import jl2755.ast.Identifier;
import jl2755.ast.PrimitiveType;
import jl2755.ast.ReturnType;
import jl2755.ast.Type;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.dataflow.IRExprOverrider;
import jl2755.type.ClassType;
import jl2755.type.FunType;
import jl2755.type.VType;
import jl2755.type.VarType;

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
	
	@Test
	public void test5() {
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
	

	@Test 
	public void test6() {
		TestingEquals temp = new TestingEquals3();
		TestingEquals2 temp2 = new TestingEquals3();
		System.out.println(temp.equals(null));
		System.out.println(temp2.equals(null));

	}
	@Test
	public void testCFG() {
		List<Instruction> list = new ArrayList<Instruction>();
		Label l = new Label("func");
		Instruction label = new Instruction(Operation.LABEL,l);
		Register temp = new Register("temp");
		Instruction move = new Instruction(Operation.MOVQ,new Constant(5),temp);
		Label l1 = new Label("l1");
		Label l2 = new Label("l2");
		Instruction label1 = new Instruction(Operation.LABEL,l1);
		Instruction label2 = new Instruction(Operation.LABEL,l2);
		Instruction cj = new Instruction(Operation.JNZ,l1,l2);
		Instruction call = new Instruction(Operation.CALLQ,new Label("func2"));
		Instruction jmp = new Instruction(Operation.JMP,l2);
		Instruction ret = new Instruction(Operation.RET);
		list.add(label);
		list.add(move);
		list.add(cj);
		list.add(label1);
		list.add(call);
		list.add(jmp);
		list.add(label2);
		list.add(ret);
		ControlFlowGraph cfg = new ControlFlowGraph(list);
		System.out.println(cfg.dotOutput());
			
		try{
		File file = new File("cfgtest" + "_" + "main" + "_" + "initial.dot");
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bwCFG = new BufferedWriter(fw);
		bwCFG.write(cfg.dotOutput());
		bwCFG.close();
		} catch (IOException e) {
			
		}
	}

	@Test
	public void testIRExprOverriderHashSet() {
		IRTemp temp1 = new IRTemp("Testing");
		IRMem mem1 = new IRMem(temp1);
		IRTemp temp2 = new IRTemp("Testing");
		IRMem mem2 = new IRMem(temp2);
		IRExprOverrider overrider1 = new IRExprOverrider(mem1,null);
		IRExprOverrider overrider2 = new IRExprOverrider(mem2,null);
		System.out.println(overrider1.equals(overrider2));
		System.out.println(overrider1.hashCode());
		System.out.println(overrider2.hashCode());
	}
	
	@Test
	public void testClassTypeEquals() {
		HashMap<String, FunType> hm1 = new HashMap<String, FunType>();
		HashMap<String, VType> instance = new HashMap<String, VType>();
		
		Type intType = new PrimitiveType(0);
		FunctionParam fp = new FunctionParam(new Identifier("arg1", 0, 0), intType);
		ReturnType rt = new ReturnType();
		BlockStmt bs = new BlockStmt();
		FunctionDecl fd = new FunctionDecl(new Identifier("foo", 0, 0), fp, rt, bs);
		hm1.put("foo", new FunType(fd));
		int[] x;
		
		Animal a = new Animal();
		Dog d = new Dog();
		CFGTester tester = new CFGTester();
		boolean same = a == d;
		
		String s = null;
		String s2 = null;
		boolean equalaasss = s2.equals(s2);
	}
}
