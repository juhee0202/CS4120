use io; use conv
foo(a:int[]):int {
	x:int = a[2] + 10
	println("Inside foo a[2] = " + unparseInt(a[2]))
	y:int = 0
	if (x < 50) y = goo(a,x)
	println(unparseInt(y))
	return y		}
goo(a:int[], x:int): int {
	a[2] = x;
	println("Inside goo a[2] = " + unparseInt(a[2]))
	y:int = foo(a)
	return y}
main(args:int[][] ) { 
	i:int = 0
	a:int[10]
	while((false | true) & i < 20) {
		if(i < length(a)) a[i] = 10*i+4
		i = i + 1}
	 _ = foo(a)	}
