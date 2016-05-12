use io
use conv

main(args: int[][]) {
a: int[3]
a[0] = f(1)
a[1] = f(2)
a[2] = f(3)
println(unparseInt(length(a)))
println(unparseInt(a[1]))
}

f(x: int): int {
print("f() called: ")
println(unparseInt(x))
return x
}
