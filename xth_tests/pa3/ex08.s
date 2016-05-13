	.text
	.globl	_Ifoo_b
	.align	4
_Ifoo_b:
	enter	$32, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$2, %rcx
	movq	%rcx, -8(%rbp)
	movq	$3, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rcx
	movq	-8(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	cmovne	$1, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	cmove	$0, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
