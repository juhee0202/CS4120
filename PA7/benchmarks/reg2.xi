use io
use conv
// Testing spilling
main(args:int[][]) {
    a:int = 0
    b:int = 1
    c:int = 2
    d:int = 3
    e:int = 4
    f:int = 5
    g:int = 6
    h:int = 7
    i:int = 8
    j:int = 9
    k:int = 10
    l:int = 11
    m:int = 12
    n:int = 13
    z:int = a+b+c+d+e+f+g+h+i+j+k+l+m+n 
    println(unparseInt(z))
}