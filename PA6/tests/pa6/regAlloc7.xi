use io
use conv

main(args: int[][]) {
	print(unparseInt(largestprime(1000)))
}

largestprime(max:int):int {
    aa:int = 1
    largest:int = 1
    while (aa < max) {
        n:int = aa
        i:int = 2
        result:bool = true
        while (i*i <= n) {
            a:int = i
            b:int = n
            while (a != 0) {
                if (a<b) b = b - a
                else a = a - b
            }
            if (b != 1) {
                result = false
            }
            i = i+1
        }
      if (result) largest = aa
      aa = aa+1
    }
    return largest
}