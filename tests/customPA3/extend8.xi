use io
use conv 
use person

class Student extends Person {
    studentID:int[]
}

class Object {
    objectID: int
}

main(args:int[][]) {
    s:Object = new Student
    s.objectID = 2
    s.studentID = 4
}