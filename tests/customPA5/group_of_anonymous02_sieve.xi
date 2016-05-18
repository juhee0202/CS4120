asser(c:bool) { if (!c) { x:int = 1/0; } }
main(args:int[][]) {
  N:int = 10000000
  a:bool[N]
  i:int = N
  while (i > 2) { i = i - 1 a[i] = true }

  while (i < N) {
    if (a[i]) {
      start:int = i * i j:int = 0 t:int = start + j * i
      while (t < N) { a[t] = false j = j + 1 t = start + j * i }
    }
    i = i + 1
  }
  count:int = 0
  i = 2
  while (i < N) { if (a[i]) { count = count + 1 } i  = i + 1 }
  asser(count == 664579)
}
// Sieve of Eratosthenes: Counts # primes up to 10,000,000
// Counts
//main(args:int[][]) {
//  N:int = 10000000
//  a:bool[N]
//  i:int = 2
//  while (i < N) {
//    a[i] = true
//
//    i = i + 1
//  }
//
//  i = 2
//  while (i < N) {
//    if (a[i]) {
//      start:int = i * i
//      j:int = 0
//      t:int = start + j * i
//      while (t < N) {
//        a[t] = false
//
//        j = j + 1
//        t = start + j * i
//      }
//    }
//
//    i = i + 1
//  }
//      
//  count:int = 0
//  i = 2
//  while (i < N) {
//    if (a[i]) {
//      count = count + 1
//    }
//
//    i  = i + 1
//  }
//
//  asser(count == 664579)
//}
