// tests array literal and function call

use io

main(args:int[][]) {
    array:int[] = createArray(90,7);

    print(array + "\n");
}

createArray(x:int,y:int):int[] {
	array:int[] = {x+y};
	return array;
}