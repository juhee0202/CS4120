use io
use conv

// usage() {
//     println("Please specify the input size")
// }

main(args:int[][]) {
    n: int = 11
    r: int = Ack(2, 1)
    
    print("Ack(2,")
    print(unparseInt(n))
    // print("): ")
    // print(unparseInt(r))
    // println("")
}

Ack(m:int, n:int):int {
    if (m == 0) { return n+1 }
    else if (n == 0) { return 1 }
    else { return 2 }
}
