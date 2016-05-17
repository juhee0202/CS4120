class Creature {}
class Animal extends Creature {
    getWeight():int { return 0 }
}
class Dog extends Animal {}
class BullDog extends Dog {}
class Cat extends Animal {}

f() {
    N:int = 10
    
    animals:Animal[N]
    animals[0] = new Dog
    animals[1] = new Animal
    animals[2] = new Cat
    
    weights:int[N]
    weights[0] = animals[0].getWeight()
    weights[1] = animals[1].getWeight()
    weights[2] = animals[2].getWeight()
}