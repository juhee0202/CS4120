	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$48, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	$5, %rcx
	movq	%rcx, -64(%rbp)
	movq	$4, %rcx
	movq	%rcx, -72(%rbp)
	movq	-64(%rbp), %rcx
	cmpq	$5, %rcx
	movq	%rcx, -64(%rbp)
	jg	l0
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	addq	$5, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
