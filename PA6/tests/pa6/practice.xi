use io
use conv

main(args: int[][]) {
	x:int = 5;
	y:int = 4;
	if (x > 5) {
		y = x;
	} else {
		y = x + 5;
	}
	z:int = y + 3;
}