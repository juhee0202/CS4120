use io
use conv

main(args: int[][]) {
  x:int = 0;
  {
    if (x < 3) {
      x = 1
      return
    } else {
      x = 2
      {
        return
      }
    }
  }
  x = 2
}

