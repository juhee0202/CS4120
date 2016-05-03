// test various expressions with bools.
// will get array index out of bounds if generated code is incorrect
f(a:int[]) : bool { a[1] = 9; return true }
main(args: int[][]) {
  x : bool[][][] = {{}}
  x[0] = {{}} + {{false}, {}}
  x = {{{true}}}; y: int[] = {}
  x[0][0][0] = (1 > 0) | (y[5] > 3)
  z : int = 0
  if (1 > 0 | y[4] > 5) { z = z + 1 } // +1
  if (1 < 0 | 1 > 0) { z = z + 1 } // +1
  if (1 < 0 & y[3] > 5) { z = z + 1 } // no effect
  if (1 > 0 & 1 < 0) { z = z + 1 } // no effect
  if (1 > 0 & 2 > 0) { z = z + 1 } // +1
  a : int[4]; a[1] = 7
  if (x[0][0][0] & z == 3 & f(a)) {
    if (a[1] != 9) { z = y[2] }
  } else {
    z = y[90]
  }
}
