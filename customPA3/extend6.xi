
class Student extends Person {
    //globalVar:int     not allowed
    id:int
    p:Person

    init(n:int[], a:int):Student {
        // id:int       not allowed
        p.name = n;
        p.age = a;
        return this
    }
}

class Person {
    name:int[]
    age:int
    id:int

    init(n:int[], a:int):Person {
        name = n;
        age = a;
        return this;
    }
}

main(args:int[][]) {
}
    