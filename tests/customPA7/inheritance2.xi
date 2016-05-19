use io
use conv

main(args: int[][]) {
	student:Student = new Student
	jona:Person = student
	jona.name = "Jona"

	jonathan:Student = student
	jonathan.id = 1
	println(unparseInt(jonathan.id))
}

class Person {
	name:int[]
}

class Student extends Person {
	id:int
}
