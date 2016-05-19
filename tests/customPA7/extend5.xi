use io
use conv

x, y, z: int;
everyone: int[6];

class Organism {
    size: int;
    evolve() {
    size = 10;
        println("WOP WOP");
    }
}

class Animal extends Organism {
    species: int;
    sleep(c: int) {
        println("HELLO");
    }
    wake() {
        println(unparseInt(species));
        println("WORLD");
    }
}

class Dog extends Animal {
    weight: int;
    eat() {
        println("DOG IS EAT");
    }
    sleep(c: int) {
        println("NO HELLO");
    }
}

main(args: int[][]) {
    // doggie: Dog = new Dog;
    everyone = "working";
    println(everyone);
}