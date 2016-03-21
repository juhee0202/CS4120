//tests unary expressions

f1(a: int, b:int): int {
	return -a
}

f2(a: int, b:int): int {
	return -(a+b*4)
}

f3(a: int, b:int): bool {
	c:bool = !true
	return c
}

f4(a: int, b:int): bool {
	c:bool = !false
	return c
}

f5(a: int, b:int): bool {
	d:bool = true
	c:bool = !d
	return c
}
	
f6(a: int, b:int): int {
	a = -9223372036854775808
	return -a;
}