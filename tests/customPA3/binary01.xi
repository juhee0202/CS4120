main(args:int[][]) {
    a:Animal[]
    b:Animal[]
    c:Animal[] = a+b

    d:bool[]
    e:bool[]
    f:bool[] = d+e

    g:Animal
    h:Animal
    i:bool = (g==h)
    j:bool = (g!=h)


    dog1:Dog
    k:bool = (dog1 != dog1)
}

class Animal {
	
}

class Dog extends Animal {

}