// Tests assignment to function call

ass(){
	foo()[1] = 5
}

foo():int[] {
	return {1,2,3}
}