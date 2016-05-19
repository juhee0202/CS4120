use io
use conv

main(args: int[][]) {
	a:Animal = new Animal
	if (a.male) {
		println("I'm a male");
	} else {
		println("I'm a female");
	}
}

class Animal {
	height:int
	weight:int
	male:bool
}
