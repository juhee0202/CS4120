	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$32, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	$5, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	jle	l0
l1:
	leave
	ret
l0:
	movq	-16(%rbp), %rcx
	movq	$3, %rcx
	movq	%rcx, -16(%rbp)
	jmp	l1
