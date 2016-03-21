// Tests assignment to function call

ass(){
	x:bool
	x = foo()
}

foo():int {
	return 5
}