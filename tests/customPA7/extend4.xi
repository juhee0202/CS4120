use io
use conv

x, y, z: int;

class Animal {
    species: int;
	sleep(c: int) {
		println("HELLO");
	}
	wake() {
		println("WORLD");
	}
}

class Dog extends Animal {
	weight: int;
	eat() {
		println("DOG IS EAT");
	}
}

main(args: int[][]) {
	doggie: Dog = new Dog;
	a: Animal = doggie;
	a.sleep(2755);
	a.wake();
	doggie.eat();
	println(unparseInt(doggie.weight));
}