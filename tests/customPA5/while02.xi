use io
use conv

main(args: int[][]) {
x: int = 0
while (lt(x, 7)) {
println(unparseInt(x))
x = x + 1
}
println("done")
}

lt(x: int, y: int): bool {
println("lt() called: " + unparseInt(x) + " < " + unparseInt(y))
return x < y
}
