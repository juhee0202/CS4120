	.text
	.global	FUNC(Imain_paai)
	.align	4
FUNC(Imain_paai):
	enter	$88 $0
	movq	%rdi %args
	movq	$1 %temp0
	movq	$2 %temp1
	movq	$3 %temp2
	movq	$4 %temp3
	movq	$5 %temp4
	movq	$6 %temp5
	movq	$7 %temp6
	movq	$8 %temp7
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%temp7
	pushq	%temp6
	movq	%temp5 %r9
	movq	%temp4 %r8
	movq	%temp3 %rcx
	movq	%temp2 %rdx
	movq	%temp1 %rsi
	movq	%temp0 %rdi
	subq	$8 %rsp
	callq	FUNC(If_t2iiiiiiiiii)
	movq	%rax %x1
	movq	%rdx %x2
	movq	64(%rsp) %rax
	movq	56(%rsp) %rcx
	movq	48(%rsp) %rdx
	movq	40(%rsp) %r8
	movq	32(%rsp) %r9
	movq	24(%rsp) %r10
	movq	16(%rsp) %r11
	addq	$80 %rsp
	leave
	ret

	.global	FUNC(If_t2iiiiiiiiii)
	.align	4
FUNC(If_t2iiiiiiiiii):
	enter	$64 $0
	movq	%rdi %a1
	movq	%rsi %a2
	movq	%rdx %a3
	movq	%rcx %a4
	movq	%r8 %a5
	movq	%r9 %a6
	movq	16(%rbp) %a7
	movq	24(%rbp) %a8
	movq	%a1 %rax
	movq	%a2 %rdx
	leave
	ret
