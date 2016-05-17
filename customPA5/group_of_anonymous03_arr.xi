use io
use conv

main(args: int[][]) {
  s:int[3][]
  s[0] = {1, 2, 3}
  s[1] = {4, 5, 6}
  s[2] = {7, 8, 9}
  println(unparseInt(s[1][2]))
  println(unparseInt(s[0][1]))

  strs:int[][] = {"foo", "bar", "quux"}
  println(strs[0])
  println(strs[1])
  println(strs[2])
}
