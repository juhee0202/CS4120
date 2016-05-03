use io
use conv

foo(a:int):int {
	if (a == 1) {
		return 10;
	}
	return 1 + foo(a-1);
}

main(args:int[][]) {
	a:int[] = {1, 2, 3}
	println(unparseInt(foo(a[foo(2)%3])))
}
