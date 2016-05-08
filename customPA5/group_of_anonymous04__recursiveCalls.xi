use io
use conv

printHelloWorld45Times(a:int) {
if (a < 45) {
println("Hello World" + unparseInt(a))
printHelloWorld45Times(a+1);
}
return
}



main(args:int[][]) {
a: int = 0;
printHelloWorld45Times(a)
}
