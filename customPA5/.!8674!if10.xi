use io
use conv

main(args: int[][]) {
if (lt(0, 0) | lt(0, 0)) println("true") else println("false")
if (lt(0, 0) | lt(0, -1)) println("true") else println("false")
if (lt(0, 0) | lt(0, 1)) println("true") else println("false")
if (lt(0, -1) | lt(0, 0)) println("true") else println("false")
if (lt(0, -1) | lt(0, -1)) println("true") else println("false")
if (lt(0, -1) | lt(0, 1)) println("true") else println("false")
if (lt(0, 1) | lt(0, 0)) println("true") else println("false")
if (lt(0, 1) | lt(0, -1)) println("true") else println("false")
if (lt(0, 1) | lt(0, 1)) println("true") else println("false")
println("printed")
}

lt(x: int, y: int): bool {
print("lt() called: ")
print(unparseInt(x))
print(" < ")
println(unparseInt(y))
return x < y
}
