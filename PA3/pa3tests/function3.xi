// tests recursion

f(x:int, y:int): int {
  return f(x+y,y+x);
}