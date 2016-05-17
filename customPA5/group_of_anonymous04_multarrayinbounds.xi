use io;
use conv;

main(args:int[][]) {  
  a:int[][][] = {{{11,22,33},{44,55,66}}};
  b:int = a[0][0][0];
  println(unparseInt(b));
}
