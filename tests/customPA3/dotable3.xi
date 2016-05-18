N:int = 10;

class A {
    array:int[N]
}

class B extends A {
    array:A[N]
}

initB(n:int):B[] {
	array:B[n];
	array[0] = new B;
	array[0].array[0] = initA();
	array[0].array[1] = initA();
	return array; 
}

initA():A {
	return new A
}

test() {
	array:B[] = initB(5);
	i:int = array[0].array[0].array[0];
}