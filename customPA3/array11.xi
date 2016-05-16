use io

x: int[5];

class Animal {
    sleep() {
        println("I'm gonna sleep");
    }
}

class Dog extends Animal {
    siblings: Dog[];
    height: int;

    initDog(argHeight: int): Dog {
        height = argHeight;
        temp: Dog[] = {new Dog};
        siblings = {new Dog, new Dog, temp};
        return this;
    }
}