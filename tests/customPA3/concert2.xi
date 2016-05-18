use io

n_gates:int = 8;

class Concert {
    lines:Person[n_gates][]
    n_guests: int

    enter(gate:int, p:Person) {
        lines[gate] = enque(lines[gate],p)
        n_guests = n_guests + 1
    }
}

class Person {
    name:int[]
    age:int

    init(n:int[], a:int) {
        name = n;
        age = a;
    }
}

enque(line:Person[], p:Person):Person[] {
    return line + {p}
}

peek(line:Person[]):Person {
    return line[0];
}

test1() {
    coldplayConcert:Concert = new Concert;
    thomas:Person = new Person;
    thomas.init("Thomas", 21);
    jeff:Person = new Person;
    jeff.init("Jeff",20);
    jona:Person = new Person;
    jona.init("Jona",20);
    juhee:Person = new Person;
    juhee.init("Juhee",21);

    coldplayConcert.enter(0,thomas);
    coldplayConcert.enter(1,jeff);
    coldplayConcert.enter(1,jona);
    coldplayConcert.enter(2,juhee);

    println(coldplayConcert.lines[1][0].name);
}

