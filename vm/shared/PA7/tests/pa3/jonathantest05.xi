//Tests if bool arrays and int arrays match (should not)

foo() {
    a:int[] = {2,2};
    b:bool[] = {true, true};
    c:int[] = a + b;
}