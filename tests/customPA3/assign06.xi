main(args:int[][]) {
	aDog:Dog
    foo()[0] = aDog

    height:int = aDog.getHeight()
}

foo():Dog[] {
	dogs:Dog[]
	return dogs
}

class Dog extends Animal {
	height:int
	setHeight(h:int) {
		this.height = h
	}

	getHeight():int {
		return this.height
	}
}


class Cat extends Animal {
	
}

class Animal {
	
}