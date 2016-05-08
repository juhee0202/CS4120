use io
use conv

main(args: int[][]) {
x:int, y: bool, z: int = f()
println(unparseInt(x))
if (y) println("true") else println("false")
println(unparseInt(z))
println("done")
}

f(): int, bool, int {
println("f() called")
return 17, true, 42
}
