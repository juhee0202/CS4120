use io
use conv

class Dog {
	size:int
	age:int

	init(x:int) {
		println("sub")
		println(unparseInt(x))
	}
}

main(args:int[][]) {
	d:Dog = new Dog
	println("hello")
	d.init(98)
}