use io

main(args:int[][]) {
    f1()
    if (f1() | f2()) {
        print("Hello\n")
    }
}

f1(): bool {
    return true;
}

f2(): bool {
    print("Shouldn't print this")
    return true;
}