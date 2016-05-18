use io
assert(b:bool, message:int[]) {
    if (b) println("Pass: " + message)
    else { println("Fail: " + message) a:int[0] a[-1] = 42 }
}
inc(a:int[]): int { a[0] = a[0] + 1 return a[0] }
checkSequential(a:int, b:int, c:int): bool { return a == 1 & b == 2 & c == 3 }
main(args:int[][]) {
    a:int[2] b:int[2][2]
    a[0] = 0 assert(a[0] != inc(a), "binop")
    a[0] = 1 assert(checkSequential(a[0], inc(a), inc(a)), "call args")
    a = {0, 2} a[a[0]] = inc(a) assert(a[0] == 1 & a[1] == 2, "assign")
    a = {0, 3, 6} a[inc(a)] = inc(a)
    assert(
        a[0] == 2 & a[1] == 2 & a[2] == 6,
        "assign2"
    )
}
