use io
use conv

main(args: int[][]) {
a: int[] = {1, 2, 3}
a[a[f(a, 1)]] = a[g(a, 2)]
println(unparseInt(a[0]))
println(unparseInt(a[1]))
println(unparseInt(a[2]))
}

f(a: int[], i: int): int {
print("f() called: ")
println(unparseInt(i))
return i
}

g(a: int[], i: int): int {
print("g() called: ")
println(unparseInt(i))
a[i] = 47
return i
}
