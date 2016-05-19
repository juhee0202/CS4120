use io
use conv

class Animal {
	age:int

	init(x:int) {
		age = x
		println("super")
		println(unparseInt(age))
	}
}

class Dog extends Animal {
	size:int
	age:int

	init(x:int) {
		a:Animal = this
		a.age = x
		println("sub")
		println(unparseInt(a.age))
		age = 97
	}
}

main(args:int[][]) {
	d:Dog = new Dog
	d.init(98)
	println(unparseInt(d.age))
	a:Animal = new Animal
	a.init(99)
	ad:Animal = d
	println(unparseInt(ad.age))
}