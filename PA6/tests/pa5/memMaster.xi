use io

main(args: int[][]) {
  x:int = 5
  y:int = 3
  array:int[][] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"}
  // println(array[x])				    // f
  // println(array[x + 1])			  // g
  // println(array[1 + x])			  // g
  // println(array[x + y])			  // i
  // println(array[x + y*2])		  // l
  // println(array[x + 2*y])		  // l
  // println(array[y*2 + x])		  // l
  // println(array[2*y + x])		  // l
  // println(array[x + y*2 + 1])	// m
  // println(array[x + 2*y + 1])	// m
  // println(array[y*2 + x + 1])	// m
  // println(array[2*y + x + 1])	// m
  // println(array[x + 1 + y*2])	// m
  // println(array[x + 1 + 2*y])	// m
  // println(array[y*2 + 1 + x])	// m
  // println(array[2*y + 1 + x])	// m
  // println(array[1 + x + y*2])	// m
  // println(array[1 + x + 2*y])	// m
  // println(array[1 + y*2 + x])	// m
  println(array[1 + 2*y + x])	// m
}