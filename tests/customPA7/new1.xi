use io
use conv

main(args: int[][]) {
	a:Animal = new Animal
	a.x = 3
	println(unparseInt(a.x));
}

class Animal {
	x:int
}
