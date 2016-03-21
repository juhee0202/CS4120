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
    // print({f});
    // print({foo({1,2,3})})
    // print({c[0]})
    // g: int = c[0]
    // j: int = '3'
    // h: int[3][j];
    t1: int[] = {'a'}+{'b'};
    test: int[2][]
    test = {1}
    print(t1)

}


foo(bar:int[]):int {
    if (!true) {
        return 97;
    } else {
        return 98;
    }
    return -------5;
}