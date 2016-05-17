// tests the while loop
// should print a

use io

main(args:int[][]) {
    x:int = 0;
    while(x < 97) {
        x = x + 1;
    }
    array:int[] = {x};
    print(array + "\n");
}
