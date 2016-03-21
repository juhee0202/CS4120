// tests function scope

f1(x:int, y:int): int {
  _, x:int, _ = f2();
  return x;
}

f2(): int, int, int {
  return 1,2,3;
}

