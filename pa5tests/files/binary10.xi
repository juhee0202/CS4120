use io
use conv

main(args: int[][]) {
a1: int[] = {1, 2, 3, 4}
a2: int[] = {5, 6, 7}
a3: int[] = {}
printai(f(a1) + f(a2))
printai(f(a1) + f(a3))
printai(f(a2) + f(a3))
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
