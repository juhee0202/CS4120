use io

x, y, z: int;

<<<<<<< HEAD
class Organism {
	size: int;
	evolve() {
	size = 10;
		println("WOP WOP");
	}
}

class Animal extends Organism {
    species: int;
	sleep(c: int) {
		println("HELLO");
	}
	wake() {
		println(unparseInt(species));
		println("WORLD");
	}
}

class Dog extends Animal {
	weight: int;
	eat() {
		println("DOG IS EAT");
	}
=======
class Animal {
    a: int;
>>>>>>> 697c94971bd9ea32e9fa8a0572c4c48413cc0095
	sleep(c: int) {
		
	}
}

main(args: int[][]) {
<<<<<<< HEAD
	doggie: Dog = new Dog;
	a: Animal = doggie;
	a.sleep(2755);
	a.wake();
	doggie.eat();
	println(unparseInt(doggie.weight));
	o:Organism = doggie;
	o.evolve();
	println(unparseInt(o.size));
=======
	a: Animal = new Animal;
	m: int = 10;
	a.sleep(m);
>>>>>>> 697c94971bd9ea32e9fa8a0572c4c48413cc0095
}