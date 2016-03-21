use io

main(args:int[][]) {
    a: int = 4-4-4-4-4-4-4-4--4
    b: int = 4-(-(-4))
    // c: int[3] = {1,2,3}      we shoudln't allow this.. fix mixed brackets
    c: int[] = {97}
    d: int[][] = {{1},{2}}
    i: int[] = "hello";
    // e: int = c[i];           //fixed, should not typecheck cuz i is not declared
    f: int = {97,2,3}[0]
    print({f});
    print({foo()[0]})
    print({c[0]})
    // g: int = c[0]
    // j: int = '3'
    // h: int[3][j];
}


foo():int[] {
    return {98,2,3}
}