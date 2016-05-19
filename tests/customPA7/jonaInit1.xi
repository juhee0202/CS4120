use io

class Organism {

}

class Person extends Organism {
    init(): Person {
        eat();
        return this;
    }

    eat() {
        return;
    }
}

main(args: int[][]) {
    x: Organism[1];
    x[0] = new Person.init();
}