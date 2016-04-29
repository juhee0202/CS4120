use io
use conv

main(args: int[][]) {
	a:int = 1;
	b:int = 2;
	c:int = a + b;
	if (c < 5) {
		b = a + c;
	}
	c = b;
}