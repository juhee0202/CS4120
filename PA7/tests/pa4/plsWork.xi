a(i:int, j:int): int, int {
    return i, (2 * j);
    }
b(i:int, j:int): int {
    x:int, y:int = a(i, j);
    return x + 5 * y;
    }