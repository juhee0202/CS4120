use io
use conv

main(args: int[][]) {
	print(unparseInt(gcd(14,21)))
}

gcd(a:int, b:int):int {
    while (a != 0) {
        if (a<b) b = b - a
        else a = a - b
    }
    return b
}