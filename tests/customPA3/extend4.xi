use io

class Animal {
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