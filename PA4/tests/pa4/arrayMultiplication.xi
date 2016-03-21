use io

main(args:int[][]) {
    _, y:int[][] = multiple({2,2},{3,3});
    print(y[0]);
}

multiple(x: int[], y: int[]): int[], int[][]{
    a: int[][] = {y};
    return x, a;
}