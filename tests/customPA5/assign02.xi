use io
use conv

main(args: int[][]) {
x: int
x = f(47)
println(unparseInt(x))
}

f(x: int): int {
println("f() called")
return x
}
