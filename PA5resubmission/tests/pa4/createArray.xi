main(args:int[][]){
    exprs: int[] = {1,1}
    index: int = 0
    base: int = createArray(exprs,index)
}

createArray(exprs: int[], index: int): int {
    if (index == length(exprs)) {
        return 0;
    }
    addr: int = malloc(exprs[index])
    i: int = 0
    array: int[] = {0}
    while (i < exprs[index]) {
        array[i] = createArray(exprs,index+1)
        i = i+1
    }
    return addr
}

malloc(x: int): int {
    return x
}