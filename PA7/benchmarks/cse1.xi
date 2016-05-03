use io
use conv
// Testing ability to eliminate common subexpressions
main(args:int[][]) {
	a:int = 0
	b:int = 1
	c:int = a
	d:int = b
	e:int = c + d
	f:int = c
	g:int = d
	h:int = f + g
	i:int = e
	j:int = h
	k:int = e + h
	l:int = i + j
	m:int = k + l
	println(unparseInt(m))
}