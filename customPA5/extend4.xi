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
		return "Woof"
	}

	sleep() {
		println(bark());
	}
}