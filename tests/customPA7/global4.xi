use io
use conv

x:int = 5

main(args:int[][]) {
	println(unparseInt(x))
	x = 10
	println(unparseInt(x))
}