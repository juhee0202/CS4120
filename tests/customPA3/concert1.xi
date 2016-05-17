n_gates:int = 8;

class Concert {
    lines:Person[n_gates][]
    n_guests: int

    enter(gate:int, p:Person) {
        lines[gate] = enque(lines[gate],p)
        n_guests = n_guests + 1
    }
}

enque(line:Person[], p:Person):Person[] {
    return line + {p}
}

class Person {
    name:int[]
    age:int

    init(n:int[], a:int) {
        name = n;
        age = a;
    }
}