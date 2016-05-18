use io
use conv

main(args: int[][]) {
a1: int[] = {1, 2, 3}
a2: int[] = a1
a3: int[] = {1, 2, 3}
println(b2s(a1 == a2))
println(b2s(a1 != a2))
println(b2s(a1 == a3))
println(b2s(a1 != a3))
println(b2s(a2 == a3))
println(b2s(a2 != a3))
}

b2s(b: bool): int[] {
if (b) { return unparseInt(1) } else { return unparseInt(0) }
}
