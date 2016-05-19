
public class TestInstanceVariables extends Animal implements testInterface{

	int foo;
	
	public int foo() {
		int x = y + z;
		return x;
	}
	int y = 3;
	int z = 5;
	public static void main (String[] args) {
		int[] array = {1, 2, 3};
		array[0]++;
		System.out.println(array[0]);
	}
}
