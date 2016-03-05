//testing function call and array element

f2(a: int, b:int): int {
	return -(a+b*4)
}

f3(a: int, b:int): int[] {
	arr:int[] = {a,b} 
	return {1,2,3,}
}

f4():int[] {
	return {1,2,3}
}

f5():int, int {
	return 1,2
}

main() {
	c:int = 4;
	result:int = f2(c,4)

	wat:int[] = f3(c,c)

	f4()[2] = 3
	_ = f4()

	_,a:int = f5()

	b:int,_ = f5()

	num:int = (5)

}