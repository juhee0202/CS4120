use io
use conv

main(args: int[][]) {
	print(unparseInt(largestprime(1000)))
}

isprime(n:int):bool {
    i:int = 2
    while (i*i <= n) {
        a:int = i
        b:int = n
        while (a != 0) {
            if (a<b) b = b - a
            else a = a - b
        }
        if (b != 1) {
            return false
        }
        i = i+1
    }
    return true
}

largestprime(max:int):int {
    a:int = 1
    largest:int = 1
    while (a < max) {
      if (isprime(a)) largest = a
      a = a+1
    }
    return largest
}