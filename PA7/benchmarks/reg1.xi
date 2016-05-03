use io;
use conv
// Testing ability to reuse the same variable
main(args:int[][]) {
    a:int = 1
    b:int = a
    c:int = b
    d:int = c
    e:int = d
    f:int = e
    g:int = f
    h:int = g
    i:int = h
    j:int = i
    k:int = j
    l:int = k
    m:int = l
    n:int = m
    o:int = n
    println("DONE" + unparseInt(o));
}