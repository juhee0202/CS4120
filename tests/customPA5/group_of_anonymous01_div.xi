use io
use conv

main(args:int[][]) {
    a:int[1]
    a[1/-2] = 42 // did they remember to signed-extend RAX into RDX?
    println(unparseInt(a[0]))
}
