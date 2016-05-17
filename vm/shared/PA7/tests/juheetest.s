FUNC(Imain_paai):
	enter	$0 $0
	movq	%rdi %args
	movq	$3 %x
	cmpq	%x $5
	cmovge	$1 %tileRegister0
	cmovl	$0 %tileRegister0
	movq	%tileRegister0 %y
	leave
	ret
