use io
use conv

main(args: int[][]) {
println(b2s(f(1) < f(2)))
println(b2s(f(3) <= f(2)))
println(b2s(f(4) > f(2)))
println(b2s(f(5) >= f(2)))
println(b2s(f(6) == f(2)))
println(b2s(f(7) != f(2)))
}

f(x: int): int {
print("f() called: ")
println(unparseInt(x))
return x
}

b2s(b: bool): int[] {
if (b) { return unparseInt(47) } else { return unparseInt(25) }
}
