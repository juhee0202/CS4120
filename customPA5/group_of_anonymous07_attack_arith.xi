// various tricky arithmetic ops
main(args: int[][]) {
  // the core of this test will be to get others' generated code to
  // incorrectly index into [a] if they don't perform the operations
  // correctly, triggering an out-of-bounds error.
  a : int[] = {0}
  x : int = 9223372036854775807
  y : int = 32523984792 
  z : int = 7034985438 
  a[ a[(-7/2)+3] // correct sign extending
    +a[-17%4+1] // same as above
    +a[32523984792 *>> 7034985438 - 12] // regular himult
    +a[y *>> z - 12] // again but with vars
    +a[(9223372036854775807+2)+(9223372036854775807+3)-3] // adding large nums
    +a[(x+2)+(x+3)-3] // again but with vars
    +a[length(a)-1] // correct length
    +a[length("''" + "'" + "\n\b ") - 6] // more correct length
    +a[-3+2*3/2] // associativity (2*(3/2) <> (2*3)/2)
    // add more a[expression that should evaluate to 0] here!
  ] = 17 }
