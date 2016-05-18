use io
fact(x:int) : int {
  n:int = 1
  i:int = 2
  while(i <= x) {
    n = n * i
    i = i + 1 
  }
  return n
}
main(args:int[][])  {
println("This program will print x")
println({fact(5)})
}
