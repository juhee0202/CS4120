class Student extends Person {
    //globalVar:int     not allowed
    id:int
    p:Person

    init(n:int[], a:int):Student {
        // id:int       not allowed
        p.name = n;
        p.age = a;
        return new Student
    }
}

class Person extends Object{
    name:int[]
    age:int
    id:int

    init(n:int[], a:int):Student {
        name = n;
        age = a;
        return new Student;
    }
}

class Object {
    
}

main(args:int[][]) {

}