import static org.junit.Assert.*;

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
	}

}
