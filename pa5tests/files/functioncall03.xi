use io
use conv

main(args: int[][]) {
println(unparseInt(g(f(1), f(2), f(3), f(4), f(5), f(6))))
}

f(x: int): int {
print("f() called: ")
println(unparseInt(x))
return x
}

g(x1: int, x2: int, x3: int, x4: int, x5: int, x6: int): int {
print("g() called: ")
print(unparseInt(x1))
print(" ")
print(unparseInt(x2))
print(" ")
print(unparseInt(x3))
print(" ")
print(unparseInt(x4))
print(" ")
print(unparseInt(x5))
print(" ")
print(unparseInt(x6))
println("")
return x1 + x2 + x3 + x4 + x5 + x6
}
