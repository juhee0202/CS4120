use io

main(args:int[][]) {
    x: int[] = {1,2,3,4,5,6};
    if (x[5] > x[2] | 5/0 > 3) {
        print("Yes baybeeee");
    }
    if (x[5] < x[2] & 5/0 > 3) {
        print("No baybeeeeeee");
    }
}