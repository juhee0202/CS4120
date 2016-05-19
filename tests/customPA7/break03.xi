use io
use conv

main(args:int[][]) {
    i,j:int
    l0: while (i < 3) {
    	println("a")
    	while (j < 3) {
    		println("b")
    		if (j == 0) {
    			break _to_ l0
    		}
    	}
    }
}