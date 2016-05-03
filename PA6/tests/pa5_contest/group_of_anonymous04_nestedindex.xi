use io
use conv

main(args:int[][]) {
  a:int[] = {0, 1, 2, 3, 1};
  b:int[] = {0, 1};
  c:int[][] = {{4}};
  d:int[] = {0,100};
  println(unparseInt(d[b[a[c[a[0]][b[0]]]]]));
  a[1] = d[1] + 50;
  println(unparseInt(a[1]));
}
