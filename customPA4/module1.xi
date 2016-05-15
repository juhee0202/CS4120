count:int = 0

class Person {
	name:int[]
	age:int

	initPerson(n:int[], a:int):Person {
		name = n;
		age = a;
		count = count + 1
		return this;
	}

	getAge(p:Person):int {
		return age;
	}

	getName(p:Person):int[] {
		return name;
	}
}

getCount():int {
	return count
}