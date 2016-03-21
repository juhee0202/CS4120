f() {
  x:int = 3;
  {
    y:int;
    {
      y = x;
      z:int = 5;
    }
    y = z;
  }
}