use io
use conv

bar2(): int, int {
  return 1, 2
}

bar6(): int, int, int, int, int, int {
  println("bar6 called")
  return 10, 20, 30, 40, 50, 60
}

main(args: int[][]) {
  x:int, _ = bar2()
  println(unparseInt(x))
  _, _, z:int, _, _, w:int = bar6()
  println(unparseInt(z))
  println(unparseInt(w))
}
