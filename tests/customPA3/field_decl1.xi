class Person {
	name:int[]
	age:int
	s1,s2,s3:int

	initPerson(n:int[], a:int):Person {
		name = n;
		age = a;
		return this;
	}
}