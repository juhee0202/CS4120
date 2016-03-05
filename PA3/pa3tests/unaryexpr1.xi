// tests integer literal out of bound error.

f() {
  x:int = -9223372036854775808;
  y:int = ---9223372036854775808;
  z:int = 9223372036854775807;
  w:int = --(9223372036854775808 - 5);
}