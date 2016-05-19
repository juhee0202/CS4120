use io
use conv

main(args: int[][]) {
	student:Student = new Student
	student.name = "Thomas"

	thomas:Person = student
	if (student.name == thomas.name) {
		println("no")
	} else {
		println("yes");
	}
}

class Person {
	name:int[]
	init() {}
}

class Student extends Person {
	name:int[]
	study() {}
}
