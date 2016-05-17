class Animal {
	eat() {}
}

class Dog extends Animal {
	bark() {}
}

class BullDog extends Dog {
	bite() {}
}

main() {
	a:Animal = new BullDog
	a.bite()
}