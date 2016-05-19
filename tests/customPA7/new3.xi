use io
use conv

main(args: int[][]) {
	student:Student = new Student
	student.name = "Thomas"
	student.id = 8

	println(student.name)
	println(unparseInt(student.id))
}

class Person {
	name:int[]
}

class Student extends Person {
	name:int[]
	id:int
}