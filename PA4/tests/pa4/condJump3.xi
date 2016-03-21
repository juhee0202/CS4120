// tests the conditional translation logic.
// print in f2 should not be printed

use io

main(args:int[][]) {
    while(f1() | f2()) {
    	print("Hello\n")
    	return;
    }
}

f1(): bool {
    return true;
}

f2(): bool {
    print("Shouldn't print this")
    return true;
}