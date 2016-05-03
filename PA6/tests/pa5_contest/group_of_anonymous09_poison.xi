use io

poison() : int[] { return "poison  " }

main(args:int[][]) {
   x:int[] = poison()
   y:int[] = poison()
   y[0] = 'a'
   y[1] = 'n'
   y[2] = 't'
   y[3] = 'i'
   y[4] = 'd'
   y[5] = 'o'
   y[6] = 't'
   y[7] = 'e'
   println("To cure " + x + "you need an " + y)
}
