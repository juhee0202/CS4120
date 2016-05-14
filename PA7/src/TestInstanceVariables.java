
public class TestInstanceVariables extends Animal implements testInterface{

	int foo;
	
	public int foo() {
		int x = y + z;
		return x;
	}
	int y = 3;
	int z = 5;
}
