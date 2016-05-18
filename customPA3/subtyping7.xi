class Creature {}
class Animal extends Creature {}
class Dog extends Animal {}
class BullDog extends Dog {}
class Cat extends Animal {}

f() {
	N:int = 10
	animals:Animal[N]

	animals[0] = new Dog
	animals[1] = new Animal
	animals[2] = new Cat
	animals[3] = new Creature
}