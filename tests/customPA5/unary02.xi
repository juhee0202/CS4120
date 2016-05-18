use io
use conv

main(args: int[][]) {
println(unparseInt(-f(47)))
println(unparseInt(-f(0)))
}

f(x: int): int {
println("f() called: " + unparseInt(x))
return x
}
