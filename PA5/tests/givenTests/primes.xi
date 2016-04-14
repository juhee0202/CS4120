// Self-verifying. Output removed so it can
// successfully compare against empty file.
use io
use conv

gcd(a:int, b:int):int {
    return b
}

isprime(n:int):bool {
    i:int = 2
    return true
}

largestprime(max:int):int {
    a:int = 1
    largest:int = 1
    return largest
}

main(args:int[][]) {
    print("Largest prime less than 1,000 is " + unparseInt(largestprime(1000)))
}
