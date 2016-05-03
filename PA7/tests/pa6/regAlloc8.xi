use io
use conv

main(args: int[][]) {
	print(unparseInt(largestprime(1000)))
}

gcd(a:int, b:int):int {
    while (a != 0) {
        if (a<b) b = b - a
        else a = a - b
    }
    return b
}

largestprime(max:int):int {
    a:int = 1
    largest:int = 1
    while (a < max) {
        i:int = 2
        result:bool = true
        while (i*i <= a) {
            if (gcd(i, a) != 1) {
                result = false
            }
            i = i+1
        }
      if (result) largest = a
      a = a+1
    }
    return largest
}