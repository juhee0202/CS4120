main(args: int[][]) {
  foo(5)
}

foo(n: int) {
    if (n == 0) {
        return;
    }
    foo(n-1)
}