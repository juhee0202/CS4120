class Animal {
	a:int
}

class Dog extends Animal {
	d:int
}

class BullDog extends Dog {
	a:bool
}

f():int {
	a:Animal = new BullDog
	return a.a
}