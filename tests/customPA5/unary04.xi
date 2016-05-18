use io
use conv

main(args: int[][]) {
println(b2s(!f(true)))
println(b2s(!f(false)))
}

f(x: bool): bool {
println("f() called: " + b2s(x))
return x
}

b2s(b: bool): int[] {
if (b) { return "true" } else { return "false" }
}
