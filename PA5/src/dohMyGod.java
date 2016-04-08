import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class dohMyGod {

	@Test
	public void test() {
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
	}

}
