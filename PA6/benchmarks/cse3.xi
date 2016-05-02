use io
use conv
// Testing ability to move coalesce with computation
main(args:int[][]) {
	a:int = 1
	b:int = a + 1
	c:int = b + 1
	d:int = c + 1
	e:int = d + 1
	f:int = e + 1
	g:int = f + 1
	h:int = g + 1
	i:int = h + 1 
	j:int = i + 1
	k:int = j + 1
	l:int = k + 1
	m:int = l + 1
	n:int = m + 1
	o:int = n + 1
	println(unparseInt(o))
}