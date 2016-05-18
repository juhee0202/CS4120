use io
use conv

main(args: int[][]) {
a: int[] = {1, 2, 3}
a[f(a, 1)] = a[g(a, 2)]
}

f(a: int[], i: int): int {
print("f() called: ")
println(unparseInt(i))
a[i] = 25
return i
}

g(a: int[], i: int): int {
print("g() called: ")
println(unparseInt(i))
a[i] = 47
return i
}
