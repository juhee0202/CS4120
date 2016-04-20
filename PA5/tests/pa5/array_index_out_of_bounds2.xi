use io
 use conv

 main(args:int[][]) {
	foo()
 }

 foo() {
	 a:int[] = {1,2,3}
	 a[f(a)[1]] = a[g(a)]
 }

 f(a:int[]):int[] {
	 println("hello");
	 return a;
 }

 g(a:int[]):int {
	 println("world");
	 a[1] = 6
	 return 1;
 }