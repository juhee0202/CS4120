//Tests if binary expressions checks dimensions correctly.

foo() {
    a:int[] = {2,2};
    b:int[] = a + {{2}};
}