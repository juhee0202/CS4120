n:int = 10;

class A {
    array:int[n]
}

class B extends A {
    array:A[n]
}

test() {
    array:B[n];

    i:int = array[0].array[0].array[1]
}