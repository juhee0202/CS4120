use io
use conv

x: int[5][5];

main(args: int[][]) {
    x[2][3] = 5;
    println(unparseInt(x[2][3]));
}