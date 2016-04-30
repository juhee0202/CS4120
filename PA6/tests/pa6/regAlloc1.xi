use io
use conv

main(args: int[][]) {
	x:int = 5
	y:int = x
	z:int = x + y
	print(unparseInt(z));
}