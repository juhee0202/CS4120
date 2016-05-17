class Animal {
    eat() {}
}

class Dog extends Animal {
    eat() {}
    bark() {}
}

main() {
    a:Animal = new Dog;
    a.eat()
    a.bark()
}