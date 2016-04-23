// tests multiple return

use io

main(args:int[][]) {
    x:int,y:int = multiple(90,7);
    print({x+y} + "\n");
}

multiple(x: int, y: int): int, int {
	return x,y
}