use io
use conv

main(args:int[][]) {
	println(unparseInt({{1,2},{3,4}}[1][0]));
}

createArray(): int[][] {
	array:int[][] = {{1,2},{3,4}}
	return array;
}