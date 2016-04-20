	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$16, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rcx
	movq	-16(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jnz	l0
l1:
	leave
	ret
l0:
	leave
	ret
label0:
l2:
	leave
	ret
