use io
use conv

main(args: int[][]) {
	jona:Person = new Student
	jona.name = "Jona"

	println(jona.name)
}

class Person {
	name:int[]
}

class Student extends Person {
	id:int
}
