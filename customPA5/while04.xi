use io
use conv

main(args: int[][]) {
x: int = 0
while (f() | lt(x, 7) | g()) {
println(unparseInt(x))
x = x + 1
}
println("done")
}

lt(x: int, y: int): bool {
print("lt() called: ")
print(unparseInt(x))
print(" < ")
println(unparseInt(y))
return x < y
}

f(): bool {
println("f() called")
return false
}

g(): bool {
println("g() called")
return false
}
