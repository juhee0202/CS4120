// tests the conditional translation logic.
// print in f2 should be printed

use io

main(args:int[][]) {
    f1()
    if (f1() & f2()) {
        print("Hello\n")
    }
}

f1(): bool {
    return false;
}

f2(): bool {
    print("Shouldn't print this")
    return true;
}