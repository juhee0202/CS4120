use io
use conv

main(args: int[][]) {
a: int[] = {1, 2, 3}
x: int[f(a, 0)][f(a, 0)][f(a, 0)][][]
println(unparseInt(length(x)))
  println(unparseInt(length(x[0])))
  println(unparseInt(length(x[0][0])))
 println(unparseInt(a[0]))
 println(unparseInt(a[1]))
 println(unparseInt(a[2]))
 }

 f(a: int[], i: int): int {
 print("Index: ")
 println(unparseInt(i))
 a[i] = a[i] + 1
 return a[i]
 }

// use io
//  use conv

//  main(args:int[][]) {
//  println({{"hello"}, {"world"}}[1][0])
//  _ = id(id(id(1)))

//  foo()

//  aa:int[][] = {{1,2,3},{4,5},{6}}
//  ff(aa)[1] = aa[2][0]
//  println(unparseInt(aa[0][1]))
//  println(unparseInt(length(aa[2])))

//  aa[0][2] = gg(aa)
//  println(unparseInt(aa[0][2]))
//  }

//  ff(a:int[][]): int[] {
//  a[2] = {47,48,49,50}
//  return a[0]
//  }

//  gg(a:int[][]): int {
//  a[0] = {0}
//  return 59
//  }

//  foo() {
//  a:int[] = {1,2,3}
//  a[f(a)[1]] = a[g(a)]
//  println(unparseInt(a[0]))
//  println(unparseInt(a[1]))
//  println(unparseInt(a[2]))
//  }

//  f(a:int[]):int[] {
//  println("hello");
//  return a;
//  }

//  g(a:int[]):int {
//  println("world");
//  a[1] = 6
//  return 1;
//  }

//  id(x: int): int {
//  return x;
//  }
