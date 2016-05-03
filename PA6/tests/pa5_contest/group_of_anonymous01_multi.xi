use io use conv
main(args:int[][]) {
    arr:int[] = {0} 
    i:int = 0 
    foo()
    a:int=1 b:int=2 c:int=3 d:int=4 e:int=5 f:int=6 g:int=7 h:int=8
    a, b, c = ret() i = arr[a-1] + arr[b-2] + arr[c-3]
    arg(arr, 1, 2, 3, 4, 5, 6, 7)
    a, b, c, d, e, f, g, h = both(arr, a, b, c, d, e, f, g, h)
    i=arr[a-1]+arr[b-2]+arr[c-3]+arr[d-4]+arr[e-5]+arr[f-6]+arr[g-7]+arr[h-8]
    println(unparseInt(i)) } // makes sure i is used

foo() {} ret(): int, int, int { return 1, 2, 3 }

arg(arr:int[], a:int, b:int, c:int, d:int, e:int, f:int, g:int) {
    a = arr[a-1] + arr[b-2] + arr[c-3] }
    
both(arr:int[], a:int, b:int, c:int, d:int, e:int, f:int, g:int, h:int)
: int, int, int, int, int, int, int, int {
    a=arr[a-1]+arr[b-2]+arr[c-3]+arr[d-4]+arr[e-5]+arr[f-6]+arr[g-7]+arr[h-8]+1
    return a, b, c, d, e, f, g, h }
