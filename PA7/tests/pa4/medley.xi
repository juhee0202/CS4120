use io
use conv

main(args:int[][]) {
    aa:int[][] = {{1,2,3},{4,5},{6}}
    ff(aa)[1] = aa[2][0]
     }

ff(a:int[][]): int[] {
    a[2] = {47,48,49,50}
    return a[0]
}