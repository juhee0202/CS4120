use io
use conv

main(args:int[][]) {
    dog:Dog = new Dog 
    dog.name = "alice"
    a:Animal = dog
    a.name = "notTobi"
    println(dog.name)
    // println(a.name)
}

class Dog extends Animal {
    name:int[]
    bark() {
        println("TOBI BARK!!")
    }
}

class Animal {
    name:int[]
    bark() {
        println("ANIMAL BARK!!")
    }
}