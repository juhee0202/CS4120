// dce test4: should only remove arg0 irstmt because argument 
// to main isn't being used

use io
use conv

main(args:int[][]) {
	x:int = f()
	y:int = x
	println(unparseInt(y))
}

f():int {
	println("17")
	return 7
}