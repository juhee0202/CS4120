use io
use conv

main(args:int[][]) {
    i,j:int
    l0: while (i < 2) {
        println("a")
        l1: while (j < 3) {
            println("b")
            j = j+1
            while (true) {
                break _from_ l1
            }
        }
        i = i+1
        println("hello")
    }
    println("goodbye")
}