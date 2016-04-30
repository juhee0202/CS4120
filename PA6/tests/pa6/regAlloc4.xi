use io
use conv

main(args: int[][]) {
	x:int
	if (isprime(100)) {
		x = 1
	} else {
		x = 0
	}
	print(unparseInt(x))
}

gcd(a:int, b:int):int {
    while (a != 0) {
        if (a<b) b = b - a
        else a = a - b
    }
    return b
}

isprime(n:int):bool {
    i:int = 2
    while (i*i <= n) {
        if (gcd(i, n) != 1) {
            return false
        }
        i = i+1
    }
    return true
}