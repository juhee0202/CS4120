use io
use conv


n_gates:int = 8;
GATE_CAPACITY:int = 2;

main(args: int[][]) {
    beatlesConcert:Concert = new Concert;
    beatlesConcert.init()

    guest1:Person = new Person
    guest1.init("Jeff", 20);
    beatlesConcert.enter(0,guest1);

    guest2:Person = new Person
    guest2.init("Jona", 20);
    beatlesConcert.enter(0,guest2);

    guest3:Person = new Person
    guest3.init("Juhee", 21);
    beatlesConcert.enter(1,guest3);

    guest4:Person = new Person
    guest4.init("Thomas", 21);
    beatlesConcert.enter(2,guest4);
}


class Concert {
    lines:Person[][]
    gateCount:int[]
    n_guests:int

    init() {
        tempLines:Person[n_gates][GATE_CAPACITY];
        lines = tempLines;

        tempGateCount:int[n_gates];
        gateCount = tempGateCount;
    }

    enter(gate:int, p:Person) {
        if (gateCount[gate] < GATE_CAPACITY) {
            count:int = gateCount[gate]
            lines[gate][count] = p;
            gateCount[gate] = count + 1
            n_guests = n_guests + 1
            println(p.name + " entered Gate " + unparseInt(gate));
        } else {
            println("Gate " + unparseInt(gate) + " is full.");
        }
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