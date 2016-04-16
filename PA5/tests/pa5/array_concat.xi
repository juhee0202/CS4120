use io
use conv

foo() {
    a:int[] = {1,2,3}
    a[a[1]] = 1
}

f(a:int[]):int[] {
    return a;
}

g(a:int[]):int {
    a[1] = 6
    return 1;
}