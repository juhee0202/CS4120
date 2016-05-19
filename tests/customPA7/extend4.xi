use io

x, y, z: int;

class Animal {
    a: int;
	sleep(c: int) {
		println("HELLO");
	}
	wake() {
		println("WORLD");
	}
}

main(args: int[][]) {
	a: Animal = new Animal;
	a.sleep(2755);
	a.wake();
}