use io
use conv

main(args: int[][]) {
a: int[5][4]
{
i: int = 0
while (i < length(a)) {
j: int = 0
while (j < length(a[i])) {
a[i][j] = i + j
j = j + 1
}
i = i + 1
}
}
{
i: int = 0
while (i < length(a)) {
j: int = 0
while (j < length(a[i])) {
println(unparseInt(a[i][j]))
j = j + 1
}
i = i + 1
}
}
}
