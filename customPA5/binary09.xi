use io
use conv

main(args: int[][]) {
a1: int[] = {1, 2, 3, 4}
a2: int[] = {5, 6, 7}
a3: int[] = {}
printai(a1 + a2)
printai(a1 + a3)
printai(a2 + a3)
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
