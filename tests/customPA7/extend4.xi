use io

x, y, z: int;

class Organism {
	size: int;
	evolve() {
		size = 2*size;
	}
}

class Animal extends Organism {
    species: int;
    strength: int;
	sleep(c: int) {
	}
	wake() {
	}
}

class Dog extends Animal {
	weight: int;
	eat() {
		weight = weight + 1;
	}

	init(initWeight: int, initStrength: int) {
		weight = initWeight;
		animalView: Animal = this;
		animalView.strength = initStrength;
	}
}

class Human extends Animal {
	intelligence: int;
	history: int[];
	learn(info: int) {
		intelligence = int;
	}

	init(lifecycle: int, iq: int, power: int): Human {
		temp: int[lifecycle];
		history = temp;
		intelligence = iq;
		animalView: Animal = this;
		animalView.strength = power;
		return this;
	}
}

main(args: int[][]) {

}