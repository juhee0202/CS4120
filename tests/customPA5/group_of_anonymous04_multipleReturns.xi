use io
use conv

returnNext5Sequential(a:int):int, int, int, int, int {
    return a+1, a+2, a+3, a+4, a+5
}



main(args:int[][]) {
	a:int = 1
	b:int, _, c:int, _, d:int = returnNext5Sequential(a);
	println("Next 3 even num " + unparseInt(b) + unparseInt(c) + unparseInt(d));
}
