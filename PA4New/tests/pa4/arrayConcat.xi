main(args:int[][]){
    x: int[] = {1}
    y: int[] = {2}
    z: int[] = concat(x,y)
}

concat(x: int[], y: int[]): int[] {
    z: int[length(x) + length(y)];
    i: int = 0;
    while (i < length(x)) {
        z[i] = x[i];
    }
    i = 0;
    while (i < length(y)) {
        z[i + length(x)] = y[i];
    }
    return z;
}