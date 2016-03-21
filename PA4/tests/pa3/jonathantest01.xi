// Throws error if trying to access dimensions that don't exist.
foo() {
    a: int[][] = {{2,2},{2,2}};
    a[1][1][1] = 3;
}