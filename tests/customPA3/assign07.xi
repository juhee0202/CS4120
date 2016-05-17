class Dog extends Animal {
	height:int
	setHeight() {
		this = new Dog
	}

	getHeight():int {
		return this.height
	}
}

class Animal {
	
}

main(args:int[][]) {
	d:Dog = new Dog
	dogHeight:int = d.getHeight();
}