use io; use conv;
f():bool {
	println("Inside function f()")
	return true
}

g():bool {
	println("Inside function g()")
	return false}
main(args:int[][]){
	x:int = 30
	y:int = 50
	out:int = 0
	if (f() & g()) { out = out + 10 }
	if (f() | g()) { out = out + 20  }
	if (g() & f()) { out = out + 30  }
	if (g() | f()) { out = out + 40  }
	if (x > 10 & y < 30 & g() | f() & x == 30 | f()) {out = out + 50 }
println(unparseInt(out)) }