FUNC(Imain_paai):
	enter	$0 $0
	movq	%rdi %args
	movq	$3 %x
	addq	$5 %x
	movq	%x %temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp0 %rdi
	callq	FUNC(If_ii)
	movq	%rax %temp1
	movq	$48(%rsp) %rax
	movq	$40(%rsp) %rcx
	movq	$32(%rsp) %rdx
	movq	$24(%rsp) %r8
	movq	$16(%rsp) %r9
	movq	$8(%rsp) %r10
	movq	$0(%rsp) %r11
	addq	$56 %rsp
	movq	%temp1 %z
	leave
	ret
FUNC(If_ii):
	enter	$0 $0
	movq	%rdi %x
	addq	$1 %x
	movq	%x %y
	movq	%y %_RET0
	leave
	ret
