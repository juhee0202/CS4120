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
    hibernation: int;
    sleeping: bool;

	sleep() {
		hibernation = hibernation - 1;
		if (hibernation == 0) {
			wake();
		}
	}

	hibernate() {
		sleeping = true;
	}

	wake() {
		sleeping = false;
	}

	isSleep(): bool {
		return sleeping;
	}

	status(): int[] {
		if (sleeping) {
			return "sleeping";
		}
		else {
			return "awake";
		}
	}
}

class Dog extends Animal {
	weight: int;
	eat() {
		weight = weight + 1;
	}

	init(initWeight: int, initStrength: int): Dog {
		weight = initWeight;
		animalView: Animal = this;
		animalView.strength = initStrength;
		animalView.species = 1;
		animalView.hibernation = initStrength*3;
		return this;
	}
}

class Human extends Animal {
	intelligence: int;
	history: int[];
	learn(info: int) {
		intelligence = info;
	}

	init(lifecycle: int, iq: int, power: int): Human {
		temp: int[lifecycle];
		history = temp;
		intelligence = iq;
		animalView: Animal = this;
		animalView.strength = power;
		animalView.hibernation = 0;
		animalView.species = 0;
		return this;
	}
}

main(args: int[][]) {
	population: Animal[5];
	population[0] = new Human.init(30, 100, 50);
	population[1] = new Human.init(20, 130, 23);
	population[2] = new Dog.init(40, 150);
	population[3] = new Dog.init(100, 20);
	population[4] = new Human.init(60, 115, 10);

	counter: int = 0;
	while (counter < 5) {
		population[counter].hibernate();
		counter = counter + 1;
	}

	while (population[0].isSleep() | population[1].isSleep()
		| population[2].isSleep() | population[3].isSleep()
		| population[4].isSleep()) {
		counter2: int = 0;
		while (counter2 < 5) {
			population[counter2].sleep();
			counter2 = counter2 + 1;
		}
		oneLine: int[0];
		oneLine = oneLine + "0 is " + population[0].status() + "\n";
		oneLine = oneLine + "1 is " + population[1].status() + "\n";
		oneLine = oneLine + "2 is " + population[2].status() + "\n";
		oneLine = oneLine + "3 is " + population[3].status() + "\n";
		oneLine = oneLine + "4 is " + population[4].status();
		println(oneLine);
	}
	return;
}