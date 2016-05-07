// tests multiple return

use io

main(args:int[][]) {
    x:int,y:int[],z:int = multiple(90,7);
    y[0] = y[0] + 1;
    print(y + "\n");
}

multiple(x: int, y: int): int, int[], int {
	z:int = x+y;
	array:int[] = {z};
	print(array + "\n");
	return y,array,1;
}