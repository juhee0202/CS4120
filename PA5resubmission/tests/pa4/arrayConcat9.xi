use io

main(args:int[][]) {
	x: int[2][]
    x[0] = "Hello, "
    x[1] = "it's "
    y: int[1][3]
    y[0] = "me."
    result: int[][] = x+y
    print(result[0]);
    print(result[1]);
    print(result[2]);
}