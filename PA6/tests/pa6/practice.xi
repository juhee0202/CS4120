use io
use conv

main(args: int[][]) {
	a:int = 1;
	b:int = 2;
	c:int = 3;
	if (a < 3) {
		a = a + 1;
		b = a + 1;
		c = b + a;
	}
	d:int = a + b + c;
}