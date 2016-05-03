gcd(a:int, b:int):int {
    while (a != 0) {
        // if (a<b) b = b - a
        // else a = a - b
        a = a - 1
    }
    return b
}