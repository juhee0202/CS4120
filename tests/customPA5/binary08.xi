use io
use conv

main(args: int[][]) {
a1: int[] = {1, 2, 3}
a2: int[] = a1
a3: int[] = {1, 2, 3}
println(b2s(f(a1) == f(a2)))
println(b2s(f(a1) != f(a2)))
println(b2s(f(a1) == f(a3)))
println(b2s(f(a1) != f(a3)))
println(b2s(f(a2) == f(a3)))
println(b2s(f(a2) != f(a3)))
}

f(x: int[]): int[] {
print("f() called: ")
printai(x)
return x
}

printai(a: int[]) {
i: int = 0
print("{")
while (i < length(a)) {
if (i > 0) print(", ")
print(unparseInt(a[i]))
i = i + 1
}
println("}")
}

b2s(b: bool): int[] {
if (b) { return unparseInt(1) } else { return unparseInt(0) }
}
