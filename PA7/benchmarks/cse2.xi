use io
use conv
// Testing ability to move coalesce with computation
main(args:int[][]) {
	a:int = 1
	b:int = a
	c:int = a + b
	d:int = a + b + c
	e:int = a + b + c + d
	f:int = a + b + c + d + e
	g:int = a + b + c + d + e + f
	h:int = a + b + c + d + e + f + g
	i:int = a + b + c + d + e + f + g + h 
	j:int = a + b + c + d + e + f + g + h + i
	k:int = a + b + c + d + e + f + g + h + i + j
	l:int = a + b + c + d + e + f + g + h + i + j + k
	m:int = a + b + c + d + e + f + g + h + i + j + k + l
	n:int = a + b + c + d + e + f + g + h + i + j + k + l + m
	o:int = a + b + c + d + e + f + g + h + i + j + k + l + m + n
	println(unparseInt(o))
}