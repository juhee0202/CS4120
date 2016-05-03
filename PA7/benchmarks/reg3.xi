use io
use conv
// Testing ability to color with spilling
main(args:int[][]) {
a:int[] = "hello world!"
b:int[]="hi";c:int[]="hi";d:int[]="hi";e:int[]="hi";f:int[]="hi";g:int[]="hi"
b[0] = a[0]
c[0] = a[1]
d[0] = a[2]
e[0] = a[3]
f[0] = a[4]
g[0] = a[5]
b[1] = a[6]
c[1] = a[7]
d[1] = a[8]
e[1] = a[9]
f[1] = a[10]
g[1] = a[11]
z:int[] = a+"\n"+b+"\n"+c+"\n"+d+"\n"+e+"\n"+f+"\n"+g
println(unparseInt(z))
}