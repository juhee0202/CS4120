main(args: int[][]) {
  x:int = 5
  y1:int, y2:int = f1(x)
  z1:int, z2:int, z3:int, z4:int = f2(y1,y2)
}

f1(x:int):int,int {
  if (x < 3) {
    return 3,1;
  }
  return 5,6
}

f2(x:int, y:int):int,int,int,int {
  return x,y,x+y,x+y+3
}