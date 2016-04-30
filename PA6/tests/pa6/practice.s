	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$64, $0
	movq	%rdi, -8(%rbp)
	movq	%rsi, -16(%rbp)
	movq	%rbx, -24(%rbp)
	movq	%r12, -32(%rbp)
	movq	%r13, -40(%rbp)
	movq	%r14, -48(%rbp)
	movq	%r15, -56(%rbp)
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$5, %rcx
	movq	%rcx, -72(%rbp)
	movq	$4, %rcx
	movq	%rcx, -80(%rbp)
	movq	-72(%rbp), %rcx
	cmpq	$5, %rcx
	movq	%rcx, -72(%rbp)
	jg	l0
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$5, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
