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

	println(unparseInt(x)); 	// x = 5
	println(unparseInt(y));		// y = 10
	println(unparseInt(z));		// z = 13
}