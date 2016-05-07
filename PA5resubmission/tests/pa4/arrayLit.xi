// tests array literal containing a binary expression

use io

main(args:int[][]) {
    x:int = 45;
    y:int = 52;

    array:int[] = {x+y}

    print(array + "\n");
}