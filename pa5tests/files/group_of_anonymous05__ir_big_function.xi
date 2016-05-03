use io
use conv
big_function(x:int, y:int, z:int, a:int, b:int, c:int, d:int, e:int,
f:int, g:int):
int, int, int, int, int, int, int, int, int, int, int[] {
if (c > 5) {
nx:int, ny:int, nz:int, na:int, nb:int, nc:int, nd:int, ne:int,
nf:int, ng:int, arr:int[] =
big_function(x, y, z, a, b, c - 1, d, e, f, g)
arr[0] = arr[0] + 1
return nx, ny, nz, na, nb, nc, nd, ne, nf, ng, arr
} else {return 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, {0}}
}
main(args:int[][]) {
a:int, b:int, c:int, d:int, e:int, f:int, g:int, h:int, i:int,
j:int, res:int[] =
big_function(100, 200, 300, 400, 500, 10, 700, 800, 900, 1000)
println(unparseInt(res[0]))
}
