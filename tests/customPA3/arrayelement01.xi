main(args:int[][]) {
    x:int[]
    y:int = x[0]
    z:int = foo()[0][0]
    z2:int[] = foo()[0]
    arr:int[] = {1,2,3}
    arrelem:int = arr[0]

    i:int

}

foo():int[][] {
    y:int[][]
    return y
}

class Point {
    x:int
    test() {
        x = this[0]
    }
}