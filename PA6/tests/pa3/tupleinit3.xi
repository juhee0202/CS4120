// tests tuplieinit multiple assignment

f1(): int, int, int {
  _, x:int, _ = f2(1);
  return x,x,x;
}

f2(x: int): int,int,int {
  a:int, b:int, c:int = f1();
  return f2(a);
}