use io;
use conv
// Testing move coalescing
main(args:int[][]) {
    a:int = 1
    b:int = 1
    b = a + b
    c:int = 1
    c = b + c
    d:int = 1
    d = c + d
    e:int = 1
    e = d + e
    f:int = 1
    f = e + f
    g:int = 1
    g = f + g
    h:int = 1
    h = g + h
    println(unparseInt(h));
}