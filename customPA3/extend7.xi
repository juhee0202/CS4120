use io
use conv 

class Student extends Person {
    studentID:int[]
    name:int[]
}

class Person {
    name:int[]
    age:int
    setName(n:int[]) {
        name = n
        this.name = n
    }
}

main(args:int[][]) {
    p:Person = new Person 
    s:Student = new Student
    juhee:Person = new Student
    p.setName("Juhee")
    s.setName("Juhee")
    juhee.setName("Juhee")

    juhee.name = "Juhee"
    juhee.age = 21

    s.studentID = "31242"

    (new Student) = new Student

}