class A {
    x:int
}

class B {
    a:A;

    getA():int {
        return a.x
    }
}