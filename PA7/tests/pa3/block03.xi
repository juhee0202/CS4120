//tests unreachable statement elimination

foo() {
	foo()
	{
		foo()
		return
	}
	foo()
}