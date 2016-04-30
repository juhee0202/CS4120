	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$80, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %rcx
	movq	%rcx, -64(%rbp)
	movq	$5, %rcx
	movq	%rcx, -72(%rbp)
	movq	$1, %rcx
	movq	%rcx, -80(%rbp)
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
