// tests multiple return

use io

main(args:int[][]) {
    _, y:int[][] = multiple({2,2},{97,98});
}

multiple(x: int[], y: int[]): int[], int[][]{
	print("*\n");
	print(y + "\n");
	print("*\n");
    a: int[][] = {y};
    return x, a;
}