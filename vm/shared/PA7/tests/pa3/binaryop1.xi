//tests +,- binary operation

sum(a: bool[][], b:bool[][]): bool[][] {
	a = {{true, false}, {true, false}, {true, false}}
	b = {{true}}
	return a+b
}

sum2(a: int, b:int): int {
	return a+b
}

subtract(a: int, b:int): int {
	return a-b
}

subtract2(a: int[], b:int[]): int[] {
	return a-b
}

