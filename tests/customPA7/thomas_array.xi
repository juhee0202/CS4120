use io
use conv

main(args: int[][]) {
	person:Person = new Person

	temp:int[3][2]
	person.array = temp

	person.array[0][0] = 0
	person.array[0][1] = 1
}

class Person {
	array:int[][]
}