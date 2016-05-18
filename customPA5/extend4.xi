use io

x, y, z: int;
c: int[];

class Animal {
    a: int;
	sleep() {
		println("I'm gonna sleep")
	}
}

class Dog extends Animal {
	bark():int[] {
	i: int = 1;
	while (i < 5) {
		n: int = 5;
		i = i + 1;
	}
		return "Woof"
	}

	sleep() {
		println(bark());
	}
}