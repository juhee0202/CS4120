// Tests assignment to function call

ass(){
	foo()[1] = true
}

foo():int[] {
	return {1,2,3}
}