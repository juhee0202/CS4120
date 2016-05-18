use io use conv
// I'm kidding, this is the slowest square root
// if s is not a perfect square, gives floor
sqrt(s:int) : int {
  i:int = 1 c:int = 0
  while(true) {
    if(i * i > s) {
      return c
    }
    i = i + 1 c = c + 1
  }
  return -1
}
main(args:int[][]) {
  print("N: ") println("625*625 + 3");
  what:int = --625*625--3;
  print("The floor of the square root of " + unparseInt(what))
  println(" is " + unparseInt(sqrt(what)))
}
