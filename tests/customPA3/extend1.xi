class Student extends Person {
	id:int
	init(n:int[], a:int):Person {
		name = n;
		age = a;
		return this
	}
}

class Person {
	name:int[]
	age:int

	init(n:int[], a:int):Person {
		name = n;
		age = a;
		return this;
	}
}